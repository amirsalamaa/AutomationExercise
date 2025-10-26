package com.AutomationExercise.media;


import com.AutomationExercise.utils.TimeManager;
import com.AutomationExercise.utils.logs.LogsManager;
import com.AutomationExercise.utils.report.AllureAttachmentManager;
import com.AutomationExercise.utils.report.AllureConstants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenShotsManager {
    // Use project user.dir to build a cross-platform path
    public static final Path SCREENSHOTS_DIR = Paths.get(String.valueOf(AllureConstants.USER_DIR), "test-output", "screenshots");

    // Provide a string path for compatibility with existing FileUtils/TestNGListeners usage
    public static final String SCREENSHOTS_PATH = SCREENSHOTS_DIR.toString();

    //take full page screenshot and save to disk + attach
    public static void takeFullPageScreenshot(WebDriver driver, String screenshotName) {
        takeFullPageScreenshot(driver, screenshotName, true);
    }

    //take full page screenshot with option to save to disk
    public static void takeFullPageScreenshot(WebDriver driver, String screenshotName, boolean saveToDisk) {
        if (driver == null) {
            LogsManager.warn("Driver is null, cannot take screenshot for: ", screenshotName);
            return;
        }

        try {
            byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // Attach directly to Allure from bytes
            AllureAttachmentManager.attachScreenshot(screenshotName, bytes);

            if (saveToDisk) {
                try {
                    Files.createDirectories(SCREENSHOTS_DIR);
                    String fileName = screenshotName + "-" + TimeManager.getTimestamp() + ".png";
                    File screenshotFile = SCREENSHOTS_DIR.resolve(fileName).toFile();
                    FileUtils.writeByteArrayToFile(screenshotFile, bytes);
                    LogsManager.info("Saved screenshot to disk:", screenshotFile.getAbsolutePath());
                } catch (Exception ex) {
                    LogsManager.warn("Failed to save screenshot to disk:", ex.getMessage());
                }
            }

            LogsManager.info("Capturing Screenshot Succeeded", screenshotName);
        } catch (Exception e) {
            LogsManager.error("Failed to Capture Screenshot: " + e.toString());
        }
    }

    //take element screenshot and save to disk + attach
    public static void takeElementScreenshot(WebDriver driver, By elementSelector) {
        if (driver == null) {
            LogsManager.warn("Driver is null, cannot take element screenshot for selector: ", elementSelector.toString());
            return;
        }

        try {
            Files.createDirectories(SCREENSHOTS_DIR);

            WebElement el = driver.findElement(elementSelector);
            if (el == null) {
                LogsManager.warn("Element not found for selector: ", elementSelector.toString());
                return;
            }

            String ariaName = null;
            try {
                ariaName = el.getAccessibleName();
            } catch (Exception ignore) {
                // accessible name may not be available in all drivers
            }

            if (ariaName == null || ariaName.trim().isEmpty()) {
                // fallback to locator or tag name for filename
                ariaName = elementSelector.toString().replaceAll("[^a-zA-Z0-9_-]", "_");
            }

            byte[] bytes = el.getScreenshotAs(OutputType.BYTES);

            // Attach to Allure from bytes
            AllureAttachmentManager.attachScreenshot("element-" + ariaName, bytes);

            String fileName = ariaName + "-" + TimeManager.getTimestamp() + ".png";
            File screenshotFile = SCREENSHOTS_DIR.resolve(fileName).toFile();
            FileUtils.writeByteArrayToFile(screenshotFile, bytes);

            LogsManager.info("Capturing Element Screenshot Succeeded", screenshotFile.getAbsolutePath());
        } catch (Exception e) {
            LogsManager.error("Failed to Capture Element Screenshot: " + e.toString());
        }
    }



}
