package Base;

import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;

public class MyChromeOptions {

	public ChromeOptions setChromeOptions() {

		ChromeOptions options = new ChromeOptions();

      // Headless mode - to run the project without UI
      // options.addArguments("--headless");

		// Disable Chrome automation message
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

		// Disable extensions
		options.addArguments("--disable-extensions");

		// Start maximized
		options.addArguments("--start-maximized");

		// Disable notifications
		options.addArguments("--disable-notifications");

		// Add other arguments
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-gpu");

		// Set preferences
		options.setExperimentalOption("prefs", Map.of("profile.default_content_setting_values.notifications", 2,
				"profile.default_content_setting_values.geolocation", 2));

		// Accept insecure certificates
		options.setAcceptInsecureCerts(true);

		return options;

	}

}
