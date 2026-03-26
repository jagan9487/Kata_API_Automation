package com.booking.dataMapper;

import com.booking.models.Booking;
import com.booking.models.BookingDates;

import java.util.Map;

public class BookingDataMapper {

           public static Booking mapToBooking(Map<String, String> data) {

            return new Booking(
                    parseInteger(data.get("roomId")),
                    getValue(data.get("firstName")),
                    getValue(data.get("lastName")),
                    parseBoolean(data.get("depositPaid")),
                    new BookingDates(
                            getValue(data.get("checkIn")),
                            getValue(data.get("checkOut"))
                    ),
                    getValue(data.get("email")),
                    getValue(data.get("phone"))
            );
        }

        private static String getValue(String value) {
            return (value == null || value.trim().isEmpty()) ? null : value;
        }

        private static Integer parseInteger(String value) {
            return (value == null || value.trim().isEmpty())
                    ? null
                    : Integer.valueOf(value);
        }

        private static Boolean parseBoolean(String value) {
            return (value == null || value.trim().isEmpty())
                    ? null
                    : Boolean.valueOf(value);
        }
    }