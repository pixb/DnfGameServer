# DNF Go Server Dockerfile

# Build stage
FROM golang:1.24-alpine AS builder

# Install build dependencies
RUN apk add --no-cache git make

# Set working directory
WORKDIR /app

# Copy go mod files
COPY go.mod go.sum ./

# Download dependencies
RUN go mod download

# Copy source code
COPY . .

# Build the binary
RUN CGO_ENABLED=0 GOOS=linux go build -ldflags="-w -s" -o bin/dnf-server ./cmd/server

# Runtime stage
FROM alpine:latest

# Install runtime dependencies
RUN apk --no-cache add ca-certificates tzdata

# Create non-root user
RUN addgroup -g 1000 -S dnf && \
    adduser -u 1000 -S dnf -G dnf

# Set working directory
WORKDIR /app

# Copy binary from builder
COPY --from=builder /app/bin/dnf-server /app/dnf-server

# Copy configs
COPY --from=builder /app/configs /app/configs

# Create data directory
RUN mkdir -p /data && chown -R dnf:dnf /data

# Switch to non-root user
USER dnf

# Expose ports
EXPOSE 8081

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8081/healthz || exit 1

# Run the server
ENTRYPOINT ["/app/dnf-server"]
CMD ["serve", "--config=/app/configs/config.docker.yaml"]
