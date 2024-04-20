package automation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class landingPage1 {
	WebDriver driver;
	ConferencePlaybook cplaybook;
	
	
	public landingPage1(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy (id="loginUsername")
	WebElement userEmail;
	
	@FindBy (id="loginPassword")
	WebElement userPassword;
	
	@FindBy (id="loginSubmitButton")
	WebElement login;
	

	public void goTo() {
		driver.get("https://qa.inflexionrx.com/login");
	}
	
	
	
	public void login(String email, String password) throws InterruptedException {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		Thread.sleep(2000);
	}

	
	

}
