import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class LinkedinTest {

    private static IOSDriver<IOSElement> driver;

    @BeforeClass
    public static void setup() {
        driver = Driver.getDriver();
        System.out.println("Test başlatılıyor...");
    }

    @Test(priority = 1, description = "LinkedIn uygulamasının anasayfasını açar")
    public void anasayfaTesti() {
        WebElement anaSayfaButon = driver.findElement(MobileBy.AccessibilityId("12000"));
        anaSayfaButon.click();
        Assert.assertTrue(anaSayfaButon.isDisplayed(),"Ana sayfa butonu görünür değil");
        System.out.println("Anasayfa açıldı");
    }

    @Test(dependsOnMethods = {"anasayfaTesti"}, description = "LinkedIn ağ sayfası testi")
    public void agSayfasiTesti() {
        WebElement agSayfasiButon = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"15400\"]"));
        agSayfasiButon.click();
        Assert.assertTrue(agSayfasiButon.isEnabled(), "Ağ sayfası butonu aktif değil");
        System.out.println("Ağ sayfası açıldı");
    }

    @Test(dependsOnMethods = {"agSayfasiTesti"}, description = "LinkedIn İş İlanları sayfası testi")
    public void isIlaniSayfasiTesti() {
        WebElement isIlaniButon = driver.findElement(MobileBy.AccessibilityId("17900"));
        Assert.assertTrue(isIlaniButon.isEnabled(), "İş ilanları sayfası butonu aktif değil");
        isIlaniButon.click();
        System.out.println("İş ilanları sayfası açıldı");
    }

    @Test(dependsOnMethods = {"isIlaniSayfasiTesti"}, description = "LinkedIn Bildirimler sayfası testi")
    public void bildirimTesti()
    {
        WebElement bildirimButon = driver.findElement(MobileBy.iOSNsPredicateString("name == \"5500\""));
        Assert.assertTrue(bildirimButon.isEnabled(), "Bildirimler sayfası butonu aktif değil");
        bildirimButon.click();
        System.out.println("Bildirimler sayfası açıldı");
    }

    @Test(dependsOnMethods = {"bildirimTesti"}, description = "LinkedIn arama testi")
    public void aramaTesti() throws InterruptedException {
        WebElement aramaAlani = driver.findElement(MobileBy.AccessibilityId("5501_placeholder"));
        Assert.assertTrue(aramaAlani.isEnabled(), "Arama butonu aktif değil");
        aramaAlani.click();


        Thread.sleep(2000);

        WebElement aramaInputText = driver.findElement(MobileBy.AccessibilityId("5418"));
        aramaInputText.sendKeys("Appium", Keys.ENTER);

        Thread.sleep(2000);

    }

    @AfterClass
    public void tearDown() {
        Driver.closeDriver();
        System.out.println("Test tamamlandı ve uygulama kapatıldı.");

    }

}
