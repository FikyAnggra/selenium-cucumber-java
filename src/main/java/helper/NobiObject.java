package helper;

import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class NobiObject {
    public static By elementTagByParameter(String elementTag, String elementParameter, String elementValue) {
        return By.xpath("//"+elementTag+"[@"+elementParameter+"='"+elementValue+"']");
    }

    public static By elementTagByText(String ElementTag, String ElementText) {
        return By.xpath("//"+ElementTag+"[text()='"+ElementText+"']");
    }
}
