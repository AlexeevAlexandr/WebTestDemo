package com.webTestDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

class BookingPage extends PageObject{

    BookingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "ss")
    private WebElement city;

    @FindBy(name = "checkin_month")
    private WebElement checkin_month;

    @FindBy(name = "checkin_monthday")
    private WebElement checkin_monthday;

    @FindBy(name = "checkin_year")
    private WebElement checkin_year;

    @FindBy(name = "checkout_month")
    private WebElement checkout_month;

    @FindBy(name = "checkout_monthday")
    private WebElement checkout_monthday;

    @FindBy(name = "checkout_year")
    private WebElement checkout_year;

    @FindBy(xpath = "//div[@class='xp__button']")
    private WebElement submit;

    void enterCity(String city) {
        this.city.sendKeys(city);
    }

    void enterArrivalDate(String month, String day, String year) {
        this.checkin_month.sendKeys(month);
        this.checkin_monthday.sendKeys(day);
        this.checkin_year.sendKeys(year);
    }

    void enterDepartureDate(String month, String day, String year) {
        this.checkout_month.sendKeys(month);
        this.checkout_monthday.sendKeys(day);
        this.checkout_year.sendKeys(year);
    }

    void clickSubmit(){
        this.submit.click();
    }

    List getDataFromBookingPage() {
        List<String> list = new ArrayList<>();
        list.add(city.getAttribute("value"));
        list.add(checkin_month.getAttribute("value"));
        list.add(checkin_monthday.getAttribute("value"));
        list.add(checkin_year.getAttribute("value"));
        list.add(checkout_month.getAttribute("value"));
        list.add(checkout_monthday.getAttribute("value"));
        list.add(checkout_year.getAttribute("value"));
        return list;
    }
}