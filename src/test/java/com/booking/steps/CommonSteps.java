package com.booking.steps;

import com.booking.api.BookingAPI;
import com.booking.dataMapper.BookingDataMapper;
import com.booking.models.Booking;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import com.booking.utils.ContextUtility;
import com.booking.utils.SchemaValidator;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class CommonSteps {
    private Booking booking;
    public Response createBookingResponse;
    private final BookingAPI bookingAPI = new BookingAPI();
    private final ContextUtility context;

    public CommonSteps(ContextUtility context) {
        this.context = context;
    }

    @Then("the system should deny request")
    public void validate_invalid_request_status_code() {
        context.getResponse().then().statusCode(400);
    }

    @Then("the booking response should follow the expected structure")
    public void validate_json_schema() {
        SchemaValidator.validateSchema(context.getResponse(), "BookingSchema");
    }

    @Given("booking is created with the following details")
    public void user_enters_booking_details(io.cucumber.datatable.DataTable table) {
        Map<String, String> data = table.asMaps().getFirst();
        booking = BookingDataMapper.mapToBooking(data);
        context.setBooking(booking);
        createBookingResponse = bookingAPI.createBooking(booking);
        context.setResponse(createBookingResponse);
    }

    @Then("the user should get the error message {string}")
    public void validate_error_message(String expectedMessage) {
        List<String> errors = createBookingResponse.jsonPath().getList("errors");
        boolean isPresent= errors.stream().anyMatch(e -> e.contains(expectedMessage));
        Assert.assertTrue("Expected error message not found", isPresent);

    }
}
