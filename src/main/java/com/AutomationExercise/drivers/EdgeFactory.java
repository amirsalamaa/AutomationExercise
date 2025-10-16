package com.AutomationExercise.drivers;
import com.AutomationExercise.utils.dataReader.PropertyReader;
import com.AutomationExercise.utils.logs.LogsManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;

public class EdgeFactory extends AbstractDriver {

    private EdgeOptions getOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--start-maximized");
        if(PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless") ||
                PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote"))
        {
            options.addArguments("--headless");
        }
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return options;
    }

    @Override
    public WebDriver createDriver() {
        if(PropertyReader.getProperty("executionType").equalsIgnoreCase("Local") ||
                PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless"))
        {
            return new EdgeDriver(getOptions());
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
