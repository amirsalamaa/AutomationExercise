package com.AutomationExercise.utils.actions;

import com.AutomationExercise.utils.WaitManager;
import com.AutomationExercise.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class ElementActions {
    private final WebDriver driver;
    private WaitManager waitManager ;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }

    //Clicking
    public void click(By locator) {
        waitManager.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJs(locator);
                        element.click();
                        LogsManager.info("Clicked on element: " + locator);
                        return true;
                    } catch (Exception e) {
                        LogsManager.error("Failed to click on element: " + locator + " - " + e.getMessage());
                        return false;
                    }
                }
        );
    }

    //Typing
    public void type(By locator, String text) {
        waitManager.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJs(locator);
                        element.clear();
                        LogsManager.info("Typing text: '" + text + "' into element: " + locator);
                        element.sendKeys(text);
                        LogsManager.info("Typed text: '" + text + "' into element: " + locator);
                        return true;
                    } catch (Exception e) {
                        LogsManager.error("Failed to type text: '" + text + "' into element: " + locator + " - " + e.getMessage());
                        return false;
                    }
                }
        );
    }

    //Getting text
    public String getText(By locator) {
        return waitManager.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJs(locator);
                        String msg = element.getText();
                        LogsManager.info("Retrieved text: '" + msg + "' from element: " + locator);
                        return !msg.isEmpty() ? msg : null;
                    } catch (Exception e) {
                        LogsManager.error("Failed to retrieve text from element: " + locator + " - " + e.getMessage());
                        return null;
                    }
                }
        );
    }

    //upload file
    public void uploadFile(By locator, String filePath){
        String absolutePath = System.getProperty("user.dir") + File.separator+ filePath;
        waitManager.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJs(locator);
                        element.sendKeys(absolutePath);
                        LogsManager.info("Uploaded file: '" + absolutePath + "' using element: " + locator);
                        return true;
                    } catch (Exception e) {
                        LogsManager.error("Failed to upload file: '" + absolutePath + "' using element: " + locator + " - " + e.getMessage());
                        return false;
                    }
                }
        );
    }

    //scrolling to element using js
    public void scrollToElementJs(By locator) {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("""
                arguments[0].scrollIntoView({behaviour:"auto",block:"center",inline:"center"});""", findElement(locator));
        LogsManager.info("Scrolled to element: " + locator);
    }
    //select from dropdown
    public ElementActions selectFromDropdown(By locator, String value) {
        waitManager.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJs(locator);
                        Select select = new Select(element);
                        select.selectByVisibleText(value);
                        LogsManager.info("Selected value '" + value + "' from dropdown: " + locator);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
        return this;
    }

    //find element
    public WebElement findElement(By locator) {

        return driver.findElement(locator);}
}
