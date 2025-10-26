package com.AutomationExercise.Listeners;

import com.AutomationExercise.FileUtils;
import com.AutomationExercise.drivers.UITest;
import com.AutomationExercise.drivers.WebDriverProvider;
import com.AutomationExercise.media.ScreenShotsManager;
import com.AutomationExercise.utils.dataReader.PropertyReader;
import com.AutomationExercise.utils.logs.LogsManager;
import com.AutomationExercise.utils.report.AllureAttachmentManager;
import com.AutomationExercise.utils.report.AllureConstants;
import com.AutomationExercise.utils.report.AllureEnvironmentManager;
import com.AutomationExercise.utils.report.AllureReportGenerator;
import com.AutomationExercise.validations.Validation;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;

public class TestNGListeners implements ISuiteListener, IExecutionListener, IInvokedMethodListener, ITestListener{

        public void onStart(ISuite suite) {
            suite.getXmlSuite().setName("Automation Exercise");
        }
        public void onExecutionStart() {
            LogsManager.info("Test Execution started");
            cleanTestOutputDirectories();
            LogsManager.info("Directories cleaned");
            createTestOutputDirectories();
            LogsManager.info("Directories created");
            PropertyReader.loadProperties();
            LogsManager.info("Properties loaded");
            AllureEnvironmentManager.setEnvironmentVariables();
            LogsManager.info("Allure environment set");
        }

        public void onExecutionFinish() {
            AllureReportGenerator.copyHistory();
            AllureReportGenerator.generateReports(false);
            AllureReportGenerator.generateReports(true);
            AllureReportGenerator.openReport(AllureReportGenerator.renameReport());
            LogsManager.info("Test Execution Finished");
        }


//        public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
//
//            if (method.isTestMethod()) {
//                if (testResult.getInstance() instanceof UITest)
//                {
//                    ScreenRecordManager.startRecording();
//                }
//                LogsManager.info("Test Case " + testResult.getName() + " started");
//            }
//        }

        public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
            WebDriver driver = null;
            if (method.isTestMethod())
            {
                Object instance = testResult.getInstance();

                // Prefer getting driver from WebDriverProvider if available (works even without @UITest)
                if (instance instanceof WebDriverProvider provider) {
                    try {
                        driver = provider.getWebDriver(); //initialize driver from WebDriverProvider
                    } catch (Exception ignored) {
                        LogsManager.warn("Unable to get WebDriver from provider for test:", testResult.getName());
                    }
                }

                // If the class is annotated with @UITest we may have additional behaviors (e.g. recordings)
                boolean isUiTest = instance != null && instance.getClass().isAnnotationPresent(UITest.class);

                if (driver != null) {
                    switch (testResult.getStatus()){
                        case ITestResult.SUCCESS -> ScreenShotsManager.takeFullPageScreenshot(driver,"passed-" + testResult.getName());
                        case ITestResult.FAILURE -> ScreenShotsManager.takeFullPageScreenshot(driver,"failed-" + testResult.getName());
                        case ITestResult.SKIP -> ScreenShotsManager.takeFullPageScreenshot(driver,"skipped-" + testResult.getName());
                    }
//                    if (isUiTest) AllureAttachmentManager.attachRecords(testResult.getName());
                } else if (isUiTest) {
                    // annotated as UI test but driver not available — log a helpful message
                    LogsManager.warn("Test marked as @UITest but WebDriver instance was not available for:", testResult.getName());
                }

                Validation.assertAll(testResult);

                AllureAttachmentManager.attachLogs();

            }
        }


        public void onTestSuccess(ITestResult result) {
            LogsManager.info("Test Case " + result.getName() + " passed");
        }

        public void onTestFailure(ITestResult result) {
            LogsManager.info("Test Case " + result.getName() + " failed");
        }

        public void onTestSkipped(ITestResult result) {
            LogsManager.info("Test Case " + result.getName() + " skipped");
        }


        // cleaning and creating dirs (logs, screenshots, recordings,allure-results)
        private void cleanTestOutputDirectories() {
            // Implement logic to clean test output directories
            FileUtils.cleanDirectory(AllureConstants.RESULTS_FOLDER.toFile());
            String screenshotsPath = String.valueOf(AllureConstants.USER_DIR) + File.separator + "test-output" + File.separator + "screenshots";
            FileUtils.cleanDirectory(new File(screenshotsPath));
//            FileUtils.cleanDirectory(new File(ScreenRecordManager.RECORDINGS_PATH));
            FileUtils.cleanDirectory(new File("src/test/resources/downloads/"));
            FileUtils.forceDelete(new File(LogsManager.LOGS_PATH +File.separator+ "logs.log"));
        }

        private void createTestOutputDirectories() {
            // Implement logic to create test output directories
            String screenshotsPath = String.valueOf(AllureConstants.USER_DIR) + File.separator + "test-output" + File.separator + "screenshots";
            FileUtils.createDirectory(screenshotsPath);
//            FileUtils.createDirectory(ScreenRecordManager.RECORDINGS_PATH);
            FileUtils.createDirectory("src/test/resources/downloads/");

        }


}
