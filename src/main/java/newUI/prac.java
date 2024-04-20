package newUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class prac {
	enum Month {
		Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec
	}

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		String username = "pjain@prescienthg.com";
		String password = "Mnb@0987";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://qa.inflexionrx.com/authentications/login");
		driver.findElement(By.id("loginUsername")).sendKeys(username);
	
		driver.findElement(By.id("loginPassword")).sendKeys(password + Keys.ENTER);

		driver.findElement(By.linkText("Conference")).click();
		driver.findElement(By.linkText("Playbooks")).click();
		Thread.sleep(5000);
		System.out.println("added");
		// (A)Sorting on Conference Page:
		// (i)sort on name
		
		
		WebElement name = driver.findElement(By.xpath("//table[@class=\"dataTable no-footer\"]/thead/tr/th[1]"));
		name.click();
		Thread.sleep(2000);
		List<WebElement> nameElements = driver.findElements(By.xpath("//tr//td[1]//div//a"));
		sort(nameElements);
		// reverse order
		name.click();
		Thread.sleep(2000);
		List<WebElement> nameElements1 = driver.findElements(By.xpath("//tr//td[1]//div//a"));
		reverseSort(nameElements1);

		// (ii)sort on month
		WebElement month = driver.findElement(By.xpath("//tr//th[3]"));
		month.click();
		Thread.sleep(2000);
		List<WebElement> monthElements = driver
				.findElements(By.xpath("//table[@id=\"conferenceTable\"]/tbody/tr/td[3]"));
		monthSort(monthElements);
		month.click();
		Thread.sleep(2000);
		List<WebElement> monthElements1 = driver
				.findElements(By.xpath("//table[@id=\"conferenceTable\"]/tbody/tr/td[3]"));
		monthReverseSort(monthElements1);

		// (iii)sort on last updated at
		WebElement lastUpdated = driver.findElement(By.xpath("//tr//th[4]"));
		lastUpdated.click();
		Thread.sleep(2000);
		List<WebElement> lastUpdatedElements = driver
				.findElements(By.xpath("//table[@id=\"conferenceTable\"]/tbody/tr/td[4]"));
		lastUpdatedSort(lastUpdatedElements);
		lastUpdated.click();
		Thread.sleep(2000);
		List<WebElement> lastUpdatedElements1 = driver
				.findElements(By.xpath("//table[@id=\"conferenceTable\"]/tbody/tr/td[4]"));
		lastUpdatedReverseSort(lastUpdatedElements1);

		// (B)Filter on Conference Page:
		// (i)Search By Keyword
		String filterWord;
		String conference = "ENDO";
		String year = "2023";
		String Month = "Jun";
		WebElement filter = driver.findElement(By.id("conferenceIrxSearchFilterButton"));
		WebElement reset = driver.findElement(By.id("conferenceListResetButton"));
		WebElement search = driver.findElement(By.id("conferenceListSearchButton"));
		filter.click();
		driver.findElement(By.id("keyword")).sendKeys(conference);
		Thread.sleep(2000);
		search.click();
		Thread.sleep(2000);
		List<WebElement> filterName = driver.findElements(By.xpath("//table[@id=\"conferenceTable\"]/tbody/tr/td[1]"));
		filterWord = conference;
		filter(filterName, filterWord);

		// (ii)Search By Year
		filter.click();
		Thread.sleep(1000);
		reset.click();
		Thread.sleep(2000);
		driver.findElement(By.id("conferenceyearAutosuggestValueContainer")).click();
		driver.findElement(By.cssSelector("input[data-action='conferenceyearAutosuggestSearch']")).sendKeys(year);
		driver.findElement(By.xpath("//label[normalize-space()='2023']")).click();
		Thread.sleep(2000);
		search.click();
		Thread.sleep(2000);
		List<WebElement> filterYear = driver.findElements(By.xpath("//table[@id=\"conferenceTable\"]/tbody/tr/td[1]"));
		filterWord = year;
		filter(filterYear, filterWord);

		// (iii)Search By Month
		filter.click();
		Thread.sleep(1000);
		reset.click();
		Thread.sleep(2000);
		driver.findElement(By.id("conferencemonthAutosuggestValueContainer")).click();
		driver.findElement(By.cssSelector("input[data-action='conferencemonthAutosuggestSearch']")).sendKeys(Month);
		driver.findElement(By.xpath("//label[normalize-space()='Jun']")).click();
		Thread.sleep(2000);
		search.click();
		Thread.sleep(2000);
		List<WebElement> filterMonth = driver.findElements(By.xpath("//table[@id=\"conferenceTable\"]/tbody/tr/td[3]"));
		filterWord = Month;
		filter(filterMonth, filterWord);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@aria-label=\"Clear All\"]")).click();
		Thread.sleep(2000);
		
		//pagination
		WebElement pageValues= driver.findElement(By.id("conferenceTable_paginate_select"));
		  String totalEntriesString= driver.findElement(By.xpath("//div[@id=\"conferenceTable_info\"]/span")).getText(); 
		  int totalEntries= Integer.parseInt(totalEntriesString);
		 
		  int listPage= driver.findElements(By.xpath("//select[@id=\"conferenceTable_paginate_select\"]/option")).size();
		 List<String> allNameElements = new ArrayList<>(); 
		 for(int i=1; i<=listPage;i++)
		 { 
			 pageValues.click();
			 String paginationSelector= "select[id='conferenceTable_paginate_select'] option[value='"+i+"']";
		 driver.findElement(By.cssSelector(paginationSelector)).click();
		  Thread.sleep(2000);
		  List<WebElement>nameElements2 = driver.findElements(By.xpath("//tr//td[1]//div//a"));
		 for(WebElement nameElement: nameElements2) {
			 	allNameElements.add(nameElement.getText());
			 }
		 }
		 int totalNames= allNameElements.size();
		 Assert.assertEquals(totalEntries,totalNames);

		// Add record in Conference
		filter.click();
		driver.findElement(By.id("keyword")).sendKeys("AACR");
		Thread.sleep(2000);
		search.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),\"AACR 2023\")]")).click();
		Thread.sleep(2000);
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		it.next();
		String childWindow = it.next();
		driver.close();
		String currentChildWindow = null;
		currentChildWindow = switchWindow(currentChildWindow, driver);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@aria-label='Add Playbook']")).click();
		driver.findElement(By.linkText("Automatic Playbook")).click();
		Thread.sleep(2000);
		currentChildWindow = switchWindow(currentChildWindow, driver);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[@data-key=\"sessionId\"][@onclick=\"playbookTriggerAutosuggest(this);\"])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class=\"icon-pin playbookColumnPin\"]")).click();
		Thread.sleep(2000);
	    driver.findElement(By.xpath("//span[@data-key=\"sessionId\"][@onclick=\"playbookTriggerAutosuggest(this);\"]")).click();
		String playbookCheckFilter= "ED008";
		driver.findElement(By.xpath("//input[@data-action=\"playbookAutosuggestSearch\"]")).sendKeys(playbookCheckFilter);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[text()=\"ED008\"]")).click();
		driver.findElement(By.xpath("//button[@data-action=\"playbookAutosuggestOkay\"]")).click();
		Thread.sleep(2000);
		List<WebElement> sessionIdData= driver.findElements(By.xpath("//table[@id=\"playbookViewTable\"]//tbody/tr/td[1]"));
		Thread.sleep(2000);
		filter(sessionIdData,playbookCheckFilter);
		String automaticEntries= driver.findElement(By.xpath("//span[@class=\"totalEntries\"]")).getText();
		System.out.println(automaticEntries);
		
		driver.findElement(By.className("icon-save")).click();
		driver.findElement(By.id("playbookSaveAsFinalButton")).click();
		Thread.sleep(1000);
		String actualTitle = renameTitle();
		driver.findElement(By.id("playbookListTitle")).sendKeys(actualTitle);
		driver.findElement(By.id("playbookDataSaveButton")).click();
		Thread.sleep(1000);
		driver.close();
		driver.switchTo().window(childWindow);
		Thread.sleep(1000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		WebElement recordAddress= driver.findElement(By.xpath("//table[@id=\"playbookTable\"]//tr[1]/td[1]"));
		String expectedTitle =recordAddress.getText();
		Assert.assertEquals(actualTitle, expectedTitle);
		Thread.sleep(2000);
	
		driver.findElement(By.xpath("//table[@id=\"playbookTable\"]//tr[1]/td[1]/div/div/a")).click();

		currentChildWindow = switchWindow(currentChildWindow, driver);
		Thread.sleep(2000);
		String playbookEntries= driver.findElement(By.xpath("//div[@id=\"playbookViewTable_info\"]/span")).getText();
		size(automaticEntries,playbookEntries);

	}
	
	private static void size(String automaticEntries, String playbookEntries) {
		Assert.assertEquals(automaticEntries,playbookEntries);
		
	}

	private static String renameTitle() {
		String play = "playbook";
		String newTitle = play + "_" + UUID.randomUUID();
		return newTitle;

	}

	private static String switchWindow(String currentChildWindow, WebDriver driver) {
		for (String childWindow : driver.getWindowHandles()) {
			if (!childWindow.equals(currentChildWindow)) {
				driver.switchTo().window(childWindow);
				currentChildWindow = childWindow;
			}
		}
		return currentChildWindow;

	}

	private static void filter(List<WebElement> filterNames, String filterWords) {
		List<WebElement> filteredName = filterNames.stream().filter(e -> e.getText().contains(filterWords))
				.collect(Collectors.toList());
		Assert.assertEquals(filterNames.size(), filteredName.size());
	}

	private static void lastUpdatedReverseSort(List<WebElement> elements) {
		List<Date> originalList3 = elements.stream().map(WebElement::getText).map(t -> convertStringToTimestampZone(t))
				.collect(Collectors.toList());

		List<Date> sortedList3 = originalList3;
		Collections.sort(sortedList3, Comparator.reverseOrder());
		Assert.assertTrue(originalList3.equals(sortedList3));

	}

	private static void lastUpdatedSort(List<WebElement> elements) {
		List<Date> originalList3 = elements.stream().map(WebElement::getText).map(t -> convertStringToTimestampZone(t))
				.collect(Collectors.toList());

		List<Date> sortedList3 = originalList3;
		Collections.sort(sortedList3);
		Assert.assertTrue(originalList3.equals(sortedList3));

	}

	public static Date convertStringToTimestampZone(String date) {

		if (date == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm");
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {

			return null;
		}
	}

	private static void monthReverseSort(List<WebElement> elements) {
		List<Month> originalList2 = elements.stream().map(WebElement::getText).map(Month::valueOf)
				.collect(Collectors.toList());
		List<Month> sortedList2 = originalList2.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		Assert.assertTrue(originalList2.equals(sortedList2));

	}

	private static void monthSort(List<WebElement> elements) {

		List<Month> originalList2 = elements.stream().map(WebElement::getText).map(Month::valueOf)
				.collect(Collectors.toList());
		List<Month> sortedList2 = originalList2.stream().sorted().collect(Collectors.toList());
		Assert.assertTrue(originalList2.equals(sortedList2));
	}

	private static void reverseSort(List<WebElement> elements) {
		List<String> originalList1 = elements.stream().map(s -> s.getText().trim()).collect(Collectors.toList());
		List<String> sortedList1 = originalList1.stream().sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		Assert.assertTrue(originalList1.equals(sortedList1));

	}

	private static void sort(List<WebElement> elements) {
		List<String> originalList = elements.stream().map(s -> s.getText().trim()).collect(Collectors.toList());
		List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());
		Assert.assertTrue(originalList.equals(sortedList));

	}
}
