package com.AutomationExercise.utils.actions;

import com.AutomationExercise.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    private final WebDriver driver;

    public BrowserActions(WebDriver driver) {
        this.driver = driver;
    }

    // Method to get the current URL
    public String getCurrentUrl() {
      String url= driver.getCurrentUrl();
        LogsManager.info("Current URL: " + url);
        return url;
    }

    // Method to navigate to a specific URL
    public void navigateTo(String url) {
        driver.navigate().to(url);
        LogsManager.info("Navigated to URL: " + url);
    }

    // Method to refresh the current page
    public void refreshPage() {
        driver.navigate().refresh();
        LogsManager.info("Page refreshed");
    }

    // Method to navigate back in browser history
    public void navigateBack() {
        driver.navigate().back();
        LogsManager.info("Navigated back in browser history");
    }

    // Method to navigate forward in browser history
    public void navigateForward() {
        driver.navigate().forward();
        LogsManager.info("Navigated forward in browser history");
    }

    // Method to maximize the browser window
    public void maximizeWindow() {
        driver.manage().window().maximize();
        LogsManager.info("Browser window maximized");
    }

    // Method to get the page title
    public String getPageTitle() {
    LogsManager.info("Page title: " + driver.getTitle());
        return driver.getTitle();
    }

    // Method to closeCurrentWindow
    public void closeCurrentWindow() {
        driver.close();
    LogsManager.info("Current window closed");}

    //open new window
    public void openNewWindow() {
        driver.switchTo().newWindow(org.openqa.selenium.WindowType.WINDOW);
        LogsManager.info("New window opened");
    }



}
