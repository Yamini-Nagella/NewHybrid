package pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import exceptions.PageObjectException;
import utilities.ExtentLogUtilities;

public class ValidPage {
	WebDriver driver;
	Logger log;
	ExtentTest test;

	public ValidPage(WebDriver driver, Logger log, ExtentTest test) throws Exception {
		this.driver = driver;
		this.test = test;
		this.log = log;
	}

	public void validate(String title) throws PageObjectException, Exception {
		if (title.equalsIgnoreCase(driver.getTitle())) {
			ExtentLogUtilities.pass(driver, test, "Page opend with title " + title, log);
			
		} else {

			ExtentLogUtilities.fail(driver, test, "Page not opened with title " + title, log);
			throw new PageObjectException("Page not opened with title " + title);
		}
	}
}
