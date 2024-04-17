package Assignments;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class StockValidationAssignment {
	public static void main(String[] args) throws IOException {
        // Read data from XLS file and store in HashMap1
        Map<String, Double> hashMap1 = readFromXLS("stock_prices.xlsx");

        // Read data from website using Selenium WebDriver and store in HashMap2
        WebDriver driver = new ChromeDriver();
        driver.get("https://money.rediff.com/losers/bse/daily/groupall");

        Map<String, Double> hashMap2 = new HashMap<>();
        WebElement table = driver.findElement(By.xpath("//table[@class='dataTable']"));
        for (WebElement row : table.findElements(By.tagName("tr"))) {
            String stockName = row.findElement(By.xpath(".//td[1]")).getText();
            String priceStr = row.findElement(By.xpath(".//td[2]")).getText();
            double price = Double.parseDouble(priceStr.replaceAll("[^0-9.]", ""));
            hashMap2.put(stockName, price);
        }
        driver.quit();

        // Compare values stored in the two HashMaps
        for (String key : hashMap1.keySet()) {
            if (!hashMap2.containsKey(key)) {
                System.out.println("Stock not found on website: " + key);
            } else {
                double price1 = hashMap1.get(key);
                double price2 = hashMap2.get(key);
                if (price1 != price2) {
                    System.out.println("Price mismatch for " + key + ": Expected " + price1 + ", Actual " + price2);
                }
            }
        }
    }

    private static Map<String, Double> readFromXLS(String filePath) throws IOException {
        Map<String, Double> map = new HashMap<>();
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row>  iterator=  sheet.iterator();
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            String stockName = currentRow.getCell(0).getStringCellValue();
            double price = currentRow.getCell(1).getNumericCellValue();
            map.put(stockName, price);
        }

        workbook.close();
        fis.close();
        return map;
    }


}
