package TestClass;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.Base;
import Pages.Courses;
import Pages.FillingForm;
import Pages.HomePage;
import Pages.Languages;
import Pages.Levels;

public class NewTest {

	static Logger logger = Logger.getLogger(NewTest.class);
	WebDriver driver;
	HomePage homepage;
	Courses courses;
	Languages languages;
	Levels levels;
	FillingForm form;

	@BeforeClass(groups={"smoke","regression"})
	@Parameters({ "browser" })
	public void setUp(String br) {

		Base b = new Base();
		driver = b.driverSetup(br);

	}

	@Test(priority = 0, groups= {"smoke","regression"})
	public void openWebsite() {
		homepage = new HomePage(driver);

		homepage.openURL();
		String actualUrl = homepage.getUrl();
		String expectedUrl = "https://www.coursera.org/";
		Assert.assertEquals(expectedUrl, actualUrl);
	}

	@Test(priority = 1, dependsOnMethods= {"openWebsite"}, groups= {"smoke","regression"})
	public void locateSearchBox() throws InterruptedException {
		boolean searchBoxVisible = homepage.isSearchBoxVisible();
		assertTrue(searchBoxVisible);
	}

	@Test(priority = 2, dependsOnMethods= {"openWebsite"}, groups= {"smoke","regression"})
	public void autoSuggestion() throws InterruptedException {
		int size = homepage.autoSuggestionListSize();
		homepage.click();
		assertTrue(size > 0);
	}

	@Test(priority = 3, dependsOnMethods= {"autoSuggestion"}, groups= {"smoke","regression"})
	public void courseLanguageSelection() throws InterruptedException {
		courses = new Courses(driver);

		WebElement languageSelected = courses.languageSelection();
		assertTrue(languageSelected.isEnabled());
		languageSelected.click();
	}

	@Test(priority = 4, dependsOnMethods= {"autoSuggestion"}, groups= {"smoke","regression"})
	public void courseLevelSelection() throws InterruptedException {
		WebElement levelSelected = courses.levelSelection();
		assertTrue(levelSelected.isEnabled());
		levelSelected.click();
	}

	@Test(priority = 5, dependsOnMethods= {"courseLanguageSelection","courseLevelSelection"}, groups= {"regression"})
	public void displayFirstCourseDetails() throws InterruptedException {
		boolean res = courses.isFirstCourseDisplayed();
		assertTrue(res);
	}

	@Test(priority = 6, dependsOnMethods= {"courseLanguageSelection","courseLevelSelection"}, groups= {"regression"})
	public void displaySecondCourseDetails() throws InterruptedException {
		boolean res = courses.isSecondCourseDisplayed();
		assertTrue(res);
	}

	@Test(priority = 7, dependsOnMethods= {"autoSuggestion"}, groups= {"regression"})
	public void displayListOfLanguages() throws InterruptedException {
		languages = new Languages(driver);
		assertTrue(languages.sizeOfListOfLanguages() > 0);
	}

	@Test(priority = 8, dependsOnMethods= {"autoSuggestion"}, groups= {"regression"})
	public void displayListOfLevel() {
		levels = new Levels(driver);

		assertTrue(levels.sizeOfListOfLevel() > 0);
	}

	@Test(priority = 9, dependsOnMethods= {"autoSuggestion"}, groups= {"regression"})
	public void newTabSwitch() throws InterruptedException {
		assertTrue(courses.isCourseOpenedInNewTab());
	}

	@Test(priority = 10, groups= {"smoke","regression"})
	public void navigateBackToHome() {
		courses.returnToHomePage();
		String actualUrl = homepage.getUrl();
		String expectedUrl = "https://www.coursera.org/";
		Assert.assertEquals(expectedUrl, actualUrl);
	}

	@Test(priority = 11, dependsOnMethods= {"navigateBackToHome"}, groups= {"smoke","regression"})
	public void locateForEnterprise() {
		assertTrue(driver.findElement(By.linkText("For Enterprise")).isDisplayed());
		driver.findElement(By.linkText("For Enterprise")).click();
	}

	@Test(priority = 12, dependsOnMethods= {"locateForEnterprise"}, groups= {"smoke","regression"})
	public void locateCourseForCampus() throws InterruptedException {
		assertTrue(driver.findElement(By.linkText("For Campus")).isDisplayed());
		driver.findElement(By.linkText("For Campus")).click();
	}

	@Test(priority = 16, dependsOnMethods= {"locateCourseForCampus"}, groups= {"regression"})
	public void locateForm() throws InterruptedException {

		boolean res = driver.findElement(By.id("mktoForm_1512")).isDisplayed();
		assertTrue(res);
	}

	@Test(priority = 13, dependsOnMethods= {"locateCourseForCampus"}, groups= {"smoke","regression"})
	public void errorOnInvalidEmail() throws InterruptedException {

		form = new FillingForm(driver);

		boolean res = form.getErrorMsgOnInvalidEmail();
		assertTrue(res);
	}

	@Test(priority = 14, dependsOnMethods= {"locateCourseForCampus"}, groups= {"regression"})
	public void errorOnInvalidMobile() throws InterruptedException {
		boolean res = form.getErrorMsgOnInvalidPhone();
		assertTrue(res);
	}

	@Test(priority = 15, dependsOnMethods= {"locateCourseForCampus"}, groups= {"regression"})
	public void errorOnBlankFields() throws InterruptedException {
		boolean res = form.getErrorMsgOnNotFilling();
		assertTrue(res);
	}

	@Test(priority = 17, dependsOnMethods= {"locateCourseForCampus"}, groups= {"regression"})
	public void locateStateOnSelectingIndia() {
		assertTrue(driver.findElement(By.xpath("//*[@id=\"State\"]")).isDisplayed());
	}

	@Test(priority = 18, dependsOnMethods= {"locateCourseForCampus"}, groups= {"smoke","regression"})
	public void submitFormVerification() throws InterruptedException {
		
		boolean res = form.getSuccessMsgOnValidData();
		assertTrue(res);
	}

	@AfterClass(groups= {"smoke","regression"})
	public void afterClass() {
		driver.quit();
	}

}
