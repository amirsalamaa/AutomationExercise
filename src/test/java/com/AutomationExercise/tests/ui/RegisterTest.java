package com.AutomationExercise.tests.ui;

import com.AutomationExercise.apis.UserManagementAPI;
import com.AutomationExercise.drivers.GUIDriver;
import com.AutomationExercise.pages.SignUpLoginPage;
import com.AutomationExercise.pages.SignUpPage;
import com.AutomationExercise.pages.components.Navbar;
import com.AutomationExercise.tests.BaseTest;
import com.AutomationExercise.utils.TimeManager;
import com.AutomationExercise.utils.dataReader.JsonReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    String timeStamp = TimeManager.getTimestamp();
    //Tests
    @Test
    public void ValidSignUpTC(){
        new SignUpLoginPage(driver)
                .navigateToSignUpLoginPage()
                .enterSignUpName(testData.getJsonData("name"))
                .enterSignUpEmail(testData.getJsonData("email")+timeStamp+"@gmail.com")
                .clickOnSignUpButton();
                new SignUpPage(driver)
                .fillRegisterationForm(
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("password"),
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("country"),
                        testData.getJsonData("state"),
                        testData.getJsonData("city"),
                        testData.getJsonData("zipCode"),
                        testData.getJsonData("mobileNumber")
                )
                .clickOnCreateAccountButton()
                .verifyAccountCreatedSuccessfully();
//        new UserManagementAPI().deleteUserAccount(testData.getJsonData("email")+timeStamp+"@gmail.com"
//                        ,testData.getJsonData("password" ))
//                .verifyUserDeletedSuccessfully();
    }

    @Test
    public void VerifyErrorMessageWithExistingEmailTC(){

      //  preCondition>create user via api
        new UserManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("name"),
                (testData.getJsonData("email")+timeStamp+("@gmail.com")),
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("password"),
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("country"),
                        testData.getJsonData("state"),
                        testData.getJsonData("city"),
                        testData.getJsonData("zipCode"),
                        testData.getJsonData("mobileNumber")
        );

        new SignUpLoginPage(driver)
                .navigateToSignUpLoginPage()
                .enterSignUpName(testData.getJsonData("name"))
                .enterSignUpEmail(testData.getJsonData("email")+timeStamp+"@gmail.com")
                .clickOnSignUpButton()
                .verifyRegisterErrorMessage("Email Address already exist!");

    }




    //configurations
    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("registerData");
    }

    @BeforeMethod
    public void setUp() {
        driver = new GUIDriver();
        new Navbar(driver).navigate();

    }

    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }



}
