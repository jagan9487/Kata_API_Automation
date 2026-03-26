package com.booking.runners;

import io.cucumber.junit.CucumberOptions;

    @CucumberOptions(
            features = "src/test/resources/features",
            glue = "steps",
            plugin = {"pretty", "html:target/cucumber-report.html"}
    )
    public class TestRunner{
    }

