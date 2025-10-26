package com.AutomationExercise.tests;

import com.AutomationExercise.drivers.GUIDriver;
import com.AutomationExercise.drivers.WebDriverProvider;
import com.AutomationExercise.utils.dataReader.JsonReader;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

public class BaseTest implements WebDriverProvider {
    protected GUIDriver driver;
    protected JsonReader testData;


    @Override
    public WebDriver getWebDriver() {
        return driver.getWebDriver();
    }
}
