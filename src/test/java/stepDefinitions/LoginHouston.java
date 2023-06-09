package stepDefinitions;

import helper.NobiWeb;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginHouston {
    @Given("User Redirect to Houston")
    public void userRedirectToHouston() {
        NobiWeb.OpenBrowser("chrome", "http://dev-houston.honestmining.org/login");
    }

    @When("User input email")
    public void userInputEmail() {
        NobiWeb.WaitForElementPresent(NobiWeb.ElementTagByParameter("*", "placeholder", "Email"), 20);
        NobiWeb.SetText("fiky.anggra@usenobi.com", NobiWeb.ElementTagByParameter("*", "placeholder", "Email"));
    }

    @And("User Input Password")
    public void userInputPassword() {
        NobiWeb.SetText("Usenobi123#", NobiWeb.ElementTagByParameter("*", "placeholder", "Password"));
    }

    @And("User Click Button Login")
    public void userClickButtonLogin() {
        System.out.println(NobiWeb.VerifyElementPresent(NobiWeb.ElementTagByParameter("div", "class", "modal-content"), 20));
        NobiWeb.Click(NobiWeb.ElementTagByParameter("button", "type", "button"));
        if (NobiWeb.VerifyElementPresent(NobiWeb.ElementTagByParameter("div", "class", "modal-content"), 20)) {
            NobiWeb.WaitForElementPresent(NobiWeb.ElementTagByText("div", "Yes"), 20);
            NobiWeb.Click(NobiWeb.ElementTagByText("div", "Yes"));
        }

    }

    @Then("User Redirect To Homepage")
    public void userRedirectToHomepage() {
        NobiWeb.CloseBrowser();
    }
}
