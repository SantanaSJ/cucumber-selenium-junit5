package com.example.demo.webDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class Driver {

    private static WebDriver driver;

    public synchronized static WebDriver getDriver() {
        if (driver == null) {
            driver = WebDriverManager.chromedriver().create();
            driver.manage().window().maximize();
        }
        return driver;
    }
}
