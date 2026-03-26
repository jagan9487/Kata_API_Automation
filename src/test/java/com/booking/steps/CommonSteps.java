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

    @Then("verify response code should be {int}")
    public void validate_status_code(int statusCode) {
        context.getResponse().then().statusCode(statusCode);
    }

    @Then("verify response should match the {string} JSON schema")
    public void validate_json_schema(String schemaName) {
        SchemaValidator.validateSchema(context.getResponse(), schemaName);
    }

    @Given("booking is created with the following details")
    public void user_enters_booking_details(io.cucumber.datatable.DataTable table) {
        Map<String, String> data = table.asMaps().getFirst();
        booking = BookingDataMapper.mapToBooking(data);
        context.setBooking(booking);
        createBookingResponse = bookingAPI.createBooking(booking);
        context.setResponse(createBookingResponse);
    }

    @Then("verify response should contain the error message {string}")
    public void validate_error_message(String expectedMessage) {
        List<String> errors = createBookingResponse.jsonPath().getList("errors");
        boolean isPresent= errors.stream().anyMatch(e -> e.contains(expectedMessage));
        Assert.assertTrue("Expected error message not found", isPresent);

    }
}
