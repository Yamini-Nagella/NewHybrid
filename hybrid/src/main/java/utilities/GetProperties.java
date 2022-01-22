package utilities;

import java.io.FileInputStream;
import java.util.Properties;

import exceptions.UtilityException;

public class GetProperties {
	public static Properties get;

	public GetProperties() throws UtilityException, Exception {
		if (get == null) {
			try {
				get = new Properties();
				FileInputStream fs = new FileInputStream(
						System.getProperty("user.dir") + "/src/main/resources/config.properties");
				get.load(fs);
			} catch (Exception e) {
				throw new UtilityException(e.getMessage());
			}
		}
	}
}
