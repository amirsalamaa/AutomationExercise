package com.AutomationExercise.drivers;
import com.AutomationExercise.utils.dataReader.PropertyReader;
import com.AutomationExercise.utils.logs.LogsManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;

public class FirefoxFactory extends AbstractDriver {
    private FirefoxOptions getOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--start-maximized");
        if(PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless") ||
                PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote"))
        {
            options.addArguments("--headless");
        }
        return options;
    }

    @Override
    public WebDriver createDriver() {
        if(PropertyReader.getProperty("executionType").equalsIgnoreCase("Local") ||
                PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless"))
        {
            return new FirefoxDriver(getOptions());
        }else if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote"))
        {
            try{
                return new RemoteWebDriver(
                        new URI("http://" + remoteHost + ":"+ remotePort+ "/wd/hub").toURL(),
                        getOptions());
            }catch (Exception e)
            {
                LogsManager.error("Error while creating RemoteWebDriver: " + e.getMessage());
                throw new RuntimeException("Failed to create remoteWebDriver",e);
            }


        }
        else
        {
            LogsManager.error("Invalid execution type for Chrome: " + PropertyReader.getProperty("executionType"));
            throw new IllegalArgumentException("Invalid execution type for Chrome: " + PropertyReader.getProperty("executionType"));
        }
    }
}
