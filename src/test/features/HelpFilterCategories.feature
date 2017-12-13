@all
Feature: Help

  Scenario Outline: filter Help categories

    Given User is on "Home" page
    And User clicks on "Help" link
    When User navigates to "<category>" and selects "<sub-category>"
    Then User was check breadcrumbs that contains "<category>" and "<sub-category>"


    Examples:
      | category          | sub-category      |
      | ORDER ISSUES      | Faulty Item       |
      | RETURNS & REFUNDS | Can I exchange?   |
      | DELIVERY          | Where's my order? |

