@all
Feature: filter products

  Scenario Outline: filter products by categories and refine by creterias

    Given User is on "Home" page
    And User clicks on "Sign In" link
    And User opens "Registration" form
    And User inputs "Valid registration data"
    When User navigates to <Sex Category> and selects <Category>
    Then User was check breadcrumbs that contains <Breadcrumb sex category> and <Category>
    And User checks the product with <Index>
    And User selects size of product
    And add product to Bag
    Then products was added to Bag
    And User clicks on "Bag" link
    Then User checks page "<Title>"
    When User makes CheckOut product with "<Delivery>" type
    When User clicks "Check Out" button
    And User selects Delivery "<country>"
    And User inputs "<mobileNumber>" and "<address>"
    And User clicks "Deliver to this address" button


    Examples:
      | Sex Category | Category | Breadcrumb sex category | Index | Title                | Delivery                 | country | mobileNumber     | address       |
      | "WOMEN"      | "Tall"   | "Women"                 | 4     | Shopping Bag \| ASOS | Standard Delivery (Free) | UA      | 3274638264287364 | st.martin str |