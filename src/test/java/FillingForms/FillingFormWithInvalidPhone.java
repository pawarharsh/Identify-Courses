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

public class FillingFormWithInvalidPhone extends Base {
	
	WebDriver driver;

	public FillingFormWithInvalidPhone(WebDriver driver) {
		this.driver=driver;
	}

	public String getMsg() throws InterruptedException {

		
		WebElement web = driver.findElement(By.xpath("//*[@id=\"ValidMsgPhone\"]"));
		

		String ErrorMsg = web.getText(); // Text of the error message is stored here
		Thread.sleep(1000);
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		String sc="C:\\Users\\2373652\\eclipse-workspace\\Hackathon\\Screenshots\\Screenshot_of_empty_Invalid_Phone.png";
		try {
			FileUtils.copyFile(screenshot, new File(sc));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		System.out.println("--------Invalid Phone Erro msg--------");
		System.out.println();
		System.out.println(ErrorMsg); // Error message is printed on console
		System.out.println();
		System.out.println("--------------------------------------");
		return ErrorMsg;
	}


}
