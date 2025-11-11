package com.AutomationExercise.pages;

import com.AutomationExercise.drivers.GUIDriver;
import com.AutomationExercise.pages.components.Navbar;
import com.AutomationExercise.utils.dataReader.PropertyReader;
import com.AutomationExercise.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductDetailsPage {
    private final GUIDriver driver;
    public Navbar navbar;
    public ProductDetailsPage(GUIDriver driver) {
        this.driver = driver;
        this.navbar=new Navbar(driver);
    }
    //vars
    private String productDetailsEndpoint = "/product-details/2";
    //locators
    private final By productName = By.cssSelector(".product-information > h2");
    private final By productPrice = By.cssSelector(".product-information > span > span");
    private final By name = By.id("name");
    private final By email = By.id("email");
    private final By reviewTextArea = By.id("review");
    private final By reviewButton = By.id("button-review");
    private final By reviewMsg = By.cssSelector("#review-section span");


    //actions
    public ProductDetailsPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + productDetailsEndpoint);
        return this;
    }
    @Step("write review on product")
    public ProductDetailsPage addReview(String name, String email, String review) {
        driver.element().type(this.name, name);
        driver.element().type(this.email, email);
        driver.element().type(this.reviewTextArea, review);
        driver.element().click(this.reviewButton);
        return this;
    }

    //validations
    @Step("verify product details")
    public ProductDetailsPage verifyProductDetails(String pName, String pPrice) {
        String actualProductName = driver.element().getText(productName);
        String actualProductPrice = driver.element().getText(productPrice);
        LogsManager.info("actual product name:", actualProductName, "actual price:", actualProductPrice);
        driver.validate().Equals(actualProductName, pName, "Product Name Verification Failed");
        driver.validate().Equals(actualProductPrice, pPrice, "Product Price Verification Failed");
        return this;
    }

    @Step("verify review message")
    public ProductDetailsPage verifyReviewMsg(String msg) {
        String actualMsg = driver.element().getText(reviewMsg);
        LogsManager.info("actual msg:", actualMsg);
        driver.verify().Equals(actualMsg, msg, "Review Message Verification Failed");
        return this;
    }
}
