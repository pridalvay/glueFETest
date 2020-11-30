@ageverifier
Feature: Age Verifer
  As an adult I should be eligible



  Scenario Outline: My age is correctly validated
    Given I have a MainActivity
    When I input age <age>
    Then I should <see> age error

    Examples:
      | age    |  see   |
      | 18 | true  |
      | 21  |  true |
      | 50  |  true |
      | 0 |  false |
      | 17 |  false |
      | 51  |  false |
      | 100  |  false |
   #   | -5   | false  |

  #Scenario : My age is negative
  #  Given I have a MainActivity
  #  When I input age -5
  #  Then I should see HTTP age error