package AutomaticTest.AutomaticTest;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class DeptTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://10.124.5.40:8082/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testDept() throws Exception {
		driver.get(baseUrl + "default/coframe/auth/login/login.jsp");
		driver.findElement(By.id("password$text")).clear();
		driver.findElement(By.id("password$text")).sendKeys("000000");
		driver.findElement(By.id("userId$text")).clear();
		driver.findElement(By.id("userId$text")).sendKeys("sysadmin");
		driver.findElement(By.id("2")).click();
		driver.findElement(By.id("1082")).click();
		driver.findElement(By.id("1082")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [selectFrame |
		// main | ]]
		driver.findElement(By.xpath("//td[@id='mini-16$2']/span")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow |
		// null | ]]
		driver.findElement(By.xpath("//a[@id='edit_btn']/span")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow |
		// null | ]]
		driver.findElement(By.xpath("//a/span")).click();
		driver.findElement(By.linkText("娉ㄩ攢")).click();
		driver.findElement(By.cssSelector("input.log")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
