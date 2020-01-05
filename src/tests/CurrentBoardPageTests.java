package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class CurrentBoardPageTests extends TestBase{
    @BeforeMethod
    public void initTest(){
        WebElement loginIcon = driver.findElement(By
                .xpath("//a[@class='btn btn-sm btn-link text-white']"));

        loginIcon.click();
        waitUntilElementIsClickable(By.id("login"),30);
        WebElement userField = driver.findElement(By.id("user"));
        userField.click();
        userField.clear();
        userField.sendKeys("lanaioffe@mail.ru");
        driver.findElement(By.id("login")).click();

        waitUntilElementIsClickable(By.id("login-submit"),30);
        driver.findElement(By.id("login-submit")).click();

        waitUntilElementIsClickable(By.id("password"),30);
        waitUntilElementIsClickable(By.id("login-submit"),30);
        driver.findElement(By.id("password")).sendKeys("Rainbow02");
        driver.findElement(By.id("login-submit")).click();

        waitUntilElementIsClickable(By
                .xpath("//button[@data-test-id='header-boards-menu-button']"),30);

    }

    @Test
    public void createNewList() throws InterruptedException {


//        ----Open 'QA 4 Auto' board--------
        driver.findElement(By.xpath("//div[@class='board-tile-details is-badged']")).click();
        Thread.sleep(5000);

        //-----Add a new list ------
        driver.findElement(By.cssSelector(".placeholder")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector(".list-name-input")).sendKeys("New List");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(5000);
    }

    @Test
    public void addFirstCardInNewList() throws InterruptedException {

//        ----Open 'QA 4 Auto' board--------
        driver.findElement(By.xpath("//div[@class='board-tile-details is-badged']")).click();
        Thread.sleep(5000);

        //--------Get quantity of 'Add another card' buttons at the beginning----
        int quantityAddAnotherButtonBeg = driver.findElements(By.xpath("//span[@class= 'js-add-another-card']")).size();

        //-----Add a new list------
        driver.findElement(By.cssSelector(".placeholder")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector(".list-name-input")).sendKeys("New List");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(5000);

        //----- Get the last 'Add card' button----
        List<WebElement> listAddCardButtons = driver.findElements(By.xpath("//span[@class = 'js-add-a-card']"));
        int sizeLstAddCardButtons = listAddCardButtons.size();
        WebElement lastAddCardButton = listAddCardButtons.get(sizeLstAddCardButtons-1);
        //----Add a first card for any new list
        lastAddCardButton.click();
        Thread.sleep(5000);
        driver.findElement(By
                .xpath("//textarea[@placeholder='Enter a title for this card…']")).sendKeys("text");
        driver.findElement(By
                .xpath("//input[@class='primary confirm mod-compact js-add-card']")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("a.js-cancel")).click();

        //--------Get quantity of 'Add another card' buttons at the end----

        int quantityAddAnotherButtonEnd = driver.findElements(By.xpath("//span[@class= 'js-add-another-card']")).size();

        Assert.assertEquals(quantityAddAnotherButtonBeg+1, quantityAddAnotherButtonEnd);
    }

    @Test
    public void deleteList() throws InterruptedException {

//        ----Open 'QA 4 Auto' board--------
        driver.findElement(By.xpath("//div[@class='board-tile-details is-badged']")).click();
        Thread.sleep(5000);

        //--------Get quantity of 'Add another card' buttons at the beginning----
        int quantityAddAnotherButtonBeg = driver.findElements(By.xpath("//span[@class= 'js-add-another-card']")).size();

//      -----Delete New list------
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[4]//div[1]//div[1]//div[2]//a[1]")).click();
        Thread.sleep(5000);

    }



}