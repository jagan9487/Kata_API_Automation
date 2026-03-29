@DeleteBooking
Feature: Validate Delete Booking API

  Background:
    Given booking is created with the following details
      | firstName     | lastName     | depositPaid | email          | phone          | checkIn    | checkOut   |
      | Testfirstname | Testlastname | true        | jagan@test.com | +91-9843945628 | 2026-08-21 | 2026-08-22 |

  Scenario: Booking should successfully deleted with valid booking id
    Given the user requests to delete the booking
    And the booking should be deleted successfully
    Then the user should get success message

  Scenario: Booking deletion should fail with Non-existent booking id
    Given the user attempts to delete the booking with Non-existent booking id
    And the system should reject the request
    Then the user should get the error "Failed to delete booking"

  Scenario: Booking deletion should fail with invalid authentication
    Given the user attempts to delete the booking with invalid authentication
    And the system should deny request
    Then the user should get the error message "Unauthorized"

  Scenario: Booking deletion should fail without authentication
    Given the user attempts to delete the booking without authentication
    And the system should deny request
    Then the user should get the error message "Unauthorized"
