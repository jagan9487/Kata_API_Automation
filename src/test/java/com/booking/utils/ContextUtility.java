package com.booking.utils;

import com.booking.models.Booking;
import io.restassured.response.Response;

public class ContextUtility {

    private Response response;
    private Booking booking;

    public Response getResponse(){
        return response;
    }

    public void setResponse(Response response){
        this.response= response;
    }

    public Booking getBooking(){
        return booking;
    }

    public void setBooking(Booking booking){
        this.booking = booking;
    }

}
