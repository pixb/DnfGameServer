.PHONY: build build-linux build-windows build-mac clean test run migrate version dev dev-sqlite help

# Variables
BINARY_NAME=dnf-server
VERSION=$(shell git describe --tags --always --dirty 2>/dev/null || echo "dev")
COMMIT=$(shell git rev-parse --short HEAD 2>/dev/null || echo "unknown")
BUILD_TIME=$(shell date -u '+%Y-%m-%d_%H:%M:%S')
LDFLAGS=-ldflags "-X github.com/pixb/DnfGameServer/dnf-go-server/cmd/server/cmd.Version=${VERSION} \
                  -X github.com/pixb/DnfGameServer/dnf-go-server/cmd/server/cmd.Commit=${COMMIT} \
                  -X github.com/pixb/DnfGameServer/dnf-go-server/cmd/server/cmd.BuildTime=${BUILD_TIME}"

# Default target
.DEFAULT_GOAL := help

help: ## Show this help message
	@echo "DNF Go Server - Available Commands:"
	@echo ""
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "  \033[36m%-20s\033[0m %s\n", $$1, $$2}'

# =============================================================================
# Build Commands
# =============================================================================

build: ## Build the server binary
	@echo "Building DNF Server..."
	@mkdir -p bin
	go build ${LDFLAGS} -o bin/${BINARY_NAME} ./cmd/server
	@echo "Build complete: bin/${BINARY_NAME}"

build-nocgo: ## Build without CGO (SQLite disabled)
	@echo "Building DNF Server without CGO..."
	@mkdir -p bin
	CGO_ENABLED=0 go build ${LDFLAGS} -o bin/${BINARY_NAME} ./cmd/server
	@echo "Build complete: bin/${BINARY_NAME} (MySQL only)"

build-linux: ## Build for Linux (amd64)
	@echo "Building for Linux amd64..."
	@mkdir -p bin
	GOOS=linux GOARCH=amd64 go build ${LDFLAGS} -o bin/${BINARY_NAME}-linux-amd64 ./cmd/server
	@echo "Build complete: bin/${BINARY_NAME}-linux-amd64"

build-windows: ## Build for Windows (amd64)
	@echo "Building for Windows amd64..."
	@mkdir -p bin
	GOOS=windows GOARCH=amd64 go build ${LDFLAGS} -o bin/${BINARY_NAME}-windows-amd64.exe ./cmd/server
	@echo "Build complete: bin/${BINARY_NAME}-windows-amd64.exe"

build-mac: ## Build for macOS (amd64)
	@echo "Building for macOS amd64..."
	@mkdir -p bin
	GOOS=darwin GOARCH=amd64 go build ${LDFLAGS} -o bin/${BINARY_NAME}-darwin-amd64 ./cmd/server
	@echo "Build complete: bin/${BINARY_NAME}-darwin-amd64"

build-all: build build-linux build-windows build-mac ## Build for all platforms

# =============================================================================
# Development Commands (SQLite - 推荐用于开发和测试)
# =============================================================================

dev: ## Run server in development mode with SQLite (recommended)
	@echo "Starting server in dev mode with SQLite..."
	@echo "Database: ./data/dnf_game.db"
	@mkdir -p data
	go run ./cmd/server serve --config=configs/config.dev.yaml

dev-sqlite: dev ## Alias for dev

dev-migrate: ## Run migrations with SQLite
	@echo "Running migrations with SQLite..."
	@mkdir -p data
	go run ./cmd/server migrate --config=configs/config.dev.yaml

dev-reset: ## Reset SQLite database (delete and recreate)
	@echo "Resetting SQLite database..."
	@rm -f data/dnf_game.db data/dnf_game.db-shm data/dnf_game.db-wal
	@mkdir -p data
	go run ./cmd/server migrate --config=configs/config.dev.yaml
	@echo "Database reset complete!"

dev-test: ## Run tests with SQLite
	@echo "Running tests with SQLite..."
	@mkdir -p data
	go test -v ./... -tags sqlite

