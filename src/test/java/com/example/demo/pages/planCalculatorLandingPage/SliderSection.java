package com.example.demo.pages.planCalculatorLandingPage;

import com.example.demo.browser.BrowserException;
import com.example.demo.webDriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.example.demo.common.LocatorConstants.*;

public class SliderSection {

    private final WebDriver driver = Driver.getDriver();
    By roadLengthSlider = By.xpath(ROOT_DIV_SELECTOR + "/div[1]/div[1]/div/div[1]/span/span[2]");
    By intersectionSlider = By.xpath(ROOT_DIV_SELECTOR + "/div[1]/div[1]/div/div[2]/span/span[2]");
    By intersectionInput = By.xpath(INPUT_TYPE_SELECTOR + "[2]");
    By roadLengthInput = By.xpath(INPUT_TYPE_SELECTOR + "[1]");

    public SliderSection() throws BrowserException {
    }


    //    RL Slider
    public WebElement getRoadLengthSliderElement() {
        return driver.findElement(roadLengthSlider);
    }

    //    NSI Slider
    public WebElement getNumberOfSignalizedIntersectionsSliderElement() {
        return driver.findElement(intersectionSlider);
    }

    public WebElement getIntersectionsInputElement() {
        return driver.findElement(intersectionInput);
    }

    public WebElement getRoadLengthInputElement() {
        return driver.findElement(roadLengthInput);
    }


}
