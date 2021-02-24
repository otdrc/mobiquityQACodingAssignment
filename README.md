# Mobiquity - QA coding assignment
# The project represents Mobiquity - QA coding assignment
    test automation skeleton for API testing using RestAssured library

## To run tests and generate Allure report locally:

* run `mvn clean test`
* run `mvn allure:serve`
    please note it will automatically open `http://localhost:8080` in your browser - in case the port is busy it will automatically switch to another free port

## Circle CI Test Execution:

1. https://app.circleci.com/pipelines/github/otdrc/mobiquityQACodingAssignment

2. Click to rerun workflow from start

3. Once rerun finished navigate to artifacts and open Allure/index.html