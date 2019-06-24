//import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Must have:
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Intro {

	public static void main(String[] args) {
		Dimension dim77 = new Dimension (700,700);
		Point p = new Point(300,300);
//		ArrayList<WebDriver> drivers = new ArrayList<WebDriver>();

//1. System properties for WebDrivers
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");

//2. Create driver object for Chrome
		WebDriver driver = new ChromeDriver();
		WebDriverWait chromeWaiter = new WebDriverWait(driver, 2);   //enables to use ExpectedConditions

//3. Test script
	    Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
	    String n = cap.getBrowserName().toLowerCase();
	    String os = cap.getPlatform().toString();
	    String v = cap.getVersion().toString();
	    String webDriverParams = String.format("OS=%s ; Browser=%s ; Version = %s", os, n, v);

	    String url = "http://yahoo.com";
	    
		driver.get(url);
		System.out.println(webDriverParams + " is on: -> " + driver.getTitle());
		
		System.out.println(driver.getCurrentUrl());
		driver.navigate().to("http://google.com");
//		String source = driver.getPageSource();

		waitForMe(5);
		driver.navigate().back();

		driver.manage().window().maximize();

		waitForMe(2);			
		driver.manage().window().setSize(dim77);

		waitForMe(2);			
		driver.manage().window().setPosition(p);

		if(driver.findElements(By.xpath("/html/body/div/div/div/form/div/button[2]")).size() != 0)
			driver.findElement(By.xpath("/html/body/div/div/div/form/div/button[2]")).submit();

		chromeWaiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/form/div/button[2]")));

        WebElement element = driver.findElement(By.cssSelector("#uh-search-box"));
        element.sendKeys("Hello world!");
        element.submit();

        waitForMe(5);
		driver.close();   // close active browser
		driver.quit();    // close child windows too
		}

	private static void waitForMe(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}