package com.webTestDemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

class PageObject {

    PageObject (WebDriver driver){
        PageFactory.initElements(driver, this);
    }
}