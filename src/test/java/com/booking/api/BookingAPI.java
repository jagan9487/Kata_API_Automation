package com.booking.api;

import io.restassured.response.Response;
import com.booking.models.Booking;
import com.booking.utils.CommonUtility;
import com.booking.utils.ConfigManager;

import java.util.Map;

public class BookingAPI {

    public Response createBooking(Booking booking) {
        return CommonUtility.post(
                ConfigManager.get("base.url") + "/booking",
                booking,
                Map.of("Content-Type", "application/json")
        );
    }

    public Response getBooking(int id, String token) {
        return CommonUtility.get(
                ConfigManager.get("base.url") + "/booking/" + id,
                Map.of("Cookie", "token=" + token)
        );
    }

    public Response updateBooking(int id, Booking booking, String token) {
        return CommonUtility.put(
                ConfigManager.get("base.url") + "/booking/" + id,
                booking,
                Map.of("Cookie", "token=" + token)
        );
    }

    public Response deleteBooking(int id, String token) {
        return CommonUtility.delete(
                ConfigManager.get("base.url") + "/booking/" + id,
                Map.of("Cookie", "token=" + token)
        );
    }
}
