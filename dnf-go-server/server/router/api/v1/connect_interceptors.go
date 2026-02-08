package v1

import (
	"context"
	"fmt"
	"log/slog"
	"runtime/debug"

	"connectrpc.com/connect"
	pkgerrors "github.com/pkg/errors"
)

// LoggingInterceptor logs Connect RPC requests with appropriate log levels.
type LoggingInterceptor struct {
	logStacktrace bool
}

func NewLoggingInterceptor(logStacktrace bool) *LoggingInterceptor {
	return &LoggingInterceptor{logStacktrace: logStacktrace}
}

func (in *LoggingInterceptor) WrapUnary(next connect.UnaryFunc) connect.UnaryFunc {
	return func(ctx context.Context, req connect.AnyRequest) (connect.AnyResponse, error) {
		resp, err := next(ctx, req)
		in.log(req.Spec().Procedure, err)
		return resp, err
	}
}

func (in *LoggingInterceptor) log(procedure string, err error) {
	level, msg := in.classifyError(err)
	attrs := []slog.Attr{slog.String("method", procedure)}
	if err != nil {
		attrs = append(attrs, slog.String("error", err.Error()))
		if in.logStacktrace {
			attrs = append(attrs, slog.String("stacktrace", fmt.Sprintf("%+v", err)))
		}
	}
	slog.LogAttrs(context.Background(), level, msg, attrs...)
}

func (in *LoggingInterceptor) classifyError(err error) (slog.Level, string) {
	if err == nil {
		return slog.LevelInfo, "OK"
	}

	var connectErr *connect.Error
	if !pkgerrors.As(err, &connectErr) {
		return slog.LevelError, "unknown error"
	}

	// Client errors (expected, log at INFO)
	switch connectErr.Code() {
	case connect.CodeCanceled,
		connect.CodeInvalidArgument,
		connect.CodeNotFound,
		connect.CodeAlreadyExists,
		connect.CodePermissionDenied,
		connect.CodeUnauthenticated,
		connect.CodeResourceExhausted,
		connect.CodeFailedPrecondition,
		connect.CodeAborted,
		connect.CodeOutOfRange:
		return slog.LevelInfo, "client error"
	default:
		return slog.LevelError, "server error"
	}
}

func (in *LoggingInterceptor) WrapStreamingClient(next connect.StreamingClientFunc) connect.StreamingClientFunc {
	return next
}

func (in *LoggingInterceptor) WrapStreamingHandler(next connect.StreamingHandlerFunc) connect.StreamingHandlerFunc {
	return next
}

// RecoveryInterceptor recovers from panics in Connect handlers.
type RecoveryInterceptor struct {
	logStacktrace bool
}

func NewRecoveryInterceptor(logStacktrace bool) *RecoveryInterceptor {
	return &RecoveryInterceptor{logStacktrace: logStacktrace}
}

func (in *RecoveryInterceptor) WrapUnary(next connect.UnaryFunc) connect.UnaryFunc {
	return func(ctx context.Context, req connect.AnyRequest) (resp connect.AnyResponse, err error) {
		defer func() {
			if r := recover(); r != nil {
				in.logPanic(req.Spec().Procedure, r)
				err = connect.NewError(connect.CodeInternal, pkgerrors.New("internal server error"))
			}
		}()
		return next(ctx, req)
	}
}

func (in *RecoveryInterceptor) logPanic(procedure string, panicValue any) {
	attrs := []slog.Attr{
		slog.String("method", procedure),
		slog.Any("panic", panicValue),
	}
	if in.logStacktrace {
		attrs = append(attrs, slog.String("stacktrace", string(debug.Stack())))
	}
	slog.LogAttrs(context.Background(), slog.LevelError, "panic recovered in Connect handler", attrs...)
}

func (in *RecoveryInterceptor) WrapStreamingClient(next connect.StreamingClientFunc) connect.StreamingClientFunc {
	return next
}

func (in *RecoveryInterceptor) WrapStreamingHandler(next connect.StreamingHandlerFunc) connect.StreamingHandlerFunc {
	return func(ctx context.Context, req connect.StreamingHandlerConn) error {
		defer func() {
			if r := recover(); r != nil {
				in.logPanic(req.Spec().Procedure, r)
			}
		}()
		return next(ctx, req)
	}
}

// AuthInterceptor handles authentication for Connect handlers.
type AuthInterceptor struct {
	authenticator Authenticator
}

// Authenticator interface for authentication
type Authenticator interface {
	Authenticate(ctx context.Context, authHeader string) *AuthResult
}

// AuthResult represents the result of authentication
type AuthResult struct {
	User        interface{}
	Claims      interface{}
	AccessToken string
}

func NewAuthInterceptor(auth Authenticator) *AuthInterceptor {
	return &AuthInterceptor{authenticator: auth}
}

func (in *AuthInterceptor) WrapUnary(next connect.UnaryFunc) connect.UnaryFunc {
	return func(ctx context.Context, req connect.AnyRequest) (connect.AnyResponse, error) {
		header := req.Header()
		authHeader := header.Get("Authorization")

		result := in.authenticator.Authenticate(ctx, authHeader)

		// Enforce authentication for non-public methods
		if result == nil && !IsPublicMethod(req.Spec().Procedure) {
			return nil, connect.NewError(connect.CodeUnauthenticated, pkgerrors.New("authentication required"))
		}

		// Set context based on auth result
		if result != nil {
			if result.Claims != nil {
				// ctx = SetUserClaimsInContext(ctx, result.Claims)
			}
			if result.User != nil {
				// ctx = SetUserInContext(ctx, result.User, result.AccessToken)
			}
		}

		return next(ctx, req)
	}
}

func (in *AuthInterceptor) WrapStreamingClient(next connect.StreamingClientFunc) connect.StreamingClientFunc {
	return next
}

func (in *AuthInterceptor) WrapStreamingHandler(next connect.StreamingHandlerFunc) connect.StreamingHandlerFunc {
	return next
}
