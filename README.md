Repository with Selenium WebDriver basic introduction.
 
 Pre-requisities: 
 
 1. Install Java JDK
 2. Add JAVA_HOME system variable with location of Java main folder and system Path to /bin folder
 3. Download and Install Eclipse IDE ( www.eclipse.org/downloads/ ). 
 4. Download Selenium and WebDrivers ( seleniumhq.org/downloads ). 
 	!! For IEDriver add WebDriver location to system Path !!
 
 5. Create new project, add Selenium JARS in: Properties/Java Build Path/External libraries
 6. Create new Test class (with main method)
 7. In Test class set System properties for specific WebDriver, i.e.: 
	
	//System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");

 8. Create driver object for your browser, i.e. : 
	
	//WebDriver driver = new ChromeDriver();

	
	Test Drive!