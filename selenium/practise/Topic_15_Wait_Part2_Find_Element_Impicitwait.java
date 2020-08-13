package practise;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_15_Wait_Part2_Find_Element_Impicitwait {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
	}


	//@Test
	public void TC_01_Find_Element() {
		
		// 1 element
		
		System.out.println("1 - Start = " + getDateTimeNow());
		driver.findElement(By.xpath("//button[@id='loginbutton']")).isDisplayed();
		System.out.println("1 - End = " + getDateTimeNow());
		
		// >1 element
		
		System.out.println("2 - Start = " + getDateTimeNow());
		driver.findElement(By.xpath("//input[@name='sex']")).click();
		System.out.println("2 - End = " + getDateTimeNow());
		
		// 0 element
	
		System.out.println("3 - Start = " + getDateTimeNow());
		try {
		driver.findElement(By.xpath("//input[@name='address']")).isDisplayed();
		} catch (Exception e) {
		System.out.println("3 - End = " + getDateTimeNow());
		throw e;
		}
		
	}
	
	//@Test
	public void TC_02_Find_Elements() {
		List<WebElement> elements ;
		
		// 1 element
		
		System.out.println("1 - Start = " + getDateTimeNow());
		elements = driver.findElements(By.xpath("//button[@id='loginbutton']"));
		System.out.println("Element size = " + elements.size());
		System.out.println("1 - End = " + getDateTimeNow());
		
		// >1 element
			
		System.out.println("2 - Start = " + getDateTimeNow());
		elements = driver.findElements(By.xpath("//input[@name='sex']"));
		System.out.println("Element size = " + elements.size());
		System.out.println("2 - End = " + getDateTimeNow());
		
		// 0 element
	
		System.out.println("3 - Start = " + getDateTimeNow());
		elements = driver.findElements(By.xpath("//input[@name='address']"));
		System.out.println("Element size = " + elements.size());
		System.out.println("3 - End = " + getDateTimeNow());
		elements.get(0).isDisplayed();

	}
	
	//@Test
	public void TC_03_FindElements() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
	    System.out.println("Checkbox size = " + checkBoxes.size());
	    for (WebElement checkBox : checkBoxes) {
	    	checkBox.click();
	    	
	    }
	}
	
	@Test
	public void TC_04_Implicit_Wait() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://juliemr.github.io/protractor-demo/");
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("5");
		driver.findElement(By.id("gobutton")).click();

		// đang tìm element với thẻ h2 có text=10
		Assert.assertTrue(driver.findElement(By.xpath("//h2[@text()='10']")).isDisplayed());
		
		// đang tìm element với thẻ h2
		// Assert.assertEquals(driver.findElement(By.tagName("h2")).getText(), "10");
	}

	@AfterClass
	public void afterClass() {
	driver.quit();
	}
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
}