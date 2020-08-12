package practise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_UserInteraction_Part1 {
	WebDriver driver;
	Actions action;
	WebElement element;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		action = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Hover_Mouse() {
		driver.get("https://www.myntra.com/");
		WebElement element = driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']"));

		// Hover to KID Menu
		action.moveToElement(element).perform();
		sleepInSecond(2);

		driver.findElement(By.xpath("//ul[@class='desktop-navBlock']//a[text()='Home & Bath']")).click();

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']"))
				.isDisplayed());
	}

	// @Test
	public void TC_02_Click_and_Hold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		String[] selectedTextExpected = { "1", "2", "3", "4", "5", "6", "7", "8" };

		List<WebElement> allItems = driver.findElements(By.cssSelector("#selectable>li"));

		// Click then select 1 to 8 ( click 1, hold and move to 8 then release mouse)
		action.clickAndHold(allItems.get(0)).moveToElement(allItems.get(7)).release().perform();

		// Verify
		List<WebElement> allItemsSelected = driver.findElements(By.cssSelector(".ui-selected"));

		// Verify size = 8
		Assert.assertEquals(allItemsSelected.size(), 8);

		// Create ArrayList to save allItemsSelectedText
		ArrayList<String> allItemsSelectedText = new ArrayList<String>();

		// Print 1-8 ?
		for (WebElement webElement : allItemsSelected) {
			allItemsSelectedText.add(webElement.getText());
		}

		Object[] selectedTextActual = (Object[]) allItemsSelectedText.toArray();
		Assert.assertEquals(selectedTextExpected, selectedTextActual);
	}

	// @Test
	public void TC_03_Click_and_Hold_Random() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");

		List<WebElement> allItems = driver.findElements(By.cssSelector("#selectable>li"));

		// Hold control button
		action.keyDown(Keys.CONTROL).perform();

		// Click 1/4/7/12
		action.click(allItems.get(0)).click(allItems.get(3)).click(allItems.get(6)).click(allItems.get(11)).perform();

		// Release Control button
		action.keyUp(Keys.CONTROL).perform();

		// Verify 1/4/7/12
		List<WebElement> allItemsSelected = driver.findElements(By.cssSelector(".ui-selected"));

		Assert.assertEquals(allItemsSelected.size(), 4);
	}

	@Test
	public void TC_04_Double_click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		element = driver.findElement(By.xpath("//button[text()='Double click me']"));

		action.doubleClick(element).perform();
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).isDisplayed());

	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}