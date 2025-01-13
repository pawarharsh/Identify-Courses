package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Base;

public class Levels extends Base {
	
	WebDriver driver;
	public Levels(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="/html/body/div[2]/div/div/main/div[1]/div/div/div/div/div[1]/div/div/div/div/div/div/div/div[4]/div/div[2]/div")
	List<WebElement> levelChecks;

	public int sizeOfListOfLevel() {
//		List<WebElement> levelCheck = driver.findElements(By.xpath("/html/body/div[2]/div/div/main/div[1]/div/div/div/div/div[1]/div/div/div/div/div/div/div/div[4]/div/div[2]/div"));
		System.out.println("------------Levels List---------------");
		System.out.println();
		for (int i = 0; i < levelChecks.size(); i++) {
			System.out.println(levelChecks.get(i).getText());
		}
		System.out.println();
		System.out.println("--------------------------------------");
		return levelChecks.size();
	}

}
