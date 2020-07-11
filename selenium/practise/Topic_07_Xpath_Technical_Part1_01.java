package practise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Xpath_Technical_Part1_01 {

	WebDriver driver;
	@BeforeClass
	
	public void beforeClass() {
	driver = new FirefoxDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
	
	@Test
	public void TC_08_Xpath() throws InterruptedException {	
	//Selenium sẽ tương tác với thẻ input(element) đầu tiên trong 5 cái tìm được
	//Selenium luôn tìm 1 matching note
    driver.findElement(By.xpath("//input")).sendKeys("tim cai dau tien");
	}
	@AfterClass//Post-codition
    public void afterClass() {
    driver.quit();
    System.out.println("DONE_Testcase_topic_07_Xpath_Technical_Part_01");
			    }
	}