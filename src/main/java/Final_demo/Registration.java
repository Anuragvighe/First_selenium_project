package Final_demo;


import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Registration {

    WebDriver driver = null;


    //Constructor
    public Registration(WebDriver driver) {
        this.driver = driver;
    }


    public void get_data() throws IOException {

        FileInputStream inputStream = new FileInputStream("C:\\Users\\HP\\Desktop\\Mercury_data .xlsx");
        XSSFWorkbook Wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = Wb.getSheet("Sheet1");

        int RowNm = sheet.getLastRowNum();
        int ColNm = sheet.getRow(1).getLastCellNum();

        //System.out.println("Row Number " + RowNm);
        //System.out.println("Column Number " + ColNm);

        for (int i = 1; i < RowNm + 1; i++) {
            //System.out.println(i+ "For loup Start");
            XSSFRow Row = sheet.getRow(i);
            String First_Name = Row.getCell(0).getStringCellValue();
            String Last_Name = Row.getCell(1).getStringCellValue();

            //Casting
            XSSFCell PhoneS = Row.getCell(2);
            int Phone = (int) PhoneS.getNumericCellValue();

            String Email = Row.getCell(3).getStringCellValue();
            String Address = Row.getCell(4).getStringCellValue();
            String City = Row.getCell(5).getStringCellValue();
            String State = Row.getCell(6).getStringCellValue();

            XSSFCell Postal_codeS = Row.getCell(7);
            int Postal_code = (int) Postal_codeS.getNumericCellValue();

            String Country = Row.getCell(8).getStringCellValue();
            String Username = Row.getCell(9).getStringCellValue();
            String Password = Row.getCell(10).getStringCellValue();

            //Locators
            driver.findElement(By.name("firstName")).clear();
            driver.findElement(By.name("firstName")).sendKeys(First_Name);

            driver.findElement(By.name("lastName")).clear();
            driver.findElement(By.name("lastName")).sendKeys(Last_Name);

            driver.findElement(By.name("phone")).clear();
            driver.findElement(By.name("phone")).sendKeys(String.valueOf(Phone));

            driver.findElement(By.xpath("//input[@id='userName']")).clear();
            driver.findElement(By.xpath("//input[@id='userName']")).sendKeys(Email);

            driver.findElement(By.name("address1")).clear();
            driver.findElement(By.name("address1")).sendKeys(Address);

            driver.findElement(By.name("city")).clear();
            driver.findElement(By.name("city")).sendKeys(City);

            driver.findElement(By.name("state")).clear();
            driver.findElement(By.name("state")).sendKeys(State);

            driver.findElement(By.name("postalCode")).clear();
            driver.findElement(By.name("postalCode")).sendKeys(String.valueOf(Postal_code));


            Select country = new Select(driver.findElement(By.name("country")));
            country.selectByVisibleText(Country);

            driver.findElement(By.id("email")).clear();
            driver.findElement(By.id("email")).sendKeys(Username);

            driver.findElement(By.name("password")).clear();
            driver.findElement(By.name("password")).sendKeys(Password);

            driver.findElement(By.name("confirmPassword")).clear();
            driver.findElement(By.name("confirmPassword")).sendKeys(Password);

            //Click on Submit Button
            driver.findElement(By.xpath("//tbody/tr[17]/td[1]/input[1]/parent::td/child::input")).click();

            String data1 = "Thank you for registering. You may now sign-in using the user name and password you've just entered.";
            String data = driver.findElement(By.xpath("//tbody/tr[3]/td[1]/p[2]/font[1]")).getText();
            //System.out.println(data1);
            //System.out.println(data);

            //If registration Done successfully hard Assertion
            Assert.assertEquals(data, data1);
            System.out.println(First_Name + "'s Registration done");

            sheet.getRow(i).createCell(12).setCellValue(First_Name + " Registered Successfully...");
            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\HP\\Desktop\\Mercury_data .xlsx");
            Wb.write(outputStream);

            //open url again for new entry
            if (i < RowNm) {
                driver.navigate().to("http://demo.guru99.com/test/newtours/register.php");
                driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
            }
            //If last iteration
            if (i == RowNm) {
                Wb.close();
                outputStream.close();
            }

        }

    }
}

