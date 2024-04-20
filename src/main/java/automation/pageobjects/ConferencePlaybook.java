package automation.pageobjects;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import automation.abstractcomponent.abstractComponent1;


public class ConferencePlaybook extends abstractComponent1{
	enum Month {
		Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec
	}
	
	WebDriver driver;
	
	public ConferencePlaybook(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this); 
	}
	
	@FindBy (xpath="//div[@class='spinner-border']")
	WebElement spinner;
	
	@FindBy (xpath= "//table[@class='dataTable no-footer']/thead/tr/th[1]")
	WebElement nameConference;
	
	
	@FindBy (xpath= "//tr//th[3]")
	WebElement monthConference;
	
	@FindBy (xpath= "//tr//th[4]")
	WebElement lastUpdatedConference;
	
	@FindBy (id="conferenceIrxSearchFilterButton")
	WebElement filterConference;
	
	@FindBy (id="conferenceListResetButton")
	WebElement resetConference;
	
	@FindBy (id="conferenceListSearchButton")
	WebElement searchConference;
	
	@FindBy (id="keyword")
	WebElement sKeywordConference;
	
	@FindBy (id="conferencemonthAutosuggestValueContainer")
	WebElement monthContainer;
	
	@FindBy (css="input[data-action='conferencemonthAutosuggestSearch']")
	WebElement monthAutosuggest;
	
	@FindBy (xpath="//label[normalize-space()='Jun']")
	WebElement monthSelect;
	
	@FindBy (id="conferenceyearAutosuggestValueContainer")
	WebElement yearContainer;
	
	@FindBy (css="input[data-action='conferenceyearAutosuggestSearch']")
	WebElement yearAutosuggest;
	
	@FindBy (xpath="//label[normalize-space()='2023']")
	WebElement yearSelect;
	
	@FindBy (xpath="//span[@aria-label='Clear All']")
	WebElement deleteIcon;
	
	@FindBy (id="conferenceTable_paginate_select")
	WebElement pageDropdownConference;
	
	@FindBy (xpath="//select[@id='conferenceTable_paginate_select']/option")
	List<WebElement> pageList;
	
	
	public void sort() {
		nameConference.click();
		waitForElementToDisappear(spinner);
		List<WebElement> nameElements = driver.findElements(By.xpath("//tr//td[1]//div//a"));
		sort(nameElements);
		
		nameConference.click();
		waitForElementToDisappear(spinner);
		List<WebElement> nameElements1 = driver.findElements(By.xpath("//tr//td[1]//div//a"));
		reverseSort(nameElements1);
	
		monthConference.click();
		waitForElementToDisappear(spinner);
		List<WebElement> monthElements = driver.findElements(By.xpath("//table[@id=\"conferenceTable\"]/tbody/tr/td[3]"));
		monthSort(monthElements);
		
		monthConference.click();
		waitForElementToDisappear(spinner);
		List<WebElement> monthElements1 = driver.findElements(By.xpath("//table[@id=\"conferenceTable\"]/tbody/tr/td[3]"));
		monthReverseSort(monthElements1);
	
		lastUpdatedConference.click();
		waitForElementToDisappear(spinner);
		List<WebElement> lastUpdatedElements = driver.findElements(By.xpath("//table[@id=\"conferenceTable\"]/tbody/tr/td[4]"));
		lastUpdatedSort(lastUpdatedElements);
		
		lastUpdatedConference.click();
		waitForElementToDisappear(spinner);
		List<WebElement> lastUpdatedElements1 = driver.findElements(By.xpath("//table[@id=\"conferenceTable\"]/tbody/tr/td[4]"));
		lastUpdatedReverseSort(lastUpdatedElements1);
	}
	
	public void filter(String keyword, String month, String year) {
		filterConference.click();
		sKeywordConference.sendKeys(keyword);
		searchConference.click();
		waitForElementToDisappear(spinner);
		List<WebElement> filterKeywordlist = driver.findElements(By.xpath("//table[@id=\"conferenceTable\"]/tbody/tr/td[1]"));
		String filterKeywordWord = keyword;
		filter(filterKeywordlist, filterKeywordWord);
	
		filterConference.click();
		resetConference.click();
		waitForElementToDisappear(spinner);
		monthContainer.click();
		monthAutosuggest.sendKeys(month);
		monthSelect.click();
		searchConference.click();
		waitForElementToDisappear(spinner);
		List<WebElement> filterMonthlist = driver.findElements(By.xpath("//table[@id=\"conferenceTable\"]/tbody/tr/td[3]"));
		String filterMonthWord = month;
		filter(filterMonthlist, filterMonthWord);
	
		filterConference.click();
		resetConference.click();
		waitForElementToDisappear(spinner);
		yearContainer.click();
		yearAutosuggest.sendKeys(year);
		yearSelect.click();
		searchConference.click();
		waitForElementToDisappear(spinner);
		List<WebElement> filterYearlist = driver.findElements(By.xpath("//table[@id=\"conferenceTable\"]/tbody/tr/td[1]"));
		String filterYearWord = year;
		filter(filterYearlist, filterYearWord);
		deleteIcon.click();
		waitForElementToDisappear(spinner);
	}
	
	public void pagination() {
		String totalEntriesString= driver.findElement(By.xpath("//div[@id=\"conferenceTable_info\"]/span")).getText(); 
		  int totalEntries= Integer.parseInt(totalEntriesString);
		  int noOfPages= pageList.size();
		  List<String> allpageElements = new ArrayList<>(); 
		  for(int i=1; i<=noOfPages;i++)
		   { 
			  pageDropdownConference.click();
			  String paginationSelector= "select[id='conferenceTable_paginate_select'] option[value='"+i+"']";
			  driver.findElement(By.cssSelector(paginationSelector)).click();
			  waitForElementToDisappear(spinner);
			  List<WebElement>nameElements1 = driver.findElements(By.xpath("//tr//td[1]//div//a"));
			  for(WebElement nameElement: nameElements1) {
				 allpageElements.add(nameElement.getText());
				 }
			}
		    int totalPageEntries= allpageElements.size();
			Assert.assertEquals(totalEntries,totalPageEntries);
		}
	
	
		
}
