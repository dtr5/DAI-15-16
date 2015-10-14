package ua.dlsi.dai.autocorrector;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptTest {

	private static WebDriver driver;

	@Before
	public void setUp() {
		driver = new SafeDriver();
		driver.get(Practica2.url);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void addCuestionarioWorks_JS() throws Exception {
		WebElement form = driver.findElement(By
				.cssSelector("#nuevoCuestionario"));
		Assert.assertNotNull(
				"El formulario nuevoCuestionario no se puede encontrar", form);
		WebElement tema = driver.findElement(By
				.cssSelector("#nuevoCuestionario input[name='tema']"));
		Assert.assertNotNull(
				"El textinput tema de nuevoCuestionario no se puede encontrar",
				tema);
		WebElement imageUrl = driver.findElement(By
				.cssSelector("#nuevoCuestionario input[name='imagen']"));
		Assert.assertNotNull(
				"El textinput imagen de nuevoCuestionario no se puede encontrar",
				imageUrl);
		WebElement boton = driver.findElement(By
				.cssSelector("#nuevoCuestionario input[name='crea']"));
		Assert.assertNotNull(
				"El botón crea de nuevoCuestionario no se puede encontrar",
				boton);

		tema.sendKeys("San Sebastián");
		imageUrl.sendKeys("ss.jpg");
		boton.click();

		WebElement newCuestionario = driver.findElement(By.cssSelector("#c1"));

		Assert.assertNotNull("No se ha creado correctamente el cuestionario",
				newCuestionario);
	}

	@Test
	public void removeExistingPregunta_JS() throws Exception {
		WebElement form = driver
				.findElement(By.xpath("//section[@id='paris']"));

		Assert.assertNotNull("La sección paris no se puede encontrar", form);
		List<WebElement> tema = driver.findElements(By
				.cssSelector("#paris div[class='bloque']"));

		Assert.assertEquals("No hay 3 divs bloque en la sección paris", 3,
				tema.size());

		List<WebElement> buttons = driver.findElements(By
				.cssSelector("#paris div[class='bloque'] div[class='borra']"));
		Assert.assertEquals(
				"No hay botones para borrar cuestiones en la sección paris o no respetan la estructura",
				3, buttons.size());

		buttons.get(0).click();

		tema = driver
				.findElements(By.cssSelector("#paris div[class='bloque']"));

		Assert.assertEquals(
				"No se ha borrado correctamente una pregunta en la sección parís",
				2, tema.size());
	}

}
