import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;

public class Selenium {

    public static void main(String[] args) throws InterruptedException{
        WebDriver driver = new ChromeDriver();
        driver.get("http://127.0.0.1:10008/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        Thread.sleep(1000);

        //1
        WebElement button_b_1 = driver.findElement(By.className("button1"));
        button_b_1.click();
        if(driver.getTitle().equals("B"))
            System.out.println("Test 1: OK");
        else
            System.out.println("Test 1: FAILED");
        Thread.sleep(1000);
        //2
        WebElement button_a_2 = driver.findElement(By.className("button1"));
        button_a_2.click();
        if(driver.getTitle().equals("A"))
            System.out.println("Test 2: OK");
        else
            System.out.println("Test 2: FAILED");
        Thread.sleep(1000);

        //3
        WebElement button_c_3 = driver.findElement(By.className("button2"));
        button_c_3.click();
        if(driver.getTitle().equals("C"))
            System.out.println("Test 3: OK");
        else
            System.out.println("Test 3: FAILED");
        Thread.sleep(1000);

        //4
        WebElement button_a_4 = driver.findElement(By.className("button1"));
        button_a_4.click();
        if(driver.getTitle().equals("A"))
            System.out.println("Test 4: OK");
        else
            System.out.println("Test 4: FAILED");
        Thread.sleep(1000);

        //5
        WebElement button_d_5 = driver.findElement(By.className("button3"));
        button_d_5.click();
        if(driver.getTitle().equals("D"))
            System.out.println("Test 5: OK");
        else
            System.out.println("Test 5: FAILED");
        Thread.sleep(1000);

        //6
        WebElement button_a_6 = driver.findElement(By.className("button1"));
        button_a_6.click();
        if(driver.getTitle().equals("A"))
            System.out.println("Test 6: OK");
        else
            System.out.println("Test 6: FAILED");
        Thread.sleep(1000);

        driver.quit();
    }
}