# =============================================================================
# Production Commands (MySQL)
# =============================================================================

run: build ## Build and run the server with MySQL
	@echo "Starting server with MySQL..."
	./bin/${BINARY_NAME} serve

migrate: build ## Run database migrations with MySQL
	@echo "Running migrations with MySQL..."
	./bin/${BINARY_NAME} migrate --config=configs/config.yaml

migrate-status: build ## Check migration status
	./bin/${BINARY_NAME} migrate status

migrate-from-java: build ## Migrate from Java DnfGameServer
	./bin/${BINARY_NAME} migrate from-java

# =============================================================================
# Test Commands
# =============================================================================

test: ## Run all tests
	@echo "Running tests..."
	go test -v ./...

test-short: ## Run short tests only
	@echo "Running short tests..."
	go test -short -v ./...

test-race: ## Run tests with race detector
	@echo "Running tests with race detector..."
	go test -race -v ./...

# =============================================================================
# Maintenance Commands
# =============================================================================

clean: ## Clean build artifacts and SQLite databases
	@echo "Cleaning..."
	@rm -rf bin/
	@rm -rf data/
	@go clean
	@echo "Clean complete"

clean-sqlite: ## Clean only SQLite databases
	@echo "Cleaning SQLite databases..."
	@rm -f data/*.db data/*.db-shm data/*.db-wal
	@echo "SQLite databases cleaned"

fmt: ## Format Go code
	@echo "Formatting code..."
	go fmt ./...

vet: ## Run go vet
	@echo "Running go vet..."
	go vet ./...

lint: ## Run linter (requires golangci-lint)
	@echo "Running linter..."
	@which golangci-lint > /dev/null 2>&1 || (echo "golangci-lint not installed. Install with: go install github.com/golangci/golangci-lint/cmd/golangci-lint@latest" && exit 1)
	golangci-lint run

check: fmt vet test ## Run all checks (format, vet, test)
	@echo "All checks passed!"

# =============================================================================
# Dependencies
# =============================================================================

deps: ## Download and verify dependencies
	@echo "Downloading dependencies..."
	go mod download
	go mod verify

deps-update: ## Update all dependencies
	@echo "Updating dependencies..."
	go get -u ./...
	go mod tidy

# =============================================================================
# Docker Commands
# =============================================================================

docker-build: ## Build Docker image
	@echo "Building Docker image..."
	docker build -t dnf-go-server:${VERSION} .

docker-run: ## Run Docker container
	@echo "Running Docker container..."
	docker-compose up -d

docker-stop: ## Stop Docker container
	@echo "Stopping Docker container..."
	docker-compose down

# =============================================================================
# Protobuf Commands
# =============================================================================

proto: ## Generate protobuf code
	@echo "Generating protobuf code..."
	cd proto && buf generate

proto-lint: ## Lint protobuf files
	@echo "Linting protobuf files..."
	cd proto && buf lint

# =============================================================================
# Version and Info
# =============================================================================

version: build ## Show version information
	./bin/${BINARY_NAME} version

info: ## Show project info
	@echo "DNF Go Server"
	@echo "============="
	@echo "Version:    ${VERSION}"
	@echo "Commit:     ${COMMIT}"
	@echo "Build Time: ${BUILD_TIME}"
	@echo ""
	@echo "Development:"
	@echo "  make dev          - Run with SQLite (recommended for dev)"
	@echo "  make dev-reset    - Reset SQLite database"
	@echo ""
	@echo "Production:"
	@echo "  make run          - Run with MySQL"
	@echo "  make migrate      - Run MySQL migrations"

# =============================================================================
# Documentation
# =============================================================================

docs: ## Build documentation
	@echo "Documentation is in docs/ directory"

docs-serve: ## Serve documentation locally
	@echo "Serving documentation at http://localhost:8080"
	@cd docs && python3 -m http.server 8080 2>/dev/null || python -m SimpleHTTPServer 8080 2>/dev/null || echo "Please install Python to serve docs"
