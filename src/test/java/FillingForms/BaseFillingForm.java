package FillingForms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Base.Base;
import UtilFiles.ExcelUtils;

public class BaseFillingForm extends Base {
    
    private String filepath;
    private String sheetName;

    WebDriver driver;

    // Constructor to initialize WebDriver and PageFactory elements
    public BaseFillingForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    // WebElement for first name input field
    @FindBy(xpath = "//*[@id=\"FirstName\"]")
    WebElement firstName;
    
    // WebElement for last name input field
    @FindBy(xpath = "//*[@id=\"LastName\"]")
    WebElement lastName;
    
    // WebElement for email input field
    @FindBy(xpath = "//*[@id=\"Email\"]")
    WebElement email;
    
    // WebElement for phone input field
    @FindBy(xpath = "//*[@id=\"Phone\"]")
    WebElement phone;
    
    // WebElement for company input field
    @FindBy(xpath = "//*[@id=\"Company\"]")
    WebElement company;
    
    // WebElement for institution type dropdown
    @FindBy(xpath = "//*[@id=\"Institution_Type__c\"]")
    WebElement institute;
    
    // WebElement for job role dropdown
    @FindBy(xpath = "//*[@id=\"Title\"]")
    WebElement jobRole;
    
    // WebElement for department dropdown
    @FindBy(xpath = "//*[@id=\"Department\"]")
    WebElement department;
    
    // WebElement for describes dropdown
    @FindBy(xpath = "//*[@id=\"What_the_lead_asked_for_on_the_website__c\"]")
    WebElement describes;
    
    // WebElement for country dropdown
    @FindBy(xpath = "//*[@id=\"Country\"]")
    WebElement country;
    
    // WebElement for submit button
    @FindBy(xpath = "//*[@id=\"mktoForm_1512\"]/div[50]/span/button")
    WebElement submit;
    
    // Method to enter data into the form fields
    public void dataEntry(String filepath, String sheetName) throws InterruptedException {
        this.filepath = filepath;
        this.sheetName = sheetName;
        
        // Enter data into form fields using data from Excel file
        firstName.sendKeys(cellData(1, 0));
        lastName.sendKeys(cellData(1, 1));
        email.sendKeys(cellData(1, 2));
        phone.sendKeys(cellData(1, 3));
        company.sendKeys(cellData(1, 4));
        
        // Select values from dropdowns
        Select selectInstitute = new Select(institute);
        selectInstitute.selectByVisibleText(cellData(1, 5));
        
        Select selectJobRole = new Select(jobRole);
        selectJobRole.selectByVisibleText(cellData(1, 6));
        
        Select selectDepartment = new Select(department);
        selectDepartment.selectByValue(cellData(1, 7));
        
        Select selectDescribes = new Select(describes);
        selectDescribes.selectByValue(cellData(1, 8));
        
        Select selectCountry = new Select(country);
        selectCountry.selectByValue(cellData(1, 9));
        
        // Click the submit button
        submit.click();
        Thread.sleep(10000);
    }

    // Method to get cell data from Excel file
    public String cellData(int rowNum, int colNum) {
        ExcelUtils excel = new ExcelUtils(filepath, sheetName);
        return excel.getCellData(rowNum, colNum);
    }
}
