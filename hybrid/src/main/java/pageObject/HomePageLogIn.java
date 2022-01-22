package pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import exceptions.PageObjectException;
import exceptions.ReusableComponentException;
import reusableComponent.WebDriverSupport;
import uiStore.HomePageLogInUi;
import utilities.ExtentLogUtilities;

public class HomePageLogIn extends HomePageLogInUi {

	WebDriver driver;
	Logger log;
	ExtentTest test;

	public HomePageLogIn(WebDriver driver, Logger log, ExtentTest test) throws Exception {
		this.driver = driver;
		this.test = test;
		this.log = log;
	}

	public void login(String Uid, String Pw) throws ReusableComponentException, Exception {
		try {
			WebDriverSupport.click(driver, logInButton, "Home Page", "Log In Button", log, test);
			Thread.sleep(5000);
			WebDriverSupport.sendKeys(driver, email, "Account Log in", "Email field", log, test, Uid);
			WebDriverSupport.sendKeys(driver, pass, "Account Log in", "password field", log, test, Pw);
			WebDriverSupport.click(driver, signin, "Account Log in", "sign in button", log, test);
			Thread.sleep(5000);
			String text = driver.findElement(vheading).getText();
			if (text.contains("Hi")) {
				ExtentLogUtilities.pass(driver, test, "Successfully logged in", log);
			} else {
				ExtentLogUtilities.fail(driver, test, "logged in failed", log);
				throw new PageObjectException("Logged in failed");
			} 
			
		} catch (Exception e) {
			throw new PageObjectException(e.getMessage());
		}
	}

}
