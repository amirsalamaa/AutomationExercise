package com.AutomationExercise.drivers;
import com.AutomationExercise.utils.actions.AlertsActions;
import com.AutomationExercise.utils.actions.BrowserActions;
import com.AutomationExercise.utils.actions.ElementActions;
import com.AutomationExercise.utils.actions.FrameActions;
import com.AutomationExercise.utils.dataReader.PropertyReader;
import com.AutomationExercise.utils.logs.LogsManager;
import com.AutomationExercise.validations.Validation;
import com.AutomationExercise.validations.Verification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class GUIDriver {
    private final String browser = PropertyReader.getProperty("browserType");
    private  ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public GUIDriver()
    {

        Browser browserType = Browser.valueOf(browser.toUpperCase());
        LogsManager.info("Selected Browser: " + browserType);
        AbstractDriver abstractDriver = browserType.getDriverFactory();
        WebDriver driver = ThreadGuard.protect(abstractDriver.createDriver());
        driverThreadLocal.set(driver);
    }

    public ElementActions element(){
        return new ElementActions(getWebDriver());
    }
    public BrowserActions browser (){
        return new BrowserActions(getWebDriver());
    }
    public FrameActions frame(){
        return new FrameActions(getWebDriver());}
    public AlertsActions alerts (){
        return new AlertsActions(getWebDriver());
    }
    //soft assertions
    public Validation validate(){
        return new Validation(getWebDriver());
    }
    //hard assertions
    public Verification verify(){
        return new Verification(getWebDriver());
    }


    public WebDriver getWebDriver() {
        return driverThreadLocal.get();
    }

    public  void quitDriver() {
        driverThreadLocal.get().quit();
    }
}
