# API TESTS FOR INTERACTIVE

### Used Technologies & Tools:
<p align="left">
<img height="40" width="40" src="images/logo/java-logo.svg" alt="java">
<img height="40" width="40" src="images/logo/JUnit5.svg" alt="junit5">
<img height="40" width="40" src="images/logo/rest-assured-logo.png" alt="rest-assured">
<img height="40" width="40" src="images/logo/gradle-logo.svg" alt="gradle">
<img height="40" width="40" src="images/logo/IDEA-logo.svg" alt="IDEA">
<img height="40" width="40" src="images/logo/git-logo.svg" alt="git">
<img height="40" width="40" src="images/logo/swagger-logo.png" alt="swagger">
<img height="40" width="40" src="images/logo/allure-Report-logo.svg" alt="allure">
<img height="40" width="40" src="images/logo/Jenkins.svg" alt="jenkins">
</p>

### Swagger as a REST API Service Self-Documentation:
Swagger is available on by [link](https://hr-challenge.interactivestandard.com/v3/swagger-ui/index.html?configUrl=%2Fv3%2Fapi-docs%2Fswagger-config&urls.primaryName=QA#/qa-test-controller)

### Autotests coverage next handlers:
- [X] Get user
- [X] Get user list

### How To Run Tests </br>
```bash
gradle clean test
```
### Allure Report For Test Results Presentation
#### How to Run Allure Report
After tests are executed run the following command at Terminal
```bash
gradle allureServe
```
or run report using GUI
![image](images/screens/allureServe.png)

### Allure Report Results
[REPORT](https://jenkins.autotests.cloud/job/interactive-api-tests/4/allure/)
![image](images/screens/allure_overview.png)
![image](images/screens/allure_behaviors.png)
### Custom template for API Report
![image](images/screens/allure_custom_template_for_response.png)

### Jenkins build
[BUILD](https://jenkins.autotests.cloud/job/interactive-api-tests/4/)
![image](images/screens/Jenkins_overview.png)
![image](images/screens/Jenkins_console.png)
