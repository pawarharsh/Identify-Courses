package Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Base;

public class Courses extends Base {

	WebDriver driver;
	public Courses(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[@id=\"cds-react-aria-18\"]")
	WebElement EnglishLanguage;
	
	@FindBy(xpath="//*[@id=\"cds-react-aria-50\"]")
	WebElement BegineerLevel;
	
	@FindBy(xpath="//*[@class='cds-ProductCard-content']//h3")
	List<WebElement> heading;
	
	@FindBy(xpath="//*[@class='cds-ProductCard-content']")
	List<WebElement> courses;
	
	@FindBy(xpath="//*[@class='cds-CommonCard-ratings']/div/div/span")
	List<WebElement> ratings;
	
	@FindBy(xpath="//*[@class='cds-CommonCard-metadata']/p")
	List<WebElement> description;
	
	public WebElement languageSelection() throws InterruptedException {
		Thread.sleep(3000);
		return EnglishLanguage;
	}
	
	public WebElement levelSelection() throws InterruptedException {
		Thread.sleep(3000);
		return BegineerLevel;
	}
	
	public boolean isFirstCourseDisplayed() throws InterruptedException {
		Thread.sleep(5000);
		System.out.println("------------- COURSE 1 ---------------");
		System.out.println();
		System.out.println(heading.get(0).getText());
		System.out.println(ratings.get(0).getText());
		System.out.println(description.get(0).getText());
		System.out.println();
		System.out.println("--------------------------------------");
		if(heading.get(0).getText()!=null) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean isSecondCourseDisplayed() throws InterruptedException {
		System.out.println("----------- COURSE 2 -----------------");
		System.out.println();
		System.out.println(heading.get(1).getText());
		System.out.println(ratings.get(1).getText());
		System.out.println(description.get(1).getText());
		System.out.println();
		System.out.println("--------------------------------------");
		if(heading.get(1).getText()!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void returnToHomePage() {
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();
	}
	
	public boolean isCourseOpenedInNewTab() throws InterruptedException {
		
		courses.get(0).click();
		Set<String> windows=driver.getWindowHandles();
		List<String> windowsList=new ArrayList<String>(windows);
		String parent=windowsList.get(0);
		String child=windowsList.get(1);
		String beforeSwitchingTitle = driver.getTitle();
		driver.switchTo().window(child);
		Thread.sleep(3000);
		String afterSwitchingTitle = driver.getTitle();
		driver.close();
		driver.switchTo().window(parent);
		Thread.sleep(3000);
		if(beforeSwitchingTitle.equals(afterSwitchingTitle)) {
			return false;
		}
		else {
			return true;
		}
	}
}
