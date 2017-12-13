# new feature
# Tags: optional
@all

Feature: My account

  Scenario Outline: Edit My Details

    Given User is on "Home" page
    When he tries to login with "1511211932566@mailinator.com" email and "q!w@e#r$1234" password
    And User clicks on "My account" link
    When User clicks on "My details" link
    And User inputs "<First Name>" and "<Last Name>" and "<Email>"
    And User clicks "Save changes" button
    Then Notification message displayed "Changes saved"

    Examples:
      | First Name | Last Name | Email              |
      | Marla      | Zinger    | fightclub1@mail.ru |

