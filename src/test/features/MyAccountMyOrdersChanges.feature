@all
Feature: My account

  Scenario Outline: Check My orders

    Given User is on "Home" page
    When he tries to login with "s1511211932566@mailinator.com" email and "q!w@e#r$1234" password
    And User clicks on "My account" link
    When User clicks on "My orders" link
    And User clicks "Start shopping" button
    Then User checks page "<Title>"

    Examples:

      | Title           |
      | Women's Clothes |


