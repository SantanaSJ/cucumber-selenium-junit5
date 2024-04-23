Feature: X Way Pulse + Twin + Neural
  Background:
    Given user is on pricing calculator page "https://axilion.z6.web.core.windows.net/#/"

  Scenario Outline: X Way (Pulse + Twin + Neural) service
    When user sets road length slider value to <roadLength>
    And user sets number of signalized intersections slider value to <signalizedIntersections>
    And user selects "<service>" service from the dropdown menu
    And user sets measuring unit to "<measuringUnit>"
    Then the selected service should be "<service>"
    And the measuring unit should be "<measuringUnit>"
    And the expected price should match the suggested price
    And the actual plan should match the expected

    Examples:
      | roadLength | signalizedIntersections |      service                        | measuringUnit |
      |    1       |         1               |    X Way (Pulse + Twin + Neural)    |    METRIC     |
      |    1       |         250             |    X Way (Pulse + Twin + Neural)    |    METRIC     |
      |    1       |         500             |    X Way (Pulse + Twin + Neural)    |    METRIC     |
      |    500     |         500             |    X Way (Pulse + Twin + Neural)    |    METRIC     |
      |    500     |         1000            |    X Way (Pulse + Twin + Neural)    |    METRIC     |
      |    500     |         250             |    X Way (Pulse + Twin + Neural)    |    METRIC     |