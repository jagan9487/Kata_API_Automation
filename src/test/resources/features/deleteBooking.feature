Feature: Validate Delete Booking API

  Background:
    Given booking is created with the following details
      | firstName     | lastName     | depositPaid | email          | phone          | checkIn    | checkOut   | roomId |
      | Testfirstname | Testlastname | true        | jagan@test.com | +91-9843945628 | 2026-08-21 | 2026-08-22 | 43     |

  Scenario: Delete booking with valid id
    Given user sends Delete request to delete the booking details
    And verify response code should be 201
    Then verify the response message as "Booking deleted successfully"

  Scenario: Delete booking with invalid token
    Given user sends Delete request with Invalid token
    And verify response code should be 401
    Then verify response should contain the error message "Unauthorized"

  Scenario: Delete booking without token
    Given user sends Delete request without token
    And verify response code should be 401
    Then verify response should contain the error message "Unauthorized"
