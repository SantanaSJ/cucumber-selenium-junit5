package com.example.demo.pages.planCalculatorLandingPage;

import com.example.demo.actions.CalculatorPageActions;
import com.example.demo.browser.BrowserException;
import com.example.demo.webDriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.example.demo.common.LocatorConstants.*;

public class SliderSection {

    private final WebDriver driver = Driver.getDriver();
    private CalculatorPageActions actions;
    private final By roadLengthSlider = By.xpath(ROOT_DIV_SELECTOR + "/div[1]/div[1]/div/div[1]/span/span[2]");
    private final By intersectionSlider = By.xpath(ROOT_DIV_SELECTOR + "/div[1]/div[1]/div/div[2]/span/span[2]");
    private final By intersectionInput = By.xpath(INPUT_TYPE_SELECTOR + "[2]");
    private final By roadLengthInput = By.xpath(INPUT_TYPE_SELECTOR + "[1]");

    public SliderSection() throws BrowserException {
    }

    public void setPageActions(CalculatorPageActions actions) {
        this.actions = actions;
    }

    //    RL Slider
    public WebElement getRoadLengthSliderElement() {
        return driver.findElement(roadLengthSlider);
    }

    //    NSI Slider
    public WebElement getNumberOfSignalizedIntersectionsSliderElement() {
        return driver.findElement(intersectionSlider);
    }

    public void setSliderValue(WebElement slider, int value) {
        actions.setSliderValue(slider, value);
    }

    public WebElement getIntersectionsInputElement() {
        return driver.findElement(intersectionInput);
    }

    public WebElement getRoadLengthInputElement() {
        return driver.findElement(roadLengthInput);
    }


}
