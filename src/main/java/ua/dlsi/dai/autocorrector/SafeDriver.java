package ua.dlsi.dai.autocorrector;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SafeDriver extends ChromeDriver {
	
	public SafeDriver() {
	}

	public SafeDriver(DesiredCapabilities dc) {
		super(dc);
	}

	@Override
	public WebElement findElement(By by) {
		try {
			return super.findElement(by);
		} catch (Exception ex) {
//			ex.printStackTrace();
			return null;
		}
	}
}
