package com.step2qa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Rahul R on 1/20/2019
 * @version 1.0.1
 */
public class HeadlessChrome {

    private WebDriver driver;

    @BeforeTest
    public void setup() {

        System.out.println("-----  Initiating the browser instance -----");

        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);

        driver.manage().window().maximize();

        driver.get("https://www.google.com");

        System.out.println("Opening Google in browser.");

    }

    @Test
    public void open_headless_chrome() throws InterruptedException {

        System.out.println("Typing Step2QA in Google Search");
        driver.findElement(By.name("q")).sendKeys("Step2QA");

        System.out.println("Search Step2QA");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        System.out.println("Click on Step2QA link");
        driver.findElement(By.xpath("//ol//a[contains(text(),'Elevate Quality Engineering')]")).click();

        Thread.sleep(5000);

        System.out.println("Verify Step2QA homepage opens");
        Assert.assertEquals(driver.getTitle(), "Step2QA – Elevate Quality Engineering", "Failed to open the clicked site.");

    }

    @AfterTest
    public void teardown() {
        System.out.println("------ Killing browser instance ------");
        driver.quit();
    }

}
