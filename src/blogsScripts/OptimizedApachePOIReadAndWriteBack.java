package blogsScripts;

import org.testng.annotations.Test;

import blogsScripts.ApachePOIMethods.resultEnum;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public class OptimizedApachePOIReadAndWriteBack {
	WebDriver driver = null;
	File scenarioFile = new File("D:\\bckup\\Documents\\Documents\\Selenium\\Apache POI\\TestScenarios.xlsx");
	int XLSXresultCell = 3;	// target cell in scenarioFile where PASS/FAIL should be written

	@Test(dataProvider = "dp")
	public void f(String testCaseID, String userName, String password, String result) {
		driver.findElement(By.xpath(".//*[@name = 'userName']")).sendKeys(userName);
		driver.findElement(By.xpath(".//*[@name = 'password']")).sendKeys(password);
		driver.findElement(By.xpath(".//*[@name='login']")).click();

		if (driver.getTitle().equals("Find a Flight: Mercury Tours:")) {
			ApachePOIMethods.writeTestResultToXLSX(scenarioFile, testCaseID, XLSXresultCell, resultEnum.PASS);
		} else {
			ApachePOIMethods.writeTestResultToXLSX(scenarioFile, testCaseID, XLSXresultCell, resultEnum.FAIL);
		}
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://newtours.demoaut.com");
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	@DataProvider
	public Object[][] dp() {
		return ApachePOIMethods.readFromXLSX(scenarioFile);
	}
}
