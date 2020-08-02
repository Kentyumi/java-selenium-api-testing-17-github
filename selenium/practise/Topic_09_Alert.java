package practise;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_09_Alert {
	WebDriver driver;
    Alert alert;
   
    String rootFolder = System.getProperty("user.dir");
    String firefoxAuthen = rootFolder + "\\autoITScript\\authen_firefox.exe";
    String chromeAuthen = rootFolder + "\\autoITScript\\authen_chrome.exe";
	
    @BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Bank_Guru() throws InterruptedException  {
		driver.get("http://demo.guru99.com/v4/index.php");
		
		// Click login button
		driver.findElement(By.name("btnLogin")).click();
        sleepInSecond(2);		
		// Alert display
		
		// Switch vao Alert
		alert = driver.switchTo().alert();
		 sleepInSecond(2);
		 
		// Get text Alert
		System.out.println("Alert text= " + alert.getText());
		
		// Send text vào Alert (Prompt)
		// alert.sendKeys("");
		
		// Accept Alert
		alert.accept();
		
		// Cancel Alert
		//alert.dismiss();
		
	}

	@Test
	public void TC_02_JS_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
        
		/*   --------------------------- 1- Alert accept ------------------------------- */                                                 
		
		// Click JS Alert
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		//Switch vào Alert
		alert = driver.switchTo().alert();
		//Verify Alert text
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		//Accept alert
		alert.accept();
		sleepInSecond(3);
		//Verify Accept alert text
		Assert.assertEquals(driver.findElement(By.id("result")).getText() ,"You clicked an alert successfully");
		
		/*   --------------------------- 2- Alert confirm ------------------------------- */   
		
		// Tải lại trang
		driver.navigate().refresh();
		
		// Click  JS confirm 
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		//Switch vào Alert
		alert = driver.switchTo().alert();
		// Verify Alert text
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		// Cancel alert
		alert.dismiss();
		sleepInSecond(3);
		// Verify dismiss alert text
		Assert.assertEquals(driver.findElement(By.id("result")).getText() ,"You clicked: Cancel");
		
		/*   --------------------------- 3- Alert prompt ------------------------------- */  
		
		// Tải lại trang
		driver.navigate().refresh();
		// Click  JS prompt 
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		//Switch vào Alert
		alert = driver.switchTo().alert();
		// Verify Alert text
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		// Sendkey to Alert
		alert.sendKeys("Automation Kent");
		sleepInSecond(3);
		// Accept alert
		alert.accept();
		sleepInSecond(3);
		// Verify accepted alert text
		Assert.assertEquals(driver.findElement(By.id("result")).getText() ,"You entered: Automation Kent");
	}

	@Test
	public void TC_03_Authentication_Alert() {
	    
		String username = "admin";
	    String password = "admin";
	    
	    // Xác thực qua link không cần bật alert lên nữa
		driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");	
		
		// By pass Authentication Alert
	    Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Basic Auth']")).isDisplayed());
		

	}
	
	@Test
	public void TC_04_Authentication_Alert() {
		
		String username = "admin";
	    String password = "admin";
		
		driver.get("http://the-internet.herokuapp.com/");
		
		// Click Basic Auth link 
		
		
		// Get link href
		String basicAuthLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");	
		handleAuthenticationAlert(basicAuthLink, username , password);
		
	}

	@Test
	public void TC_05_Authentication_Alert_autoIT() throws IOException {

		String username = "admin";
	    String password = "admin";
	    String authenUrl = "http://the-internet.herokuapp.com/basic_auth";
	    
	    if(driver.toString().contains("firefox")) {
	    	System.out.println("Run on firefox");
	    	Runtime.getRuntime().exec(new String[] {firefoxAuthen,username,password});
	    } else if (driver.toString().contains("chrome")) {
	    	System.out.println("Run on chrome");	
	    	Runtime.getRuntime().exec(new String[] {chromeAuthen,username,password});
	    }
	   driver.get(authenUrl);
	   sleepInSecond(3);
	
	   // By pass Authentication Alert
	    Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Basic Auth']")).isDisplayed()); 
	   
	}

	public void handleAuthenticationAlert(String link, String username , String password) {
		// link = http://the-internet.herokuapp.com/basic_auth
		
		String splitLink[] = link.split("//");
		link = splitLink[0] + "//" + username + ":" + password + "@" + splitLink[1];
		driver.get(link);
		
	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}