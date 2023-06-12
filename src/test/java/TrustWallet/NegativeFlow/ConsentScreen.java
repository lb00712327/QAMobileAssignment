package TrustWallet.NegativeFlow;

import TrustWallet.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConsentScreen  extends BaseTest {

    @BeforeClass  //This can ensure all the test methods under this class can start from consent Screen.
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

        //Confirm Passcode
        walletObjects.clickSetPasscodeScreen(3);
        walletObjects.clickSetPasscodeScreen(1);
        walletObjects.clickSetPasscodeScreen(3);
        walletObjects.clickSetPasscodeScreen(2);
        walletObjects.clickSetPasscodeScreen(1);
        walletObjects.clickSetPasscodeScreen(4);

        //Deny Biometric Login
        walletObjects.denyBiometricLogin();

        //Secret phrase backup(choose backup manually)
        walletObjects.SelectManualBackup();

    }

    //Test Case 2.3 - Verify User cannot navigate to Recovery Phrase screen from Consent screen if any term is not agreed.
    @Test(priority = 2)
    public void UserCannotProcessIfDisagree() {

        System.out.println("Test Case 2.3 - Verify User cannot navigate to Recovery Phrase screen from Consent screen if any term is not agreed.");
        //1. Continue button is not clickable if not agree all the terms
        Assert.assertFalse("Continue button should not be clickable!", driver.findElementById("com.wallet.crypto.trustapp:id/next").isEnabled());
        //2. User still stays on Consent Screen
        Assert.assertTrue("Consent screen is not displayed", wait.until(ExpectedConditions.textToBe(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[1]")
                , "Back up your wallet now!")));
        //3. Agree first term displayed on the screen
        walletObjects.clickCheckBoxOfConsentItems(1);
        //4. Verify the continue button is still not clickable
        Assert.assertFalse("Continue button should not be clickable!", driver.findElementById("com.wallet.crypto.trustapp:id/next").isEnabled());
        //5. User still stays on Consent Screen
        Assert.assertTrue("Consent screen is not displayed", wait.until(ExpectedConditions.textToBe(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[1]")
                , "Back up your wallet now!")));
        //6. Agree second term displayed on the screen
        walletObjects.clickCheckBoxOfConsentItems(2);
        //7. Verify the continue button is still not clickable
        Assert.assertFalse("Continue button should not be clickable!", driver.findElementById("com.wallet.crypto.trustapp:id/next").isEnabled());
        //8. User still stays on Consent Screen
        Assert.assertTrue("Consent screen is not displayed", wait.until(ExpectedConditions.textToBe(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[1]")
                , "Back up your wallet now!")));
        //9. Agree third term displayed on the screen, but disagree the first term
        walletObjects.clickCheckBoxOfConsentItems(3);
        walletObjects.clickCheckBoxOfConsentItems(1);
        //10. Verify the continue button is still not clickable
        Assert.assertFalse("Continue button should not be clickable!", driver.findElementById("com.wallet.crypto.trustapp:id/next").isEnabled());
        //11. User still stays on Consent Screen
        Assert.assertTrue("Consent screen is not displayed", wait.until(ExpectedConditions.textToBe(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[1]")
                , "Back up your wallet now!")));
        System.out.println("Test Case 2.3 Passed");
    }

}
