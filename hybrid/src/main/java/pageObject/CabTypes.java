package pageObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;

import exceptions.PageObjectException;
import exceptions.ReusableComponentException;
import reusableComponent.WebDriverSupport;
import uiStore.CabTypesUi;
import utilities.ExtentLogUtilities;

public class CabTypes extends CabTypesUi{
	WebDriver driver;
	Logger log;
	ExtentTest test;

	public CabTypes(WebDriver driver, Logger log, ExtentTest test) throws Exception {
		this.driver = driver;
		this.test = test;
		this.log = log;
	}

	public void check(String title) throws ReusableComponentException, Exception {
		try {
			driver.findElement(foot).click();
			WebDriverSupport.click(driver, sitemap, "Home Page", "Select sitemap Link", log, test);
			Thread.sleep(2000);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			WebDriverSupport.click(driver, cabtypes, "sitemap page", "Select cab Types Link", log, test);
			Thread.sleep(2000);
			tabs.addAll(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(2));
			
			Set<String> tabids = driver.getWindowHandles();
			Iterator<String> it = tabids.iterator();
			while(it.hasNext()) {
				driver.switchTo().window(it.next());
			}
			String ActualTitle = driver.getTitle();
			
			if (ActualTitle.contains(title)) {
				ExtentLogUtilities.pass(driver, test, "Successfully accessed cab types page", log);
				
			} else {
				ExtentLogUtilities.fail(driver, test, "Failed to access", log);
				throw new PageObjectException("Logged in failed");
			}
			
		} catch (Exception e) {
			throw new PageObjectException(e.getMessage());
		}
	}

}
