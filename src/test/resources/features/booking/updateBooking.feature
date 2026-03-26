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