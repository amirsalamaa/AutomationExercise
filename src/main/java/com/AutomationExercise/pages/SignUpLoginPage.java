package com.AutomationExercise.pages;

import com.AutomationExercise.drivers.GUIDriver;
import com.AutomationExercise.pages.components.Navbar;
import com.AutomationExercise.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignUpLoginPage {
    private final String signupLoginEndpoint = "/login";
    public Navbar navbar;
    private final GUIDriver driver;
    public SignUpLoginPage(GUIDriver driver) {
        this.driver = driver;
        this.navbar = new Navbar(driver);
    }


    //Locators
    private By loginEmailInput =By.xpath("//input[@data-qa='login-email']");
    private By loginPassInput =By.xpath("//input[@data-qa='login-password']");
    private By loginButton =By.xpath("//button[@data-qa='login-button']");
    private By signUpNameInput =By.xpath("//input[@data-qa='signup-name']");
    private By signUpEmailInput =By.xpath("//input[@data-qa='signup-email']");
    private By signUpButton =By.xpath("//button[@data-qa='signup-button']");
    private final By signupLabel = By.cssSelector(".signup-form > h2");
    private final By loginError = By.cssSelector(".login-form  p");
    private final By registerError = By.cssSelector(".signup-form p");




    //actions
    @Step("Navigate to Sign Up/Login page")
    public SignUpLoginPage navigateToSignUpLoginPage(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+signupLoginEndpoint);
        return this;
    }
    @Step("Enter login email: {email}")
    public SignUpLoginPage enterLoginEmail(String email){
        driver.element().type(loginEmailInput,email);
        return this;
    }
    @Step("Enter login password: {password}")
    public SignUpLoginPage enterLoginPassword(String password){
        driver.element().type(loginPassInput,password);
        return this;
    }
    @Step("Click on login button")
    public SignUpLoginPage clickOnLoginButton(){
        driver.element().click(loginButton);
        return this;
    }

    @Step("Enter sign up name: {name}")
    public SignUpLoginPage enterSignUpName(String name){
        driver.element().type(signUpNameInput,name);
        return this;
    }
    @Step("Enter sign up email: {email}")
    public SignUpLoginPage enterSignUpEmail(String email){
        driver.element().type(signUpEmailInput,email);
        return this;
    }
    @Step("Click on sign up button")
    public SignUpLoginPage clickOnSignUpButton(){
        driver.element().click(signUpButton);
        return new SignUpLoginPage(driver);
    }



    //validations
    @Step("Verify 'New User Signup!' label is visible")
    public SignUpLoginPage verifySignUpLabelIsVisible(){
        driver.validate().isElementVisible(signupLabel);
        return this;
    }
    @Step("Verify login error message: {expectedMessage}")
    public SignUpLoginPage verifyLoginErrorMessage(String expectedMessage){
        String actualMessage = driver.element().getText(loginError);
        driver.validate().Equals(actualMessage,expectedMessage,"Login error message does not match");
        return this;
    }
    @Step("Verify register error message: {expectedMessage}")
    public SignUpLoginPage verifyRegisterErrorMessage(String expectedMessage){
        String actualMessage = driver.element().getText(registerError);
        driver.validate().Equals(actualMessage,expectedMessage,"Register error message does not match");
        return this;
    }
}

