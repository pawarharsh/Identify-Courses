package FillingForms;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Base.Base;

public class FillingFormWithInvalidEmail extends Base {

    WebDriver driver;

    // Constructor to initialize WebDriver
    public FillingFormWithInvalidEmail(WebDriver driver) {
        this.driver = driver;
    }

    // Method to get the error message for invalid email
    public String getMsg() throws InterruptedException {

        // Locate the error message element
        WebElement web = driver.findElement(By.xpath("//*[@id=\"ValidMsgEmail\"]"));

        // Get the text of the error message
        String ErrorMsg = web.getText(); // Text of the error message is stored here
        Thread.sleep(1000);

        // Take a screenshot of the error message
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String sc = "Screenshots\\Screenshot_of_invalidEmail.png";
        try {
            FileUtils.copyFile(screenshot, new File(sc));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the error message to the console
        System.out.println("-------Invalid Email Error msg--------");
        System.out.println();
        System.out.println(ErrorMsg); // Error message is printed on console
        System.out.println();
        System.out.println("--------------------------------------");

        // Return the error message
        return ErrorMsg;
    }
}