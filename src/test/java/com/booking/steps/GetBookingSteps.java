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

    @When("the user requests the booking details with valid data")
    public void getBooking() {
        token = authAPI.getToken();
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.getBooking(bookingId, token);
    }

    @Then("the booking details should be returned successfully")
    public void validate_status_code() {
        context.getResponse().then().statusCode(200);
    }

    @When("the user attempts to retrieve booking details with invalid authentication")
    public void getBooking_Token() {
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.getBooking(bookingId, "-sad1213");
    }

    @When("the user attempts to retrieve booking details with Non-existent booking id")
    public void getBooking_invalidId() {
        response = bookingAPI.getBooking(-1313, token);
    }

    @When("user sends Get request without token")
    public void getBooking_withoutToken() {
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.getBooking(bookingId, "");
    }

    @Then("the booking information should match the stored data")
    public void validate_response_data() {
        BookingResponseValidator.validateGetBookingResponse(response, context.getBooking());
    }
}