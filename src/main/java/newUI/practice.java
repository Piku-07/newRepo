package newUI;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class practice {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options= new ChromeOptions();
		HashMap<String, Object> chromePrefs= new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		String downloadFilePath= System.getProperty("user.dir");
		chromePrefs.put("download.default_directory", downloadFilePath);
		options.setExperimentalOption("prefs", chromePrefs);
		WebDriver driver= new ChromeDriver(options);
		driver.get("https://omayo.blogspot.com/p/page7.html");
		driver.findElement(By.linkText("ZIP file")).click();
		
		File file = new File(downloadFilePath + "\\DownloadDemo-master.zip");
		if(file.exists()) {
			System.out.println("Success");
		}
		else {
			System.out.println("no");
		}
	}

}
