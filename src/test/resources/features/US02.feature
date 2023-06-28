Feature: Id field and segment_type

  Scenario: Verify id field and the segment_type
    Given user connects to the baseUrl
    When user sends GET request to the end point "ottplatform/media"
    Then user verifies the id field is never null or empty for all fourteen items present in the data array
    And user verifies the segment_type field for every track is always “music”