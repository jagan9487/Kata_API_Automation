package com.booking.api;

import io.restassured.response.Response;
import com.booking.models.AuthRequest;
import com.booking.utils.CommonUtility;
import com.booking.utils.ConfigManager;

import java.util.Map;

public class AuthAPI {

    public String getToken() {
        AuthRequest request = new AuthRequest(
                ConfigManager.get("username"),
                ConfigManager.get("password")
        );

        Response response = CommonUtility.post(
                ConfigManager.get("base.url") + "/auth/login",
                request,
                Map.of("Content-Type", "application/json")
        );

        return response.jsonPath().getString("token");
    }
}
