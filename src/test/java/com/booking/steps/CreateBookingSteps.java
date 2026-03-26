package com.booking.steps;

import com.booking.api.BookingAPI;
import com.booking.dataMapper.BookingDataMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import com.booking.models.Booking;
import org.junit.Assert;
import com.booking.responseValidators.BookingResponseValidator;
import com.booking.utils.ContextUtility;

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

    @Given("user provides the following booking details")
    public void user_enters_booking_details(io.cucumber.datatable.DataTable table) {
        Map<String, String> data = table.asMaps().getFirst();
        booking = BookingDataMapper.mapToBooking(data);
        context.setBooking(booking);
    }

    @Then("the booking should be created successfully")
    public void validate_success_status_code() {
        context.getResponse().then().statusCode(200);
    }

    @Then("the booking should not be created")
    public void validate_invalid_request_status_code() {
        context.getResponse().then().statusCode(400);
    }


    @When("the user submits the booking request")
    public void user_sends_post_request() {
        createBookingResponse = bookingAPI.createBooking(booking);
        context.setResponse(createBookingResponse);
    }

    @Then("the user should receives the booking details successfully")
    public void validate_response_data() {
        BookingResponseValidator.validateCreateBookingResponse(createBookingResponse, booking);
    }


}
