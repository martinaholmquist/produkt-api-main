import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class SeleniumAppClassTest {

/*
    @Test
    void findCorrectTitleOfWebb(){
        //given
        WebDriver driver = new ChromeDriver();
        driver.get(("https://java22.netlify.app"));
        //when&then
        Assertions.assertEquals("Webbutik", driver.getTitle(), "Titel stämmer inte med förväntat");
        driver.quit();
    }

    @Test
    void findCorrectTitleAtWebb(){
        //given
        WebDriver driver = new ChromeDriver();
        driver.get(("https://java22.netlify.app"));
        //when
        String h1 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/h1")).getText();
        //then
        Assertions.assertEquals("Testdriven utveckling - projekt", h1, "Titel stämmer inte med förväntat");
        driver.quit();
    }


    @Test
    void checkCategoryNameList(){
        //given
        WebDriver driver = new ChromeDriver();
        driver.get(("https://java22.netlify.app"));
        //when
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.tagName("div")));

        List<WebElement> elements = element.findElements(By.className("menuLink"));

        List<String> products = new ArrayList<>();
        for (WebElement e : elements) {
            products.add(e.getText());
        }
        //then
        Assertions.assertEquals("[electronics, jewelery, men's clothing, women's clothing]", products.toString());
        driver.quit();
    }


    @Test
    void checkifDisplayedPicFjallraven() {
        //given
        WebDriver driver = new ChromeDriver();
        driver.get(("https://java22.netlify.app"));
        //when
        WebElement pic = new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//img[@class='card-img-top' and contains(@src,'https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg')]")));
        //then
        Assertions.assertTrue(pic.isDisplayed(),  "bilden verkar inte läsas in");
        driver.quit();
    }

    @Test
    void checkifDisplayedPicMensCasual() {
        //given
        WebDriver driver = new ChromeDriver();
        driver.get(("https://java22.netlify.app"));
        //when
        WebElement pic = new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//img[@class='card-img-top' and contains(@src,'https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg')]")));
        //then
        Assertions.assertTrue(pic.isDisplayed(),  "bilden verkar inte läsas in");
        driver.quit();
    }

    @Test
    void checkifDisplayedPicMensCottonJacket() {
        //given
        WebDriver driver = new ChromeDriver();
        driver.get(("https://java22.netlify.app"));
        //when
        WebElement pic = new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//img[@class='card-img-top' and contains(@src,'https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg')]")));
        //then
        Assertions.assertTrue(pic.isDisplayed(),  "bilden verkar inte läsas in");
        driver.quit();
    }

    @Test
    void checkSRCPicFjallraven() {
        //given
        WebDriver driver = new ChromeDriver();
        driver.get(("https://java22.netlify.app"));
        //when
        WebElement pic = new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//img[@class='card-img-top' and contains(@src,'https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg')]")));
        //then
        Assertions.assertEquals("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg", pic.getAttribute("src"), "Fel SRC");
        driver.quit();
    }


    @Test
    void checkSRCPicMensCasual() {
        //given
        WebDriver driver = new ChromeDriver();
        driver.get(("https://java22.netlify.app"));
        //when
        WebElement pic = new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//img[@class='card-img-top' and contains(@src,'https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg')]")));
        //then
        Assertions.assertEquals("https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg", pic.getAttribute("src"), "Fel SRC");
        driver.quit();
    }



    @Test
    void checkSRCPicMensCottonJacket() {
        //given
        WebDriver driver = new ChromeDriver();
        driver.get(("https://java22.netlify.app"));
        //when
        WebElement pic = new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//img[@class='card-img-top' and contains(@src,'https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg')]")));
        //then
        Assertions.assertEquals("https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg", pic.getAttribute("src"), "Fel SRC");
        driver.quit();
    }

    @Test
    void checkAllProductsName(){
        //given
        WebDriver driver = new ChromeDriver();
        driver.get(("https://java22.netlify.app"));
        //when
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//*[@id=\"root\"]/div/div[6]")));
        List<WebElement> elements = element.findElements(By.cssSelector("div[class^='card-title ']"));

        List<String> products = new ArrayList<>();
        for (WebElement e : elements) {
            products.add(e.getText());
        }

        //then
        Assertions.assertEquals("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops, " +
                "Mens Casual Premium Slim Fit T-Shirts, " +
                "Mens Cotton Jacket, " +
                "Mens Casual Slim Fit, " +
                "John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet, " +
                "SolGold Petite Micropave, " +
                "White Gold Plated Princess, " +
                "Pierced Owl Rose Gold Plated Stainless Steel Double, " +
                "WD 2TB Elements Portable External Hard Drive - USB 3.0, " +
                "SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s, " +
                "Silicon Power 256GB SSD 3D NAND A55 SLC Cache Performance Boost SATA III 2.5, " +
                "WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive, " +
                "Acer SB220Q bi 21.5 inches Full HD (1920 x 1080) IPS Ultra-Thin, " +
                "Samsung 49-Inch CHG90 144Hz Curved Gaming Monitor (LC49HG90DMNXZA) – Super Ultraw Screen QLED, " +
                "BIYLACLESEN Women's 3-in-1 Snowboard Jacket Winter Coats, " +
                "Lock and Love Women's Removable Hooded Faux Leather Moto Biker Jacket, " +
                "Rain Jacket Women Windbreaker Striped Climbing Raincoats, " +
                "MBJ Women's SolShort Sleeve Boat Neck V, " +
                "Opna Women's Short Sleeve Moisture, " +
                "DANVOUY Womens T Shirt Casual Cotton Short", products.toString().replace("[", "").replace("]", ""));
        driver.quit();
    }



    @Test
    void checkPriceBagTshirtJacketProducts(){
        //given
        WebDriver driver = new ChromeDriver();
        driver.get(("https://java22.netlify.app"));
        //when
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//*[@id=\"root\"]/div/div[6]")));
        List<WebElement> elements = element.findElements(By.cssSelector("p[class^='card-text']"));

        List<String> products = new ArrayList<>();
        for (WebElement e : elements) {
            products.add(e.getText());
        }

        String[] productBag = products.get(0).split("\\r?\\n|\\r");
        String priceBag = productBag[productBag.length-1];

        String[] tShirt = products.get(1).split("\\r?\\n|\\r");
        String pricetShirt = tShirt[tShirt.length-1];

        String[] jacket = products.get(2).split("\\r?\\n|\\r");
        String priceJacket = jacket[jacket.length-1];

        //then
        Assertions.assertEquals("109.95", priceBag,  "Priset för väskan är ej korrekt");
        Assertions.assertEquals("22.3", pricetShirt,  "Priset för T-shirt är ej korrekt");
        Assertions.assertEquals("55.99", priceJacket,  "Priset för jackan är ej korrekt");
        driver.quit();
    }

    @Test
    void checkPriceSlimFitJohnHardyGoldPetiteProducts(){
        //given
        WebDriver driver = new ChromeDriver();
        driver.get(("https://java22.netlify.app"));
        //when
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//*[@id=\"root\"]/div/div[6]")));
        List<WebElement> elements = element.findElements(By.cssSelector("p[class^='card-text']"));

        List<String> products = new ArrayList<>();
        for (WebElement e : elements) {
            products.add(e.getText());
        }

        String[] slimFit = products.get(3).split("\\r?\\n|\\r");
        String slimFitPrice = slimFit[slimFit.length-1];

        String[] johnHardy = products.get(4).split("\\r?\\n|\\r");
        String pricejohnHardy = johnHardy[johnHardy.length-1];

        String[] goldPetit = products.get(5).split("\\r?\\n|\\r");
        String pricegoldPetit = goldPetit[goldPetit.length-1];

        //then
        Assertions.assertEquals("15.99", slimFitPrice,  "Priset för tröjan är ej korrekt");
        Assertions.assertEquals("695", pricejohnHardy,  "Priset för armbandet är ej korrekt");
        Assertions.assertEquals("168", pricegoldPetit,  "Priset för ringen är ej korrekt");
        driver.quit();
    }

    @Test
    void findCorrectNoOfProducts(){
        //given
        WebDriver driver = new ChromeDriver();
        driver.get(("https://java22.netlify.app"));
        //when
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.className("productItem")));

        List<WebElement> allProducts = driver.findElements(By.className("productItem"));
        //then
        Assertions.assertEquals(20, allProducts.size(),"Antalet stämmer inte med förväntat"  );
        driver.quit();
    }

    @Test
    void checkNumbersOfWomensClothesInCategory() {
        //given
        WebDriver driver = new ChromeDriver();
        driver.get(("https://java22.netlify.app"));

        //when
        WebElement womensClothes = new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@id=\"root\"]/div/div[5]/a")));
        womensClothes.click();
        List<WebElement> result = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[6]/div/div/div/div"));

        //then
        Assertions.assertEquals(6, result.size(),"Antalet stämmer inte med förväntat"  );
        driver.quit();
    }

    @Test
    void checkNumbersOfMensClothesInCategory() {
        //given
        WebDriver driver = new ChromeDriver();
        driver.get(("https://java22.netlify.app"));

        //when
        WebElement menClothes = new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@id=\"root\"]/div/div[4]/a")));
        menClothes.click();
        List<WebElement> result = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[6]/div/div/div/div"));

        //then
        Assertions.assertEquals(4, result.size(),"Antalet stämmer inte med förväntat"  );
        driver.quit();
    }

    @Test
    void checkNumbersOfJewelleryInCategory() {
        //given
        WebDriver driver = new ChromeDriver();
        driver.get(("https://java22.netlify.app"));

        //when
        WebElement jewellery = new WebDriverWait(driver, Duration.ofSeconds(30)).
                until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@id=\"root\"]/div/div[3]/a")));
        jewellery.click();
        List<WebElement> result = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[6]/div/div/div/div"));

        //then
        Assertions.assertEquals(4, result.size(),"Antalet stämmer inte med förväntat"  );
        driver.quit();
    }

    @Test
    void checkNumbersOfElectronicsInCategory() {
        //given
        WebDriver driver = new ChromeDriver();
        driver.get(("https://java22.netlify.app"));

        //when
        WebElement electronics = new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@id=\"root\"]/div/div[2]/a")));
        electronics.click();
        List<WebElement> result = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[6]/div/div/div/div"));

        //then
        Assertions.assertEquals(6, result.size(),"Antalet stämmer inte med förväntat"  );
        driver.quit();
    }

*/
}
