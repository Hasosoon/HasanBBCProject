@wip
Feature: Status code and response time

  Background:
    Given user connects to the baseUrl
    When user sends GET request to the end point "ottplatform/media"

  Scenario: TC_001 get status code and response time
    Then user verifies status code is 200
    And user verifies response time of the request is below 1000 milliseconds

  Scenario: TC_002
    Then user verifies the id field is never null or empty for all fourteen items present in the data array
    And user verifies the segment_type field for every track is always music

  Scenario: TC_003
    Then user verifies the primary field in title_list is never null or empty for any track

  Scenario: TC_004
    Then user verifies that only one track in the list has now_playing field in offset as true

  Scenario: TC_005
    Then user verifies in the response headers contain Date value