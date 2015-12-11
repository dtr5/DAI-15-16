package ua.dlsi.dai.autocorrector;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AppEnginePreguntasTest {

	private static SafeDriver driver;

	private static WebElement button, text;

	@BeforeClass
	public static void create() {
		driver = new SafeDriver();
		driver.get("http://localhost:8080/nuevocuestionario?tema=Alicante");
		try {
			text = driver.findElement(By.cssSelector("#email"));
			if (text != null) {
				text.clear();
				text.sendKeys("newAccount@fake.com");
			}
			button = driver.findElement(By.cssSelector("#btn-login"));
			if (button != null) {
				button.click();
			}
		} catch (Exception e) {
		}
		driver.get("http://localhost:8080/_ah/logout?continue=%2Fmain");
	}

	@Before
	public void setUp() {
		driver = new SafeDriver();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void addPreguntaAppEngine() throws Exception {
		driver.get("http://localhost:8080/main");

		Util.appEngineLogin(driver);

		Thread.sleep(1000);

		WebElement tema = driver.findElement(By
				.cssSelector("input[name='c1_pregunta']"));
		Assert.assertNotNull(
				"El textinput pregunta de la sección no se puede encontrar",
				tema);
		List<WebElement> radio = driver.findElements(By
				.cssSelector("input[name='c1_respuesta']"));
		Assert.assertTrue(
				"El radiobutton respuesta de la sección no se puede encontrar",
				radio.size() == 2);
		WebElement boton = driver.findElement(By
				.cssSelector("#c1 input[type='button']"));
		Assert.assertNotNull(
				"El botón crea de nuevoCuestionario no se puede encontrar",
				boton);

		tema.sendKeys("Badajoz fue fundada en el siglo XIX.");
		radio.get(1).click();
		boton.click();

		Thread.sleep(1000);

		button = driver.findElement(By.cssSelector("#logout a"));
		Assert.assertNotNull("No existe el botón salir", button);
		button.click();

		driver.get("http://localhost:8080/main");

		Util.appEngineLogin(driver);

		Thread.sleep(2000);

		WebElement insertedQuestion = driver
				.findElement(By
						.xpath("//div[div[. = 'Badajoz fue fundada en el siglo XIX.']]"));

		Assert.assertNotNull(
				"No se ha añadido correctamente la pregunta al cuestionario",
				insertedQuestion);

		WebElement value = driver
				.findElement(By
						.xpath("//div[div[. = 'Badajoz fue fundada en el siglo XIX.']]/div[@data-valor='false']"));

		Assert.assertNotNull(
				"No se ha añadido correctamente la respuesta de la pregunta al cuestionario",
				value);

		button = driver.findElement(By.cssSelector("#logout a"));
		Assert.assertNotNull("No existe el botón salir", button);
		button.click();
	}

	@Test
	public void addPreguntaServiceAppEngine() throws Exception {
		driver.get("http://localhost:8080/nuevapregunta?tema=Alicante&respuesta=false&pregunta=1234");

		WebElement button = driver.findElement(By.cssSelector(".btn-lg"));
		Assert.assertNull(
				"No se ha redirigido la petición incorrecta correctamente",
				button);

		Util.appEngineHalfLogin(driver);

		driver.get("http://localhost:8080/main");
		Thread.sleep(1000);

		button = driver.findElement(By.cssSelector("#nuevoCuestionario"));
		Assert.assertNotNull("No se ha realizado el login correctamente",
				button);

		WebElement insertedQuestion = driver.findElement(By
				.xpath("//div[div[. = '1234']]"));

		Assert.assertNotNull(
				"No se ha añadido correctamente la pregunta al cuestionario",
				insertedQuestion);

		WebElement value = driver.findElement(By
				.xpath("//div[div[. = '1234']]/div[@data-valor='false']"));

		Assert.assertNotNull(
				"No se ha añadido correctamente la respuesta de la pregunta al cuestionario",
				value);

		button = driver.findElement(By.cssSelector("#logout a"));
		Assert.assertNotNull("No existe el botón salir", button);
		button.click();
	}

}
