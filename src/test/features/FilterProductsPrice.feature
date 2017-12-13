@all
Feature: filter products

  Scenario Outline: filter products by product price

    Given User is on "Home" page
    When User navigates to <Sex Category> and selects <Category>
    Then User was check breadcrumbs that contains <Breadcrumb sex category> and <Category>
    And User move Price range slider to the "<Move left>" left and "<Move right>" right


    Examples:
      | Sex Category | Category | Breadcrumb sex category | Move left | Move right |
      | "WOMEN"      | "Shoes"  | "Women"                 | 30        | 0          |