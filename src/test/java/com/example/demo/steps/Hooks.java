package com.example.demo.steps;
import com.example.demo.webDriver.Driver;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;

public class Hooks {
    public static WebDriver driver;

    @Before
    public static void setup() {
        driver = Driver.getDriver();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
