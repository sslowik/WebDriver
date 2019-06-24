import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SeleniumWD {

	public static void main(String[] args) {

		Dimension dim77 = new Dimension (700,700);
		ArrayList<WebDriver> drivers = new ArrayList<WebDriver>();

//System properties for WebDrivers
	//1. Chrome	
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");

		//2. Edge
		System.setProperty("webdriver.edge.driver", "C:\\Selenium\\MicrosoftWebDriver.exe");
	//3. Firefox
		System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\geckodriver.exe");

	//4. Internet Explorer
		System.setProperty("webdriver.ie.driver", "C:\\eclipse\\IEDriverServer.exe");
		//for IE -> PATH variable to exe must be set in OS
		//       -> internet security protection level must be set to the same for all zones!!!!
		//          Computer\HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Internet Settings\Zones\ 1,2,3,4 ->  2500 key
	
	//Create driver object for Chrome
		WebDriver driver = new ChromeDriver();
//		WebDriverWait chromeWaiter = new WebDriverWait(driver, 2);   //enables to use ExpectedConditions
		
	//Create driver object for Edge		
		WebDriver driver2 = new EdgeDriver();
//		WebDriverWait edgeWaiter = new WebDriverWait(driver2, 2);   //enables to use ExpectedConditions

		//Create driver object for Firefox
		WebDriver driver3 = new FirefoxDriver();

	//Create driver object for IE		
		WebDriver driver4 = new InternetExplorerDriver();

	//Add drivers to array
		drivers.add(driver); 
		drivers.add(driver2); 
		drivers.add(driver3); 
		drivers.add(driver4); 

	//Script for drivers
		for(WebDriver d : drivers) {
		    Capabilities cap = ((RemoteWebDriver) d).getCapabilities();
		    String n = cap.getBrowserName().toLowerCase();
		    String os = cap.getPlatform().toString();
		    String v = cap.getVersion().toString();
 		    String webDriverParams = String.format("OS=%s ; Browser=%s ; Version = %s", os, n, v);
			String url = "http://yahoo.com";
 		    d.get(url);
			System.out.println(webDriverParams + " is on: -> " + d.getTitle());
//			chromeWaiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#uh-search-box")));
			
			System.out.println(d.getCurrentUrl());
			d.navigate().to("http://google.com");
			waitForMe(5);
			d.navigate().back();
			waitForMe(2);
			d.manage().window().maximize();
			String source = d.getPageSource();
			Methods.ValidatePageSource(source, url);
			waitForMe(2);			
			d.manage().window().setSize(dim77);
			waitForMe(2);			
			Point p = new Point( ((drivers.indexOf(d) + 1) * 100) , ( ( drivers.indexOf(d) + 1 ) * 100 ) );
			d.manage().window().setPosition(p);

			if(d.findElements(By.xpath("/html/body/div/div/div/form/div/button[2]")).size() != 0)
				d.findElement(By.xpath("/html/body/div/div/div/form/div/button[2]")).submit();

	        WebElement element = d.findElement(By.cssSelector("#uh-search-box"));
	        // Enter something to search for
	        element.sendKeys("Hello world of Selenium!");
	        // Now submit the form. WebDriver will find the form for us from the element
	        element.submit();
		}
		
		waitForMe(5);
		for(WebDriver d : drivers) {
		d.close();   // close active browser
		d.quit();    // child windows too
		}
	}

	private static void waitForMe(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}