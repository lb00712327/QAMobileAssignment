package TrustWallet.UtilTool;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Objects are used often are listed here, so the code can be clean and easier maintained.
public class WalletObjects {
    WebDriver driver;
    By newAccountButton = By.id("com.wallet.crypto.trustapp:id/new_account_action");
    By acceptCheckBox = By.id("com.wallet.crypto.trustapp:id/acceptCheckBox");
    By nextButton = By.id("com.wallet.crypto.trustapp:id/next");
    String setPasscodeScreen = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView";
    By denyBiometric = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.TextView");
    By ManualBackup = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView");
    String ConsentItems = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout";
    String recoveryPhraseBaseXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.view.ViewGroup/android.widget.LinearLayout";
    By actionVerify = By.id("com.wallet.crypto.trustapp:id/action_verify");
    By actionDone = By.id("com.wallet.crypto.trustapp:id/action_done");

    public WalletObjects(WebDriver driver) {
        this.driver = driver;
    }

    public void clickNewAccountButton() {
        driver.findElement(newAccountButton).click();
    }

    public void checkAcceptCheckBox() {
        driver.findElement(acceptCheckBox).click();
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void clickSetPasscodeScreen(int index) {
        driver.findElement(By.xpath(setPasscodeScreen + "[" + index + "]")).click();
    }

    public void denyBiometricLogin() {
        driver.findElement(denyBiometric).click();
    }

    public void SelectManualBackup() {
        driver.findElement(ManualBackup).click();
    }

    public void clickCheckBoxOfConsentItems(int index) {
        driver.findElement(By.xpath(ConsentItems + "[" + index + "]/android.view.ViewGroup/android.widget.CheckBox")).click();
    }

    public String[] getRecoveryPhrases(int numberOfPhrases) {
        String[] recoveryPhrases = new String[numberOfPhrases];
        for (int i = 1; i <= numberOfPhrases; i++) {
            String phraseXPath = recoveryPhraseBaseXPath.toString() + "[" + i + "]/android.widget.TextView[2]";
            recoveryPhrases[i - 1] = driver.findElement(By.xpath(phraseXPath)).getText();
        }
        return recoveryPhrases;
    }
    public void clickActionVerify() {
        driver.findElement(actionVerify).click();
    }
    public void clickActionDone() {
        driver.findElement(actionDone).click();
    }

}