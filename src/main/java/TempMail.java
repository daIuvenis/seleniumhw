
import lombok.Getter;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class TempMail {

    public WebDriver driver;

    public TempMail() {
        PageFactory.initElements(singleton.getDriver(), this);

    }

    @FindBy(css = "#pre_rand")
    private WebElement randomButton;

    public void clickToButtonRandom() {
        randomButton.click();
    }

    @FindBy(css = "#domain")
    private WebElement dropDownMenu;

    public void openDropDownMenu() {
        dropDownMenu.click();
    }

    @FindBy(css = "#pre_button")
    private WebElement inputNameAddress;

    public String getNameAddress() {
        String nameAddress = inputNameAddress.getAttribute("value");
        String domainText = dropDownMenu.getAttribute("textContent");
        return nameAddress + domainText;
    }


    @FindBy(css = "#pre_form > div > div.dropdown.mb-30.mb-md-0.show > div > button:nth-child(6)")
    private WebElement roverInfo;


    @FindBy(css = "#pre_settings")
    private WebElement buttonSettings;

    public void clickToButtonSetting() {
        buttonSettings.click();
    }

    @FindBy(css = "#secret-address")
    private WebElement textFieldSecretAddress;

    public String getTextSecretAddress() {
        String secretAddress = textFieldSecretAddress.getAttribute("textContent");
        return secretAddress;
    }

    @FindBy(css = "#modal-settings > div > form > div.modal-header > div > button")
    private WebElement buttonClose;

    public void clickToButtonClose() {
        buttonClose.click();
    }

    @FindBy(css = "#container-body > div > div.inbox > div > span")
    private WebElement textWaitNewMessage;

    public void getAssertTextInMenu() {
        String waitMessageText = textWaitNewMessage.getAttribute("textContent");
        Assert.assertEquals("В ожидании новых писем...", waitMessageText);
    }

    @FindBy(css = "#compose")
    private WebElement buttonSend;

    public void buttonComposeIsDisplayed() {
        Assert.assertTrue(buttonSend.isDisplayed());
    }

    public void clickToButtonCompose() {
        buttonSend.click();
    }


    public static WebElement waitForVisibility(WebElement element, int timeOfWait, int... timeOfTryOut) {
        WebElement webElement = null;
        int timeOfRevision = timeOfTryOut.length == 0
                ? 100
                : timeOfTryOut[0];
        try {
            webElement = new WebDriverWait(singleton.getDriver(),
                    timeOfWait,
                    timeOfRevision
            ).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webElement;
    }

    @FindBy(css = "#to")
    private WebElement sendTo;

    public void setTextInTextFieldTo() {
        sendTo.sendKeys(getNameAddress());
    }

    @FindBy(css = "#subject")
    private WebElement textFieldTheme;


    @FindBy(css = "#text")
    private WebElement textFieldTextMessage;

    public void setTextInFieldText() {
        textFieldTextMessage.sendKeys(getTextSecretAddress());
    }

    public void setSecondTextInFieldText() {
        textFieldTextMessage.sendKeys("Test2");
    }


    @FindBy(css = "#submit")
    private WebElement buttonSubmit;

    public void clickToButtonSubmit() {
        buttonSubmit.click();
    }

    @FindBy(css = "#container-body > div > div.inbox > div.mail > div")
    private WebElement divMessage;


    public void clickToDivMessage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#container-body > div > div.inbox > div.mail > div")));
        divMessage.click();
    }

    @FindBy(css = "#info > div.row.row-info.no-gutters > div.col.d-flex.mb-10 > span")
    private WebElement emailSender;

    public String getTextInFieldEmailSender() {
        return emailSender.getAttribute("textContent");
    }

    @FindBy(css = "#info > div.subject.mb-20")
    private WebElement textFieldThemeSender;

    public String getTextInFieldThemeSender() {
        return textFieldThemeSender.getAttribute("textContent");
    }

    public void assertThemeSender() {
        Assert.assertEquals("Test", getTextInFieldEmailSender());
    }

    @FindBy(css = "#info > div.overflow-auto.mb-20")
    private WebElement textSenderMessage;

    public String getTextInFieldSenderMessage() {
        return textSenderMessage.getAttribute("textContent");
    }

    public void assertSecretAddress() {
        Assert.assertEquals(getTextSecretAddress(), getTextInFieldSenderMessage());
    }

    @FindBy(css = "#reply")
    private WebElement buttonReply;

    public void clickToButtonReply() {
        buttonReply.click();
    }

    @FindBy(css = "#back")
    private WebElement buttonBackToMenu;

    public void clickToButtonBack() {
        buttonBackToMenu.click();
    }

    @FindBy(css = "#info > div.overflow-auto.mb-20")
    private WebElement textReTest2;

    @FindBy(css = "#container-body > div > div.inbox > div:nth-child(2) > div")
    private WebElement divReTest;

    public void clickToMessageText() {
        textReTest2.click();
    }

    public String getTextReTest() {
        return textReTest2.getAttribute("textContent");
    }

    public void assertReTest() {
        Assert.assertEquals("Re: Test", getTextReTest());
    }

    @FindBy(css = "#info > div.overflow-auto.mb-20")
    private WebElement textMessageSenderSecond;

    public void assertTestSecond() {
        String textMessageTest2 = textMessageSenderSecond.getAttribute("textContent");
        Assert.assertEquals("Test2", textMessageTest2);
    }

    @FindBy(css = "#delete_mail")
    private WebElement buttonDelete;

    public void clickToButtonDelete() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#delete")));
        buttonDelete.click();
    }

    @FindBy(css = "#confirm_mail")
    private WebElement buttonConfirmDelete;

    public void clickToButtonConfirm() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#confirm_mail")));
        buttonConfirmDelete.click();
    }

    public void findElementRe() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#delete")));
        Assert.assertFalse(driver.getPageSource().contains("Re: Test"));
    }


}