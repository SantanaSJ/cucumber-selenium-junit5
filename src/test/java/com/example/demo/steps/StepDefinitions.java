package com.example.demo.steps;
import com.example.demo.actions.CalculatorPageActions;
import com.example.demo.common.CalculatorLogic;
import com.example.demo.pages.planCalculatorLandingPage.ServiceSection;
import com.example.demo.pages.planCalculatorLandingPage.SuggestedPlanSection;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.*;

public class StepDefinitions {

    private final WebDriver driver = Hooks.driver;
    private String selectedServiceText;
    private final ServiceSection serviceSection;
    private final CalculatorPageActions pageActions;
    private final CalculatorLogic calculatorLogic;
    private final SuggestedPlanSection suggestedPlanSection;

    public StepDefinitions(ServiceSection serviceSection, CalculatorLogic calculatorLogic,
                           SuggestedPlanSection suggestedPlanSection, CalculatorPageActions pageActions) {
        this.calculatorLogic = calculatorLogic;
        this.serviceSection = serviceSection;
        this.suggestedPlanSection = suggestedPlanSection;
        this.pageActions = pageActions;
    }

    @Given("user is on pricing calculator page {string}")
    public void userIsOnPricingCalculatorPage(String url) {
        driver.get(url);
        driver.navigate().refresh();
    }

    @When("user sets road length slider value to {int}")
    public void userSetsRoadLengthSliderValueToRoadLength(int roadLength) {
        System.out.println("Road Length: " + roadLength);
        pageActions.setRoadLengthSliderValue(roadLength);
    }

    @And("user sets number of signalized intersections slider value to {int}")
    public void setNumberOfSignalizedIntersections(int intersections) {
        System.out.println("Signalized Intersections: " + intersections);
        pageActions.setNumberOfSignalizedIntersections(intersections);
    }

    @And("user selects {string} service from the dropdown menu")
    public void selectService(String service) {
        System.out.println("Service: " + service);
        serviceSection.findServiceDropdown().click();
        serviceSection.accessDropdownOptions();
        WebElement optionToSelect = serviceSection.selectService(service);
        selectedServiceText = optionToSelect.getText();
        optionToSelect.click();
    }

    @And("user sets measuring unit to {string}")
    public void setMeasuringUnit(String measuringUnit) {
        pageActions.setMeasuringUnit(measuringUnit);
    }

    @Then("the selected service should be {string}")
    public void verifySuggestedPlan(String service) {
        assertThat(service).isEqualTo(selectedServiceText);
    }

    @And("the measuring unit should be {string}")
    public void verifyMeasuringUnit(String expectedUnit) {
        String actualUnit = pageActions.getMeasuringUnit();
        assertThat(actualUnit).isEqualTo(expectedUnit);
    }

    @And("the expected price should match the suggested price")
    public void verifyPrice() {
        int expectedPrice = calculatorLogic.calculatePrice(selectedServiceText);
        int suggestedPrice = pageActions.getSuggestedPrice();
        assertThat(suggestedPrice).isEqualTo(expectedPrice);
    }

    @And("the actual plan should match the expected")
    public void theActualPlanShouldMatchTheExpected() {
        String expectedPlan = calculatorLogic.getExtendedPlan().toString();
        String suggestedPlan = suggestedPlanSection.getSuggestedPlanText();
        assertThat(suggestedPlan).isEqualTo(expectedPlan);
    }
}
