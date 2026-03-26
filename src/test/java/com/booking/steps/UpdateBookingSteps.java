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

    @When("user sends Update request to modify the booking details")
    public void updateBooking() {
        token = authAPI.getToken();
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.updateBooking(bookingId,context.getBooking(),token);
        context.setResponse(response);
    }

    @When("user sends Update request without token")
    public void updateBooking_withoutToken() {
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.updateBooking(bookingId,context.getBooking()," ");
        context.setResponse(response);
    }

    @When("user sends Update request with Invalid token")
    public void updateBooking_invalidToken() {
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.updateBooking(bookingId,context.getBooking(),"-hh7y32");
        context.setResponse(response);
    }

    @Then("verify response data should match the input data for Updatebooking api")
    public void validate_response_data() {
        BookingResponseValidator.validateUpdateBookingResponse(response, context.getBooking());
    }
}
