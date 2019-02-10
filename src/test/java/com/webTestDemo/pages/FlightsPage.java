package com.webTestDemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class FlightsPage extends PageObject {

    public FlightsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "origin")
    private WebElement origin;

    @FindBy(name = "destination")
    private WebElement destination;

    @FindBy(xpath = "//div[@aria-label='September 1']")
    private WebElement departDate;

    @FindBy(xpath = "//div[@class='navItem nextMonth']")
    private WebElement clickNextMonth;

    @FindBy(xpath = "//div[@aria-label='September 1']")
    private WebElement setDate;

    @FindBy(xpath = "//label[@title='One-way']")
    private WebElement oneWay;

    @FindBy(xpath = "//div[@class='dateInput size-l input-flat']")
    private WebElement checkDate;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit;

    @FindBy(xpath = "//div[@class='keel-grid dateRangeGrid']")
    private WebElement datePicker;

    public void selectOneWay() {
        this.oneWay.click();
    }

    public void enterData(String origin, String destinetion) throws InterruptedException {
        this.origin.clear();
        this.origin.sendKeys(origin);
        this.origin.click();
        Thread.sleep(1000);
        this.destination.clear();
        this.destination.sendKeys(destinetion);
        this.destination.click();
        Thread.sleep(1000);

        this.datePicker.click();
        setNextMonth().click();
    }

    private WebElement setNextMonth(){
        String value = "";
        while (! value.equals("September 1")) {
        try {
            value = this.setDate.getAttribute("aria-label");
        }catch (Exception ignored){}
            this.clickNextMonth.click();
        }
        return departDate;
    }

    public boolean isSelectedOneWay(){
        return oneWay.isEnabled();
    }

    public List getDataForVerification() {
        List<String> data = new ArrayList<>();
        data.add(origin.getAttribute("value"));
        data.add(destination.getAttribute("value"));
        data.add(checkDate.getText());
        return data;
    }

    public void submitForm() {
        this.submit.click();
    }
}