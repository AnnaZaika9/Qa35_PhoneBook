package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public boolean isLogged() {
        //
        List<WebElement> list = wd.findElements(By.xpath("//button[text()='Sign Out']"));
        //list.size()>0; list.size()=0;
        return list.size() > 0;
    }

    public void logout() {
        // WebElement element = wd.findElement(By.xpath("//button[text()='Sign Out']"));
        // element.click();
        wd.findElement(By.xpath("//button[text()='Sign Out']")).click();

    }
    public void openLoginRegistrationForm(){
        WebElement loginTab = wd.findElement(By.xpath("//a[@href='/login']"));
        loginTab.click();
    }

    public void fillLoginRegistrationForm(String email, String password){
        WebElement inputEmail = wd.findElement(By.xpath("//input[@placeholder='Email']"));
        inputEmail.click();
        inputEmail.clear();
        inputEmail.sendKeys(email);


        WebElement inputPassword = wd.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys(password);
    }

    public void submitLogin(){
        WebElement loginButton = wd.findElement(By.xpath("//*[text()=' Login']"));
        loginButton.click();
    }


}
