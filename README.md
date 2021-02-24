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


Please note for this project you will need to have maven and java installed, so follow the setup guides for installation and configuration:
(Versions selected are based on ones during project development)
* https://www.oracle.com/java/technologies/javase-jdk15-downloads.html - JDK 15
* https://maven.apache.org/download.cgi - Maven 3.6.3
    Installation guide: https://maven.apache.org/install.html