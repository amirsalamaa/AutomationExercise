package com.AutomationExercise.utils.actions;

import com.AutomationExercise.utils.WaitManager;
import com.AutomationExercise.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FrameActions   {
    private final WaitManager waitManager;
    private final WebDriver driver;

    public FrameActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }
    // Switch to frame by index
    public void switchToFrameByIndex(int index) {
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().frame(index);
                LogsManager.info("Switched to frame with index: " + index);
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to switch to frame with index: " + index + " - " + e.getMessage());
                return false;
            }
        });}

    // Switch to frame by name or ID
    public void switchToFrameByNameOrId(String nameOrId) {
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().frame(nameOrId);
                LogsManager.info("Switched to frame with name or ID: " + nameOrId);
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to switch to frame with name or ID: " + nameOrId + " - " + e.getMessage());
                return false;
            }
        });}

    // Switch to frame by WebElement
    public void switchToFrameByElement(By frameLocator) {
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().frame(d.findElement(frameLocator));
                LogsManager.info("Switched to frame using locator: " + frameLocator);
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to switch to frame using locator: " + frameLocator + " - " + e.getMessage());
                return false;
            }
        });}

    // Switch back to the main document
    public void switchToDefaultContent() {
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().defaultContent();
                LogsManager.info("Switched back to the main document");
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to switch back to the main document - " + e.getMessage());
                return false;
            }
        }
        );}

}
