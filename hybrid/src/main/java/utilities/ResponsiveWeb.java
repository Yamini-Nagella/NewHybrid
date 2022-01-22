package utilities;

import java.util.*;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ResponsiveWeb extends ExcelSheetRead {
	WebDriver driver;

	public ResponsiveWeb(WebDriver driver) throws Exception {
		super();
		this.driver = driver;
	}

	public void setDimension() {
		Dimension dimension = new Dimension((int) Double.parseDouble(excelData.get("width")),
				(int) Double.parseDouble(excelData.get("height")));
		driver.manage().window().setSize(dimension);
	}

	public static ChromeOptions setDevice() {
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", get.getProperty("device"));
		ChromeOptions opt = new ChromeOptions();
		opt.setExperimentalOption("mobileEmulation", mobileEmulation);
		return opt;
	}
}
