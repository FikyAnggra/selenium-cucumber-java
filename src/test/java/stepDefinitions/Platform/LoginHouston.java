package stepDefinitions.Platform;

import helper.NobiObject;
import helper.NobiWeb;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class LoginHouston {
    @Given("User Open Browser And Go To Houston")
    public void userOpenBrowserAndGoToHouston() {
        NobiWeb.openBrowser("chrome", "http://dev-houston.honestmining.org/login");
    }

    @When("User Input {string} And {string}")
    public void userInputAnd(String Username, String Password) {
        NobiWeb.waitForElementPresent(NobiObject.elementTagByParameter("input","placeholder", "Email"),20);
        NobiWeb.setText(NobiObject.elementTagByParameter("input", "placeholder", "Email"),Username);
        NobiWeb.setText(NobiObject.elementTagByParameter("input", "placeholder", "Password"),Password);
    }

    @And("User Click Button Login")
    public void userClickButtonLogin() {
        NobiWeb.click(NobiObject.elementTagByText("div", "Submit"));
    }

    @Then("Verify {string}")
    public void verifyExpectedResult(String ExpectedResult) {
        NobiWeb.verifyElementPresent(NobiObject.elementTagByText("div", ExpectedResult),20);
        NobiWeb.closeBrowser();
    }


}
