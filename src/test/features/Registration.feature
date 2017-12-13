# new feature
# Tags: optional
@all
Feature: A description

  Scenario: Registration with valid data

    Given User is on "Home" page
    When User clicks on "Sign In" link
    And User opens "Registration" form
    And User inputs "Valid registration data"
    And User clicks on "My account" link
    Then Page url contains "my-account"
    And Page contains Users "full name" from "Valid registration data"


