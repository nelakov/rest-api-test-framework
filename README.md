<h1 align="center">REST API Test Framework</h1>

<p align="center">
  API tests for the HR-Challenge REST API. Responses are validated against a JSON Schema, the cases are data-driven, and runs are reported with Allure.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-25_LTS-orange?logo=openjdk&logoColor=white">
  <img src="https://img.shields.io/badge/Gradle-9.6.0-02303A?logo=gradle&logoColor=white">
  <img src="https://img.shields.io/badge/JUnit-6.1-25A162?logo=junit5&logoColor=white">
  <img src="https://img.shields.io/badge/REST--Assured-6.0-43853D">
  <img src="https://img.shields.io/badge/Allure-2.35-FF5722">
  <img src="https://img.shields.io/badge/AssertJ-3.27-blue">
  <img src="https://img.shields.io/badge/tests-data--driven-success">
</p>

<p align="center">
<img height="38" src="images/logo/java-logo.svg" alt="java">&nbsp;&nbsp;
<img height="38" src="images/logo/gradle-logo.svg" alt="gradle">&nbsp;&nbsp;
<img height="38" src="images/logo/JUnit5.svg" alt="junit">&nbsp;&nbsp;
<img height="38" src="images/logo/rest-assured-logo.png" alt="rest-assured">&nbsp;&nbsp;
<img height="38" src="images/logo/allure-Report-logo.svg" alt="allure">&nbsp;&nbsp;
<img height="38" src="images/logo/swagger-logo.png" alt="swagger">&nbsp;&nbsp;
<img height="38" src="images/logo/Telegram.svg" alt="telegram">&nbsp;&nbsp;
<img height="38" src="images/logo/Jenkins.svg" alt="jenkins">
</p>

---

## What it does

- Every response is validated against a JSON Schema (`schemas/schemaV3.json`) before any field assertion runs, so structural drift fails the test early.
- Parametrized tests cover positive and negative cases from `@ValueSource` sets: valid IDs, missing IDs, non-existent IDs, special characters, invalid and blank gender.
- Request/response `Specification`s are built once in `@BeforeAll`, so the tests stay short.
- The `given/when/then` calls live in reusable `TestBase` helpers (`getUserById`, `getUser`, `getUsersByGender`), so each test is about four lines.
- Custom Allure Freemarker templates render each exchange as a request/response card with a copy-ready cURL.
- Runs on the JUnit Platform with configurable parallelism.
- The Gradle wrapper plus the foojay toolchain resolver fetch the right JDK automatically.
- Allure summaries are posted to Telegram.

## Architecture

```mermaid
flowchart LR
    A["Parametrized test<br/>@ValueSource"] --> B["TestBase helper<br/>given / when / then"]
    B --> C["REST-Assured client<br/>shared Request/Response specs"]
    C --> D[("HR-Challenge API")]
    D --> E["Typed DTO<br/>(Jackson)"]
    E --> F{"JSON Schema<br/>+ AssertJ"}
    F --> G["Allure report<br/>custom templates"]
    G --> H["Telegram"]
```

## Tech stack

| Layer | Tool |
|---|---|
| Language | Java 25 (LTS) |
| Build | Gradle 9.6.0 (wrapper) + foojay |
| Test engine | JUnit 6 |
| HTTP & validation | REST-Assured 6 (JSON Schema validation) |
| Assertions | AssertJ 3.27 |
| Reporting | Allure 2.35 |
| Mapping | Jackson (response to DTOs) |
| Notifications | Telegram |
| CI | Jenkins |

## Project structure

```
src/test/
├── java/
│   ├── tests/        # TestBase (shared specs + request helpers) + GetUser / GetAllUsers suites
│   ├── models/       # response DTOs: GetUserResponse, GetUserListResponse, CommonResponseError
│   └── listeners/    # CustomAllureListener: request/response Allure templates
└── resources/
    ├── schemas/      # JSON Schema (schemaV3.json): the response contract
    └── tpl/          # Freemarker templates powering the Allure cards
notifications/        # Allure to Telegram delivery (jar + config)
```

## Test design

| Endpoint | Positive | Negative |
|---|---|---|
| `GET /api/test/user/{id}` | valid male / female / any IDs | missing ID · non-existent ID · special characters |
| `GET /api/test/users?gender=` | valid genders | invalid gender · blank · missing parameter |

Every case validates the body against `schemas/schemaV3.json` and asserts the
business fields with AssertJ.

## Getting started

```bash
# Run the whole suite (Gradle is pinned by the wrapper; the JDK is auto-provisioned)
./gradlew clean test

# Run a single suite
./gradlew test --tests "tests.GetUserTests"

# Run in parallel (N threads)
./gradlew clean test -Dthreads=4
```

> No local Gradle needed; the wrapper pins **9.6.0**, and the **foojay** resolver
> provisions **JDK 25** if it isn't already installed.

## Reporting

```bash
./gradlew allureServe
```

Or launch from the IDE:

![Run Allure](images/screens/allureServe.png)

**Overview & behaviors**

![Allure overview](images/screens/allure_overview.png)
![Allure behaviors](images/screens/allure_behaviors.png)

**Custom request/response template (with cURL)**

![Custom template](images/screens/allure_custom_template_for_response.png)

## Telegram notifications

Deliver the Allure summary to a chat via
[allure-notifications](https://github.com/qa-guru/allure-notifications):

```bash
java -jar notifications/allure-notifications-4.2.1.jar -c notifications/telegram_config.json
```

Provide your bot **token** and **chat id** in `notifications/telegram_config.json`.
> Keep real credentials out of version control.

## API Documentation

Swagger UI: [hr-challenge.interactivestandard.com](https://hr-challenge.interactivestandard.com/v3/swagger-ui/index.html?configUrl=%2Fv3%2Fapi-docs%2Fswagger-config&urls.primaryName=QA#/qa-test-controller)

## Continuous Integration

Jenkins: [build](https://jenkins.autotests.cloud/job/interactive-api-tests/4/) · [allure report](https://jenkins.autotests.cloud/job/interactive-api-tests/4/allure/)

![Jenkins overview](images/screens/Jenkins_overview.png)
![Jenkins console](images/screens/Jenkins_console.png)
