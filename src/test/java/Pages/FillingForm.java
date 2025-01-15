package Pages;

import org.openqa.selenium.WebDriver;

import Base.Base;
import FillingForms.BaseFillingForm;
import FillingForms.FillingFormSuccessfully;
import FillingForms.FillingFormWithEmptyFeilds;
import FillingForms.FillingFormWithInvalidEmail;
import FillingForms.FillingFormWithInvalidPhone;

public class FillingForm extends Base{
	
	FillingFormWithInvalidPhone obj1; 
	FillingFormWithInvalidEmail obj2;
	FillingFormWithEmptyFeilds obj3;
	FillingFormSuccessfully obj4;
	BaseFillingForm form;
	String filepath = "src\\test\\resources\\EmptyField.xlsx";
	String filepath1 = "src\\test\\resources\\ValidData.xlsx";
	String filepath2 = "src\\test\\resources\\InvalidMobile.xlsx";
	String filepath3 = "src\\test\\resources\\InvalidEmail.xlsx";
	
	String sheetName = "Sheet1";
	
	WebDriver driver;
	public FillingForm(WebDriver driver) {
		this.driver=driver;
	}

	public boolean getErrorMsgOnInvalidEmail() throws InterruptedException {
		form = new BaseFillingForm(driver);
		Thread.sleep(6000);
		form.dataEntry(filepath3, sheetName);
		obj2 = new FillingFormWithInvalidEmail(driver);
		if(obj2.getMsg().contains("Must be valid email")) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean getErrorMsgOnInvalidPhone() throws InterruptedException {
		
		driver.navigate().refresh();
		form = new BaseFillingForm(driver);
		Thread.sleep(6000);
		form.dataEntry(filepath2, sheetName);
		obj1 = new FillingFormWithInvalidPhone(driver);
		if(obj1.getMsg().contains("Must be a phone number")) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean getErrorMsgOnNotFilling() throws InterruptedException {
		driver.navigate().refresh();
		form = new BaseFillingForm(driver);
		Thread.sleep(6000);
		form.dataEntry(filepath, sheetName);
		obj3 = new FillingFormWithEmptyFeilds(driver);
		if(obj3.getMsg().contains("This field is required")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean getSuccessMsgOnValidData() throws InterruptedException {
		driver.navigate().refresh();
		form = new BaseFillingForm(driver);
		Thread.sleep(6000);
		form.dataEntry(filepath1, sheetName);
		obj4 = new FillingFormSuccessfully(driver);
		if(obj4.getMsg().equalsIgnoreCase("success")) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
