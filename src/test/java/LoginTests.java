import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


public class LoginTests extends TestBase {


    @Test
    public void loginSuccess() {


        //open login form
        WebElement loginTab = wd.findElement(By.xpath("//a[@href='/login']"));
        loginTab.click();
        pause(3000);

        //fill email ....Nik@gmail.com	(password 123589$Nik)
        WebElement inputEmail = wd.findElement(By.xpath("//input[@placeholder='Email']"));
        inputEmail.click();
        inputEmail.clear();
        inputEmail.sendKeys("Nik@gmail.com");
        pause(3000);

        WebElement inputPassword = wd.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys("123589$Nik");
        pause(3000);

        // submit login
        WebElement loginButton = wd.findElement(By.xpath("//*[text()=' Login']"));
        loginButton.click();
        pause(3000);


    }

    @Test
    public void loginNegativeWrongEmailFormat() {

    }

    @Test
    public void loginNegativeWrongPasswordFormat() {


    }
}
