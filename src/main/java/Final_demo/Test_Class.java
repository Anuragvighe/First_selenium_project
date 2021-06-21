package Final_demo;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Test_Class {
    WebDriver driver = null;

    @Test(priority = 0)
    public void open_Url() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Desktop\\Maven_Projects\\wed_Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        //Site
        driver.get("http://demo.guru99.com/test/newtours//");

        //Click on registration button
        driver.findElement(By.xpath("//a[contains(text(),'REGISTER')]/parent::td")).click();
    }

    @Test(priority = 1)
    public void fill_Data() throws IOException {
        Registration R = new Registration(driver);
        R.get_data();
    }

    @AfterSuite
    public void close(){
        driver.close();
    }
}
