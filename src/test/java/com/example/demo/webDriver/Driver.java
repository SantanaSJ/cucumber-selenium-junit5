package com.example.demo.webDriver;
import com.example.demo.browser.BrowserException;
import com.example.demo.browser.Chrome;
import com.example.demo.browser.Edge;
import com.example.demo.browser.Firefox;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class Driver {

    private static WebDriver driver;

    public synchronized static WebDriver getDriver() throws BrowserException {
        if (driver == null) {
            String browser = ConfigurationReader.getProperty("browser");
            switch (browser.trim()) {
                case "chrome" -> driver = new Chrome().createInstance();
                case "edge" -> driver = new Edge().createInstance();
                case "firefox" -> driver = new Firefox().createInstance();
                default -> throw new BrowserException(browser + " is not supported");
            }
//            driver = WebDriverManager.chromedriver().create();
            driver.manage().window().maximize();
        }
        return driver;
    }
}
