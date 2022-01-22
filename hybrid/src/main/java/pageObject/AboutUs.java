package pageObject;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import exceptions.PageObjectException;
import exceptions.ReusableComponentException;
import reusableComponent.WebDriverSupport;
import uiStore.AboutUsUi;
import utilities.ExtentLogUtilities;

public class AboutUs extends AboutUsUi {

	WebDriver driver;
	Logger log;
	ExtentTest test;

	public AboutUs(WebDriver driver, Logger log, ExtentTest test) throws Exception {
		this.driver = driver;
		this.test = test;
		this.log = log;
	}

	public void About(String ceoname) throws ReusableComponentException, Exception {
		try {
			driver.findElement(foot).click();
			WebDriverSupport.click(driver, about, "Home Page", "About Us page", log, test);
			Thread.sleep(2000);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			
			String ActualCeoName = driver.findElement(ceo).getText();
			if (ActualCeoName.equalsIgnoreCase(ceoname)) {
				ExtentLogUtilities.pass(driver, test, "CEO name validated", log);
			}else {
				ExtentLogUtilities.fail(driver, test, "CEO name Invalid", log);
				throw new PageObjectException("Logged in failed");
			}
			
		} catch (Exception e) {
			throw new PageObjectException(e.getMessage());
		}
	}

}
