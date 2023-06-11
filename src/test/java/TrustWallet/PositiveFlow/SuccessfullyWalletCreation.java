package TrustWallet.PositiveFlow;

import TrustWallet.BaseTest;
import io.appium.java_client.MobileBy;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.Arrays;

//Scenarios: Positive Flow -  User create a new wallet successfully.
public class SuccessfullyWalletCreation extends BaseTest {

    String[] recoveryWords;

    //Test Case 1.1 - Verify User can navigate to Legal screen from Welcome screen successfully
    @Test(priority = 1)
    public void WelcomeScreenToLegalScreen() {

        System.out.println("Test Case 1.1 - Verify User can navigate to Legal screen from Welcome screen");
        //1. Launch the TrustWallet app. Done by init function in BaseTest.Java
        //2. Verify that the Welcome screen is displayed. Assuming below 2 buttons are always and only displayed on Welcome page
        Assert.assertTrue("CREATE A NEW WALLET button is not shown", driver.findElementById("com.wallet.crypto.trustapp:id/new_account_action").isDisplayed());
        Assert.assertTrue("I already have a wallet button is not shown", driver.findElementById("com.wallet.crypto.trustapp:id/import_account_action").isDisplayed());
        //3. Click on "CREATE A NEW WALLET" button
        Assert.assertTrue("CREATE A NEW WALLET button is not shown", driver.findElementById("com.wallet.crypto.trustapp:id/new_account_action").isEnabled());
        walletObjects.clickNewAccountButton();
        //4. Verify that the Legal screen is displayed.
        Assert.assertTrue("Legal screen is not displayed", wait.until(ExpectedConditions.textToBe(By.id("com.wallet.crypto.trustapp:id/title"), "Legal")));
        System.out.println("Test Case 1.1 Passed");

    }

    //Test Case 1.2 - Verify User can navigate to Set Passcode screen from Legal screen successfully.
    @Test(priority = 1, dependsOnMethods = "WelcomeScreenToLegalScreen")
    public void LegalScreenToSetPasscodeScreen() {

        System.out.println("Test Case 1.2 - Verify User can navigate to Set Passcode screen from Legal screen successfully.");
        //1. Verify "Privacy Policy" and "Terms of Service" buttons are displayed
        Assert.assertTrue("CREATE A NEW WALLET button is not shown", driver.findElementById("com.wallet.crypto.trustapp:id/privacy").isDisplayed());
        Assert.assertTrue("I already have a wallet button is not shown", driver.findElementById("com.wallet.crypto.trustapp:id/terms").isDisplayed());
        //2. Verify that user can agree "I've read and accept the Terms of Service and Privacy Policy" condition
        walletObjects.checkAcceptCheckBox();
        //3. Click on "Continue" button
        walletObjects.clickNextButton();
        //4. Verify that the Set Passcode screen is displayed.
        Assert.assertTrue("Set Passcode screen is not displayed", wait.until(ExpectedConditions.textToBe(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.TextView[1]")
                , "Create Passcode")));
        System.out.println("Test Case 1.2 Passed");

    }

    //Test Case 1.3 - Verify User can navigate to Confirm Passcode screen from Set Passcode screen successfully.
    @Test(priority = 1, dependsOnMethods = "LegalScreenToSetPasscodeScreen")
    public void SetPasscodeScreenToConfirmPasscodeScreen() {

        System.out.println("Test Case 1.3 - Verify User can navigate to Confirm Passcode screen from Set Passcode screen successfully.");
        //1. Enter 6 digits passcode with test data "313214"
        walletObjects.clickSetPasscodeScreen(3);
        walletObjects.clickSetPasscodeScreen(1);
        walletObjects.clickSetPasscodeScreen(3);
        walletObjects.clickSetPasscodeScreen(2);
        walletObjects.clickSetPasscodeScreen(1);
        walletObjects.clickSetPasscodeScreen(4);
        //2. Verify that the Confirm Passcode screen is displayed.
        Assert.assertTrue("Confirm Passcode screen is not displayed", wait.until(ExpectedConditions.textToBe(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.TextView[1]")
                , "Confirm Passcode")));
        System.out.println("Test Case 1.3 Passed");

    }

    //Test Case 1.4 - Verify User can navigate to Secret Phrase Backup screen from Confirm Passcode screen successfully.
    @Test(priority = 1, dependsOnMethods = "SetPasscodeScreenToConfirmPasscodeScreen")
    public void ConfirmPasscodeScreenToSecretPhraseBackupScreen(){

        System.out.println("Test Case 1.4 - Verify User can navigate to Secret Phrase Backup screen from Confirm Passcode screen successfully.");
        //1. Enter 6 digits passcode with test data "313214"
        walletObjects.clickSetPasscodeScreen(3);
        walletObjects.clickSetPasscodeScreen(1);
        walletObjects.clickSetPasscodeScreen(3);
        walletObjects.clickSetPasscodeScreen(2);
        walletObjects.clickSetPasscodeScreen(1);
        walletObjects.clickSetPasscodeScreen(4);
        //2. Verify that a popup window of Biometric Login Setting pops up
        Assert.assertTrue("Biometric Login Setting is not displayed", wait.until(ExpectedConditions.textToBe(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.TextView[1]")
                , "Biometric Login")));
        //3. Select "Deny"
        walletObjects.denyBiometricLogin();
        //4. Verify that the Secret Phrase Backup screen is displayed.
        Assert.assertTrue("Secret Phrase Backup screen is not displayed", wait.until(ExpectedConditions.textToBe(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.TextView[1]")
                , "Secret phrase backup")));
        System.out.println("Test Case 1.4 Passed");

    }

