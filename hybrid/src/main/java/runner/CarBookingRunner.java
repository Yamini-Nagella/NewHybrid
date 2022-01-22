package runner;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import exceptions.ApplicationException;
import exceptions.UtilityException;
import pageObject.AboutUs;
import pageObject.AirportTaxi;
import pageObject.CabTypes;
import pageObject.ContactUs;
import pageObject.HomePageLogIn;
import pageObject.LocalCars;
import pageObject.OutStationTaxi_OneWay;
import pageObject.RoundTrip;
import pageObject.TravelAgent;
import pageObject.UserRegister;
import pageObject.ValidPage;
import pageObject.ViewBookings;
import reusableComponent.Base;
import utilities.ExtentLogUtilities;
import utilities.GetProperties;

public class CarBookingRunner extends Base{
	
	static Logger log;
	static ExtentTest test;
	static ExtentReports report;
	int cnt = 12;
	public CarBookingRunner() throws UtilityException, Exception {
		super();
	}
	
	@BeforeMethod
	public void initializeDriver() throws ApplicationException {
		try {
			report = new ExtentReports(System.getProperty("user.dir") + "\\reports\\" + "ExtentReportResults-"
					+ System.currentTimeMillis() + "-.html");
			log = Logger.getLogger(CarBookingRunner.class.getName());
			driver = initialize();
			test = report.startTest("Home Page");
			driver.get(GetProperties.get.getProperty("url"));
			driver.manage().window().maximize();
			ExtentLogUtilities.pass(driver, test, "URL Opened", log);
			report.endTest(test);
			report.flush();
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@AfterMethod
	public void EndTheTest() throws Exception {
		if(--cnt == 0) {
			driver.quit();
		}else {
			driver.quit();
			driver = null;
			driver = initialize();
		}
		
	}
/* 1. Page Title validation */
	
	@Test(priority = 1, dataProvider = "getTitle")
	public void validateTitle(String title) throws ApplicationException {
		test = report.startTest("Validate Title");
		try {
			new ValidPage(driver, log, test).validate(title);
			report.endTest(test);
			report.flush();
			
		} catch (Exception e) {
			report.endTest(test);
			report.flush();
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@DataProvider
	public Object[] getTitle() {
		Object data[] = new Object[1];
		data[0] = get.getProperty("title");
		return data;
	}
	

/* 2. Login as a Existing user */
	
	@Test(priority = 2, dataProvider = "getData")
	public void LogIn(String uid, String pw) throws ApplicationException {
		test = report.startTest("Log in");
		try {
			new HomePageLogIn(driver, log, test).login(uid, pw);
			report.endTest(test);
			report.flush();
			
		} catch (Exception e) {
			report.endTest(test);
			report.flush();
			throw new ApplicationException(e.getMessage());
		}
	}

	@DataProvider
	public Object[] getData() {
		Object data[][] = new Object[1][2];
		data[0][0] = excelData.get("id");
		data[0][1] = excelData.get("pw");
		return data;
	}
	
/* 3. View Bookings */
	@Test(priority = 3, dataProvider = "getViewBooking")
	public void Bookings(String email, String pass, String verify) throws ApplicationException {
		test = report.startTest("View Bookings");
		try {
			new HomePageLogIn(driver, log, test).login(email, pass);
			new ViewBookings(driver, log, test).View(verify);
			report.endTest(test);
			report.flush();
			
		} catch (Exception e) {
			report.endTest(test);
			report.flush();
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@DataProvider
	public Object[] getViewBooking() {
		Object data[][] = new Object[1][3];
		data[0][0] = excelData.get("id");
		data[0][1] = excelData.get("pw");
		data[0][2] = excelData.get("View Bookings");
		return data;
	}
	
/* 4. Register as a new user */
	
	@Test(priority = 4, dataProvider = "getuserData")
	public void Register(String uname, String num, String email, String pass, String cpass) throws ApplicationException {
		test = report.startTest("User Register");
		try {
			new UserRegister(driver, log, test).Register(uname, num, email, pass, cpass);
			report.endTest(test);
			report.flush();
			
		} catch (Exception e) {
			report.endTest(test);
			report.flush();
			throw new ApplicationException(e.getMessage());
		}
	}

	@DataProvider
	public Object[][] getuserData() {
		Object[][] val = new Object[1][5];
		val[0][0] = excelData.get("User Name");
		val[0][1] = excelData.get("Mobile Number");
		val[0][2] = excelData.get("Email");
		val[0][3] = excelData.get("Password");
		val[0][4] = excelData.get("Confirm Password");
		return val;
	} 

/* 5. About Us Page */
	@Test(priority = 5, dataProvider = "getCEOName")
	public void Aboutus(String ceoname) throws ApplicationException {
		test = report.startTest("About us Page");
		try {
			new AboutUs(driver, log, test).About(ceoname);
			report.endTest(test);
			report.flush();
			
		} catch (Exception e) {
			report.endTest(test);
			report.flush();
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@DataProvider
	public Object[] getCEOName() {
		Object data[] = new Object[1];
		data[0] = excelData.get("CEO Name");
		return data;
	}
	
/* 6. AirportTaxiRunner*/
	@Test(priority = 6, dataProvider = "getAirportdata")
	public void airporttaxi(String source, String add, String month, String date, String time, String vcity, String vdate) throws ApplicationException {
		test = report.startTest("Book Airport Taxi");
		try {
			new AirportTaxi(driver, log, test).select(source, add, month, date, time, vcity, vdate);
			report.endTest(test);
			report.flush();
			
		} catch (Exception e) {
			report.endTest(test);
			report.flush();
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@DataProvider
	public Object[] getAirportdata() {
		Object data[][] = new Object[1][7];
		data[0][0] = excelData.get("A_Source");
		data[0][1] = excelData.get("A_Address");
		data[0][2] = excelData.get("A_Month");
		data[0][3] = excelData.get("A_Date");
		data[0][4] = excelData.get("A_Time");
		data[0][5] = excelData.get("V_A_City");
		data[0][6] = excelData.get("V_A_Date");
		return data;
	}
/* 7. Cab Types */
	@Test(priority = 7, dataProvider = "getCabType")
	public void CheckCabTypes(String title) throws ApplicationException {
		test = report.startTest("Check Cab Types");
		try {
			new CabTypes(driver, log, test).check(title);
			report.endTest(test);
			report.flush();
			
		} catch (Exception e) {
			report.endTest(test);
			report.flush();
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@DataProvider
	public Object[] getCabType() {
		Object data[][] = new Object[1][1];
		data[0][0] = excelData.get("Cab Type Page Title");
		return data;
	}
	
/* 8. Contact Us */
	@Test(priority = 8, dataProvider = "getContact")
	public void ContactUs(String cheading) throws ApplicationException {
		test = report.startTest("Contact us Page");
		try {
			new ContactUs(driver, log, test).Contact(cheading);
			report.endTest(test);
			report.flush();
			
		} catch (Exception e) {
			report.endTest(test);
			report.flush();
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@DataProvider
	public Object[] getContact() {
		Object data[] = new Object[1];
		data[0] = excelData.get("Contact");
		return data;
	}
	
/* 9. LocalCars Booking */
	@Test(priority = 9, dataProvider = "getLocal")
	public void localcars(String source, String month1, String date1, String time, String vcity, String vdate) throws ApplicationException {
		test = report.startTest("Local Car Booking");
		try {
			new LocalCars(driver, log, test).select(source,  month1, date1, time, vcity, vdate);
			report.endTest(test);
			report.flush();
			
		} catch (Exception e) {
			report.endTest(test);
			report.flush();
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@DataProvider
	public Object[] getLocal() {
		Object data[][] = new Object[1][6];
		data[0][0] = excelData.get("L_Source");
		data[0][1] = excelData.get("L_Month");
		data[0][2] = excelData.get("L_Date");
		data[0][3] = excelData.get("L_Time");
		data[0][4] = excelData.get("V_L_City");
		data[0][5] = excelData.get("V_L_Date");
		return data;
	}
	
/* 10. One way trip */
	@Test(priority = 10, dataProvider = "getoneway")
	public void oneway(String source, String destin, String month, String date, String time, String vcity, String vdate) throws ApplicationException {
		test = report.startTest("One way Taxi Booking");
		try {
			new OutStationTaxi_OneWay(driver, log, test).onewayBooking(source, destin, month, date, time, vcity, vdate);
			report.endTest(test);
			report.flush();
			
		} catch (Exception e) {
			report.endTest(test);
			report.flush();
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@DataProvider
	public Object[] getoneway() {
		Object data[][] = new Object[1][7];
		data[0][0] = excelData.get("Source");
		data[0][1] = excelData.get("Destination");
		data[0][2] = excelData.get("Month");
		data[0][3] = excelData.get("Date");
		data[0][4] = excelData.get("Time");
		data[0][5] = excelData.get("Validate_O_City");
		data[0][6] = excelData.get("Validate_O_Date");
		return data;
	}
	
/* 11. RoundTrip */
	@Test(priority = 11, dataProvider = "getRoundTrip")
	public void RoundTrip(String source, String destin, String month1, String date1, String month2, String date2, String time, String vcity, String vdate) throws ApplicationException {
		test = report.startTest("Round Trip Taxi Booking");
		try {
			new RoundTrip(driver, log, test).Booking(source, destin, month1, date1, month2, date2, time, vcity, vdate);
			report.endTest(test);
			report.flush();
			
		} catch (Exception e) {
			report.endTest(test);
			report.flush();
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@DataProvider
	public Object[] getRoundTrip() {
		Object data[][] = new Object[1][9];
		data[0][0] = excelData.get("R_Source");
		data[0][1] = excelData.get("R_Destination");
		data[0][2] = excelData.get("R_Month1");
		data[0][3] = excelData.get("R_Date1");
		data[0][4] = excelData.get("R_Month2");
		data[0][5] = excelData.get("R_Date2");
		data[0][6] = excelData.get("R_Time");
		data[0][7] = excelData.get("V_R_City");
		data[0][8] = excelData.get("V_R_Date");
		return data;
	}
	
/* 12. Travel Agent */
	@Test(priority = 12)
	public void TravelAgents() throws ApplicationException {
		test = report.startTest("Travel Agent");
		try {
			new TravelAgent(driver, log, test).select();
			report.endTest(test);
			report.flush();
			
		} catch (Exception e) {
			report.endTest(test);
			report.flush();
			throw new ApplicationException(e.getMessage());
		}
	}
}


