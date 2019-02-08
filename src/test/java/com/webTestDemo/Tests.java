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

    private static WebDriver driver;

    @BeforeClass
    public static void setup(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        driver.get("https://www.booking.com/");
        BookingPage bookingPage = new BookingPage(driver);

        //set data
        bookingPage.enterCity("New York");
        bookingPage.enterArrivalDate("09", "01", "2019");
        bookingPage.enterDepartureDate("09", "30", "2019");
        bookingPage.clickSubmit();

        //validate data
        List expected =  asList(
                "New York",
                "9", "1","2019",
                "9","30","2019");
        assertEquals(expected, bookingPage.getDataFromBookingPage());
    }

    @Test
    public void test2(){
        driver.get("https://www.booking.com/");
        FlightsPage flightsPage = new FlightsPage(driver);

        //set data
        from.enterCity("American Museum of Natural History, New York, NY, USA");
        till.enterArrivalDate("Santo Domingo - Las Americas International Airport");
        date.enterDepartureDate("09", "30", "2019");
        time.enterDepartureDate("09", "30", "2019");
        submit.clickSubmit();

        //validate data
        List expected =  asList(
                "New York",
                "9", "1","2019",
                "9","30","2019");
        assertEquals(expected, bookingPage.getDataFromBookingPage());
    }
}