<div align="center">

# 🧪 user-crud-junit-mockito

### A production-style Java 17 CRUD project for mastering unit testing with JUnit 5 & Mockito

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![JUnit5](https://img.shields.io/badge/JUnit-5.10.2-25A162?style=for-the-badge&logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![Mockito](https://img.shields.io/badge/Mockito-5.11.0-78A641?style=for-the-badge)](https://site.mockito.org/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](LICENSE)
[![Tests](https://img.shields.io/badge/Tests-31%20Passing-brightgreen?style=for-the-badge)](#-test-results)
[![IDE](https://img.shields.io/badge/IDE-IntelliJ%20IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white)](https://www.jetbrains.com/idea/)

<br/>

<img src="https://img.shields.io/badge/source%20files-7-informational?style=flat-square" />
<img src="https://img.shields.io/badge/test%20files-5-informational?style=flat-square" />
<img src="https://img.shields.io/badge/test%20cases-31-informational?style=flat-square" />
<img src="https://img.shields.io/badge/layers%20tested-4-informational?style=flat-square" />

<br/><br/>

> *Stop writing "Hello World" tests. Start writing tests that actually teach you something.*

[Getting Started](#-getting-started) · [Architecture](#-architecture) · [Test Results](#-test-results) · [Techniques](#-testing-techniques-reference) · [IntelliJ Tips](#-intellij-idea-power-tips)

</div>

---

## 📖 Why This Project?

Most JUnit/Mockito tutorials show a single class with a single mock. Real codebases have **layers**, **validation**, **error handling**, and **edge cases** — and that's where testing gets interesting.

This project demonstrates testing across four layers of a CRUD application:

| Layer | What's tested | Mocking? |
|-------|--------------|----------|
| **Model** | `equals()`, `hashCode()`, getters/setters | No |
| **Repository** | Thread-safe in-memory CRUD, ID generation, reset | No |
| **Service** | Business logic, input validation, error paths | ✅ `@Mock` repository |
| **Validation** | Email regex, name constraints, null handling | No |
| **Exception** | Custom exception message and type hierarchy | No |

---

## 🏗️ Architecture

```
user-crud-junit-mockito/
│
├── pom.xml                                    # Maven build config
├── README.md
├── LICENSE                                    # MIT
├── .gitignore                                 # Java + IntelliJ + Maven
├── .editorconfig                              # Consistent code style
│
└── src/
    ├── main/java/com/example/
    │   │
    │   ├── Main.java                          # 🚀 Runnable demo
    │   │
    │   ├── model/
    │   │   └── User.java                      # Domain entity
    │   │
    │   ├── repository/
    │   │   ├── UserRepository.java            # Interface (contract)
    │   │   └── InMemoryUserRepository.java    # Thread-safe implementation
    │   │
    │   ├── service/
    │   │   └── UserService.java               # Business logic + validation
    │   │
    │   ├── validation/
    │   │   └── UserValidator.java             # Email & name rules
    │   │
    │   └── exception/
    │       └── UserNotFoundException.java     # Custom runtime exception
    │
    └── test/java/com/example/
        │
        ├── model/
        │   └── UserTest.java                  # 5 tests
        │
        ├── repository/
        │   └── InMemoryUserRepositoryTest.java # 9 tests
        │
        ├── service/
        │   └── UserServiceTest.java           # 10 tests (with Mockito)
        │
        ├── validation/
        │   └── UserValidatorTest.java         # 8 tests (parameterized)
        │
        └── exception/
            └── UserNotFoundExceptionTest.java # 2 tests
```

---

## 🚀 Getting Started

### Prerequisites

| Tool | Version | Check |
|------|---------|-------|
| Java JDK | 17+ | `java -version` |
| Maven | 3.8+ | `mvn -version` |
| IntelliJ IDEA | 2023.x+ | Community or Ultimate |

### 1️⃣ Clone

```bash
git clone https://github.com/your-username/user-crud-junit-mockito.git
cd user-crud-junit-mockito
```

### 2️⃣ Open in IntelliJ IDEA

1. Launch IntelliJ → **File → Open** → select the project root folder
2. IntelliJ auto-detects Maven and imports dependencies
3. Wait for indexing to finish (progress bar, bottom-right)

### 3️⃣ Verify JDK

**File → Project Structure → Project → SDK** → set to **Java 17**

> Don't have it? → **Add SDK → Download JDK → Oracle OpenJDK 17**

### 4️⃣ Run Tests

| Method | Shortcut / Action |
|--------|-------------------|
| ▶️ **Gutter icon** | Click the green play button next to any test class or method |
| ⌨️ **Keyboard** | `Ctrl+Shift+F10` (Win/Linux) · `⌃⇧R` (macOS) |
| 📋 **Run menu** | **Run → Run 'All Tests'** |
| 🔧 **Maven panel** | **Lifecycle → test** → double-click |
| 💻 **Terminal** | `mvn test` |

### 5️⃣ Run the Demo

```bash
mvn compile exec:java -Dexec.mainClass="com.example.Main"
```

Or in IntelliJ: open `Main.java` → click ▶️ next to `main()`.

---

## 🧪 Test Results

```
 ✅ UserTest                                     5 passed
 │  ├── users with same id should be equal
 │  ├── users with different ids should not be equal
 │  ├── user should not equal null
 │  ├── user should not equal different type
 │  └── getters and setters should work
 │
 ✅ InMemoryUserRepositoryTest                   9 passed
 │  ├── save should auto-generate id when null
 │  ├── save should preserve explicit id
 │  ├── findById should return saved user
 │  ├── findById should return empty for missing id
 │  ├── findAll should return all saved users
 │  ├── deleteById should remove the user
 │  ├── existsById should return true for existing user
 │  ├── existsById should return false for missing user
 │  └── clear should remove all users and reset id counter
 │
 ✅ UserServiceTest                              10 passed
 │  ├── createUser
 │  │   ├── should save and return user
 │  │   ├── should throw when name is blank
 │  │   └── should throw when email is null
 │  ├── getUserById
 │  │   ├── should return user when found
 │  │   └── should return empty when not found
 │  ├── getAllUsers should return list from repository
 │  ├── getAllUsers should return empty list when no users
 │  ├── updateUser
 │  │   ├── should update existing user
 │  │   └── should throw when user not found
 │  └── deleteUser
 │      ├── should delete existing user
 │      └── should throw when user not found
 │
 ✅ UserValidatorTest                             8 passed
 │  ├── valid user should produce no errors
 │  ├── null user should return error
 │  ├── blank or null name should be invalid          (×3 parameterized)
 │  ├── name exceeding 100 chars should be invalid
 │  ├── blank or null email should be invalid          (×3 parameterized)
 │  ├── malformed emails should be invalid             (×4 parameterized)
 │  ├── well-formed emails should be valid             (×3 parameterized)
 │  └── multiple errors should all be reported
 │
 ✅ UserNotFoundExceptionTest                     2 passed
    ├── should contain user id in message
    └── should be a RuntimeException

────────────────────────────────────────────────────
 Tests run: 31  |  ✅ Passed: 31  |  ❌ Failed: 0  |  ⏭️ Skipped: 0
────────────────────────────────────────────────────
```

---

## 🔬 Testing Techniques Reference

### Mockito

| Technique | Example | Used in |
|-----------|---------|---------|
| `@ExtendWith(MockitoExtension.class)` | Integrates Mockito with JUnit 5 | `UserServiceTest` |
| `@Mock` | `@Mock UserRepository repo` | `UserServiceTest` |
| `@InjectMocks` | `@InjectMocks UserService service` | `UserServiceTest` |
| `when().thenReturn()` | `when(repo.findById(1L)).thenReturn(...)` | Create, Read |
| `when().thenAnswer()` | Dynamic return based on input args | Update |
| `verify(mock, times(n))` | Assert method called `n` times | Create |
| `verify(mock, never())` | Assert method was never called | Validation failures |
| `any(Class)` | Flexible argument matching | Multiple tests |

### JUnit 5

| Technique | Example | Used in |
|-----------|---------|---------|
| `@Nested` | Group related tests into inner classes | `UserServiceTest` |
| `@DisplayName` | Human-readable names in test reports | All test classes |
| `@BeforeEach` | Reset state before each test | Multiple classes |
| `assertThrows()` | `assertThrows(IAE.class, () -> ...)` | Service + Validator |
| `assertEquals()` / `assertNotEquals()` | Value equality checks | All classes |
| `assertTrue()` / `assertFalse()` | Boolean checks | Multiple classes |
| `assertInstanceOf()` | Type checking | Exception test |
| `@ParameterizedTest` | Run same test with multiple inputs | `UserValidatorTest` |
| `@ValueSource` | Inline values for parameterized tests | Email validation |
| `@NullAndEmptySource` | Auto-supply `null` and `""` | Name + email tests |

---

## 📦 Dependencies

| Dependency | Version | Scope | Purpose |
|------------|---------|-------|---------|
| `junit-jupiter` | 5.10.2 | test | JUnit 5 framework (includes API, engine, params) |
| `mockito-core` | 5.11.0 | test | Mocking framework |
| `mockito-junit-jupiter` | 5.11.0 | test | `@ExtendWith` integration |
| `maven-surefire-plugin` | 3.2.5 | build | Test runner for `mvn test` |

---

## 💡 IntelliJ IDEA Power Tips

<details>
<summary><b>🎯 Running & Debugging Tests</b></summary>

- **Run with Coverage** → Right-click test class → **Run with Coverage** → see line-by-line hit/miss highlighting
- **Re-run failed only** → Click ⟲ in the Run panel to re-run just the failures
- **Debug a test** → Set breakpoint → right-click test → **Debug** → step through with `F8`
- **Run all tests** → Right-click `src/test` folder → **Run 'All Tests'**

</details>

<details>
<summary><b>⚡ Navigation & Productivity</b></summary>

- **Jump to test** → `Ctrl+Shift+T` (Win/Linux) · `⌘⇧T` (macOS) from any source class
- **Structure view** → `Alt+7` to see all `@Nested` groups at a glance
- **Navigate to source** → `Ctrl+Click` on any mocked method to jump to the real implementation
- **Find usages** → `Alt+F7` on `UserRepository` to see every mock and real usage

</details>

<details>
<summary><b>🛠️ Code Generation</b></summary>

- **Live Templates** → Type `@Test` + `Tab` for test method scaffolding
- **Generate test** → Inside a class, `Alt+Insert` → **Test...** to auto-generate test class
- **Surround with** → Select code → `Ctrl+Alt+T` → wrap in `assertThrows`, try/catch, etc.

</details>

<details>
<summary><b>📊 Advanced</b></summary>

- **Mutation testing** → Install [PIT plugin](https://plugins.jetbrains.com/plugin/7119-pit-mutation-testing) to find weak tests
- **Test profiling** → **Run → Profile** to spot slow tests
- **Continuous testing** → Enable **Autotest** (toggle in Run panel) to re-run on save

</details>

---

## 🗺️ Roadmap

Ideas for extending this project:

- [ ] Add Spring Boot integration with `@SpringBootTest`
- [ ] Add `@DataJpaTest` with H2 for repository integration tests
- [ ] Add REST controller with `MockMvc` tests
- [ ] Add `@Spy` and `@Captor` examples
- [ ] Add test containers for database integration
- [ ] Add CI/CD pipeline (GitHub Actions)
- [ ] Add JaCoCo code coverage report
- [ ] Add mutation testing with PIT

---

## 🤝 Contributing

Contributions are welcome! Here's how:

1. **Fork** the repository
2. **Create** a feature branch → `git checkout -b feature/add-spy-examples`
3. **Write tests first** (yes, really)
4. **Commit** with a descriptive message → `git commit -m 'feat: add @Spy examples for partial mocking'`
5. **Push** → `git push origin feature/add-spy-examples`
6. **Open** a Pull Request

Please follow [Conventional Commits](https://www.conventionalcommits.org/) for commit messages.

---

## 📄 License

Distributed under the [MIT License](LICENSE). Use it, learn from it, build on it.

---

<div align="center">

<br/>

**Built with clean code, thorough tests, and ☕**

If this project helped you learn, drop a ⭐ — it means a lot.

<br/>

[⬆ Back to top](#-user-crud-junit-mockito)

</div>
