package es.uniovi.asw;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import cucumber.api.java.en.*;

import es.uniovi.asw.util.*;

public class PruebaTest implements comprobarCabeceraAdmin {
	private SeleniumUtil sU = new SeleniumUtil();
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = sU.getDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws Throwable {
		the_user_is_on_the_login_page();
		the_user_is_logger_on_as_admin();
		the_user_is_on_the_admin_main_page();
		the_user_clicks_on_the_button("Propuestas en trámite");
		the_proposals_are_in_process_will_be_shown_to_the_user();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	@Given("^the user is on the login page$")
	public void the_user_is_on_the_login_page() throws Throwable {
		driver.get(baseUrl + "/");
		sU.comprobarTexto("titulo", "Participation System");
		assertTrue(sU.isElementPresent(By.id("dniLabel")));
		assertTrue(sU.isElementPresent(By.id("dni")));
		assertTrue(sU.isElementPresent(By.id("passwordLabel")));
		assertTrue(sU.isElementPresent(By.id("password")));
		assertTrue(sU.isElementPresent(By.id("login")));
		sU.comprobarNumElemetos("body", "./*", 2);
		sU.comprobarNumElemetos("form", "./*", 7);
		sU.comprobarNumElemetos("form", "./input", 3);
	}

	@Given("^the user is logger on as admin$")
	public void the_user_is_logger_on_as_admin() throws Throwable {
		driver.findElement(By.name("dni")).clear();
		driver.findElement(By.name("dni")).sendKeys("666xxx");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("admin");
		assertTrue(sU.isElementPresent(By.id("login")));
		driver.findElement(By.id("login")).click();
	}

	@Given("^the user is on the admin main page$")
	public void the_user_is_on_the_admin_main_page() throws Throwable {
		sU.comprobarNumElemetos("body", "./*", 1);
		sU.comprobarCabeceraAdmin();

	}

	@When("^the user clicks on the button \"([^\"]*)\"$")
	public void the_user_clicks_on_the_button(String arg1) throws Throwable {
		sU.comprobarCabeceraAdmin();
		driver.findElement(By.linkText(arg1)).click();
	}

	@Then("^the proposals are in process will be shown to the user$")
	public void the_proposals_are_in_process_will_be_shown_to_the_user()
			throws Throwable {
		sU.comprobarCabeceraAdmin();
		sU.comprobarNumElemetos("body", "./*", 3);
		sU.comprobarTexto("propuestas", "Propuestas en trámite");
		WebElement tabla = driver
				.findElement(By.id("tablaPropuestasEnTramite"));
		assertNotNull(tabla);
		List<WebElement> numFilas = tabla.findElements(
				By.xpath("id('tablaPropuestasEnTramite')/tbody/tr"));
		if (numFilas.size() > 0) {
			for (WebElement trElement : numFilas) {
				List<WebElement> numColum = trElement
						.findElements(By.xpath("td"));
				assertEquals(6, numColum.size());
				for (WebElement elemento : numColum) {
					assertNotNull(elemento);
				}
			}
		}
	}
}