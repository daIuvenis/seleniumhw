
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.interactions.Actions;


public class tempMailTest extends TempMail {
    @Before
    public void settingsOpenWindow() {
        singleton.initialize();//3.1
    }

        @After
    public void settingAfter(){
        singleton.quit();
    }
    @Test
    public void tempMailTest() throws InterruptedException {
        TempMail po = new TempMail();
        Actions action = new Actions(singleton.getDriver());
        po.getRandomButton().click();       // 3.2

        po.getDropDownMenu().click();
        po.getRoverInfo().click();          //3.3
        getNameAddress();                   // 3.4
        po.getButtonSettings().click();
        getTextSecretAddress();             //3.6c

        buttonComposeIsDisplayed();
        Thread.sleep(500);
        po.getButtonClose().click();
              //3.7
        getAssertTextInMenu();                   //3.8
        TempMail.waitForVisibility(getButtonSend(), 10);
        po.getButtonSend().click();              //3.9
        Thread.sleep(500);
        po.getSendTo().sendKeys(getNameAddress());
        po.getTextFieldTheme().sendKeys("Test");
        po.getTextFieldTextMessage().sendKeys(getTextSecretAddress());      //3.10
        po.getButtonSubmit().click();              //3.11
        TempMail.waitForVisibility(getDivMessage(), 10);
        po.getDivMessage().click();                   //3.12
        Assert.assertEquals(po.getEmailSender().getAttribute("textContent"), po.getNameAddress());
        System.out.println(po.getTextInFieldEmailSender());
        Assert.assertEquals(po.getTextFieldThemeSender().getAttribute("textContent"), "Test");
        Assert.assertEquals(po.getTextSenderMessage().getAttribute("textContent"), getTextSecretAddress());     //3.13
        po.getButtonReply().click();
        TempMail.waitForVisibility(po.getTextFieldThemeSender(), 10);
        po.getTextFieldTextMessage().sendKeys("Test2");
        po.getButtonSubmit().click();             //3.14
        action.moveByOffset(10, 10).click().build().perform();
        po.getButtonBackToMenu().click();         //3.15
        TempMail.waitForVisibility(po.getButtonSettings(), 10);
        Assert.assertTrue(singleton.driver.getPageSource().contains("Re: Test"));     //3.16
        po.getDivReTest().click();
        po.getTextReTest();
        System.out.println(getTextReTest());
        assertTestSecond();
        System.out.println("a");
        po.getButtonDelete().click();     //3.17
        po.getButtonConfirmDelete().click();   //3.18
        action.moveByOffset(10, 10).click().build().perform();
        Assert.assertFalse(singleton.driver.getPageSource().contains("Re: Test"));        //3.19
    }
}
