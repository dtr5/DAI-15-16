package ua.dlsi.dai.autocorrector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ComponentsTest {
	private static WebDriver driver;

	@BeforeClass
	public static void mainSetUp() {

	}

	@Before
	public void setUp() {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
				UnexpectedAlertBehaviour.DISMISS);
		driver = new SafeDriver(dc);
		driver.get(Practica3.url);
	}

	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void testFlickrInstancia_ComponentsTest() throws Exception {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		String script;
		String result;

		script = "n=document.createElement('imagen-flickr');"
				+ "n.tema='San Sebastián';"
				+ "p=document.getElementsByTagName('h1')[0];"
				+ "p.appendChild(n);";

		try {
			result = (String) je.executeScript(script);
		} catch (Exception ex2) {
			Assert.assertTrue("Error al crear el componente imagen-flickr.",
					false);
			result = "";
		}
	}	
	
	@Test
	public void testFlickrFormado_ComponentsTest() throws Exception {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		String script;
		String result;

		script = "n=document.createElement('imagen-flickr');"
				+ "n.tema='San Sebastián';"
				+ "p=document.getElementsByTagName('h1')[0];"
				+ "p.appendChild(n);";

		try {
			result = (String) je.executeScript(script);
		} catch (Exception ex2) {
			Assert.assertTrue("Error al crear el componente imagen-flickr.",
					false);
			result = "";
		}
		
		int times = 0;
		result = null;
		while (times++ < 10 && (result == null || result.equals("")))
		{
			script = "return document.getElementsByTagName('h1')[0].lastChild.url;"; 
			
			try {
				result = (String) je.executeScript(script);
			} catch (Exception ex2) {
				Assert.assertTrue("Error al crear el componente imagen-flickr.",
						false);
				result = "";
			}	
			
			Thread.sleep(1000);
		}
		Assert.assertNotNull("El componente imagen-flickr no tiene los atributos correctos", result);
	}


}
