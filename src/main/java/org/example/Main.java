package org.example;

//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.io.PrintStream;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\s990\\Downloads\\chromedriver.exe");

//        WebDriver driver = new ChromeDriver();
//        var url = "https://www.detmir.ru/catalog/index/name/podguzniki/brand/18384/";
//        try {
//            driver.get(url);
//            Thread.sleep(2000);
//            //WebElement productContainer = driver.findElement(By.id("product-3083457"));
//            WebElement productContainer = driver.findElement(By.className("rX"));
//
//            //List<WebElement> productElements = productContainer.findElements(By.cssSelector("section[data-product-id]"));
//            byte[] bytes = productContainer.getText().getBytes(StandardCharsets.UTF_8);
//
//            System.setOut(new PrintStream(System.out, true, "UTF-8"));
//
//            System.out.println(productContainer.getText());
            //System.out.println(new String(bytes, StandardCharsets.US_ASCII));
//            for (WebElement product : productElements) {
//                WebElement nameElement = product.findElement(By.id("product-3083457"));
//                String name = nameElement.getText();
//                System.out.println("Название: " + name);
//            }

//    } catch (Exception e) {
//        e.printStackTrace();
//    } finally {
//        // Закрываем браузер
//        driver.quit();
//    }

        try {
            // URL для запроса
            String url = "https://api.detmir.ru/v2/products?filter=categories[].alias:diapers;brand:YokoSun;&fields=name,price&limit=20";

            // Создание и настройка HTTP-соединения
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            // Чтение ответа
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();

            // Создание ObjectMapper для работы с JSON
            ObjectMapper objectMapper = new ObjectMapper();

            // Парсинг JSON в JsonNode
            JsonNode jsonArray = objectMapper.readTree(content.toString());

            // Вывод данных
            int index = 1;
            for (JsonNode product : jsonArray) {
                String id = product.get("id").asText();
                String title = product.get("title").asText();
                int price = product.get("price").get("price").asInt();

                System.setOut(new PrintStream(System.out, true, "UTF-8"));
                System.out.println(index + " || " + title + " || " + price);
                index++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}