package com.booking.steps;

import com.booking.api.AuthAPI;
import com.booking.api.BookingAPI;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import com.booking.responseValidators.BookingResponseValidator;
import com.booking.utils.ContextUtility;

public class GetBookingSteps {

    private final ContextUtility context;
    BookingAPI bookingAPI = new BookingAPI();
    AuthAPI authAPI = new AuthAPI();

    Response response;
    int bookingId;
    String token;

    public GetBookingSteps(ContextUtility context) {
        this.context = context;
    }

    @When("user sends Get request to get the booking details")
    public void getBooking() {
        token = authAPI.getToken();
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.getBooking(bookingId, token);
    }

    @When("user sends Get request with Invalid token")
    public void getBooking_Token() {
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.getBooking(bookingId, "-sad1213");
    }

    @When("user sends Get request without token")
    public void getBooking_withoutToken() {
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.getBooking(bookingId, "");
    }

    @Then("verify response data should match the input data for Getbooking api")
    public void validate_response_data() {
        BookingResponseValidator.validateGetBookingResponse(response, context.getBooking());
    }
}