package AutomaticTest.AutomaticTest;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DeptTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://10.124.5.40:8082";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void test() throws Exception {
    driver.get(baseUrl + "/default/coframe/auth/login/login.jsp");
    driver.findElement(By.id("userId$text")).clear();
    driver.findElement(By.id("userId$text")).sendKeys("sysadmin");
    driver.findElement(By.id("password$text")).clear();
    driver.findElement(By.id("password$text")).sendKeys("000000");  
    driver.findElement(By.cssSelector("input.log")).click();
    driver.findElement(By.id("2")).click();
    driver.findElement(By.id("1161")).click();
    
    //切换到mainiframe
    driver.switchTo().frame("mainframe");
    Thread.sleep(2000);
    
    //切换到员工查询页面
    driver.findElement(By.id("mini-16$body$2")).click();
    Thread.sleep(2000);
    
    //checkbox选择
    driver.findElement(By.xpath("//*[@id='1$cell$1']")).click();
    Thread.sleep(2000);
    
    //切换到iframe为src="/default/org/organization/dep_index.jsp?_t=705901&_winid=w773"
    WebElement frame=driver.findElement(By.xpath("//*[@id='mini-16$body$2']/iframe"));
    driver.switchTo().frame(frame);
    Thread.sleep(2000);
    
    //点击编辑
    driver.findElement(By.cssSelector("#edit_btn > span")).click();
    Thread.sleep(2000);
    
    //保存
    //先切换到相应iframe
    driver.switchTo().defaultContent();//回到初始iframe
    Thread.sleep(2000);
    WebElement frame2=driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/iframe"));
    driver.switchTo().frame(frame2);
    Thread.sleep(2000);
    
    //点击保存
    driver.findElement(By.xpath("/html/body/div[2]/a[1]/span")).click();
    Thread.sleep(2000);
    
    // 退出登录
    driver.switchTo().defaultContent();
	driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[1]/div[1]/p[1]/span[2]/a[2]")).click();
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
