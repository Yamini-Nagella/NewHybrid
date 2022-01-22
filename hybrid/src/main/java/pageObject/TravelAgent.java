package pageObject;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;

import exceptions.PageObjectException;
import exceptions.ReusableComponentException;
import reusableComponent.WebDriverSupport;
import uiStore.TravelAgentUi;
import utilities.ExtentLogUtilities;

public class TravelAgent extends TravelAgentUi{
	WebDriver driver;
	Logger log;
	ExtentTest test;

	public TravelAgent(WebDriver driver, Logger log, ExtentTest test) throws Exception {
		this.driver = driver;
		this.test = test;
		this.log = log;
	}

	public void select() throws ReusableComponentException, Exception {
		try {
			WebDriverSupport.click(driver, agent, "Home Page", "Travel Agent", log, test);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			WebDriverSupport.click(driver, register, "Travel Agent Page", "Register", log, test);
			String head = "Register";
			if (driver.findElement(heading).getText().contains(head)) {
				ExtentLogUtilities.pass(driver, test, "Successfully opened travel agent page", log);
			} else {
				ExtentLogUtilities.fail(driver, test, "Failed to login to Travel agent page", log);
				throw new PageObjectException("Logged in failed");
			} 
			
		} catch (Exception e) {
			throw new PageObjectException(e.getMessage());
		}
	}

}
