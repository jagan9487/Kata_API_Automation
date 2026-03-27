package com.booking.steps;

import com.booking.api.AuthAPI;
import com.booking.api.BookingAPI;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import com.booking.responseValidators.BookingResponseValidator;
import com.booking.utils.ContextUtility;

public class UpdateBookingSteps {

    private final ContextUtility context;
    AuthAPI authAPI = new AuthAPI();
    private final BookingAPI bookingAPI = new BookingAPI();

    Response response;
    int bookingId;
    String token;

    public UpdateBookingSteps(ContextUtility context) {
        this.context = context;
    }

    @When("the user submits the update request")
    public void updateBooking() {
        token = authAPI.getToken();
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.updateBooking(bookingId,context.getBooking(),token);
        context.setResponse(response);
    }

    @Then("the booking should be updated successfully")
    public void validate_status_code() {
        context.getResponse().then().statusCode(200);
    }

    @When("user sends Update request without token")
    public void updateBooking_withoutToken() {
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.updateBooking(bookingId,context.getBooking()," ");
        context.setResponse(response);
    }

    @Then("the booking should not be updated")
    public void validate_invalid_request_status_code() {
        context.getResponse().then().statusCode(400);
    }

    @When("the user attempts to update the booking with invalid authentication")
    public void updateBooking_invalidToken() {
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.updateBooking(bookingId,context.getBooking(),"-hh7y32");
        context.setResponse(response);
    }

    @When("the user attempts to update the booking with Non-existent booking id")
    public void updateBooking_invalidId() {
        response = bookingAPI.updateBooking(-121,context.getBooking(),token);
        context.setResponse(response);
    }

    @Then("the user should receives the updated booking details successfully")
    public void validate_response_data() {
        BookingResponseValidator.validateUpdateBookingResponse(response, context.getBooking());
    }
}
