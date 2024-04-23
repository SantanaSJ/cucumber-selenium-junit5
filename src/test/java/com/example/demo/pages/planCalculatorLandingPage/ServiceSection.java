package com.example.demo.pages.planCalculatorLandingPage;

import com.example.demo.webDriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.example.demo.common.LocatorConstants.*;

public class ServiceSection {

    private final WebDriver driver = Driver.getDriver();
    private final By dropdown = By.xpath(DIV_CLASS_SELECTOR + "'MuiInputBase-root MuiOutlinedInput-root MuiInputBase-formControl']");
    private final By listbox = By.xpath("//ul[@role='listbox']");
    private final By metricButton = By.xpath(DIV_CLASS_SELECTOR + "'metric active'][text()='METRIC']");
    private final By imperialButton = By.xpath(DIV_CLASS_SELECTOR + "''][text()='IMPERIAL']");
    private final By xWayPulse = By.xpath(LI_CONTAINS_SELECTOR + "(.,'X Way Pulse')]");
    private final By xWayPulseAndTwin = By.xpath(LI_CONTAINS_SELECTOR + "(.,'X Way (Pulse + Twin)')]");
    private final By xWayPulseAndTwinAndNeural = By.xpath(LI_CONTAINS_SELECTOR + "(.,'X Way (Pulse + Twin + Neural)')]");

    public WebElement findServiceDropdown() {
        return driver.findElement(dropdown);
    }

    public WebElement accessDropdownOptions() {
        return driver.findElement(listbox);
    }

    public WebElement getMetric() {
        return driver.findElement(metricButton);
    }

    public WebElement getImperial() {
        return driver.findElement(imperialButton);
    }

    public WebElement selectService(String service) {
        return switch (service) {
            case "X Way Pulse" -> accessDropdownOptions().findElement(xWayPulse);
            case "X Way (Pulse + Twin)" -> accessDropdownOptions().findElement(xWayPulseAndTwin);
            case "X Way (Pulse + Twin + Neural)" -> accessDropdownOptions().findElement(xWayPulseAndTwinAndNeural);
            default -> null;
        };
    }
}
