package automation.pageobjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import automation.abstractcomponent.abstractComponent1;

public class Playbook extends abstractComponent1{
	WebDriver driver;
	
	public Playbook(WebDriver driver) {
		super(driver);
		this.driver=  driver;
	}
	
	@FindBy (xpath="//th[contains(text(),'Title')]")
	WebElement titlePlaybook;
	
	@FindBy (xpath="//th[contains(text(),'Type')]")
	WebElement typePlaybook;
	
	@FindBy (xpath="(//th[contains(text(),'Last Updated At')])[1]")
	WebElement lastUpdatedPlaybook;
	
	@FindBy (xpath="//table[@id=\"playbookTable\"]/tbody/tr/td[1]")
	List <WebElement> titleElements;
	
	@FindBy (xpath="//table[@id=\"playbookTable\"]/tbody/tr/td[2]")
	List <WebElement> typeElements;
	
	@FindBy (xpath="//table[@id=\"playbookTable\"]/tbody/tr/td[5]")
	List <WebElement> lastUpdatedElements;
	
	@FindBy (id= "playbookIrxSearchFilterButton")
	WebElement filterPlaybook;
	
	@FindBy (id= "keyword")
	WebElement sKeywordPlaybook;
	
	@FindBy (id="playbookListSearchButton")
	WebElement searchPlaybook;
	
	@FindBy (id="playbookListResetButton")
	WebElement resetPlaybook;
	
	@FindBy (id="daterange")
	WebElement dateRange;
	
	@FindBy (xpath="//input[@data-action=\"statusAutosuggestSearch\"]")
	WebElement workflowContainer;
	
	@FindBy (xpath="//label[normalize-space()=\"APPROVED\"]")
	WebElement workflowSelect;
	
	@FindBy (xpath="//button[@class=\"btn irx-primary-button mr-2\"]")
	WebElement workflowOkay;
	
	@FindBy (id="statusAutosuggestValueContainer")
	WebElement workflowAutosuggest;
	
	@FindBy (xpath="//div[@class='spinner-border']")
	WebElement spinner;
	
	@FindBy (xpath="//span[@aria-label='Add Playbook']")
	WebElement addPlaybook;
	
	@FindBy (linkText="Automatic Playbook")
	WebElement automaticPlaybook;
	
	@FindBy (xpath="//div[@id='playbookViewTable_info']/span")
	WebElement playbookNoEntries;
	
	
	public void sortPlaybook() {
		titlePlaybook.click();
		waitForElementToDisappear(spinner);
		sort(titleElements);
		titlePlaybook.click();
		waitForElementToDisappear(spinner);
		reverseSort(titleElements);
		
		typePlaybook.click();
		waitForElementToDisappear(spinner);
		sort(typeElements);
		typePlaybook.click();
		waitForElementToDisappear(spinner);
		reverseSort(typeElements);
		
		lastUpdatedPlaybook.click();
		waitForElementToDisappear(spinner);
		lastUpdatedSort(lastUpdatedElements);
		lastUpdatedPlaybook.click();
		waitForElementToDisappear(spinner);
		lastUpdatedReverseSort(lastUpdatedElements);
	}
	
	public void filterPlaybook(String playbookFilter, String workflow) {
		filterPlaybook.click();
		sKeywordPlaybook.sendKeys(playbookFilter);
		searchPlaybook.click();
		waitForElementToDisappear(spinner);
		List<WebElement> filtertitleList= driver.findElements(By.xpath("//table[@id=\"playbookTable\"]//tr/td[1]"));
		filter(filtertitleList, playbookFilter);
		
		filterPlaybook.click();
		resetPlaybook.click();
		waitForElementToDisappear(spinner);
		workflowAutosuggest.click();
		workflowContainer.sendKeys(workflow);
		waitForElementToDisappear(spinner);
		workflowSelect.click();
		workflowOkay.click();
		searchPlaybook.click();
		waitForElementToDisappear(spinner);
		List<WebElement> filterWorkflowList= driver.findElements(By.xpath("//table[@id=\"playbookTable\"]//tr/td[3]"));
		filter(filterWorkflowList, workflow);
		
		filterPlaybook.click();
		resetPlaybook.click();
		waitForElementToDisappear(spinner);
	}
	

	public void addPlaybook(String playbookFilter) throws InterruptedException {
		addPlaybook.click();
		automaticPlaybook.click();
		waitForElementToDisappear(spinner);
		String parentWindow =driver.getWindowHandle();
		String currentChildWindow = null;
		currentChildWindow = switchWindow(currentChildWindow, driver);
		Thread.sleep(2000);
		AddPlaybook aplaybook= new AddPlaybook(driver);
		Map<String, String> data = aplaybook.goToPlaybookData(playbookFilter);
		String actualTitle = data.get("actualTitle");
		String automaticEntries = data.get("automaticEntries");
		driver.switchTo().window(parentWindow);
		driver.navigate().refresh();
		waitForElementToDisappear(spinner);
		WebElement recordAddress= driver.findElement(By.xpath("//table[@id='playbookTable']//tr[1]/td[1]"));
		String expectedTitle =recordAddress.getText();
		Assert.assertEquals(actualTitle, expectedTitle);
		driver.findElement(By.xpath("//table[@id=\"playbookTable\"]//tr[1]/td[1]/div/div/a")).click();
		waitForElementToDisappear(spinner);
		currentChildWindow = switchWindow(currentChildWindow, driver);
		Thread.sleep(3000);
		String playbookEntries= playbookNoEntries.getText();
		size(automaticEntries,playbookEntries);
		driver.close();
		driver.switchTo().window(parentWindow);
	}
	
	
}
