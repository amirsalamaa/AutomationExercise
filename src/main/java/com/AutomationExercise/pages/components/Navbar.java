package com.AutomationExercise.pages.components;

import com.AutomationExercise.drivers.GUIDriver;
import com.AutomationExercise.pages.*;
import com.AutomationExercise.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Navbar {

    private final GUIDriver driver;
    public Navbar(GUIDriver driver) {
        this.driver = driver;
    }

    //locators
    private final By homeLink = By.xpath("//a[.=' Home'] ");
    private final By productsLink = By.xpath("//a[normalize-space(text())='Products']");
    private final By cartLink = By.xpath("//a[normalize-space(.)=\"Cart\"]");
    private final By signupLoginLink = By.xpath("//a[normalize-space(.)=\"Signup / Login\"]");
    private final By testCasesLink = By.xpath("//a[normalize-space(.)=\"Test Cases\"]");
    private final By apiTestingLink = By.xpath("//a[normalize-space(.)=\"API Testing\"]");
    private final By contactUsLink = By.xpath("//a[normalize-space(.)=\"Contact us\"]");
    private final By videoTutorialsLink = By.xpath("//a[normalize-space(.)=\"Video Tutorials\"]");
    private final By deleteAccountLink = By.xpath("//a[normalize-space(.)=\"Delete Account\"]");
    private final By homePageLabel = By.cssSelector("h1 > span");
    private final By userLabel = By.tagName("b");
    private final By logoutLink = By.xpath("//a[.=\" Logout\"]");


    //actions
    @Step("Navigate to base URL")
    public Navbar navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }
    @Step("Click on Home link in Navbar")
    public Navbar clickOnHomeLink() {
        driver.element().click(homeLink);
        return this;
    }
    @Step("Click on Products link in Navbar")
    public ProductsPage clickOnProductsLink() {
        driver.element().click(productsLink);
        return new ProductsPage(driver);
    }
    @Step("Click on Cart link in Navbar")
    public CartPage clickOnCartLink() {
        driver.element().click(cartLink);
        return new CartPage(driver);
    }
    @Step("Click on Signup/Login link in Navbar")
    public SignUpLoginPage clickOnSignupLoginLink() {
        driver.element().click(signupLoginLink);
        return new SignUpLoginPage(driver);
    }
    @Step("Click on Test Cases link in Navbar")
    public TestCasesPage clickOnTestCasesLink() {
        driver.element().click(testCasesLink);
        return new TestCasesPage(driver);
    }
    @Step("Click on API Testing link in Navbar")
    public ApiTestingPage clickOnApiTestingLink() {
        driver.element().click(apiTestingLink);
        return new ApiTestingPage(driver);
    }
    @Step("Click on Contact Us link in Navbar")
    public ContactUsPage clickOnContactUsLink() {
        driver.element().click(contactUsLink);
        return new ContactUsPage(driver);
    }
    @Step("Click on Video Tutorials link in Navbar")
    public VideoTutPage clickOnVideoTutorialsLink() {
        driver.element().click(videoTutorialsLink);
        return new VideoTutPage(driver);
    }
    @Step("Click on Delete Account link in Navbar")
    public DeleteAccountPage clickOnDeleteAccountLink() {
        driver.element().click(deleteAccountLink);
        return new DeleteAccountPage(driver);
    }
    //validations
    @Step("Verify Home page is displayed")
    public Navbar verifyHomePageIsDisplayed() {
        driver.verify().isElementVisible(homePageLabel);
        return this;
    }
    @Step("Verify User Label")
    public Navbar verifyUserLabel(String expectedUsername) {
        String actualUsername = driver.element().getText(userLabel);
        driver.verify().Equals(actualUsername, expectedUsername, "Username in navbar does not match expected.");
        return this;
    }
    @Step("Click on Logout link in Navbar")
    public SignUpLoginPage clickOnLogoutLink() {
        driver.element().click(logoutLink);
        return new SignUpLoginPage(driver);
    }



}
