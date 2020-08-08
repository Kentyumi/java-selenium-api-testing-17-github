package practise;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction_Part2 {
	WebDriver driver;
	Actions action;
	WebElement element;
	JavascriptExecutor jsExecutor;
	String rootFolder = System.getProperty("user.dir");
	String javascriptPath = rootFolder + "\\drapAndDrop\\drag_and_drop_helper.js";
	String jqueryPath = rootFolder + "\\drapAndDrop\\jquery_load_helper.js";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_Right_Click() {

		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		// Right click to button
		element = driver.findElement(By.xpath("//span[text()='right click me']"));

		action.contextClick(element).perform();

		// Hover to quit
		element = driver.findElement(By.cssSelector(".context-menu-icon-quit"));
		action.moveToElement(element).perform();

		// Verify Quit has: hover and visible status
		String quitClassAtribute = element.getAttribute("class");
		System.out.println(quitClassAtribute);

		Assert.assertTrue(quitClassAtribute.contains("context-menu-visible"));
		Assert.assertTrue(quitClassAtribute.contains("context-menu-hover"));

		// Verify = is Display
		Assert.assertTrue(
				driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-visible.context-menu-hover"))
						.isDisplayed());
	}

	//@Test
	public void TC_02_Drap_drop_HTML4() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");

		WebElement source = driver.findElement(By.cssSelector("#draggable"));
		WebElement target = driver.findElement(By.cssSelector("#droptarget"));

		action.dragAndDrop(source, target).perform();
		sleepInSecond(3);

		// Cach 2 _ Click and Hold
		// action.clickAndHold(source).moveToElement(target).release().build().perform();

		Assert.assertEquals(target.getText(), "You did great!");
	}

	@Test
	public void TC_03_Drap_drop_HTML5() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		String sourceCss = "#column-a";
		String targetCss = "#column-b";

		String java_script = readFile(javascriptPath);
		
		// Inject jquery lib to site
		//String jqueryscript = readFile(jqueryPath);	
		//jsExecutor.executeScript(jqueryscript);

		// A to B
		java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		jsExecutor.executeScript(java_script);
		sleepInSecond(5);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));
		
		// B to A
		java_script = "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		jsExecutor.executeScript(java_script);
		sleepInSecond(5);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='A']"));
	}

	@Test
	public void TC_04_DragDrop_HTML5_Offset() throws InterruptedException, IOException, AWTException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
	
		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		sleepInSecond(3);
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		sleepInSecond(3);
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		sleepInSecond(3);
	}

	public boolean isElementDisplayed(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isDisplayed()) {
			return true;
		}
		return false;
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
	
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 85 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 85 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
}