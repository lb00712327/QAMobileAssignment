package TrustWallet.EndToEndTest;

import TrustWallet.BaseTest;
import TrustWallet.UtilTool.Retry;
import TrustWallet.UtilTool.WalletObjects;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class EndToEndTestOfWalletCreation extends BaseTest {

    //This test is used to ensure user can go through whole process without trouble, thus priority 1.
    @Test(priority = 1, retryAnalyzer = Retry.class)
    public void EndToEndTest() {

        WalletObjects walletObjects = new WalletObjects(driver);

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

        //Confirm Passcode screen
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
        String[] recoveryPhrase = walletObjects.getRecoveryPhrases(numberOfPhrases);

        walletObjects.clickActionVerify();

        //Confirm recovery screen
        //Click texts of Recovery phrase from the array create in previous step in correct order
        for (int i = 0; i < recoveryPhrase.length; i++) {
            //find element by using texts from recoveryPhrase array
            By byText = MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + recoveryPhrase[i] + "\")");
            //Click the text once found
            driver.findElement(byText).click();
        }

        walletObjects.clickActionDone();

    }

}
