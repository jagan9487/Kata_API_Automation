package com.booking.steps;

import api.BookingAPI;
import dataMapper.BookingDataMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.Booking;
import org.junit.Assert;
import responseValidators.BookingResponseValidator;
import utils.ContextUtility;

import java.util.List;
import java.util.Map;

public class CreateBookingSteps {

    private final ContextUtility context;
    private Booking booking;
    public Response createBookingResponse;
    private final BookingAPI bookingAPI = new BookingAPI();

    public CreateBookingSteps(ContextUtility context) {
        this.context = context;
    }

    @Given("user provides then following booking details")
    public void user_enters_booking_details(io.cucumber.datatable.DataTable table) {
        Map<String, String> data = table.asMaps().getFirst();
        booking = BookingDataMapper.mapToBooking(data);
        context.setBooking(booking);
    }

    @When("user sends POST request to create a booking")
    public void user_sends_post_request() {
        createBookingResponse = bookingAPI.createBooking(booking);
        context.setResponse(createBookingResponse);
    }

    @Then("verify response data should match the input data for Createbooking api")
    public void validate_response_data() {
        BookingResponseValidator.validateCreateBookingResponse(createBookingResponse, booking);
    }

    @Then("verify response should contain the error message {string}")
    public void validate_error_message(String expectedMessage) {
        List<String> errors = createBookingResponse.jsonPath().getList("errors");
        boolean isPresent= errors.stream().anyMatch(e -> e.contains(expectedMessage));
        Assert.assertTrue("Expected error message not found", isPresent);

    }

}
