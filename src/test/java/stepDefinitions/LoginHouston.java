package stepDefinitions;

import helper.NobiObject;
import helper.NobiWeb;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginHouston {
    @Given("User Redirect to Houston")
    public void userRedirectToHouston() {
        NobiWeb.openBrowser("chrome", "http://dev-houston.honestmining.org/login");
    }

    @When("User input email")
    public void userInputEmail() {
        NobiWeb.waitForElementPresent(NobiObject.elementTagByParameter("*", "placeholder", "Email"), 20);
        NobiWeb.setText(NobiObject.elementTagByParameter("*", "placeholder", "Email"),"fiky.anggra@usenobi.com");
    }

    @And("User Input Password")
    public void userInputPassword() {
        NobiWeb.setText( NobiObject.elementTagByParameter("*", "placeholder", "Password"),"Usenobi123#");
    }

    @And("User Click Button Login")
    public void userClickButtonLogin() {
        NobiWeb.click(NobiObject.elementTagByParameter("button", "type", "button"));
        if (NobiWeb.verifyElementPresent(NobiObject.elementTagByParameter("div", "class", "modal-content"), 20)) {
            NobiWeb.waitForElementPresent(NobiObject.elementTagByText("div", "Yes"), 20);
            NobiWeb.click(NobiObject.elementTagByText("div", "Yes"));
        }

    }

    @Then("User Redirect To Homepage")
    public void userRedirectToHomepage() {
        NobiWeb.closeBrowser();
    }
}
