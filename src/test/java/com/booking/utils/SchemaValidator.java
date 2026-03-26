package com.booking.utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class SchemaValidator {

    public static void validateSchema(Response response, String schemaName) {

        response.then().assertThat().body(
                JsonSchemaValidator.matchesJsonSchemaInClasspath(
                        "schemas/" + schemaName + ".json"
                )
        );
    }
}
