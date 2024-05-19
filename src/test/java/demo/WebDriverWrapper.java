package demo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverWrapper {
    private WebDriver driver;
    private WebDriverWait wait;

    public WebDriverWrapper(){

    WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void closePopupIfpresent(String xpath){

        try{
            WebElement close= driver.findElement(By.xpath("//span[@class='_30XB9F']"));
            close.click();
            }catch (Exception e){
              
            }
        }

        public void performSearch(String searchBoxXpath, String searchterm){
           
            WebElement searchBox= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='Pke_EE']")));
            searchBox.sendKeys(searchterm);
            // search.sendKeys(Keys.ENTER);
            searchBox.submit();
            System.out.println("searched");
        }

        public void sortBypopularity(String popularityXpath){
            WebElement popularity= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Popularity')]")));
            popularity.click();
            System.out.println("popularity");
        }

        public List<WebElement> getRatings(String ratingXpath){
            return
            driver.findElements(By.xpath("//div[@class='XQDdHH']"));
        }

        public List<WebElement> getElements(String xpath){
            return
            driver.findElements(By.xpath(xpath));
        }

        public void clickElement(String xpath){
            WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            element.click();
            System.out.println("clicked element");
        }

        public List<Item> getCombinedItems(List<WebElement>titles, List<WebElement>images, List<Integer>reviews){
            List<Item> items=new ArrayList<>();
            for(int i= 0; i<titles.size(); i++){
                String title= titles.get(i).getText();
                String imageUrl= images.get(i).getAttribute("src");
                int reviewCount=reviews.get(i);
                items.add(new Item(title, imageUrl, reviewCount));

            }
            return items;
        }

        public void quit(){
            driver.quit();
        }

}

class Item{
    private String title;
    private String imageUrl;
    private int reviews;

    public Item(String title, String imageUrl, int reviews){
        this.title= title;
        this.imageUrl= imageUrl;
        this.reviews= reviews;
}
   public String getTitle(){
    return title;
}
  public String getImageUrl(){
    return imageUrl;
}
  public int getReviews(){
    return reviews;
  }
    }


