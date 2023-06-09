package stepDefinitions;

import helper.NobiObject;
import helper.NobiWeb;

public class Facebook {
    public static void main(String[] args) {
        NobiWeb.openBrowser("chrome", "https://www.facebook.com/");
        NobiWeb.delay(10000);
        NobiWeb.waitForElementPresent(NobiObject.elementTagByParameter("h2", "class", "_8eso"),20);
        NobiWeb.setText(NobiObject.elementTagByParameter("input", "name", "email"),NobiWeb.getText(NobiObject.elementTagByParameter("h2", "class", "_8eso")));
        NobiWeb.click(NobiObject.elementTagByParameter("button", "name", "login"));
        NobiWeb.back();
    }
}
