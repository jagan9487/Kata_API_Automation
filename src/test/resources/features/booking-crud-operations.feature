@Booking_CRUD_Operations
Feature: Validate Booking CRUD Operations

  Background:
    Given booking is created with the following details
      | firstName     | lastName     | depositPaid | email          | phone          | checkIn    | checkOut   |
      | Testfirstname | Testlastname | true        | jagan@test.com | +91-9843945628 | 2026-08-21 | 2026-08-22 |

  Scenario: Verify booking creation and retrieval
    Given the user requests the booking details
    And the booking details should be returned successfully
    Then the booking details should match the created booking data

  Scenario: Verify booking updation and retrieval
    Given user provides the following booking details
      | firstName | lastName  | depositPaid | email          | phone          | checkIn    | checkOut   |
      | Testfname | Testlname | true        | jagan@test.com | +91-9843945628 | 2026-08-21 | 2026-08-22 |
    And the user attempts to update the request
    And the user requests the booking details
    Then the booking details should match the updated booking data

  Scenario: Verify booking deletion and validation
    Given the user attempts to delete the booking
    And the user requests the booking details
    Then the system should reject the request