    //Test Case 1.5 - Verify User can navigate to Consent screen from Secret Phrase Backup screen successfully.
    @Test(priority = 1, dependsOnMethods = "ConfirmPasscodeScreenToSecretPhraseBackupScreen")
    public void SecretPhraseBackupScreenToConsentScreen() {

        System.out.println("Test Case 1.5 - Verify User can navigate to Consent screen from Secret Phrase Backup screen successfully.");
        //1. Select "Back up manually"
        walletObjects.SelectManualBackup();
        //2. Verify Consent screen is displayed
        Assert.assertTrue("Consent screen is not displayed", wait.until(ExpectedConditions.textToBe(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[1]")
                , "Back up your wallet now!")));
        System.out.println("Test Case 1.5 Passed");

    }

    //Test Case 1.6 - Verify User can navigate to Recovery Phrase screen from Consent screen successfully.
    @Test(priority = 1, dependsOnMethods = "SecretPhraseBackupScreenToConsentScreen")
    public void ConsentScreenToRecoveryPhraseScreen() {

        System.out.println("Test Case 1.6 - Verify User can navigate to Recovery Phrase screen from Consent screen successfully.");
        //1. Agree first term displayed on the page
        walletObjects.clickCheckBoxOfConsentItems(1);
        //2. Agree second term displayed on the page
        walletObjects.clickCheckBoxOfConsentItems(2);
        //3. Agree third term displayed on the page
        walletObjects.clickCheckBoxOfConsentItems(3);
        //4. Click on "Continue" button
        walletObjects.clickNextButton();
        //5. Verify Recovery Phrase screen is displayed
        Assert.assertTrue("Recovery Phrase Screen is not displayed", wait.until(ExpectedConditions.textToBe(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[1]")
                , "Your Secret Phrase")));
        System.out.println("Test Case 1.6 Passed");

    }

    //Test Case 1.7 - Verify User can navigate to Confirm Recovery Phrase screen from Recovery Phrase screen successfully.
    @Test(priority = 1, dependsOnMethods = "ConsentScreenToRecoveryPhraseScreen")
    public void RecoveryPhraseScreenToConfirmRecoveryPhraseScreen() {

        System.out.println("Test Case 1.7 - Verify User can navigate to Confirm Recovery Phrase screen from Recovery Phrase screen successfully.");
        //1. Click "Copy" button
        driver.findElementById("com.wallet.crypto.trustapp:id/action_copy").click();
        //2. Verify the recovery phase are saved in clipboard
        int numberOfWords = 12;
        recoveryWords = walletObjects.getRecoveryPhrases(numberOfWords);  //Save words of Recovery phrase from App source into an array in order
        String[] clipboardText = driver.getClipboardText().split("\\s+");  //Get words from clipboard
        boolean isSame = Arrays.equals(recoveryWords, clipboardText);
            if (isSame) {
                System.out.println("Clipboard text is exactly the same as the recovery words displayed on the page.");
            } else {
                System.out.println("Clipboard text is not the same as the recovery words displayed on the page.\"");
            }
        //3. Click on "Continue" button
        walletObjects.clickActionVerify();
        //4. Verify Confirm Recovery Phrase screen is displayed
        Assert.assertTrue("Confirm Recovery Phrase Screen is not displayed", wait.until(ExpectedConditions.textToBe(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[1]")
                , "Verify Secret Phrase")));
        System.out.println("Test Case 1.7 Passed");

    }

    //Test Case 1.8 - Verify User can complete the wallet creation flow.
    @Test(priority = 1, dependsOnMethods = "RecoveryPhraseScreenToConfirmRecoveryPhraseScreen")
    public void WalletCreationComplete() {

        System.out.println("Test Case 1.8 - Verify User can complete the wallet creation flow.");
        //1. Click on the recovery words in the correct order
        for (int i = 0; i < recoveryWords.length; i++) {
            //find element by using texts from recoveryPhrase array
            By byText = MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + recoveryWords[i] + "\")");
            //Click the text once found
            driver.findElement(byText).click();
        }
        //2. Click on "Done" button
        walletObjects.clickActionDone();
        //3. Verify Wallet is now created
        Assert.assertTrue("Wallet is not created successfully", wait.until(ExpectedConditions.textToBe(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.TextView[1]")
                , "Let's explore your \n" +
                        "new wallet!")));
        System.out.println("Test Case 1.8 Passed");

    }

}
