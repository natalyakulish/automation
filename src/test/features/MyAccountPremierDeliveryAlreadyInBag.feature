@all

Feature: My account

  Scenario: Check Premier Delivery

    Given User is on "Home" page
    When he tries to login with "1511211932566@mailinator.com" email and "q!w@e#r$1234" password
    And User clicks on "My account" link
    When User clicks on "Premier delivery" link
    And User clicks "Find out more" button
    Then Page url contains "premier-delivery"
    And User clicks "Add to bag" button
    Then Error Message displayed "It looks like you already have Premier Delivery in your bag – enjoy!"


