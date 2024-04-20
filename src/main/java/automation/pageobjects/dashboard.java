package automation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.abstractcomponent.abstractComponent1;


public class dashboard extends abstractComponent1{
	WebDriver driver;
	public dashboard(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy (xpath="//div[@class='spinner-border']")
	WebElement spinner;
	
	@FindBy (linkText= "Conference")
	WebElement dropdownConference;
	
	@FindBy (linkText= "Playbooks")
	WebElement dropdownPlaybook;
	
	@FindBy (id="conferenceIrxSearchFilterButton")
	WebElement filterConference;
	
	@FindBy (id="keyword")
	WebElement sKeywordConference;
	
	@FindBy (id="conferenceListSearchButton")
	WebElement searchConference;
	
	@FindBy (xpath="//a[contains(text(),'AACR 2023')]")
	WebElement selectPlaybook;
	
	
	public ConferencePlaybook goToConference() {
		dropdownConference.click();
		dropdownPlaybook.click();
		waitForElementToDisappear(spinner);
		ConferencePlaybook cplaybook= new ConferencePlaybook(driver);
		return cplaybook;
	}
	
	
	public Playbook gotoPlaybook(String conference) {
		dropdownConference.click();
		dropdownPlaybook.click();
		waitForElementToDisappear(spinner);
		filterConference.click();
		sKeywordConference.sendKeys(conference);
		searchConference.click();
		waitForElementToDisappear(spinner);
		selectPlaybook.click();
		waitForElementToDisappear(spinner);
		driver.close();
		String currentChildWindow = null;
		currentChildWindow = switchWindow(currentChildWindow, driver);
		Playbook playbook= new Playbook(driver);
		return playbook;
	}
	
	
}
