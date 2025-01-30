package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\s990\\Downloads\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        var url = "https://www.detmir.ru/catalog/index/name/podguzniki/brand/18384/";
        try {
            driver.get(url);
            Thread.sleep(2000);
            //WebElement productContainer = driver.findElement(By.id("product-3083457"));
            WebElement productContainer = driver.findElement(By.className("rX"));

            //List<WebElement> productElements = productContainer.findElements(By.cssSelector("section[data-product-id]"));
            byte[] bytes = productContainer.getText().getBytes(StandardCharsets.UTF_8);

            System.setOut(new PrintStream(System.out, true, "UTF-8"));

            System.out.println(productContainer.getText());
            //System.out.println(new String(bytes, StandardCharsets.US_ASCII));
//            for (WebElement product : productElements) {
//                WebElement nameElement = product.findElement(By.id("product-3083457"));
//                String name = nameElement.getText();
//                System.out.println("Название: " + name);
//            }

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Закрываем браузер
        driver.quit();
    }

    }
}