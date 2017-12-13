@all
Feature: Login

  Scenario:  Login with Invalid Credentials
    Given User is on "Home" page
    When he tries to login with "shoes1@mailinator.com" email and "q!w@e#r$1234" password
    Then Error Message displayed "Looks like either your email address or password were incorrect. Wanna try again?"

