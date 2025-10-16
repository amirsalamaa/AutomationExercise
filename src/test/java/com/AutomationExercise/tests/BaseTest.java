package com.AutomationExercise.tests;

import com.AutomationExercise.drivers.GUIDriver;
import com.AutomationExercise.drivers.WebDriverProvider;
import org.openqa.selenium.WebDriver;

public class BaseTest implements WebDriverProvider {
    protected GUIDriver driver;

    @Override
    public WebDriver getWebDriver() {
        return driver.getWebDriver();
    }
}
