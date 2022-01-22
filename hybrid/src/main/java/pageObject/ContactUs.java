package pageObject;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import exceptions.PageObjectException;
import exceptions.ReusableComponentException;
import reusableComponent.WebDriverSupport;
import uiStore.ContactUsUi;
import utilities.ExtentLogUtilities;

public class ContactUs extends ContactUsUi {

	WebDriver driver;
	Logger log;
	ExtentTest test;

	public ContactUs(WebDriver driver, Logger log, ExtentTest test) throws Exception {
		this.driver = driver;
		this.test = test;
		this.log = log;
	}

	public void Contact(String cheading) throws ReusableComponentException, Exception {
		try {
			driver.findElement(foot).click();
			WebDriverSupport.click(driver, contact, "Home Page", "Contact Us page", log, test);
			Thread.sleep(5000);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			String ExpectedHeading = "Contact Us";
			String Heading = driver.findElement(heading).getText();
			if (Heading.contains(ExpectedHeading)) {
				ExtentLogUtilities.pass(driver, test, "Contact Us page validated", log);
			}else {
				ExtentLogUtilities.fail(driver, test, "Contact Us page Invalid", log);
				throw new PageObjectException("Logged in failed");
			}
			
		} catch (Exception e) {
			throw new PageObjectException(e.getMessage());
		}
	}
	
}
