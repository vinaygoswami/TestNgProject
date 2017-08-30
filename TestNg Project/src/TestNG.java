import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestNG {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    
    System.setProperty("webdriver.firefox.marionette","D:\\geckodriver.exe");
	WebDriver driver = new FirefoxDriver();
	Thread.sleep(3000);
	driver.get("URL");
	driver.manage().window().maximize();
    baseUrl = "URL";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUntitled() throws Exception {
    driver.get(baseUrl + "/register-for-business");
    driver.findElement(By.name("first_name")).clear();
    driver.findElement(By.name("first_name")).sendKeys("test");
    driver.findElement(By.name("last_name")).clear();
    driver.findElement(By.name("last_name")).sendKeys("test");
    driver.findElement(By.cssSelector("div.recaptcha-checkbox-checkmark")).click();
    driver.findElement(By.id("box1")).click();
    driver.findElement(By.name("btnSubmit")).click();
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