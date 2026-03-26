Feature: Validate Get Booking API

  Background:
    Given booking is created with the following details
      | firstName     | lastName     | depositPaid | email          | phone          | checkIn    | checkOut   | roomId |
      | Testfirstname | Testlastname | true        | jagan@test.com | +91-9843945628 | 2026-08-21 | 2026-08-22 | 43     |

  Scenario: Get booking with valid id
    When user sends Get request to get the booking details
    Then verify response code should be 200
    And verify response should match the "BookingSchema" JSON schema
    And verify response data should match the input data for Getbooking api

  Scenario: Get booking with invalid token
    When user sends Get request with Invalid token
    Then verify response code should be 401
    And verify response should contain the error message "Unauthorized"

  Scenario: Get booking without token
    When user sends Get request without token
    Then verify response code should be 401
    And verify response should contain the error message "Unauthorized"