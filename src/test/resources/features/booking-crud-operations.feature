Feature: Validate Booking CRUD Operations

  Background:
    Given booking is created with the following details
      | firstName     | lastName     | depositPaid | email          | phone          | checkIn    | checkOut   | roomId |
      | Testfirstname | Testlastname | true        | jagan@test.com | +91-9843945628 | 2026-08-21 | 2026-08-22 | 43     |

  Scenario: Verify booking creation and retrieval
    Given the user requests the booking details with valid data
    And the booking details should be returned successfully
    Then the booking details should match the created booking data

  Scenario: Verify booking updation and retrieval
    Given user provides the following booking details
      | firstName | lastName  | depositPaid | email          | phone          | checkIn    | checkOut   | roomId |
      | Testfname | Testlname | true        | jagan@test.com | +91-9843945628 | 2026-08-21 | 2026-08-22 | 54     |
    And the user submits the update request
    And the booking should be updated successfully
    And the user requests the booking details
    Then the booking details should match the updated booking data

  Scenario: Verify booking deletion and validation
    Given the user requests to delete the booking
    And the booking should be deleted successfully
    And the user requests the booking details
    Then the system should reject the request
