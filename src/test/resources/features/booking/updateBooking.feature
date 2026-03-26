Feature: Validate Update Booking API

  Background:
    Given booking is created with the following details
      | firstName     | lastName     | depositPaid | email          | phone          | checkIn    | checkOut   | roomId |
      | Testfirstname | Testlastname | true        | jagan@test.com | +91-9843945628 | 2026-08-21 | 2026-08-22 | 43     |

  Scenario: Update booking with valid data
    Given user provides then following booking details
      | firstName | lastName  | depositPaid | email          | phone          | checkIn    | checkOut   | roomId |
      | Testfname | Testlname | true        | jagan@test.com | +91-9843945628 | 2026-08-21 | 2026-08-22 | 54     |
    And user sends Update request to modify the booking details
    And verify response code should be 200
    And verify response should match the "BookingSchema" JSON schema
    Then verify response data should match the input data for Updatebooking api

  Scenario: Update booking with invalid token
    Given user sends Update request with Invalid token
    And verify response code should be 401
    Then verify response should contain the error message "Unauthorized"

  Scenario: Update booking without token
    Given user sends Update request without token
    And verify response code should be 401
    Then verify response should contain the error message "Unauthorized"

  Scenario Outline: User should get expected error message when trying to update a booking using invalid data
    Given user provides then following booking details
      | firstName   | lastName   | depositPaid   | email   | phone   | checkIn   | checkOut   | roomId   |
      | <firstName> | <lastName> | <depositPaid> | <email> | <phone> | <checkIn> | <checkOut> | <roomId> |
    And user sends Update request to modify the booking details
    And verify response code should be 400
    Then verify response should contain the error message "<errorMessage>"

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