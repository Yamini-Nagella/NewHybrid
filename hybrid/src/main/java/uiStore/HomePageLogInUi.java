package uiStore;

import org.openqa.selenium.By;

public class HomePageLogInUi {
	public static By logInButton = By.xpath(".//*[@class='bg-signin m-auto']");
	public static By email = By.xpath(".//*[@formcontrolname='userEmail']");
	public static By pass = By.xpath(".//*[@formcontrolname='userPassword']");
	public static By signin = By.xpath(".//*[@class='hori-vertical-center']");
	public static By vheading = By.xpath(".//*[@class='col-auto user_login pl-1 pl-md-2 pr-0 ng-star-inserted']");
}
