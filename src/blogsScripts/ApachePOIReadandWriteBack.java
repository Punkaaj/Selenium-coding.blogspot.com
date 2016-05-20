//Read scenarios from excel sheet and run the test accordingly and update the excel sheet with results pass/fail.
//http://poi.apache.org/apidocs/org/apache/poi/ss/usermodel/Row.html#getLastCellNum%28%29

package blogsScripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class ApachePOIReadandWriteBack {
	WebDriver driver = null;
	File scenarioFile = new File("D:\\bckup\\Documents\\Documents\\Selenium\\Apache POI\\TestScenarios.xlsx");

	@Test(dataProvider = "dp")
	public void f(String testCaseID, String userName, String password, String result) {
		driver.findElement(By.xpath(".//*[@name = 'userName']")).sendKeys(userName);
		driver.findElement(By.xpath(".//*[@name = 'password']")).sendKeys(password);
		driver.findElement(By.xpath(".//*[@name='login']")).click();

		try {
			FileInputStream fis = new FileInputStream(scenarioFile);
			XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rowNum = Integer.parseInt(testCaseID);
			Row row = sheet.getRow(rowNum);
			if (driver.getTitle().equals("Find a Flight: Mercury Tours:")) {
				row.createCell(3).setCellValue("PASS");
			} else {
				row.createCell(3).setCellValue("FAIL");
			}
			fis.close();
			FileOutputStream fos = new FileOutputStream(scenarioFile);
			workbook.write(fos);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
		Object[][] o = null;
		try {
			FileInputStream fis = new FileInputStream(scenarioFile);
			XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			o = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() == 0) {
					continue;
				}
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					o[(row.getRowNum() - 1)][cell.getColumnIndex()] = cell.getStringCellValue();
				}

			}

			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return o;
	}
}
