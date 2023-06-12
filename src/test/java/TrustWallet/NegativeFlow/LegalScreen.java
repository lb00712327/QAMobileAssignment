package TrustWallet.NegativeFlow;

import TrustWallet.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


//Scenarios: Negative Test Cases - The user is unable to proceed with the wallet creation process if the required action is not performed on each screen.
public class LegalScreen extends BaseTest {


    @BeforeClass  //This can ensure all the test methods under this class can start from the Legal Screen.
    public void NavigateToLegalScreen() {
        walletObjects.clickNewAccountButton();
    }


    //Test Case 2.1 - Verify User cannot navigate to Set Passcode screen from Legal screen without agree the condition
    @Test(priority = 2)
    public void UserCannotProcessWithoutSelectingCheckbox() {

        System.out.println("Test Case 2.1 - Verify User cannot navigate to Set Passcode screen from Legal screen without agree the condition");
        //1. Verify the "Continue" button is not clickable without ticking the agreement
        Assert.assertFalse("\"Continue\" button is clickable without ticking the agreement!", driver.findElement(walletObjects.nextButton).isEnabled());
        //2. Try to click on "Continue" button
        walletObjects.clickNextButton();
        //3. User still stays on Legal Screen
        Assert.assertTrue("The user should remain on the legal screen without selecting the agreement checkbox!", wait.until(ExpectedConditions.textToBe(By.id("com.wallet.crypto.trustapp:id/title"), "Legal")));
        System.out.println("Test Case 2.1 Passed");

    }
}
