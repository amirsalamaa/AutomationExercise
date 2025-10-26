package com.AutomationExercise.tests.ui;

import com.AutomationExercise.apis.UserManagementAPI;
import com.AutomationExercise.drivers.GUIDriver;
import com.AutomationExercise.pages.SignUpLoginPage;
import com.AutomationExercise.pages.components.Navbar;
import com.AutomationExercise.tests.BaseTest;
import com.AutomationExercise.utils.TimeManager;
import com.AutomationExercise.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



@Epic("Automation Exercise")
@Feature("UI Tests")
@Story("User Login Tests")
@Severity(SeverityLevel.CRITICAL)
@Owner("Amir Salama")
public class LoginTest extends BaseTest {
    String timeStamp = TimeManager.getTimestamp();

    @Description("Verify that user can log in with valid credentials")
    @Test
    public void ValidLoginTC(){
        new UserManagementAPI().createRegisterUserAccount(
                testData.getJsonData("name"),
                (testData.getJsonData("email")+timeStamp+("@gmail.com")),
                testData.getJsonData("password"),
                testData.getJsonData("titleMale"),
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
                .enterLoginEmail((testData.getJsonData("email")+timeStamp+"@gmail.com"))
                .enterLoginPassword(testData.getJsonData("password"))
                .clickOnLoginButton()
                .navbar.verifyUserLabel(testData.getJsonData("name"));
                new UserManagementAPI().deleteUserAccount(testData.getJsonData("email")+timeStamp+"@gmail.com"
                                ,testData.getJsonData("password" ))
                        .verifyUserDeletedSuccessfully();
    }

    @Description("Verify that user cannot log in with invalid email")
    @Test
    public void InvalidEmailLoginTC(){
        new UserManagementAPI().createRegisterUserAccount(
                testData.getJsonData("name"),
                (testData.getJsonData("email")+timeStamp+("@gmail.com")),
                testData.getJsonData("password"),
                testData.getJsonData("titleMale"),
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
                .enterLoginEmail(testData.getJsonData("invalidEmail"))
                .enterLoginPassword(testData.getJsonData("password"))
                .clickOnLoginButton()
                .verifyLoginErrorMessage("Your email or password is incorrect!");
    }
    @Description("Verify that user cannot log in with invalid password")
    @Test
    public void InvalidPasswordLoginTC() {
        new UserManagementAPI().createRegisterUserAccount(
                testData.getJsonData("name"),
                (testData.getJsonData("email")+timeStamp+("@gmail.com")),
                testData.getJsonData("password"),
                testData.getJsonData("titleMale"),
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
                .enterLoginEmail((testData.getJsonData("email") + timeStamp + "@gmail.com"))
                .enterLoginPassword(testData.getJsonData("invalidPassword"))
                .clickOnLoginButton()
                .verifyLoginErrorMessage("Your email or password is incorrect!");
    }


    //configurations
    @BeforeClass
    public void preCondition() {
        testData = new JsonReader("loginData");
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
