package reusableComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import exceptions.UtilityException;
import utilities.ExcelSheetRead;

public class Base extends ExcelSheetRead {

	public Base() throws UtilityException, Exception {
		super();
	}

	static public WebDriver driver;

	public static WebDriver initialize() throws Exception {
		if (driver == null) {
			if (get.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + get.getProperty("path"));
				ChromeOptions opt = new ChromeOptions();
				opt.addArguments("--disable-notifications");
				driver = new ChromeDriver(opt);
			}
		}
		return driver;
	}
}
