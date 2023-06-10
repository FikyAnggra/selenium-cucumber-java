package stepDefinitions;

import helper.NobiObject;
import helper.NobiWeb;

public class Facebook {
    public static void main(String[] args) {
        NobiWeb.TestDataDriven("Login", "Sheet1", (header) -> {
            NobiWeb.openBrowser("chrome", "https://www.facebook.com/");
            NobiWeb.waitForElementPresent(NobiObject.elementTagByParameter("h2", "class", "_8eso"),20);
            NobiWeb.setText(NobiObject.elementTagByParameter("input", "name", "email"), header[0].toString());
            NobiWeb.setText(NobiObject.elementTagByParameter("input", "name", "pass"), header[1].toString());
            NobiWeb.click(NobiObject.elementTagByParameter("button", "name", "login"));
        });
    }
}
