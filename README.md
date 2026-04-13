# 📱 Appium Java Mobile Automation Framework

A comprehensive, scalable **mobile automation framework** built using **Appium, Java, and TestNG**, with optional support for **BDD (Cucumber)**.
Designed for **cross-platform testing (Android & iOS)** with a clean, maintainable, and enterprise-ready architecture.

---

## 📋 Table of Contents

* [Getting Started](#getting-started)
* [Project Structure](#project-structure)
* [Environment Configuration](#environment-configuration)
* [Framework Architecture](#framework-architecture)
* [Folder Purpose & Rules](#folder-purpose--rules)
* [Naming Conventions](#naming-conventions)
* [Best Practices](#best-practices)
* [Examples](#examples)
* [Running Tests](#running-tests)
* [CI/CD Integration](#cicd-integration)

---

# 🚀 Getting Started

## ✅ Prerequisites

* Java JDK 11+
* Maven
* Node.js (required for Appium)
* Appium Server
* Android Studio / Xcode
* IDE (IntelliJ / VS Code)

---

## ⚙️ Installation

1. **Clone the repository**

```bash
git clone <repo-url>
cd <project-name>
```

2. **Install dependencies**

```bash
mvn clean install
```

3. **Install Appium & drivers**

```bash
npm install -g appium
appium driver install uiautomator2
appium driver install xcuitest
```

4. **Verify environment**

```bash
appium-doctor
```

---

# 📁 Project Structure

```text
automation/
├── pom.xml
├── testng.xml
├── README.md
│
├── src/
│   ├── main/java/
│   │   ├── base/                  # BaseTest, DriverFactory
│   │   ├── config/                # Config reader, environment loader
│   │   ├── pages/                 # Page Objects (POM)
│   │   ├── services/              # Business logic layer
│   │   ├── utils/                 # Reusable utilities
│
│   ├── test/java/
│   │   ├── tests/                 # Test classes (TestNG)
│   │   ├── stepdefinitions/       # (Optional) BDD steps
│   │   ├── runners/               # Test runners
│
│   └── test/resources/
│       ├── config.properties      # Environment configs
│       ├── testdata/              # Input data
│       └── features/              # Cucumber feature files
│
├── reports/                       # Execution reports
├── logs/                          # Logs
├── screenshots/                   # Failure evidence
```

---

# ⚙️ Environment Configuration

## What

| Component             | Purpose                             |
| --------------------- | ----------------------------------- |
| `config.properties`   | Stores environment configuration    |
| Environment variables | Override configs (CI/CD)            |
| DriverFactory         | Reads config and initializes driver |

---

## Example Config

```properties
platform=Android
deviceName=Pixel_6
automationName=UiAutomator2
appPath=/apps/app.apk
```

---

## When to Use

* Switch environment → update config
* Override values → use env variables
* Store secrets → use `.env` or CI secrets

---

## Why

* Centralized configuration
* Easy environment switching
* No hardcoding

---

# 🧱 Framework Architecture

## Layered Design

```text
Test Layer (TestNG / Cucumber)
        ↓
Service Layer (Business Logic)
        ↓
Page Object Layer (UI Interaction)
        ↓
Core Layer (Driver, Config, Utilities)
```

---

## Execution Flow

### TestNG

```text
Test → BaseTest → DriverFactory → Page → Assertion
```

### BDD (Optional)

```text
Feature → Step → Service → Page → Driver
```

---

# 📂 Folder Purpose & Rules

## `pages/`

* UI locators and actions only
* No assertions

## `services/`

* Business logic
* Reusable workflows

## `tests/`

* Test scenarios and validations

## `utils/`

* Generic helpers (waits, logs, data)

## `config/`

* Environment handling

---

# 🏷️ Naming Conventions

## Classes

* `LoginPage.java`
* `BaseTest.java`
* `DriverFactory.java`

## Methods

* `loginUser()`
* `selectItem()`
* `verifyHomeScreen()`

## Test Names

* ✅ `userCanLoginSuccessfully`
* ❌ `test1`

---

# ✍️ Best Practices

## 1. Page Object Model

✔ Keep UI logic separate
❌ No assertions inside pages

---

## 2. Driver Management

* Use ThreadLocal for parallel execution
* Avoid static driver misuse

---

## 3. Wait Strategy

✔ Explicit waits only
❌ No Thread.sleep()

---

## 4. Cross-Platform Strategy

* Prefer Accessibility ID
* Handle platform-specific locators separately

---

## 5. Test Data Management

* Externalize data
* Avoid hardcoding

---

## 6. Logging & Reporting

* Use Log4j
* Capture screenshots on failure
* Generate Extent/Allure reports

---

# 📝 Examples

## Sample Test (TestNG)

```java
@Test
public void userCanLogin() {
    loginPage.login("user", "password");
    Assert.assertTrue(homePage.isDisplayed());
}
```

---

## Sample BDD Scenario

```gherkin
Feature: Login

Scenario: User logs in successfully
  Given user is on login screen
  When user enters valid credentials
  Then user should see home screen
```

---

# 🏃 Running Tests

## Run all tests

```bash
mvn test
```

## Run specific suite

```bash
mvn test -DsuiteXmlFile=testng.xml
```

## Run by tag (BDD)

```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

---

# 🔄 CI/CD Integration

Supports:

* Jenkins
* GitHub Actions

## Pipeline Steps

1. Checkout code
2. Install dependencies
3. Start Appium server
4. Run tests
5. Publish reports

---

# ⚡ Advanced Capabilities

* Parallel execution
* Retry mechanism
* Cross-platform testing
* Screenshot/video capture
* Cloud execution (BrowserStack, Sauce Labs)

---

# 🧠 Framework Strategy

This framework is designed to:

* Scale across large projects
* Reduce flaky tests
* Support cross-platform automation
* Enable fast CI/CD feedback

---

# ⚠️ Common Pitfalls

❌ Hardcoding values
❌ Using Thread.sleep()
❌ Mixing test logic with page logic
❌ Ignoring parallel execution issues

---

# 🤝 Contributing

1. Follow project structure
2. Use POM design
3. Keep tests independent
4. Write clean reusable code

---

# 🎯 Final Thought

> A good automation framework is not about tools —
> it's about design, scalability, and reliability.

---

**Happy Testing 🚀**
