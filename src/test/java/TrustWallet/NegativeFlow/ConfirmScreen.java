package TrustWallet.NegativeFlow;

import TrustWallet.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


//Scenarios: Negative Test Cases - The user is unable to proceed with the wallet creation process if the required action is not performed on each screen.
public class ConfirmScreen extends BaseTest {

    @BeforeClass  //This can ensure all the test methods under this class can start from the Confirm Passcode Screen.
    public void NavigateToConfirmScreen() {
        // Welcome screen
        walletObjects.clickNewAccountButton();
        // Legal screen
        walletObjects.checkAcceptCheckBox();
        walletObjects.clickNextButton();

        // Set Passcode screen
        walletObjects.clickSetPasscodeScreen(3);
        walletObjects.clickSetPasscodeScreen(1);
        walletObjects.clickSetPasscodeScreen(3);
        walletObjects.clickSetPasscodeScreen(2);
        walletObjects.clickSetPasscodeScreen(1);
        walletObjects.clickSetPasscodeScreen(4);
    }

    //Test Case 2.2 - Verify User cannot navigate to Secret Phrase Backup screen from Confirm Passcode screen if passcode is different.
    @Test(priority = 2)
    public void UserCannotProcessIfPasscodeMismatch() {

        System.out.println("Test Case 2.2 - Verify User cannot navigate to Secret Phrase Backup screen from Confirm Passcode screen if passcode is different.");
        //1. Enter 6 digits passcode with test data "482919"
        walletObjects.clickSetPasscodeScreen(4);
        walletObjects.clickSetPasscodeScreen(8);
        walletObjects.clickSetPasscodeScreen(2);
        walletObjects.clickSetPasscodeScreen(9);
        walletObjects.clickSetPasscodeScreen(1);
        walletObjects.clickSetPasscodeScreen(9);

        //2. Verify a warning message shows "Those passwords didn't match" on the screen
        Assert.assertTrue("Warning message is not displayed", wait.until(ExpectedConditions.textToBe(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.TextView[2]")
                , "Those passwords didn’t match!")));

        //3. Verify that the Set Passcode screen is displayed again
        Assert.assertTrue("Set Passcode screen is not displayed", wait.until(ExpectedConditions.textToBe(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.TextView[1]")
                , "Create Passcode")));
        System.out.println("Test Case 2.2 Passed");
    }

}
