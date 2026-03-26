Feature: Validate Create Booking API

  Scenario: Verify user is able to create the booking with valid data
    Given user provides then following booking details
      | firstName     | lastName     | depositPaid | email          | phone           | checkIn    | checkOut   | roomId |
      | Testfirstname | Testlastname | true        | jagan@test.com | +1-500-673-1886 | 2026-08-21 | 2026-08-22 | 43    |
    When user sends POST request to create a booking
    Then verify response code should be 200
    And verify response should match the "BookingSchema" JSON schema
    And verify response data should match the input data for Createbooking api