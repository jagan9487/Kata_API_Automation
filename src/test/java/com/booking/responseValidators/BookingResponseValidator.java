package com.booking.responseValidators;

import io.restassured.response.Response;
import com.booking.models.Booking;
import org.junit.Assert;

public class BookingResponseValidator {

        public static void validateCreateBookingResponse(Response response, Booking expected) {

            // Extract response data
            var json = response.jsonPath();

            Assert.assertEquals(json.get("booking.roomid"), expected.getRoomid());
            Assert.assertEquals(json.getString("booking.firstname"), expected.getFirstname());
            Assert.assertEquals(json.getString("booking.lastname"), expected.getLastname());
            Assert.assertEquals(json.getBoolean("booking.depositpaid"), expected.getDepositpaid());
            Assert.assertEquals(json.getString("booking.email"), expected.getEmail());
            Assert.assertEquals(json.getString("booking.phone"), expected.getPhone());

            // Nested object validation
            Assert.assertEquals(
                    json.getString("booking.bookingdates.checkin"),
                    expected.getBookingdates().getCheckin()
            );

            Assert.assertEquals(
                    json.getString("booking.bookingdates.checkout"),
                    expected.getBookingdates().getCheckout()
            );
        }

    public static void validateGetBookingResponse(Response response, Booking expected) {

        // Extract response data
        var json = response.jsonPath();

        Assert.assertEquals(json.get("roomid"), expected.getRoomid());
        Assert.assertEquals(json.getString("firstname"), expected.getFirstname());
        Assert.assertEquals(json.getString("lastname"), expected.getLastname());
        Assert.assertEquals(json.getBoolean("depositpaid"), expected.getDepositpaid());
        Assert.assertEquals(json.getString("email"), expected.getEmail());
        Assert.assertEquals(json.getString("phone"), expected.getPhone());

        // Nested object validation
        Assert.assertEquals(
                json.getString("bookingdates.checkin"),
                expected.getBookingdates().getCheckin()
        );

        Assert.assertEquals(
                json.getString("bookingdates.checkout"),
                expected.getBookingdates().getCheckout()
        );
    }

    public static void validateUpdateBookingResponse(Response response, Booking expected) {

        // Extract response data
        var json = response.jsonPath();

        Assert.assertEquals(json.get("roomid"), expected.getRoomid());
        Assert.assertEquals(json.getString("firstname"), expected.getFirstname());
        Assert.assertEquals(json.getString("lastname"), expected.getLastname());
        Assert.assertEquals(json.getBoolean("depositpaid"), expected.getDepositpaid());
        Assert.assertEquals(json.getString("email"), expected.getEmail());
        Assert.assertEquals(json.getString("phone"), expected.getPhone());

        // Nested object validation
        Assert.assertEquals(
                json.getString("bookingdates.checkin"),
                expected.getBookingdates().getCheckin()
        );

        Assert.assertEquals(
                json.getString("bookingdates.checkout"),
                expected.getBookingdates().getCheckout()
        );
    }
}
