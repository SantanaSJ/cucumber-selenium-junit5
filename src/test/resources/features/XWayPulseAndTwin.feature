Feature: X Way Pulse + Twin
  Background:
    Given user is on pricing calculator page "https://axilion.z6.web.core.windows.net/#/"

  Scenario Outline: X Way (Pulse + Twin) service
    When user sets road length slider value to <roadLength>
    And user sets number of signalized intersections slider value to <signalizedIntersections>
    And user selects "<service>" service from the dropdown menu
    And user sets measuring unit to "<measuringUnit>"
    Then the selected service should be "<service>"
    And the measuring unit should be "<measuringUnit>"
    And the expected price should match the suggested price
    And the actual plan should match the expected

    Examples:
      | roadLength | signalizedIntersections |      service               | measuringUnit |
      |    1       |         1000            |    X Way (Pulse + Twin)    |    METRIC     |
      |    250     |         500             |    X Way (Pulse + Twin)    |    METRIC     |
      |    500     |         1               |    X Way (Pulse + Twin)    |    METRIC     |
      |    500     |         250             |    X Way (Pulse + Twin)    |    METRIC     |
      |    1000    |         1000            |    X Way (Pulse + Twin)    |    METRIC     |
      |    1000    |         1               |    X Way (Pulse + Twin)    |    METRIC     |