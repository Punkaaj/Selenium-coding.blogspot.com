package blogsScripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;

public class IEDriverTest {
	WebDriver driver = null;
	String userName = "mercury";
	String password = "mercury";

	@Test
	public void f() {
		driver.findElement(By.xpath(".//*[@name = 'userName']")).sendKeys(userName);
		driver.findElement(By.xpath(".//*[@name = 'password']")).sendKeys(password);
		driver.findElement(By.xpath(".//*[@name='login']")).click();
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.ie.driver",
				"D:\\bckup\\Documents\\Documents\\Selenium\\InternetExplorerDriver\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		driver = new InternetExplorerDriver(capabilities);
		driver.get("http://newtours.demoaut.com/");
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.quit();
	}

}
