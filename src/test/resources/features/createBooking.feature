Feature: Validate Create Booking API

  Scenario: Verify user is able to create the booking with valid data
    Given user provides the following booking details
      | firstName     | lastName     | depositPaid | email          | phone          | checkIn    | checkOut   | roomId |
      | Testfirstname | Testlastname | true        | jagan@test.com | +91-9843945628 | 2026-08-21 | 2026-08-22 | 43     |
    When the user submits the booking request
    And the booking should be created successfully
    And the booking response should follow the expected structure
    Then the user should receives the booking details successfully

  Scenario Outline: User should get expected error message when trying to create a booking using invalid data
    Given user provides the following booking details
      | firstName   | lastName   | depositPaid   | email   | phone   | checkIn   | checkOut   | roomId   |
      | <firstName> | <lastName> | <depositPaid> | <email> | <phone> | <checkIn> | <checkOut> | <roomId> |
    When the user submits the booking request
    And the booking should not be created
    Then the user should get the error message "<errorMessage>"

    Examples:
      | firstName                 | lastName                  | depositPaid | email          | phone          | checkIn    | checkOut   | roomId | errorMessage                        |
      |                           | Testlastname              | true        | jagan@test.com | +91-9843945628 | 2026-05-21 | 2026-06-01 | 1      | Firstname should not be blank       |
      | morethan19charactersgiven | Testlastname              | true        | jagan@test.com | +91-9843945628 | 2026-05-21 | 2026-06-01 | 2      | size must be between 3 and 18       |
      | th                        | Testlastname              | true        | jagan@test.com | +91-9843945628 | 2026-05-21 | 2026-06-01 | 3      | size must be between 3 and 18       |
      | Testfirstname             |                           | true        | jagan@test.com | +91-9843945628 | 2026-05-21 | 2026-06-01 | 4      | Lastname should not be blank        |
      | Testfirstname             | morethan19charactersgiven | true        | jagan@test.com | +91-9843945628 | 2026-05-21 | 2026-06-01 | 5      | size must be between 3 and 30       |
      | Testfirstname             | et                        | true        | jagan@test.com | +91-9843945628 | 2026-05-21 | 2026-06-01 | 6      | size must be between 3 and 30       |
      | Testfirstname             | Testlastname              | true        | ubwyu          | +91-9843945628 | 2026-05-21 | 2026-06-01 | 10     | must be a well-formed email address |
      | Testfirstname             | Testlastname              | true        | jagan@test.com | +91-9843945628 | 2026-05-21 | 2026-06-01 | 13     | size must be between 11 and 21      |
      | Testfirstname             | Testlastname              | true        | jagan@test.com | +91-9843945628 | 2026-05-21 | 2026-06-01 | 14     | size must be between 11 and 21      |
      | Testfirstname             | Testlastname              | true        | jagan@test.com | +91-9843945628 | 2026-06-01 | 2026-05-21 | 17     | Failed to create booking            |