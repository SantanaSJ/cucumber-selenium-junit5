package com.example.demo.common;
import com.example.demo.pages.planCalculatorLandingPage.SliderSection;
import org.openqa.selenium.WebElement;
import java.util.HashMap;
import java.util.Map;
import static com.example.demo.common.Constants.*;

public class CalculatorLogic {

    private Map<String, Integer> planRate;
    private int XWUFactor;
    private String tier;
    private int unitsIncluded;
    private String plan;
    private int unitRate;
    private final SliderSection sliderSection;

    public CalculatorLogic(SliderSection sliderSection) {
        this.sliderSection = sliderSection;
        setFlatRate();
    }

    private double calculateTrafficComplexity(String roadLength, String intersections) {
        return WEIGHT_LENGTH * Integer.parseInt(roadLength) + NUMBER_OF_SIGNALIZED_INTERSECTIONS * Integer.parseInt(intersections);
    }

    protected void calculateXWUFactor(String roadLength, String intersections) {
        System.out.println("Road Length: " + roadLength);
        System.out.println("Signalized Intersections: " + intersections);
        XWUFactor = (int) Math.ceil(XWUREF * calculateTrafficComplexity(roadLength, intersections));
        System.out.println("XWUFactor " + XWUFactor);
    }

    private void identifyTier() {
        if (XWUFactor >= T_MIN_STARTER && XWUFactor <= T_MAX_STARTER) {
            tier = "Starter";
        } else if (XWUFactor >= T_MIN_STANDARD && XWUFactor <= T_MAX_STANDARD) {
            tier = "Standard";
        } else if (XWUFactor >= T_MIN_PRO && XWUFactor <= T_MAX_PRO) {
            tier = "Pro";
        }
    }

    private void identifyPlan(String service) {
        identifyTier();
        switch (tier) {
            case "Starter" -> unitsIncluded = 14;
            case "Standard" -> unitsIncluded = 22;
            case "Pro" -> unitsIncluded = 50;
        }
        plan = service + " " + tier;
    }

    private void setUnitRate() {
        if ("X Way Pulse Starter".equals(plan)) {
            unitRate = 70;
        } else if ("X Way Pulse Standard".equals(plan)) {
            unitRate = 65;
        } else if ("X Way Pulse Pro".equals(plan)) {
            unitRate = 60;
        } else if ("X Way (Pulse + Twin) Starter".equals(plan)) {
            unitRate = 90;
        } else if ("X Way (Pulse + Twin) Standard".equals(plan)) {
            unitRate = 80;
        } else if ("X Way (Pulse + Twin) Pro".equals(plan)) {
            unitRate = 75;
        } else if ("X Way (Pulse + Twin + Neural) Starter".equals(plan)) {
            unitRate = 120;
        } else if ("X Way (Pulse + Twin + Neural) Standard".equals(plan)) {
            unitRate = 115;
        } else if ("X Way (Pulse + Twin + Neural) Pro".equals(plan)) {
            unitRate = 105;
        }
    }

    private void setFlatRate() {
        planRate = new HashMap<>();
        planRate.put("X Way Pulse Starter", 980);
        planRate.put("X Way Pulse Standard", 1430);
        planRate.put("X Way Pulse Pro", 3000);
        planRate.put("X Way (Pulse + Twin) Starter", 1260);
        planRate.put("X Way (Pulse + Twin) Standard", 1760);
        planRate.put("X Way (Pulse + Twin) Pro", 3750);
        planRate.put("X Way (Pulse + Twin + Neural) Starter", 1680);
        planRate.put("X Way (Pulse + Twin + Neural) Standard", 2530);
        planRate.put("X Way (Pulse + Twin + Neural) Pro", 5250);
    }

    public int calculatePrice(String service) {

        collectSliderParameters();
        identifyPlan(service);
        setUnitRate();

        int price = 0;

        for (Map.Entry<String, Integer> entry : planRate.entrySet()) {
            if (entry.getKey().equals(plan)) {
                price = entry.getValue();
                break;
            }
        }

        if (XWUFactor > unitsIncluded) {
            price = (price + (XWUFactor - unitsIncluded) * unitRate);
            System.out.println("price " + price);
        }
        return price;
    }

    public StringBuilder getExtendedPlan() {
        StringBuilder sb = new StringBuilder();

        int xWayUnits = getXWayUnits();
        System.out.println("XWay Units" + xWayUnits);

        if (xWayUnits < 1) {
            sb.append(plan);
        } else {
            sb
                    .append(plan)
                    .append(" ")
                    .append("+")
                    .append(" ")
                    .append(xWayUnits)
                    .append(" ")
                    .append("X Way Units");
        }
        return sb;
    }

    private int getXWayUnits() {
        return XWUFactor - unitsIncluded;
    }

    private WebElement getRoadLengthElement() {
        return sliderSection.getRoadLengthInputElement();
    }

    private WebElement getIntersectionsElement() {
        return sliderSection.getIntersectionsInputElement();
    }

    private void collectSliderParameters() {
        String valueRoadLength = getRoadLengthElement().getAttribute("value");
        String valueIntersections = getIntersectionsElement().getAttribute("value");
        calculateXWUFactor(valueRoadLength, valueIntersections);
    }


}
