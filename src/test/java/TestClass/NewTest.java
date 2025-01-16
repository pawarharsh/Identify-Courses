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

	// Setup method to initialize WebDriver before tests
	@BeforeClass(groups = { "smoke", "regression" })
	@Parameters({ "browser" })
	public void setUp(String br) {
		Base b = new Base();
		driver = b.driverSetup(br);
	}

	// Test to open the website and verify the URL
	@Test(priority = 0, groups = { "smoke", "regression" })
	public void openWebsite() {
		homepage = new HomePage(driver);
		homepage.openURL();
		String actualUrl = homepage.getUrl();
		String expectedUrl = "https://www.coursera.org/";
		Assert.assertEquals(expectedUrl, actualUrl);
	}

	// Test to locate the search box and verify its visibility
	@Test(priority = 1, dependsOnMethods = { "openWebsite" }, groups = { "smoke", "regression" })
	public void locateSearchBox() throws InterruptedException {
		boolean searchBoxVisible = homepage.isSearchBoxVisible();
		assertTrue(searchBoxVisible);
	}

	// Test to check the size of the auto-suggestion list and click on a suggestion
	@Test(priority = 2, dependsOnMethods = { "openWebsite" }, groups = { "smoke", "regression" })
	public void autoSuggestion() throws InterruptedException {
		int size = homepage.autoSuggestionListSize();
		homepage.click();
		assertTrue(size > 0);
	}

	// Test to select a course language and verify the selection
	@Test(priority = 3, dependsOnMethods = { "autoSuggestion" }, groups = { "smoke", "regression" })
	public void courseLanguageSelection() throws InterruptedException {
		courses = new Courses(driver);
		WebElement languageSelected = courses.languageSelection();
		assertTrue(languageSelected.isEnabled());
		languageSelected.click();
	}

	// Test to select a course level and verify the selection
	@Test(priority = 4, dependsOnMethods = { "autoSuggestion" }, groups = { "smoke", "regression" })
	public void courseLevelSelection() throws InterruptedException {
		WebElement levelSelected = courses.levelSelection();
		assertTrue(levelSelected.isEnabled());
		levelSelected.click();
	}

	// Test to display details of the first course and verify its visibility
	@Test(priority = 5, dependsOnMethods = { "courseLanguageSelection", "courseLevelSelection" }, groups = {
			"regression" })
	public void displayFirstCourseDetails() throws InterruptedException {
		boolean res = courses.isFirstCourseDisplayed();
		assertTrue(res);
	}

	// Test to display details of the second course and verify its visibility
	@Test(priority = 6, dependsOnMethods = { "courseLanguageSelection", "courseLevelSelection" }, groups = {
			"regression" })
	public void displaySecondCourseDetails() throws InterruptedException {
		boolean res = courses.isSecondCourseDisplayed();
		assertTrue(res);
	}

	// Test to display the list of languages and verify its size
	@Test(priority = 7, dependsOnMethods = { "autoSuggestion" }, groups = { "regression" })
	public void displayListOfLanguages() throws InterruptedException {
		languages = new Languages(driver);
		assertTrue(languages.sizeOfListOfLanguages() > 0);
	}

	// Test to display the list of levels and verify its size
	@Test(priority = 8, dependsOnMethods = { "autoSuggestion" }, groups = { "regression" })
	public void displayListOfLevel() {
		levels = new Levels(driver);
		assertTrue(levels.sizeOfListOfLevel() > 0);
	}

	// Test to verify if a course is opened in a new tab
	@Test(priority = 9, dependsOnMethods = { "autoSuggestion" }, groups = { "regression" })
	public void newTabSwitch() throws InterruptedException {
		assertTrue(courses.isCourseOpenedInNewTab());
	}

	// Test to navigate back to the home page and verify the URL
	@Test(priority = 10, groups = { "smoke", "regression" })
	public void navigateBackToHome() {
		courses.returnToHomePage();
		String actualUrl = homepage.getUrl();
		String expectedUrl = "https://www.coursera.org/";
		Assert.assertEquals(expectedUrl, actualUrl);
	}

	// Test to locate the "For Enterprise" link and verify its visibility
	@Test(priority = 11, dependsOnMethods = { "navigateBackToHome" }, groups = { "smoke", "regression" })
	public void locateForEnterprise() {
		assertTrue(driver.findElement(By.linkText("For Enterprise")).isDisplayed());
		driver.findElement(By.linkText("For Enterprise")).click();
	}

	// Test to locate the "For Campus" link and verify its visibility
	@Test(priority = 12, dependsOnMethods = { "locateForEnterprise" }, groups = { "smoke", "regression" })
	public void locateCourseForCampus() throws InterruptedException {
		assertTrue(driver.findElement(By.linkText("For Campus")).isDisplayed());
		driver.findElement(By.linkText("For Campus")).click();
	}

	// Test to verify if the form is displayed
	@Test(priority = 16, dependsOnMethods = { "locateCourseForCampus" }, groups = { "regression" })
	public void locateForm() throws InterruptedException {
		boolean res = driver.findElement(By.id("mktoForm_1512")).isDisplayed();
		assertTrue(res);
	}

	// Test to verify if the error message is displayed for invalid email
	@Test(priority = 13, dependsOnMethods = { "locateCourseForCampus" }, groups = { "smoke", "regression" })
	public void errorOnInvalidEmail() throws InterruptedException {
		form = new FillingForm(driver);
		boolean res = form.getErrorMsgOnInvalidEmail();
		assertTrue(res);
	}

	// Test to verify if the error message is displayed for invalid mobile number
	@Test(priority = 14, dependsOnMethods = { "locateCourseForCampus" }, groups = { "regression" })
	public void errorOnInvalidMobile() throws InterruptedException {
		boolean res = form.getErrorMsgOnInvalidPhone();
		assertTrue(res);
	}

	// Test to verify if the error message is displayed for blank fields
	@Test(priority = 15, dependsOnMethods = { "locateCourseForCampus" }, groups = { "regression" })
	public void errorOnBlankFields() throws InterruptedException {
		boolean res = form.getErrorMsgOnNotFilling();
		assertTrue(res);
	}

	// Test to verify if the state dropdown is displayed when India is selected
	@Test(priority = 17, dependsOnMethods = { "locateCourseForCampus" }, groups = { "regression" })
	public void locateStateOnSelectingIndia() {
		assertTrue(driver.findElement(By.xpath("//*[@id=\"State\"]")).isDisplayed());
	}

	// Test to verify if the form submission is successful with valid data
	@Test(priority = 18, dependsOnMethods = { "locateCourseForCampus" }, groups = { "smoke", "regression" })
	public void submitFormVerification() throws InterruptedException {
		boolean res = form.getSuccessMsgOnValidData();
		assertTrue(res);
	}

	// Method to quit the WebDriver after all tests are completed
	@AfterClass(groups = { "smoke", "regression" })
	public void afterClass() {
		driver.quit();
	}

}