package stepDefinitions;

import helper.NobiObject;
import helper.NobiWeb;

public class LoginHoustonSelenium {

    public static void main(String[] args) {
        NobiWeb.openBrowser("chrome", "http://dev-houston.honestmining.org/login");
        NobiWeb.waitForElementPresent(NobiObject.elementTagByParameter("*", "placeholder", "Email"), 20);
        NobiWeb.setText( NobiObject.elementTagByParameter("*", "placeholder", "Email"),"fiky.anggra@usenobi.com");
        NobiWeb.setText( NobiObject.elementTagByParameter("*", "placeholder", "Password"),"Usenobi123#");
        NobiWeb.click(NobiObject.elementTagByParameter("button", "type", "button"));
        System.out.println(NobiWeb.verifyElementPresent(NobiObject.elementTagByParameter("div", "class", "modal-content"), 20));
        if (NobiWeb.verifyElementPresent(NobiObject.elementTagByParameter("div", "class", "modal-content"), 20) == true) {
            NobiWeb.waitForElementPresent(NobiObject.elementTagByText("div", "Yes"), 20);
            NobiWeb.click(NobiObject.elementTagByText("div", "Yes"));
        } else {
            NobiWeb.closeBrowser();
        }

    }
}
