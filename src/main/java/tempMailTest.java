
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class tempMailTest extends tempMail {
    @Before
    public void settingsOpenWindow(){
        settingsOpenBefore();//3.1
    }
    @After
    public void settingsQuitAfter(){
        settingsQuit();
    }
    @Test
    public void tempMailTest(){
    clickToButtonRandom();  // 3.2
    openDropDownMenu();
    enterRoverInfo();      //3.3
    getNameAddress(); // 3.4
    clickToButtonSetting();
    getTextSecretAddress();  //3.6
    //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#modal-settings > div > form > div.modal-header > div > button")));
    clickToButtonClose();
    buttonComposeIsDisplayed();      //3.7
    getAssertTextInMenu();      //3.8
    clickToButtonCompose();      //3.9
    setTextInTextFieldTo();
    setTextInTextFieldSubject();
    setTextInFieldText();     //3.10
    clickToButtonSubmit();     //3.11
    clickToDivMessage();    //3.12
    getTextInFieldEmailSender();
    assertThemeSender();
    assertSecretAddress();       //3.13
    clickToButtonReply();
    //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#submit")));
    setSecondTextInFieldText();
    clickToButtonSubmit(); //3.14
    clickToButtonBack();   //3.15
    getTextReTest();
    assertReTest();     //3.16
    clickToMessageText();
    assertTestSecond();
    clickToButtonDelete();     //3.17
    clickToButtonConfirm();   //3.18
    findElementRe();        //3.19
}
}
