package manager;

import models.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class HelperContacts extends HelperBase{
    public HelperContacts(WebDriver wd) {

        super(wd);
    }

    public void openContactForm() {

        click(By.cssSelector("[href='/add']"));
    }

    public void fillContactForm(Contacts contacts) {
        type(By.cssSelector("[placeholder='Name']"),contacts.getName());
        type(By.cssSelector("[placeholder='Last Name']"),contacts.getLastName());
        type(By.cssSelector("[placeholder='Phone']"),contacts.getPhone());
        type(By.cssSelector("[placeholder='email']"),contacts.getEmail());
        type(By.cssSelector("[placeholder='Address']"),contacts.getAddress());
        type(By.cssSelector("[placeholder='description']"),contacts.getDescription());

    }

    public void saveContact() {
        click(By.cssSelector(".add_form__2rsm2 button"));
        //button/b
    }
    public void saveContactWithTab() {

        WebElement element = wd.findElement(By.cssSelector("[placeholder='description']"));
        element.sendKeys(Keys.TAB);
        //click(By.cssSelector(".add_form__2rsm2 button"));
       pause(500);
        element.sendKeys(Keys.ENTER);

    }

    public int getNumberOfContacts() {
        return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();


    }

    public boolean isContactAddedByName(String name) {
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));

        for (WebElement el : list) {
            if (el.getText().equals(name))
                return true;
        }
        return false;
    }


    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));

        for (WebElement el : list) {
            if (el.getText().equals(phone))
                return true;
        }
        return false;
    }

    public void fillContactRequiredForm(Contacts contacts) {
        type(By.cssSelector("[placeholder='Name']"),contacts.getName());
        type(By.cssSelector("[placeholder='Last Name']"),contacts.getLastName());
        type(By.cssSelector("[placeholder='Phone']"),contacts.getPhone());
        type(By.cssSelector("[placeholder='email']"),contacts.getEmail());
        type(By.cssSelector("[placeholder='Address']"),contacts.getAddress());
    }

    public boolean isAddPageStillDisplayed() {
        return  wd.findElements(By.cssSelector("a.active[href='/add']")).size()>0;
    }


    public void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[text()='Remove']"));
    }


    public void removeAllContact() {

       // WebElement element = wd.findElement(By.cssSelector(".contact-item_card__2SOIM"));

        while(isElementPresent(By.cssSelector(".contact-item_card__2SOIM"))){
            removeContact();
            pause(2000);
        }

    }

    public String getMessage() {
        wd.findElement(By.cssSelector(".contact-page_message__2qafk"));
        return wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1")).getText();
    }

    public boolean isNotContact() {
        List<WebElement> list = wd.findElements(By.xpath("//h1[text()=' No Contacts here!']"));
        return list.size() > 0;
    }

    public void addContacts() {

        Contacts contacts = Contacts.builder()
                .name("Masha")
                .lastName("Ivanova")
                .phone("80935555897")
                .email("masha@ukr.net")
                .address("Tel Aviv")
                .description("work")
                .build();
        openContactForm();
        fillContactForm(contacts);
        saveContact();
pause(2000);
        Contacts contacts2 = Contacts.builder()
                .name("Dasha")
                .lastName("Zov")
                .phone("80935666963")
                .email("dasha@ukr.net")
                .address("Tel Aviv")
                .description("sister")
                .build();
        openContactForm();
        fillContactForm(contacts2);
        saveContact();
        pause(2000);

        Contacts contacts3 = Contacts.builder()
                .name("Igor")
                .lastName("Fow")
                .phone("80935666968")
                .email("igor1288@ukr.net")
                .address("Holon")
                .description("work")
                .build();
        openContactForm();
        fillContactForm(contacts3);
        saveContact();
    }
}
