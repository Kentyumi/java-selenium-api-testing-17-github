package Exercise;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebElement element;
	
	String email = "quochung" + randomNumber() + "@gmail.com" ;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver" , ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_Exercise_TC03() {
		navigateToUrlByJS("http://live.demoguru99.com/");
		
		highlightElement("//div[@id='header-account']//a[@title='My Account']");	
		clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");	
		
		highlightElement("//span[text()='Create an Account']");	
		clickToElementByJS("//span[text()='Create an Account']");	
		
		sendkeyToElementByJS("//input[@id='firstname']", "Nguyen");
		sendkeyToElementByJS("//input[@id='middlename']", "Quoc");
		sendkeyToElementByJS("//input[@id='lastname']", "Hung");
		sendkeyToElementByJS("//input[@id='email_address']", email);
		sendkeyToElementByJS("//input[@id='password']", "552912");
		sendkeyToElementByJS("//input[@id='confirmation']", "552912");
		
		highlightElement("//span[text()='Register']");
		clickToElementByJS("//span[text()='Register']");
		
		verifyTextInInnerText("Thank you for registering with Main Website Store.");
		
		highlightElement("//a[text()='Log Out']");
		clickToElementByJS("//a[text()='Log Out']");
		
		driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed();
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	
	}
	
	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}
	
	public void clickToElementByJS(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(99);
	}	
	
	public void sendkeyToElementByJS(String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}
	
	public boolean verifyTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text actual = " + textActual);
		return textActual.equals(textExpected);
	}
	
	public void highlightElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}	
}