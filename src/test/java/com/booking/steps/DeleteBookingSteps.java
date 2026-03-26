package com.booking.steps;

import com.booking.api.AuthAPI;
import com.booking.api.BookingAPI;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import com.booking.utils.ContextUtility;

public class DeleteBookingSteps {

    private final ContextUtility context;
    BookingAPI bookingAPI = new BookingAPI();
    AuthAPI authAPI = new AuthAPI();

    Response response;
    int bookingId;
    String token;

    public DeleteBookingSteps(ContextUtility context) {
        this.context = context;
    }

    @When("the user requests to delete the booking")
    public void deleteBooking() {
        token = authAPI.getToken();
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.deleteBooking(bookingId, token);
    }

    @When("the user attempts to delete the booking without authentication")
    public void deleteBooking_withoutToken() {
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.deleteBooking(bookingId, "");
    }

    @Then("the booking should be deleted successfully")
    public void validate_status_code() {
        context.getResponse().then().statusCode(201);
    }

    @When("the user attempts to delete the booking with invalid authentication")
    public void deleteBooking_invalidToken() {
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.deleteBooking(bookingId, "-sad1213");
    }

    @Then("the user should get message as {string}")
    public void validateMessage(String message) {
        Assert.assertTrue(response.asString().contains(message));
    }
}
