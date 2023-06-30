@wip
Feature: Api automation testing

  Background:
    Given user connects to the baseUrl
    When user sends GET request to the end point "ottplatform/media"

  Scenario: TC_001 get status code and response time
    Then user verifies status code is 200
    And user verifies response time of the request is below 1000 milliseconds

  Scenario: TC_002 verifying id and segment type
    Then user verifies the id field is never null or empty for all fourteen items present in the data array
    And user verifies the segment_type field for every track is always music

  Scenario: TC_003 verifying the primary field
    Then user verifies the primary field in title_list is never null or empty for any track

  Scenario: TC_004 verifying in now playing of offset which is true
    Then user verifies that only one track in the list has now_playing field in offset as true

  Scenario: TC_005 verifying date in response
    Then user verifies in the response headers contain Date value