package com.booking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private Integer roomid;
    private String firstname;
    private String lastname;
    private Boolean depositpaid;
    private BookingDates bookingdates;
    private String email;
    private String phone;
}