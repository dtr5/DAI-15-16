package ua.dlsi.dai.autocorrector;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AppEngineCuestionarioTest {

	private static SafeDriver driver;
	private WebElement button, text;

	@Before
	public void setUp() {
		driver = new SafeDriver();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void loginAppEngine() throws Exception {
		driver.get(Practica4.url + "/main");

		Util.appEngineLogin(driver);

		Thread.sleep(1000);

		button = driver.findElement(By.cssSelector("#logout a"));
		Assert.assertNotNull("No existe el botón salir", button);
		button.click();
	}

	@Test
	public void crearCuestionarioAppEngine() throws Exception {
		driver.get(Practica4.url + "main");

		Util.appEngineLogin(driver);

		Thread.sleep(1000);

		text = driver.findElement(By
				.cssSelector("#nuevoCuestionario input[name='tema']"));
		Assert.assertNotNull("No se ha realizado el login correctamente", text);

		button = driver.findElement(By
				.cssSelector("#nuevoCuestionario input[type='button']"));
		Assert.assertNotNull("No se ha realizado el login correctamente",
				button);

		text.clear();
		text.sendKeys("Valencia");
		button.click();

		Thread.sleep(1000);

		button = driver.findElement(By
				.cssSelector("encabezado-cuestionario[tema='Valencia']"));
		Assert.assertNotNull("No se ha creado el cuestionario correctamente",
				button);

		button = driver.findElement(By.cssSelector("#logout a"));
		Assert.assertNotNull("No existe el botón salir", button);
		button.click();
	}

}
