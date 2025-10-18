package com.AutomationExercise.tests;

import com.AutomationExercise.drivers.GUIDriver;
import com.AutomationExercise.drivers.WebDriverProvider;
import com.AutomationExercise.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;

public class BaseTest implements WebDriverProvider {
    protected GUIDriver driver;
    protected JsonReader testData;

    public void beforeClass() {
    testData =new JsonReader("");
    }

    @Override
    public WebDriver getWebDriver() {
        return driver.getWebDriver();
    }
}
