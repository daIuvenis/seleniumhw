package singletonTempMail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class tempMail {
    @Before
    public void settingsBefore(){
        singleton.initialize();
    }
    @After
    public void settingsAfter(){
        singleton.quit();
    }
    @Test
    public void tempMailTest() {
        WebDriverWait wait = new WebDriverWait(singleton.driver, 10);
        singleton.driver.findElement(By.cssSelector("#pre_rand")).click(); // 3.2
        singleton.driver.findElement(By.cssSelector("#domain")).click();
        singleton.driver.findElement(By.cssSelector("#pre_form > div > div.dropdown.mb-30.mb-md-0.show > div > button:nth-child(6)")).click();    //3.3
        String name = singleton.driver.findElement(By.cssSelector("#pre_button")).getAttribute("value");
        String domain = singleton.driver.findElement(By.cssSelector("#domain")).getAttribute("textContent");
        String nameDomain = name + domain; // 3.4
        singleton.driver.findElement(By.cssSelector("#pre_settings")).click();
        String color = singleton.driver.findElement(By.cssSelector("#modal-settings > div > form > div.modal-body > div:nth-child(1) > div.btn-group.btn-group-toggle.d-flex > label:nth-child(3)")).getCssValue("color");
        Assert.assertEquals("rgba(0, 199, 133, 1)", color);
        String secretAddress = singleton.driver.findElement(By.cssSelector("#secret-address")).getAttribute("textContent");   //3.6
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#modal-settings > div > form > div.modal-header > div > button")));
        singleton.driver.findElement(By.cssSelector("#modal-settings > div > form > div.modal-header > div > button")).click();
        Assert.assertTrue(singleton.driver.findElement(By.cssSelector("#compose")).isDisplayed()); //3.7
        String awaiting = singleton.driver.findElement(By.cssSelector("#container-body > div > div.inbox > div > span")).getAttribute("textContent");
        Assert.assertEquals("В ожидании новых писем...", awaiting);     //3.8
        singleton.driver.findElement(By.cssSelector("#compose")).click();     //3.9
        singleton.driver.findElement(By.cssSelector("#to")).sendKeys(nameDomain);
        singleton.driver.findElement(By.cssSelector("#subject")).sendKeys("Test");
        singleton.driver.findElement(By.cssSelector("#text")).sendKeys(secretAddress);    //3.10
        singleton.driver.findElement(By.cssSelector("#submit")).click();      //3.11
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#container-body > div > div.inbox > div.mail > div")));
        singleton.driver.findElement(By.cssSelector("#container-body > div > div.inbox > div.mail > div")).click();   //3.12
        String emailSender = singleton.driver.findElement(By.cssSelector("#info > div.row.row-info.no-gutters > div.col.d-flex.mb-10 > span")).getAttribute("textContent");
        String themeSender = singleton.driver.findElement(By.cssSelector("#info > div.subject.mb-20")).getAttribute("textContent");
        Assert.assertEquals("Test", themeSender);
        String textSecretAddress = singleton.driver.findElement(By.cssSelector("#info > div.overflow-auto.mb-20")).getAttribute("textContent");
        Assert.assertEquals(secretAddress, textSecretAddress);      //3.13
        singleton.driver.findElement(By.cssSelector("#reply")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#submit")));
        singleton.driver.findElement(By.cssSelector("#text")).sendKeys("Test2");
        singleton.driver.findElement(By.cssSelector("#submit")).click();      //3.14
        singleton.driver.findElement(By.cssSelector("#back")).click();        //3.15
        String reTest = singleton.driver.findElement(By.cssSelector("#container-body > div > div.inbox > div:nth-child(2) > div > div.subj.col-12.col-md-7.px-md-3 > span")).getAttribute("textContent");
        Assert.assertEquals("Re: Test", reTest);       //3.16
        singleton.driver.findElement(By.cssSelector("#container-body > div > div.inbox > div:nth-child(2) > div > div.subj.col-12.col-md-7.px-md-3")).click();
        String test2 = singleton.driver.findElement(By.cssSelector("#info > div.overflow-auto.mb-20")).getAttribute("textContent");
        Assert.assertEquals("Test2", test2);
        singleton.driver.findElement(By.cssSelector("#delete_mail")).click();     //3.17
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#confirm_mail")));
        singleton.driver.findElement(By.cssSelector("#confirm_mail")).click();    //3.18
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#delete")));
        Assert.assertFalse(singleton.driver.getPageSource().contains("Re: Test"));    //3.19
    }
}