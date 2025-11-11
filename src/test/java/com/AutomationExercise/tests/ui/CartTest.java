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


@Epic("Cart Management")
@Feature("UI Cart Details")
@Story("Cart Details")
@Severity(SeverityLevel.CRITICAL)
@Owner("AmirSalama")
@UITest
public class CartTest extends BaseTest {


    @Test
    public void verifyProductDetailsOnCartWithoutLogin() {
        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testData.getJsonData("product.name"))
                .validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
                .clickOnViewCart()
                .verifyProductDetailsOnCart(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total")
                );
    }

    //Configurations
    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("cartData");
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
