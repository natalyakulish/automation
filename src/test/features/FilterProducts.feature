@new
Feature: filter products

  Scenario Outline: filter products by categories and refine by creterias

    Given User is on "Home" page
    When User navigates to <Sex Category> and selects <Category>
    Then User was check breadcrumbs that contains <Breadcrumb sex category> and <Category>
    And User checks first enabled checkbox in all filters
    Then User checks number of checked checkboxes
    And User checks the product with <Index>
    And User selects size of product
    And add product to Bag
    Then products was added to Bag

    Examples:
      | Sex Category | Category | Breadcrumb sex category | Index |
      | "WOMEN"      | "Shoes"  | "Women"                 | 4     |