package demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    private WebDriverWrapper webDriverWrapper;
    ChromeDriver driver;

    @BeforeClass
    public void setUp(){
        webDriverWrapper= new WebDriverWrapper();

    }
    @AfterClass
      public void tearDown(){
        webDriverWrapper.quit();
      }


    // public TestCases(){

    //     System.out.println("Constructor: TestCases");
    //     WebDriverManager.chromedriver().timeout(30).setup();
    //     driver = new ChromeDriver();
    //     driver.manage().window().maximize();
    // }

    // public void endTest()
    // {
    //     System.out.println("End Test: TestCases");
    //     driver.close();
    //     driver.quit();

    // }

     @Test (priority = 1)
    
    public  void testCase01() throws InterruptedException{
        System.out.println("Start Test case: testCase01");

        webDriverWrapper.getDriver().get("https://www.flipkart.com");
        
        // webDriverWrapper.closePopupIfpresent("div[@class='_30XB9F']");
         
        webDriverWrapper.performSearch("//input[@class='Pke_EE']", "Washing Machine");
        
        webDriverWrapper.sortBypopularity("//div[contains(text(),'Popularity')]");
        Thread.sleep(3000);

        List<WebElement> ratings= webDriverWrapper.getRatings("//*[@class='XQDdHH']");
        
        int count = 0;
        for (WebElement ratingElement : ratings) {
            String ratingText = ratingElement.getText();
            try {
                double rating = Double.parseDouble(ratingText);
                if (rating <= 4.0) {
                    count++;
                }
            } catch (NumberFormatException e) {
            }
        }
        System.out.println("Count of items with rating <= 4 stars: " + count);
    

         System.out.println("end Test case: testCase01");
    }

      @Test (priority = 2)
      
    public  void testCase02() throws InterruptedException{
        System.out.println("Start Test case: testCase02");
        webDriverWrapper.getDriver().get("https://www.flipkart.com");
        Thread.sleep(3000);

         webDriverWrapper.closePopupIfpresent("div[@class='_30XB9F']");

         webDriverWrapper.performSearch("//input[@class='Pke_EE']", "iPhone");
    
         Thread.sleep(3000);

        List< WebElement> titles= webDriverWrapper.getElements("//div[@class='KzDlHZ']");
       List<WebElement> discounts= webDriverWrapper.getElements("//div[@class='UkUFwK']");
       if(titles.size()!=discounts.size()){
        System.out.println("Mismatch");
        return;
       }
       for(int i=0; i<discounts.size(); i++){
        String discounttext= discounts.get(i).getText();
        try{
            int discountt= Integer.parseInt(discounttext.replaceAll("[^0-9]", ""));
            if(discountt >17){
                String titlee= titles.get(i).getText();
                System.out.println("Title: "+ titlee + ", Discount : "+ discountt + "%");
             }
       }catch(NumberFormatException e){

       }
       }
        System.out.println("end Test case: testCase02");
    }

    @Test (priority = 3)

    public  void testCase03() throws InterruptedException{
        System.out.println("Start Test case: testCase03");
        webDriverWrapper.getDriver().get("https://www.flipkart.com");
        Thread.sleep(3000);

         webDriverWrapper.closePopupIfpresent("div[@class='_30XB9F']");

         webDriverWrapper.performSearch("//input[@class='Pke_EE']", "Coffee Mug");
    
         Thread.sleep(3000);

         webDriverWrapper.clickElement("(//div[@class='XqNaEv'])[1]");
         Thread.sleep(3000);

         List<WebElement> titles= webDriverWrapper.getElements("//a[@class='wjcEIp']");

         List<WebElement> imageElements= webDriverWrapper.getElements("//img[@class='DByuf4']");

         List<WebElement> reviewsCounts= webDriverWrapper.getElements("//div[@class='XQDdHH']");

         List<Integer> reviews= reviewsCounts.stream().map(review->Integer.parseInt(review.getText().replaceAll("[^0-9]", ""))).collect(Collectors.toList());
      
         List<Item> items= webDriverWrapper.getCombinedItems(titles, imageElements, reviews);

         List<Item> topItems= items.stream().sorted((item1, item2)->Integer.compare(item2.getReviews(), item1.getReviews())).limit(5).collect(Collectors.toList());
 
         for(Item item: topItems){
            System.out.println("Title: "+ item.getTitle() + ", Image Url: " + item.getImageUrl());
         }
         System.out.println("End Test case: testCase03");
        }

    }
