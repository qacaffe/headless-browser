package com.step2qa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Rahul R on 1/20/2019
 * @version 1.0.1
 */
public class HeadlessPhantomJS {

    private WebDriver driver;

    @BeforeTest
    public void setup() {

        System.out.println("-----  Initiating the browser instance -----");

        WebDriverManager.phantomjs().setup();
        driver = new PhantomJSDriver();

        driver.manage().window().maximize();

        driver.get("https://www.google.com");

        System.out.println("Opening Google in browser.");

    }

    @Test
    public void open_headless_phantomJS() throws InterruptedException {

        System.out.println("Typing Step2QA in Google Search");
        driver.findElement(By.name("q")).sendKeys("Step2QA");

        System.out.println("Search Step2QA");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        System.out.println("Click on Step2QA link");
        driver.findElement(By.xpath("//ol//a[contains(text(),'Elevate Quality Engineering')]")).click();

        Thread.sleep(5000);

        System.out.println("Verify Step2QA homepage openes");
        Assert.assertEquals(driver.getTitle(), "Step2QA – Elevate Quality Engineering", "Failed to open the clicked site.");

    }

    @AfterTest
    public void teardown() {
        System.out.println("------ Killing browser instance ------");
        driver.quit();
    }

}
