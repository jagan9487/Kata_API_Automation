package com.booking.steps;

import io.cucumber.java.en.Then;
import com.booking.utils.ContextUtility;
import com.booking.utils.SchemaValidator;

public class CommonSteps {
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
}
