package com.AutomationExercise.pages;

import com.AutomationExercise.drivers.GUIDriver;
import com.AutomationExercise.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage {
    private final GUIDriver driver;
    public CartPage(GUIDriver driver) {
        this.driver = driver;
    }



    //variables
    private String cartEndpoint = "/view_cart";





    //locators
    private final By proceedToCheckoutButton = By.xpath("//a[.='Proceed To Checkout']");

    //dynamic locators
    private By productName(String productName) {
        return By.xpath("(//h4  /a[.='" + productName + "'])[1]");
    }
    private By productPrice(String productName) {
        return By.xpath("(//h4  /a[.='" + productName + "'] //following::td[@class='cart_price'] /p)[1]");
    }

    private By productQuantity(String productName) {
        return By.xpath("(//h4  /a[.='" + productName + "'] //following::td[@class='cart_quantity'] /button)[1]");
    }

    private By productTotal(String productName) {
        return By.xpath("(//h4  /a[.='"+productName+"'] //following::td[@class='cart_total'] /p)[1]");
    }

    private By removeProductDL(String productName) {
        return By.xpath("(//h4  /a[.='"+productName+"'] //following::td[@class='cart_delete'] /a)[1]");
    }


    //actions
    @Step("Navigate To Cart Page")
    public CartPage navigate()
    {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+cartEndpoint);
        return this;
    }
    @Step("Click On Proceed To Checkout Button")
    public CheckoutPage clickOnProceedToCheckout()
    {
        driver.element().click(proceedToCheckoutButton);
        return new CheckoutPage(driver);
    }
    @Step("Remove Product From Cart")
    public CartPage removeProduct(String pName)
    {
        driver.element().click(removeProductDL(pName));
        return this;
    }



    //validations
    @Step("Verify Product Details On Cart")
    public CartPage verifyProductDetailsOnCart (String productName, String productPrice, String productQuantity, String productTotal)
    {
        String actualProductName = driver.element().getText(productName(productName));
        String actualProductPrice = driver.element().getText(productPrice(productName));
        String actualProductQuantity = driver.element().getText(productQuantity(productName));
        String actualProductTotal = driver.element().getText(productTotal(productName));
        driver.validate().Equals(actualProductName,productName," Product Name is not matched");
        driver.validate().Equals(actualProductPrice,productPrice," Product Price is not matched");
        driver.validate().Equals(actualProductQuantity,productQuantity," Product Quantity is not matched");
        driver.validate().Equals(actualProductTotal,productTotal," Product Total is not matched");
        return this;
    }
}
