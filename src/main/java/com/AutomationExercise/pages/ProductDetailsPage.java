package com.AutomationExercise.pages;

import com.AutomationExercise.drivers.GUIDriver;
import com.AutomationExercise.pages.components.Navbar;

public class ProductDetailsPage {
    private final GUIDriver driver;
    public Navbar navbar;
    public ProductDetailsPage(GUIDriver driver) {
        this.driver = driver;
        this.navbar=new Navbar(driver);
    }
}
