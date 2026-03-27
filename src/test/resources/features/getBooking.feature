Feature: Validate Get Booking API

  Background:
    Given booking is created with the following details
      | firstName     | lastName     | depositPaid | email          | phone          | checkIn    | checkOut   | roomId |
      | Testfirstname | Testlastname | true        | jagan@test.com | +91-9843945628 | 2026-08-21 | 2026-08-22 | 43     |

  Scenario: Retrieve booking successfully with valid booking id
    Given the user requests the booking details with valid data
    And the booking details should be returned successfully
    And the booking response should follow the expected structure
    Then the booking information should match the stored data

  Scenario: Booking retrieval should fail with with Non-existent booking id
    Given the user attempts to retrieve booking details with Non-existent booking id
    And the system should reject the request
    Then the user should get the error message "Non-existent booking id"

  Scenario: Booking retrieval should fail with invalid authentication
    Given the user attempts to retrieve booking details with invalid authentication
    And the system should deny request
    Then the user should get the error message "Unauthorized"

  Scenario: Booking retrieval should fail without authentication
    Given user sends Get request without token
    And the system should deny request
    Then the user should get the error message "Unauthorized"