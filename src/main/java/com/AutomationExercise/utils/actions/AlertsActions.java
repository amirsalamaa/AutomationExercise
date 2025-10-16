package com.AutomationExercise.utils.actions;

import com.AutomationExercise.utils.WaitManager;
import com.AutomationExercise.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;

public class AlertsActions {
    private final WebDriver driver;
    private   final WaitManager waitManager;

    public AlertsActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);

    }

    // Accepting alert
    public void acceptAlert() {
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().alert().accept();
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to accept alert: " + e.getMessage());
                return false;
            }
        });
    }
    // Dismissing alert
    public void dismissAlert() {
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().alert().dismiss();
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to dismiss alert: " + e.getMessage());
                return false;
            }
        });}
    // Getting alert text
    public String getAlertText() {
        return waitManager.fluentWait().until(d -> {
            try {
                String text= d.switchTo().alert().getText();
                return !text.isEmpty()?text:null;
            } catch (Exception e) {
                LogsManager.error("Failed to get alert text: " + e.getMessage());
                return null;
            }
        });}
    // Sending text to alert
    public void sendTextToAlert(String text) {
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().alert().sendKeys(text);
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to send text to alert: " + e.getMessage());
                return false;
            }
        });}


}
