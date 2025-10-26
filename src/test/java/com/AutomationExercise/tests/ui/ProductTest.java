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

@Epic("Automation Exercise")
@Feature("UI Products Management")
@Story("Products Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Amir Salama")
@UITest
public class ProductTest extends BaseTest {


    @Test
    @Description("Search for a product and validate its details")
    public void searchForProductWithoutLoginTC(){
        //navigate to products page
        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testData.getJsonData("product1.name"))
                .validateItemAddedLabel(
                        testData.getJsonData("messages.cartAdded")
                );
    }

    @Test
    @Description("Add a product to cart without login")
    public void addProductToCartWithoutLoginTC(){

        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testData.getJsonData("product1.name"))
                .validateItemAddedLabel(
                        testData.getJsonData("messages.cartAdded")
                );
    }


    //Configurations
    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("productsData");
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
