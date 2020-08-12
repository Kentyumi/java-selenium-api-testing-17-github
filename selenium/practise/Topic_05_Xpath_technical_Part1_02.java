package practise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Xpath_technical_Part1_02 {

	WebDriver driver;
	@BeforeClass
	
	public void beforeClass() {
	driver = new FirefoxDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get("http://live.demoguru99.com/");
	}
	
	@Test
	public void TC_01_Xpath() throws InterruptedException {	
	//Click vao My account
	//Case này sẽ failed vì định danh có 2 matching nodes
    driver.findElement(By.xpath("a[@title='My Account']")).click();
	}
	
	@Test
	public void TC_02_Xpath() throws InterruptedException {	
	//Click vao My account
	//Dựa vào kỹ thuật NODE cha nối tiếp node con
    driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	}
	
	@Test
	public void TC_03_Xpath() throws InterruptedException {	
	//Kỹ thuật Xpath Axes
    driver.get("http://live.demoguru99.com/index.php/mobile.html");
    Thread.sleep(2000);
    //Click vào đúng button của Iphone Add to cart
    driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//button")).click();
	}
	
	@AfterClass//Post-codition
    public void afterClass() {
    driver.quit();
    System.out.println("DONE_Testcase_topic_07_Xpath_Technical_Part_02");
			    }
	}
