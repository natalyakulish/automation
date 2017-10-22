@all
Feature: Login

Scenario: User can login with previously registered data
    Given User is on "home" page
    When he tries to login with "shoes@mailinator.com" email and "q!w@e#r$1234" password
    And he opens "my-account" page
    Then he can see "Natalie Kulish" account name

Scenario: Not logged in user tries to go to my account and gets redirected to login screen
    Given User is on "home" page
    When he opens "my-account" page
    Then url must contain "/identity/login?signin" string