Feature: Status code and response time
   @wip
  Scenario: get status code and response time
    Given user connects to the baseUrl
    When user sends GET request to the end point "ottplatform/media"
    Then user verifies status code is 200
    And user verifies response time of the request is below 1000 milliseconds
