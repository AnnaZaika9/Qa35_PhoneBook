package manager;

import models.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
       // click(By.cssSelector(".add_form__2rsm2 button"));
        click(By.xpath("//button/b"));
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


    public void removeContactHW() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[text()='Remove']"));
        pause(500);
    }


    public void removeAllContactHW() {

       // WebElement element = wd.findElement(By.cssSelector(".contact-item_card__2SOIM"));

        while(isElementPresent(By.cssSelector(".contact-item_card__2SOIM"))){
            removeContactHW();
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

        Contacts contacts = Contacts.builder().name("Masha").lastName("Ivanova").phone("80935555897").email("masha@ukr.net").address("Tel Aviv").description("work").build();
        openContactForm();
        fillContactForm(contacts);
        saveContact();
        pause(500);
        Contacts contacts2 = Contacts.builder().name("Dasha").lastName("Zov").phone("80935666963").email("dasha@ukr.net").address("Tel Aviv").description("sister").build();
        openContactForm();
        fillContactForm(contacts2);
        saveContact();
        pause(500);
        Contacts contacts3 = Contacts.builder().name("Igor").lastName("Fow").phone("80935666968").email("igor1288@ukr.net").address("Holon").description("work").build();
        openContactForm();
        fillContactForm(contacts3);
        saveContact();
    }

    public int removeOneContact() {
        int before = countOfContact();

        if(!isCountListEmpty()) {
            removeContact();
        }

        int after = countOfContact();
        return before-after;
    }

    private boolean isCountListEmpty() {
        return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).isEmpty();
    }

    private void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[text()='Remove']"));
        pause(2000);
    }

    private int countOfContact() {

        return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
    }

    public void removeAllContacts() {
//       List <WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
//        for (int i = 0; i < list.size(); i++) {
//            click(By.cssSelector(".contact-item_card__2SOIM"));
//            click(By.xpath("//button[text()='Remove']"));
//            pause(500);
//        }

        while (wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size()!=0){
            removeContact();
        }

    }
    public boolean isNoContactHere() {
        return new WebDriverWait(wd, Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .textToBePresentInElement(wd.findElement(By.cssSelector(".contact-page_message__2qafk h1")),"No Contacts here!" ));
    }

    public void providerOfContacts() {
        Random random = new Random();
        // check count of contacts <3 ---> add contact 3
        if(countOfContact() < 4){
            for(int i = 0; i < 3; i++){
                int index = random.nextInt(100)+100;
                Contacts contacts =  Contacts.builder()
                        .name("Inna"+index)
                        .lastName("Snow")
                        .email("inna"+index+"gmail.ru")
                        .phone("123456"+index)
                        .address("Haifa")
                        .build();
                openContactForm();
                fillContactRequiredForm(contacts);
                saveContact();
                pause(2000);
                logger.info("Provider added contact --->" + contacts.toString());
            }
        }
    }
}
