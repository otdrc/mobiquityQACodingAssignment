Feature: Comments - Email field match format validation

  Scenario: All existing comments emails match email format
    Given GET all existing comments
    Then validate comments emails are in proper format

  Scenario: A new comment could not be created using invalid email
    Given I create a comment object with invalid email
    When send comment POST request
    Then validate I receive 4XX Client Error response code
