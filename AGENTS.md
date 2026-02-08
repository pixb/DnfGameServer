# AGENTS.md - Coding Guidelines for DnfGameServer

## Project Overview
- **Language**: Java 8 (Amazon Corretto 8)
- **Framework**: Spring Boot 1.5.14 + Apache Mina 2.0.19 + Nutz ORM
- **Build Tool**: Maven (wrapper: `./mvnw`)
- **Database**: MySQL 5.7 with Druid connection pool
- **Base Package**: `com.dnfm`

## Build Commands

### Java
```bash
# Full build (skip tests)
./mvnw clean package -DskipTests

# Compile only
./mvnw compile

# Run all tests
./mvnw test

# Run single test class
./mvnw test -Dtest=DnfGameServerApplicationTests

# Run single test method
./mvnw test -Dtest=DnfGameServerApplicationTests#contextLoads
```

### Python Tests
```bash
# Run all tests
pytest

# Run single test file
pytest tests/test_database.py

# Run single test class
pytest tests/test_database.py::TestDatabaseConnection

# Run single test method
pytest tests/test_database.py::TestDatabaseConnection::test_can_connect_to_mysql -v

# Run with markers
pytest -m "not slow"
```

### Server Operations
```bash
# Build the project
./build.sh

# Start the server
./start.sh
```

### Protobuf
```bash
# Generate protobuf files (from proto/ directory)
cd proto && buf generate
```

## Code Style Guidelines

### Naming Conventions
- **Classes**: PascalCase (e.g., `PlayerService`, `GameServer`)
- **Methods/Variables**: camelCase (e.g., `getPlayerById`, `playerName`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `MAX_CONNECTIONS`, `DEFAULT_PORT`)
- **Packages**: lowercase (e.g., `com.dnfm.game.player`)
- **Enum values**: UPPER_SNAKE_CASE (e.g., `LOGIN_SUCCESS`, `ERROR_CODE`)

### Code Formatting
- **Indentation**: 3 spaces (no tabs)
- **Line length**: 120 characters max
- **Braces**: Same line opening brace
- **Empty lines**: Between logical sections

### Imports Organization
Order imports by groups with blank lines between:
1. `com.dnfm.*` packages (project internal)
2. `java.*` and `javax.*` packages
3. Third-party libraries (`org.*`, `com.*`)

```java
import com.dnfm.common.spring.SpringUtils;
import com.dnfm.game.player.model.PlayerProfile;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
```

### Annotations
- Use Lombok (`@Data`, `@Slf4j`) where appropriate
- Spring annotations: `@Service`, `@Component`, `@Autowired`, `@Resource`
- Place class annotations on separate lines; field annotations inline

```java
@Service
public class PlayerService {
    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);
    
    @Autowired
    private Dao dao;
}
```

### Error Handling
- Use `LoggerUtils.error()` for logging exceptions
- Wrap exceptions with context when propagating
- Use specific exception types over generic `Exception`

```java
try {
    // operation
} catch (SQLException e) {
    LoggerUtils.error("Database operation failed", e);
    throw new GameException("Failed to save player data", e);
}
```

### Comments
- Javadoc for public classes and methods (in Chinese)
- Inline comments for complex logic

### Singleton Pattern
Use enum for thread-safe singletons:
```java
public enum SessionManager {
    INSTANCE;
    // methods
}
```

### Thread Safety
- Use `ConcurrentHashMap` for concurrent access
- Use `CopyOnWriteArraySet` for read-heavy collections
- Use `AtomicInteger`, `AtomicLong` for counters
- Use `NamedThreadFactory` for custom threads

## Project Structure
```
src/main/java/com/dnfm/
├── game/          # Game logic (player, role, items)
├── mina/          # Network layer (protobuf, sessions)
├── cross/         # Cross-server functionality
├── common/        # Utilities, database, Spring helpers
├── http/          # HTTP controllers (REST APIs)
├── listener/      # Event system
├── logs/          # Logging utilities
└── monitor/       # JMX monitoring
```

## Testing

### Java Tests
- JUnit 4 with Spring Boot Test
- Test classes end with `Tests` or `Test`
- Use `@RunWith(SpringRunner.class)` and `@SpringBootTest`

### Python Tests
Located in `/tests` directory:
- **test_database.py** - Database connection tests
- **test_api.py** - API endpoint tests
- **test_game_logic.py** - Game logic tests
- **test_user_auth.py** - User authentication tests
- **test_role_management.py** - Role management tests
- **test_item_shop.py** - Item purchase/sale tests
- **test_trade.py** - Trading functionality tests
- **test_chat.py** - Chat functionality tests
- **test_pet.py** - Pet functionality tests
- **conftest.py** - Pytest configuration and fixtures
- **utils/** - Test utilities

### Test Standards
- Test classes use `Test` or `Tests` suffix
- Test methods use `test_` prefix
- Use descriptive test names
- Use `@pytest.mark` markers: `auth`, `role`, `item`, `trade`, `chat`, `pet`, `game`, `slow`, `integration`

## Dependencies to Know
- **Lombok**: Code generation (`@Data`, `@Slf4j`)
- **Nutz**: ORM framework (Dao, Cnd for conditions)
- **Hutool**: Utility library
- **FastJSON**: JSON serialization
- **Guava**: Google's core libraries
- **Apache Mina**: Network framework
- **jprotobuf**: Baidu's protobuf for Java

## Important Notes
- Never commit secrets or credentials
- Always use UTF-8 encoding
- MySQL connection uses Druid pool
- Server runs on port configured in `application.properties`
- Supports hot-reload via Spring Boot DevTools

## Documentation
- Development docs in `/devdoc` directory
- Update docs when adding/modifying features
- Use Markdown format with syntax highlighting
