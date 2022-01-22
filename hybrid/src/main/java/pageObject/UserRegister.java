package pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import exceptions.PageObjectException;
import exceptions.ReusableComponentException;
import reusableComponent.WebDriverSupport;
import uiStore.UserRegisterUi;
import utilities.ExtentLogUtilities;

public class UserRegister extends UserRegisterUi{
	WebDriver driver;
	Logger log;
	ExtentTest test;
	
	public UserRegister(WebDriver driver, Logger log, ExtentTest test) throws Exception {
		this.driver = driver;
		this.test = test;
		this.log = log;
	}
	
	public void Register(String uname, String num, String uemail, String upass, String cpass) throws ReusableComponentException, Exception {
		try {
			WebDriverSupport.click(driver, logInButton, "Home Page", "Log In Button", log, test);
			Thread.sleep(5000);
			WebDriverSupport.click(driver, regbutton, "User Register", "Register Button", log, test);
			WebDriverSupport.sendKeys(driver, username, "User Register", "Name Field", log, test, uname);
			WebDriverSupport.sendKeys(driver, phonenum, "User Register", "Mobile Number Field", log, test, num);
			WebDriverSupport.sendKeys(driver, email, "User Register", "Email Field", log, test, uemail);
			WebDriverSupport.sendKeys(driver, pass, "User Register", "password field", log, test, upass);
			WebDriverSupport.sendKeys(driver, confirmpass, "User Register", "Retype password field", log, test, cpass);
			WebDriverSupport.click(driver, register, "Account Log in", "Register Button", log, test);
			Thread.sleep(5000);
			String text = driver.findElement(vheading).getText();
			if (text.contains(uname)) {
				ExtentLogUtilities.pass(driver, test, "Successfully Registered", log);
			} else {
				ExtentLogUtilities.fail(driver, test, "Registration failed", log);
				throw new PageObjectException("Logged in failed");
			} 
			
		} catch (Exception e) {
			throw new PageObjectException(e.getMessage());
		}
	}
}
