package com.webTestDemo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class Tests {

    private static BookingPage bookingPage;
    private static WebDriver driver;

    @BeforeClass
    public static void setup(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String url = "https://www.booking.com/";
        driver.get(url);
        bookingPage = new BookingPage(driver);
    }

    @After
    public void cleanUp(){
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

    @Test
    public void test1() {
        //set data
        bookingPage.enterCity("New York");
        bookingPage.enterArrivalDate("09", "01", "2019");
        bookingPage.enterDepartureDate("09", "30", "2019");
        bookingPage.clickSubmit();

        //validate data
        List expected =  asList(
                "New York",
                "9","1","2019",
                "9","30","2019");
        assertEquals(expected, bookingPage.getDataFromBookingPage());
    }


}