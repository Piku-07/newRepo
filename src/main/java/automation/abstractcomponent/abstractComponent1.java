package automation.abstractcomponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class abstractComponent1 {
	enum Month {
		Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec
	}

	WebDriver driver;

	public abstractComponent1(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToDisappear(WebElement spinner) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.invisibilityOf(spinner));
	}

	public static void sort(List<WebElement> elements) {
		List<String> originalList = elements.stream().map(s -> s.getText().trim().toLowerCase())
				.collect(Collectors.toList());
		List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());
		Assert.assertTrue(originalList.equals(sortedList));
	}

	public static void reverseSort(List<WebElement> elements) {
		List<String> originalList = elements.stream().map(s -> s.getText().trim().toLowerCase())
				.collect(Collectors.toList());
		List<String> sortedList = originalList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		Assert.assertTrue(originalList.equals(sortedList));
	}

	public static void monthSort(List<WebElement> elements) {
		List<Month> originalList = elements.stream().map(WebElement::getText).map(Month::valueOf)
				.collect(Collectors.toList());
		List<Month> sortedList = originalList.stream().sorted().collect(Collectors.toList());
		Assert.assertTrue(originalList.equals(sortedList));
	}

	public static void monthReverseSort(List<WebElement> elements) {
		List<Month> originalList = elements.stream().map(WebElement::getText).map(Month::valueOf)
				.collect(Collectors.toList());
		List<Month> sortedList = originalList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		Assert.assertTrue(originalList.equals(sortedList));

	}

	public static void lastUpdatedSort(List<WebElement> elements) {
		List<Date> originalList = elements.stream().map(WebElement::getText).map(t -> convertStringToTimestampZone(t))
				.collect(Collectors.toList());
		List<Date> sortedList = originalList;
		Collections.sort(sortedList);
		Assert.assertTrue(originalList.equals(sortedList));
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

	public static void lastUpdatedReverseSort(List<WebElement> elements) {
		List<Date> originalList = elements.stream().map(WebElement::getText).map(t -> convertStringToTimestampZone(t))
				.collect(Collectors.toList());

		List<Date> sortedList = originalList;
		Collections.sort(sortedList, Comparator.reverseOrder());
		Assert.assertTrue(originalList.equals(sortedList));
	}

	public static void filter(List<WebElement> filterList, String filterWord) {
		List<WebElement> filteredList = filterList.stream()
				.filter(e -> e.getText().toLowerCase().contains(filterWord.toLowerCase())).collect(Collectors.toList());
		Assert.assertEquals(filterList.size(), filteredList.size());
	}

	public static String switchWindow(String currentChildWindow, WebDriver driver) {
		for (String childWindow : driver.getWindowHandles()) {
			if (!childWindow.equals(currentChildWindow)) {
				driver.switchTo().window(childWindow);
				currentChildWindow = childWindow;
			}
		}
		return currentChildWindow;

	}

	public static String saveRecord() {
		String record = "record";
		String newTitle = record + "_" + UUID.randomUUID();
		return newTitle;
	}

	public static void size(String automaticEntries, String playbookEntries) {
		Assert.assertEquals(automaticEntries, playbookEntries);

	}

}
