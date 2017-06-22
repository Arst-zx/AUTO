package AutomaticTest.AutomaticTest;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MyWorkTime {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	// 测试
	@Before
	public void setUp() throws Exception {
		/*
		 * //启动谷歌 System.setProperty("webdriver.chrome.driver",
		 * "C:\\Users\\home\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe"
		 * ); driver = new ChromeDriver();
		 */
		// 启动火狐
		driver = new FirefoxDriver();
		baseUrl = "http://10.0.209.7";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testWorkTime() throws Exception {
		driver.get(baseUrl + "/ProjectManage/index.html#/index");

		// 登录用户名、密码
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("zhangx511");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("000000");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("工时填报")).click();
		Thread.sleep(2000);

		// 点击添加
		driver.findElement(By.id("bxzbutton")).click();

		// 填写工时信息
		//new Select(driver.findElement(By.id("i_projname"))).selectByVisibleText("2017项目制管理平台建设项目");
		//选择项目名称
		driver.findElement(By.cssSelector("#i_projname :last-child")).click();
		/*new Select(driver.findElement(By.xpath("//div[@id='bxzmyModal']/div/div/form/div[2]/div[2]/div/select")))
				.selectByVisibleText("代码开发");*/
		//选择活动类型
		driver.findElement(By.xpath("(//option[@value='80c806ecce6611e6bb22782bcb380923'])[2]")).click();
		
		//填写周一到周五工时
		driver.findElement(By.xpath("(//input[@type='text'])[10]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[10]")).sendKeys("8");
		driver.findElement(By.xpath("(//input[@type='text'])[11]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[11]")).sendKeys("8");
		driver.findElement(By.xpath("(//input[@type='text'])[12]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[12]")).sendKeys("8");
		driver.findElement(By.xpath("(//input[@type='text'])[13]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[13]")).sendKeys("8");
		driver.findElement(By.xpath("(//input[@type='text'])[14]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[14]")).sendKeys("8");

		// 找到保存按钮
		WebElement elementAdd = driver.findElement(By.id("bztianjia"));
		//点击保存
		elementAdd.click();

		Thread.sleep(2000);
		 
		// 提交按钮
		WebElement elementSubmit = driver.findElement(By.id("btibutton"));
		//滚动下拉框到提交按钮所在位置 
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", elementSubmit);
		 
		Thread.sleep(2000);
/*      //周五提交时请放开注释
		//点击提交按钮
		elementSubmit.click();
		
		// 弹框提示是否提交，点击确定
		driver.findElement(By.xpath("//*[@id='btjmyModal']/div/div/div[3]/button[1]")).click();
		Thread.sleep(2000);
*/
		// 退出登录
		driver.findElement(By.cssSelector("#goback > span")).click();
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
