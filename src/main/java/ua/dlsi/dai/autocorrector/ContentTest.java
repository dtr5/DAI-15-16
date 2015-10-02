package ua.dlsi.dai.autocorrector;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContentTest {

	private static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		driver = new SafeDriver();
		driver.get(Selenium2.url);
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

	@Test
	// Title should be inside header, and equal to qÜestiona
	public void titleIsQuestiona_Content() throws Exception {
		List<WebElement> divs = driver.findElements(By.xpath("//header//h1"));
		Assert.assertEquals("No hay elemento h1 en header", 1, divs.size());
		WebElement h1 = divs.get(0);
		Assert.assertEquals(
				"El título es incorrecto (posiblemente la página web no es UTF-8).",
				"qÜestiona", h1.getText());
	}

	@Test
	// No style on html file
	public void noStyle_Content() throws Exception {
		Assert.assertEquals("Hay estilo mezclado con el contenido.", 0, driver
				.findElements(By.xpath("//*[@style]")).size());
	}

	@Test
	// Nav
	public void hasNav_Content() throws Exception {
		Assert.assertEquals("No hay un elemento nav.", 1,
				driver.findElements(By.xpath("//nav")).size());
	}

	@Test
	// Sections paris
	public void hasSectionsWithparis_Content() throws Exception {
		Assert.assertTrue(
				"No existe un elemento section con id paris.",
				!driver.findElements(
						By.xpath("//main//section[@id='paris']"))
						.isEmpty());
	}
	
	@Test
	// Sections with h2 and image
	public void hasSectionsWithh2AndImage_Content() throws Exception {
		Assert.assertTrue(
				"No se respeta la estructura de section.",
				!driver.findElements(
						By.xpath("//main//section//h2//img"))
						.isEmpty());
	}

}
