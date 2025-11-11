package com.AutomationExercise.tests.ui;

import com.AutomationExercise.drivers.GUIDriver;
import com.AutomationExercise.drivers.UITest;
import com.AutomationExercise.pages.ProductsPage;
import com.AutomationExercise.pages.components.Navbar;
import com.AutomationExercise.tests.BaseTest;
import com.AutomationExercise.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Epic("Products Management")
@Feature("UI Product Details")
@Story("Product Details")
@Severity(SeverityLevel.CRITICAL)
@Owner("AmirSalama")
@UITest
public class ProductDetailsTest extends BaseTest {

    @Test
    public void verifyProductDetailsTCWithoutLogin()
    {
        new ProductsPage(driver)
                .navigate()
                .clickOnViewProduct(testData.getJsonData("product.name"))
                .verifyProductDetails(testData.getJsonData("product.name"),testData.getJsonData("product.price"));
    }

    @Test
    public void verifyReviewMessageTCWithoutLogin()
    {
        new ProductsPage(driver)
                .navigate()
                .clickOnViewProduct(testData.getJsonData("product.name"))
                .addReview(testData.getJsonData("review.name"),testData.getJsonData("review.email"),testData.getJsonData("review.review"))
                .verifyReviewMsg(testData.getJsonData("messages.review"));
    }


    //Configurations
    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("productDetails");
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
