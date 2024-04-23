package com.example.demo.actions;

import com.example.demo.pages.planCalculatorLandingPage.ServiceSection;
import com.example.demo.pages.planCalculatorLandingPage.SliderSection;
import com.example.demo.pages.planCalculatorLandingPage.SuggestedPlanSection;
import com.example.demo.webDriver.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CalculatorPageActions {

    WebDriver driver = Driver.getDriver();
    private final ServiceSection serviceSection;
    private String measuringUnit;
    private final Actions actions;
    private final SliderSection sliderSection;
    private final SuggestedPlanSection suggestedPlanSection;

    public CalculatorPageActions(ServiceSection serviceSection, SliderSection sliderSection, SuggestedPlanSection suggestedPlanSection) {
        this.serviceSection = serviceSection;
        this.actions = new Actions(driver);
        this.sliderSection = sliderSection;
        this.suggestedPlanSection = suggestedPlanSection;
    }

    public void setMeasuringUnit(String unit) {
        WebElement element;
        if ("METRIC".equals(unit)) {
            element = serviceSection.getMetric();
        } else {
            element = serviceSection.getImperial();
        }
        element.click();
        measuringUnit = element.getText();
        System.out.println(measuringUnit);
    }

    public String getMeasuringUnit() {
        return measuringUnit;
    }

    public void setRoadLengthSliderValue(int value) {
        WebElement slider = sliderSection.getRoadLengthSliderElement();
        actions.click(slider);
        for (int i = 1; i < value; i++) {
            actions.sendKeys(Keys.ARROW_RIGHT).release().build().perform();
        }
    }

    public void setNumberOfSignalizedIntersections(int value) {
        WebElement slider = sliderSection.getNumberOfSignalizedIntersectionsSliderElement();
        actions.click(slider);
        for (int i = 1; i < value; i++) {
            actions.sendKeys(Keys.ARROW_RIGHT).release().build().perform();
        }
    }

    public int getSuggestedPrice() {
        String priceText = suggestedPlanSection.getSuggestedSaaSPriceElement().getText();
        String price = priceText.replaceAll("[^0-9]", "");
        return Integer.parseInt(price);
    }
}
