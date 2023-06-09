package helper;

import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class NobiObject {
    public static By elementTagByParameter(String elementTag, String elementParameter, String elementValue) {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/main/java/ObjectRepository/ElementTagByParameter.properties");
            properties.load(fileInputStream);
            fileInputStream.close();

            String xpath = properties.getProperty("xpath");
            String formattedXpath = xpath.replace("${elementTag}", elementTag)
                    .replace("${elementParameter}", elementParameter)
                    .replace("${elementValue}", elementValue);

            return By.xpath(formattedXpath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static By elementTagByText(String elementTag, String elementText) {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/main/java/ObjectRepository/ElementTagByText.properties");
            properties.load(fileInputStream);
            fileInputStream.close();

            String xpath = properties.getProperty("xpath");
            String formattedXpath = xpath.replace("${elementTag}", elementTag)
                    .replace("${elementText}", elementText);

            return By.xpath(formattedXpath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
