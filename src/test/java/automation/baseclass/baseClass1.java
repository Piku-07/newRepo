package automation.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import automation.pageobjects.dashboard;
import automation.pageobjects.landingPage1;
import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass1 {
	public WebDriver driver;
	public static Properties prop;
	public static dashboard dboard;

	public WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\automation\\resources\\globalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
		} else if (browserName.equals("firefox")) {
			//
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

		String screenshotPath = null;

		try {
			File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destinationFile = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
			FileHandler.copy(sourceFile, destinationFile);
			String[] relativePath = destinationFile.toString().split("reports");
			screenshotPath = ".\\" + relativePath[1];
		} catch (Exception e) {
			System.out.println("Failure to take screenshot " + e);
		}
		return screenshotPath;
	}

	@BeforeMethod
	public dashboard launchApplication() throws IOException, InterruptedException {
		driver = initializeDriver();
		landingPage1 landingPage1 = new landingPage1(driver);
		landingPage1.goTo();
		landingPage1.login(prop.getProperty("username"), prop.getProperty("password"));
		dashboard dboard = new dashboard(driver);
		setdashboard(dboard);
		return dboard;
	}

	public void setdashboard(dashboard dboard) {
		this.dboard = dboard;
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
