@UpdateBooking
Feature: Validate Update Booking API

  Background:
    Given booking is created with the following details
      | firstName     | lastName     | depositPaid | email          | phone          | checkIn    | checkOut   |
      | Testfirstname | Testlastname | true        | jagan@test.com | +91-9843945628 | 2026-08-21 | 2026-08-22 |

  Scenario: Update booking successfully with valid data
    Given user provides the following booking details
      | firstName | lastName  | depositPaid | email          | phone          | checkIn    | checkOut   |
      | Testfname | Testlname | true        | jagan@test.com | +91-9843945628 | 2026-08-21 | 2026-08-22 |
    And the user submits the update request
    And the booking should be updated successfully
    And the booking response should follow the expected structure
    Then the user should receives the updated booking details successfully

  Scenario: Booking retrieval should fail with with Non-existent booking id
    Given the user attempts to update the booking with Non-existent booking id
    And the system should reject the request
    Then the user should get the error message "Failed to update booking"

  Scenario: Booking update should fail with invalid authentication
    Given the user attempts to update the booking with invalid authentication
    And the system should deny request
    Then the user should get the error message "Unauthorized"

  Scenario: Booking update should fail without authentication
    Given user sends Update request without token
    And the system should deny request
    Then the user should get the error message "Unauthorized"

  Scenario Outline: Booking update should fail with invalid or missing details
    Given user provides the following booking details
      | firstName   | lastName   | depositPaid   | email   | phone   | checkIn   | checkOut   |
      | <firstName> | <lastName> | <depositPaid> | <email> | <phone> | <checkIn> | <checkOut> |
    And the user submits the update request
    And the booking should not be updated
    Then the user should get the error message "<errorMessage>"

    Examples:
      | firstName                 | lastName                  | depositPaid | email          | phone                         | checkIn    | checkOut   | errorMessage                        |
      |                           | Testlastname              | true        | jagan@test.com | +91-9843945628                | 2026-05-21 | 2026-06-01 | Firstname should not be blank       |
      | morethan19charactersgiven | Testlastname              | true        | jagan@test.com | +91-9843945628                | 2026-05-21 | 2026-06-01 | size must be between 3 and 18       |
      | th                        | Testlastname              | true        | jagan@test.com | +91-9843945628                | 2026-05-21 | 2026-06-01 | size must be between 3 and 18       |
      | Testfirstname             |                           | true        | jagan@test.com | +91-9843945628                | 2026-05-21 | 2026-06-01 | Lastname should not be blank        |
      | Testfirstname             | morethan19charactersgiven | true        | jagan@test.com | +91-9843945628                | 2026-05-21 | 2026-06-01 | size must be between 3 and 18       |
      | Testfirstname             | et                        | true        | jagan@test.com | +91-9843945628                | 2026-05-21 | 2026-06-01 | size must be between 3 and 18       |
      | Testfirstname             | Testlastname              | true        | ubwyu          | +91-9843945628                | 2026-05-21 | 2026-06-01 | must be a well-formed email address |
      | Testfirstname             | Testlastname              | true        | jagan@test.com | +91-1234567891011121314153432 | 2026-05-21 | 2026-06-01 | size must be between 11 and 21      |
      | Testfirstname             | Testlastname              | true        | jagan@test.com | +91-9843                      | 2026-05-21 | 2026-06-01 | size must be between 11 and 21      |
      | Testfirstname             | Testlastname              | true        | jagan@test.com | +91-9843945628                | 2026-06-01 | 2026-05-21 | Failed to create booking            |