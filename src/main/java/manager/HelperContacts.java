package manager;

import models.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperContacts extends HelperBase{
    public HelperContacts(WebDriver wd) {

        super(wd);
    }

    public void addNewContact() {

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

    public void save() {
        click(By.cssSelector(".add_form__2rsm2 button"));

    }
}
