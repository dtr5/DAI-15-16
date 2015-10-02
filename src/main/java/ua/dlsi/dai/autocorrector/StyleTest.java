package ua.dlsi.dai.autocorrector;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StyleTest {

	private static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		driver = new SafeDriver();
		driver.get(Selenium2.url);
		driver.manage().window().setSize(new Dimension(1080, 1080));
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

	@Test
	// Check the style of the body
	public void bodyStyle_Style() throws Exception {
		List<WebElement> divs = driver.findElements(By.xpath("//body"));
		Assert.assertNotEquals("No hay un elemento body.", 0, divs.size());		
		WebElement body = divs.get(0);
		Assert.assertEquals("El color de letra es incorrecto.",
				"rgba(51, 51, 51, 1)", body.getCssValue("color"));
		Assert.assertEquals("El color de fondo de body es incorrecto.",
				"rgba(255, 255, 255, 1)", body.getCssValue("background-color"));

	}

	@Test
	// .idioma should be in italics
	public void idiomaStyle_Style() throws Exception {
		List<WebElement> divs = driver.findElements(By.cssSelector(".idioma"));
		Assert.assertNotEquals("No hay un elemento con clase idioma.", 0, divs.size());	
		WebElement section = divs.get(0);
		Assert.assertEquals("El estilo de fuente de idioma es incorrecto.",
				"italic", section.getCssValue("font-style"));
	}


}
