package TrustWallet.NegativeFlow;

import TrustWallet.BaseTest;
import io.appium.java_client.MobileBy;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Random;

public class ConfirmRecoveryPhraseScreen extends BaseTest {

    String[] recoveryWords;

    @BeforeClass
    //This can ensure all the test methods under this class can start from the Confirm Passcode Screen.
    public void NavigateToRecoveryPhraseScreen() {
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

        //Consent screen
        walletObjects.clickCheckBoxOfConsentItems(1);
        walletObjects.clickCheckBoxOfConsentItems(2);
        walletObjects.clickCheckBoxOfConsentItems(3);
        walletObjects.clickNextButton();

        //Recovery phrase
        //Save texts of Recovery phrase into an array in order
        int numberOfPhrases = 12;
        recoveryWords = walletObjects.getRecoveryPhrases(numberOfPhrases);
        walletObjects.clickActionVerify();

    }

    //Test Case 2.4 - Verify User cannot complete the wallet creation flow if click on the recovery word in the wrong order.
    @Test(priority = 2)
    public void UserCannotProcessIfClickRecoveryWordsInWrongOrder() {

        System.out.println("Test Case 2.4 - Verify User cannot complete the wallet creation flow if click on the recovery word in the wrong order.");
        //1. Click on the recovery words in the wrong order
        //Reorder the recorded recovery words and ensure it won't be the same as the previous ones by using random method
        String[] shuffleRecoveryWords = Arrays.copyOf(recoveryWords, recoveryWords.length);

        Random rand = new Random();
        for (int i = shuffleRecoveryWords.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            String temp = shuffleRecoveryWords[i];
            shuffleRecoveryWords[i] = shuffleRecoveryWords[j];
            shuffleRecoveryWords[j] = temp;
        }

        for (int i = 0; i < shuffleRecoveryWords.length; i++) {
            //find element by using texts from recoveryPhrase array
            By byText = MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + shuffleRecoveryWords[i] + "\")");
            //Click the text once found
            driver.findElement(byText).click();
        }

        //2. A warning message "Invalid order. Try again" is displayed
        Assert.assertTrue("Warning Message is not displayed!", wait.until(ExpectedConditions.textToBe(By.id("com.wallet.crypto.trustapp:id/message"), "Invalid order. Try again!")));
        //3. "Done" button is not clickable
        Assert.assertFalse("Done button should not be clickable!", driver.findElementById("com.wallet.crypto.trustapp:id/action_done").isEnabled());
        System.out.println("Test Case 2.4 Passed");

    }

}
