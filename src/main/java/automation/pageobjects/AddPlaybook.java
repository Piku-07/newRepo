package automation.pageobjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import automation.abstractcomponent.abstractComponent1;

public class AddPlaybook extends abstractComponent1{
	WebDriver driver;

	public AddPlaybook(WebDriver driver) {
		super(driver);
		this.driver=  driver;
	}
	
	@FindBy (xpath="//div[@class='spinner-border']")
	WebElement spinner;
	
	@FindBy (xpath="(//span[@data-key='sessionId'][@onclick='playbookTriggerAutosuggest(this);'])[1]")
	WebElement sessionThreeDots;
	
	@FindBy (xpath="//input[@data-action='playbookAutosuggestSearch']")
	WebElement sessionAutoSuggest;
	
	@FindBy (xpath="//label[text()='ED008']")
	WebElement sessionFilterValue;
	
	@FindBy (xpath="//button[@data-action='playbookAutosuggestOkay']")
	WebElement columnOkay;
	
	@FindBy (className="icon-save")
	WebElement saveicon;
	
	@FindBy (id="playbookSaveAsFinalButton")
	WebElement createPlaybook;
	
	@FindBy (id="playbookListTitle")
	WebElement playTitlebox;
	
	@FindBy (id="playbookDataSaveButton")
	WebElement savePlaybook;
	
	public Map<String, String> goToPlaybookData(String playbookFilter) {
		sessionThreeDots.click();
		waitForElementToDisappear(spinner);
		sessionAutoSuggest.sendKeys(playbookFilter);
		waitForElementToDisappear(spinner);
		sessionFilterValue.click();
		columnOkay.click();
		waitForElementToDisappear(spinner);
		List<WebElement> sessionIdData= driver.findElements(By.xpath("//table[@id=\"playbookViewTable\"]//tbody/tr/td[1]"));
		filter(sessionIdData,playbookFilter);
		String automaticEntries= driver.findElement(By.xpath("//div[@id='playbookAddTable_info']/span")).getText();
		saveicon.click();
		createPlaybook.click();
		waitForElementToDisappear(spinner);
		String actualTitle = saveRecord();
		playTitlebox.sendKeys(actualTitle);
		savePlaybook.click();
		waitForElementToDisappear(spinner);
		Map<String, String> data = new HashMap<>();
		data.put("automaticEntries", automaticEntries);
	    data.put("actualTitle", actualTitle);
	    driver.close();
		return data;
	}

}
