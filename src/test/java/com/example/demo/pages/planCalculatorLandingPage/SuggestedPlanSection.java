package com.example.demo.pages.planCalculatorLandingPage;
import com.example.demo.browser.BrowserException;
import com.example.demo.webDriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static com.example.demo.common.LocatorConstants.DIV_CLASS_SELECTOR;


public class SuggestedPlanSection {

    private final WebDriver driver = Driver.getDriver();
    private final By suggestedPlan = By.xpath(DIV_CLASS_SELECTOR + "'PlanPricing_suggestedPlan-planName__31ZTG']");
    private final By suggestedPrice = By.cssSelector("[data-testid='plan-price-line-1']");

    public SuggestedPlanSection() throws BrowserException {
    }


    public WebElement getSuggestedPlanElement() {
        return driver.findElement(suggestedPlan);
    }

    public WebElement getSuggestedSaaSPriceElement() {
        return driver.findElement(suggestedPrice);
    }

    public String getSuggestedPlanText() {
        return getSuggestedPlanElement().getText();
    }


}
