package ua.dlsi.dai.autocorrector;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Util {
	private static WebElement button, text;

	public static void appEngineLogin(WebDriver driver) throws Exception {
		appEngineLogin(driver, "newAccount@fake.com");
	}
	
	public static void appEngineHalfLogin(WebDriver driver) throws Exception {
		appEngineHalfLogin(driver, "newAccount@fake.com");
	}	

	public static void appEngineLogin(WebDriver driver, String login) throws Exception {
		button = driver.findElement(By.cssSelector(".btn-lg"));
		Assert.assertNotNull("No existe el botón de login", button);
		button.click();

		text = driver.findElement(By.cssSelector("#email"));
		Assert.assertNotNull("No existe el textinput email", text);

		text.clear();
		text.sendKeys(login);

		button = driver.findElement(By.cssSelector("#btn-login"));
		Assert.assertNotNull("No existe el botón de login", button);
		button.click();

		button = driver.findElement(By.cssSelector("#nuevoCuestionario"));
		Assert.assertNotNull("No se ha realizado el login correctamente",
				button);
	}
	
	public static void appEngineHalfLogin(WebDriver driver, String login) throws Exception {
		text = driver.findElement(By.cssSelector("#email"));
		Assert.assertNotNull("No existe el textinput email", text);

		text.clear();
		text.sendKeys(login);

		button = driver.findElement(By.cssSelector("#btn-login"));
		Assert.assertNotNull("No existe el botón de login", button);
		button.click();
	}
}
