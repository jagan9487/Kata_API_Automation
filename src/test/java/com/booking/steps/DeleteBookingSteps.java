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

    @When("user sends Delete request to delete the booking details")
    public void deleteBooking() {
        token = authAPI.getToken();
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.deleteBooking(bookingId, token);
    }

    @When("user sends Delete request without token")
    public void deleteBooking_withoutToken() {
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.deleteBooking(bookingId, "");
    }

    @When("user sends Delete request with Invalid token")
    public void deleteBooking_invalidToken() {
        Response createResponse= context.getResponse();
        bookingId = createResponse.jsonPath().getInt("bookingid");
        response = bookingAPI.deleteBooking(bookingId, "-sad1213");
    }

    @Then("verify the response message as {string}")
    public void validateMessage(String message) {
        Assert.assertTrue(response.asString().contains(message));
    }
}
