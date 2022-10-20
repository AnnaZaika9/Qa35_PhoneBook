package manager;

import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
    //   WebElement loginTab = wd.findElement(By.xpath("//a[@href='/l']"));
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
        return errorText.contains("Wrong email or password format");

        /*
        //click Cancel
        alert.dismiss();
        //type text
        alert.sendKeys("Hello");
         */
    }
    public boolean isErrorUndefined() {
        Alert alert = wd.switchTo().alert();
        String errorText = alert.getText();
        System.out.println(errorText);

        alert.accept();
        return errorText.contains("Undefined Error 500");

    }



    public void submitRegistration() {
//        WebElement loginButton = wd.findElement(By.xpath("//*[text()=' Registration']"));
//        loginButton.click();
        click(By.xpath("//button[2]"));
    }

    public boolean isNoContactsHereDisplayed() {
       // return wd.findElement(By.cssSelector("div.contact-page_message__2qafk>h1")).getText().contains("");
        return new WebDriverWait(wd, Duration.ofSeconds(5)).until(ExpectedConditions.textToBePresentInElement(wd.findElement(By.cssSelector("div.contact-page_message__2qafk>h1")),"No Contacts here!"));
    }

    public boolean isAlertWithErrorPresent(String message) {
        Alert alert = new WebDriverWait(wd, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());
        if(alert != null && alert.getText().contains(message)){
            alert.accept();
            return true;
        }
        return false;
    }
    //hw
    public boolean isAlertError(){
        Alert alert = new WebDriverWait(wd, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());

        if(alert == null) return false;

        String errorText = alert.getText();
        alert.accept();
        return errorText.contains("Wrong email or password format");

    }


    public void login(User user) {
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();

    }


}
