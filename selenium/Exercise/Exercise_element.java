package Exercise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exercise_element {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Element_isDisplayed() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		boolean status;
		status = driver.findElement(By.id("mail")).isDisplayed();
		System.out.println(status);
		
		// Email textbox
		if (status) {
			driver.findElement(By.id("mail")).sendKeys("nguyenquochung291288@gmail.com");
		}

		// Education text area
		status = driver.findElement(By.id("edu")).isDisplayed();
		System.out.println(status);	
		if (status) {
		driver.findElement(By.id("edu")).sendKeys("Bach Khoa");}
		
		// Age under 18 -radio button
		status = driver.findElement(By.id("under_18")).isDisplayed();
		System.out.println(status);	
		if (status) {
			driver.findElement(By.id("under_18")).click();
		}
	}
	@Test
	public void TC_02_Element_enable_disable() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Enable
		boolean status;
		status = driver.findElement(By.id("mail")).isEnabled();
		System.out.println(status);
		if (status) {
			System.out.println("Email is enable");
		} else {
			System.out.println("Email is disable");
		}

		status = driver.findElement(By.id("edu")).isEnabled();
		System.out.println(status);
		if (status) {
			System.out.println("Education is enable");
		} else {
			System.out.println("Education is disable");
		}
		if (driver.findElement(By.id("under_18")).isEnabled()) {
			System.out.println("Age is enable");
		} else {
			System.out.println("Age is disable");
		}

		if (driver.findElement(By.id("job1")).isEnabled()) {
			System.out.println("Job Role 1 is enable");
		} else {
			System.out.println("Job Role 1 is disable");
		}
		if (driver.findElement(By.id("job2")).isEnabled()) {
			System.out.println("Job Role 2 is enable");
		} else {
			System.out.println("Job Role 2 is disable");
		}
		if (driver.findElement(By.id("slider-1")).isEnabled()) {
			System.out.println("Slider 1 is enable");
		} else {
			System.out.println("Slider 1 is disable");
		}
		// Disable
		if (driver.findElement(By.name("user_pass")).isEnabled()) {
			System.out.println("Password is enable");
		} else {
			System.out.println("Password is disable");
		}

		if (driver.findElement(By.id("bio")).isEnabled()) {
			System.out.println("Biography is enable");
		} else {
			System.out.println("Biography is disable");
		}

		if (driver.findElement(By.id("job3")).isEnabled()) {
			System.out.println("Job Role 3 is enable");
		} else {
			System.out.println("Job Role 3 is disable");
		}

		if (driver.findElement(By.id("check-disbaled")).isEnabled()) {
			System.out.println("Interests is enable");
		} else {
			System.out.println("Interests is disable");
		}
		
		// Rút gọn qua 3 dòng code
				Assert.assertTrue(isElementEnable("//input[@id='mail']"));
				Assert.assertTrue(isElementEnable("//textarea[@id='edu']"));
				Assert.assertFalse(isElementEnable("//input[@id='slider-2']"));
	}
	@Test
	public void TC_03_Element_isSelected() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Click lần đầu
		driver.findElement(By.id("over_18")).click();
		driver.findElement(By.id("development")).click();

		Assert.assertTrue(isElementSelected("//input[@id='over_18']"));
		Assert.assertTrue(isElementSelected("//input[@id='development']"));

		// Click lần 2
		driver.findElement(By.id("over_18")).click();
		driver.findElement(By.id("development")).click();

		Assert.assertTrue(isElementSelected("//input[@id='over_18']"));
		Assert.assertFalse(isElementSelected("//input[@id='development']"));

	}

	public boolean isElementDisplayed(String xpathValue) {
		WebElement element = driver.findElement(By.xpath(xpathValue));
		if (element.isDisplayed()) {
			System.out.println("Element with xpath =" + xpathValue + "is displayed!");
			return true;
		} else {
			System.out.println("Element with xpath =" + xpathValue + "is not displayed");
			return false;
		}
	}

	public boolean isElementSelected(String xpathValue) {
		WebElement element = driver.findElement(By.xpath(xpathValue));
		if (element.isSelected()) {
			System.out.println("Element with xpath =" + xpathValue + "is selected!");
			return true;
		} else {
			System.out.println("Element with xpath =" + xpathValue + "is deselected");
			return false;
		}
	}

	public boolean isElementEnable(String xpathValue) {
		WebElement element = driver.findElement(By.xpath(xpathValue));
		if (element.isEnabled()) {
			System.out.println("Element with xpath =" + xpathValue + "is enable!");
			return true;
		} else {
			System.out.println("Element with xpath =" + xpathValue + "is disable!");
			return false;
		}
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}