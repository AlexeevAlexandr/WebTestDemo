package com.webTestDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

class FlightsPage extends PageObject{

    FlightsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "Zp56-origin")
    private WebElement origin;

    @FindBy(id = "Zp56-destination")
    private WebElement destination;

    @FindBy(id = "Zp56-depart")
    private WebElement departDate;

    @FindBy(id = "Zp56-return")
    private WebElement returnDate;

    @FindBy(id = "Zp56-submit")
    private WebElement submit;

    void enterCity(String origin, String destinetion, String departDate, String returnDate) {
        this.origin.sendKeys();
        this.destination.sendKeys();
        this.departDate.sendKeys();
        this.returnDate.sendKeys();
    }

    void submit(){
        this.submit.click();
    }
}
