package com.booking.utils;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CommonUtility {
    public static Response post(String endpoint, Object body, Map<String, String> headers) {
        return given().headers(headers).body(body).when().post(endpoint);
    }

    public static Response get(String endpoint, Map<String, String> headers) {
        return given().headers(headers).when().get(endpoint);
    }

    public static Response put(String endpoint, Object body, Map<String, String> headers) {
        return given().headers(headers).body(body).when().put(endpoint);
    }

    public static Response delete(String endpoint, Map<String, String> headers) {
        return given().headers(headers).when().delete(endpoint);
    }
}
