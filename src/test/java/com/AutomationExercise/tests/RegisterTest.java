package com.AutomationExercise.tests;

import com.AutomationExercise.drivers.GUIDriver;
import com.AutomationExercise.pages.components.Navbar;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {
    //Tests
    @Test
    public void signUpTC(){


    }




    //configurations
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
