package manager;

import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {

        super(wd);
    }

    public boolean isLogged() {
        // // //button[text()='Sign Out']
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
        type(By.xpath("//input[@placeholder='Email']"), email);
        type(By.xpath("//input[@placeholder='Password']"), password);


    }
    public void fillLoginRegistrationForm(User user) {
        type(By.xpath("//input[@placeholder='Email']"), user.getEmail());
        type(By.xpath("//input[@placeholder='Password']"), user.getPassword());

//        WebElement inputEmail = wd.findElement(By.xpath("//input[@placeholder='Email']"));
//        inputEmail.click();
//        inputEmail.clear();
//        inputEmail.sendKeys(email);


//        WebElement inputPassword = wd.findElement(By.xpath("//input[@placeholder='Password']"));
//        inputPassword.click();
//        inputPassword.clear();
//        inputPassword.sendKeys(password);
    }
    public void submitLogin(){
        WebElement loginButton = wd.findElement(By.xpath("//*[text()=' Login']"));
        loginButton.click();
    }


    public boolean isAlertPresent() {
        Alert alert = wd.switchTo().alert();
        if(alert == null){
            return false;

        }else {
            return true;
        }

    }

    public boolean isErrorWrongFormat() {
        Alert alert = wd.switchTo().alert();
        String errorText = alert.getText();
        System.out.println(errorText);

        //click OK
        alert.accept();

        /*

        //click Cancel
        alert.dismiss();
        //type text
        alert.sendKeys("Hello");

         */

        return errorText.contains("Wrong email or password format");

    }
}
