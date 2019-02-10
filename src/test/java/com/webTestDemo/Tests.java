package com.webTestDemo;

import com.webTestDemo.pages.BookingPage;
import com.webTestDemo.pages.FlightsPage;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {

    private static WebDriver driver;

    @BeforeClass
    public static void setup(){
        //set driver
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver/chromedriver.exe");

        //set language
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("intl.accept_languages", "en-en,en");
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.booking.com/");
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
        BookingPage bookingPage = new BookingPage(driver);

        //set data
        bookingPage.enterCity("New York");
        bookingPage.enterDepartureDate("09", "30", "2019");
        bookingPage.enterArrivalDate("09", "01", "2019");
        bookingPage.submitForm();

        //validate data
        List expected =  asList(
                "New York",
                "9", "1","2019",
                "9","30","2019");
        assertEquals(expected, bookingPage.getDataForVerification());
    }

    @Test
    public void test2() throws InterruptedException {
        new BookingPage(driver).switchOnFlightPage();

        //switch the driver on FlightPage url
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(2000);

        FlightsPage flightsPage = new FlightsPage(driver);
        flightsPage.selectOneWay();
        Thread.sleep(1000);
        assertTrue(flightsPage.isSelectedOneWay());

        flightsPage.enterData("Kiev", "Tokio");
        List actualData = flightsPage.getDataForVerification();

        List expected =  asList("Kiev (IEV)", "Devils Lake (DVL)", "Sun 1/9");
        assertEquals(expected, actualData);

        flightsPage.submitForm();
    }
}