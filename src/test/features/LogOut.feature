@all
Feature: LogOut

  Scenario: Logout with already registered user

    Given User is on "Home" page
    When he tries to login with "shoes@mailinator.com" email and "q!w@e#r$1234" password
    And User is on "Home" page
    And User refresh the page
    And User clicks "Sign Out" button
    Then "Home" page has "Sign in" link