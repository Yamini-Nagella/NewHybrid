package pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;

import exceptions.PageObjectException;
import exceptions.ReusableComponentException;
import reusableComponent.WebDriverSupport;
import uiStore.RoundTripUi;
import utilities.ExtentLogUtilities;

public class RoundTrip extends RoundTripUi{
		WebDriver driver;
		Logger log;
		ExtentTest test;

		public RoundTrip(WebDriver driver, Logger log, ExtentTest test) throws Exception {
			this.driver = driver;
			this.test = test;
			this.log = log;
		}

		public void Booking(String src, String destin, String month1, String day1, String month2, String day2, String time, String vcity, String vdate) throws ReusableComponentException, Exception {
			try {
				WebDriverSupport.click(driver, radio, "Home Page", "Select Round Trip", log, test);
				WebDriverSupport.sendKeys(driver, source, "Home Page", "Source city", log, test, src);
				driver.findElement(By.id("ngb-typeahead-0-0")).click();
				WebDriverSupport.sendKeys(driver, destination, "Home Page", "Destination City", log, test, destin);
				driver.findElement(By.id("ngb-typeahead-2-0")).click();
				WebDriverSupport.click(driver, pickup, "Round Trip", "Date Field", log, test);
				Thread.sleep(2000);
				while(true) {
					String Default_Month = driver.findElement(By.xpath(".//*[@class='p-datepicker-title ng-tns-c75-1']")).getText();
					if(month1.equals(Default_Month) ) {
						break;
					}
					else {
						driver.findElement(By.xpath("//*[@class=\"p-datepicker-next-icon pi pi-chevron-right ng-tns-c75-12")).click();
					}
				}
				day1 = day1.substring(0,2);
				int count = driver.findElements(By.xpath(".//tbody[@class='ng-tns-c75-1']/tr/td")).size();
				for(int i=0;i<count;i++) {
					
					if(driver.findElements(By.xpath(".//tbody[@class='ng-tns-c75-1']/tr/td")).get(i).getText().contains(day1)) {
						driver.findElements(By.xpath(".//tbody[@class='ng-tns-c75-1']/tr/td")).get(i).click();
						break;
					}
				}
				
				WebDriverSupport.click(driver, Return, "Round Trip", "Date Field", log, test);
				Thread.sleep(2000);
				while(true) {
					String Default_Month = driver.findElement(By.xpath(".//*[@class='p-datepicker-header ng-tns-c75-3']")).getText();
					if(month2.equals(Default_Month) ) {
						break;
					}
					else {
						driver.findElement(By.xpath("//*[@class=\"p-datepicker-next-icon pi pi-chevron-right ng-tns-c75-3")).click();
					}
				}
				day2 = day2.substring(0,2);
				 count = driver.findElements(By.xpath(".//tbody[@class='ng-tns-c75-3']/tr/td")).size();
				for(int i=0;i<count;i++) {
					
					if(driver.findElements(By.xpath(".//tbody[@class='ng-tns-c75-3']/tr/td")).get(i).getText().contains(day2)) {
						driver.findElements(By.xpath(".//tbody[@class='ng-tns-c75-3']/tr/td")).get(i).click();
						break;
					}
				}
				Select ttime = new Select(driver.findElement(pickupat));
				ttime.selectByVisibleText(time);
				WebDriverSupport.click(driver, book_btn, "RoundTrip Taxi Booking", "click on select car", log, test);
				String head = driver.findElement(vheading).getText();
				if (head.contains(vcity) && head.contains(vdate) && head.contains(time)) {
					ExtentLogUtilities.pass(driver, test, "Successfully opened RoundTrip Taxi Booking Page", log);
				} else {
					ExtentLogUtilities.fail(driver, test, "failed to open RoundTrip Taxi Booking Page", log);
					throw new PageObjectException("Logged in failed");
				}
				
			} catch (Exception e) {
				throw new PageObjectException(e.getMessage());
			}
		}
}
