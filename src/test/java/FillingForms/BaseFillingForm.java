package FillingForms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import Base.Base;
import UtilFiles.ExcelUtils;

public class BaseFillingForm extends Base {
	
	private String filepath;
	private String sheetName;

	WebDriver driver;
	public BaseFillingForm(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}

	public void dataEntry(String filepath, String sheetName) throws InterruptedException {
		this.filepath = filepath;
		this.sheetName = sheetName;
		
		driver.findElement(By.xpath("//*[@id=\"FirstName\"]")).sendKeys(cellData(1, 0));
		driver.findElement(By.xpath("//*[@id=\"LastName\"]")).sendKeys(cellData(1, 1));
		driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys(cellData(1, 2));
		driver.findElement(By.xpath("//*[@id=\"Phone\"]")).sendKeys(cellData(1, 3));
		driver.findElement(By.xpath("//*[@id=\"Company\"]")).sendKeys(cellData(1, 4));
		
		Select selectInstitute = new Select(driver.findElement(By.xpath("//*[@id=\"Institution_Type__c\"]")));
		selectInstitute.selectByVisibleText(cellData(1, 5));
		
		Select selectJobRole = new Select(driver.findElement(By.xpath("//*[@id=\"Title\"]")));
		selectJobRole.selectByVisibleText(cellData(1, 6));
		
		Select selectDepartment = new Select(driver.findElement(By.xpath("//*[@id=\"Department\"]")));
		selectDepartment.selectByValue(cellData(1, 7));
		
		Select selectDescribes = new Select(driver.findElement(By.xpath("//*[@id=\"What_the_lead_asked_for_on_the_website__c\"]")));
		selectDescribes.selectByValue(cellData(1, 8));
		
		Select selectCountry = new Select(driver.findElement(By.xpath("//*[@id=\"Country\"]")));
		selectCountry.selectByValue(cellData(1, 9));
		
		driver.findElement(By.xpath("//*[@id=\"mktoForm_1512\"]/div[50]/span/button")).click();
		Thread.sleep(10000);
		
	}

	public String cellData(int rowNum, int colNum) {
		
		ExcelUtils excel = new ExcelUtils(filepath,sheetName);
		return excel.getCellData(rowNum, colNum);
	}
	
}
