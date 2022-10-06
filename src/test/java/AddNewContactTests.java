
import models.Contacts;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase{

    @BeforeMethod
    public void precondition(){
       if(!app.getHelperUser().isLogged())
           app.getHelperUser().login(new User().withEmail("Nik@gmail.com").withPassword("123589$Nik"));
    }
    @Test
    public void addNewContactSuccess(){

        Random random= new Random();
        int i = random.nextInt(1000)+1000;

       // int i = (int)(System.currentTimeMillis()/1000)%3600; //hw

        Contacts contacts = Contacts.builder()
                .name("Alex"+i)
                .lastName("Smal")
                .phone("80935"+i)
                .email("Jon"+i+"@ukr.net")
                .address("Kiev, Ukraine")
                .description("friend")
                .build();

      //  int contactCountBeforeCreation = app.getContacts().getNumberOfContacts();

        app.getContacts().openContactForm();
        app.getContacts().fillContactForm(contacts);
        app.getContacts().saveContact();

//        int contactCountAfterCreation = app.getContacts().getNumberOfContacts();
//        Assert.assertEquals(contactCountAfterCreation, contactCountBeforeCreation + 1);

        Assert.assertTrue(app.getContacts().isContactAddedByName(contacts.getName()));
        Assert.assertTrue(app.getContacts().isContactAddedByPhone(contacts.getPhone()));

         }
    @Test
    public void addNewContactSuccessRequiredFields(){

        Random random= new Random();
        int i = random.nextInt(1000)+1000;

        Contacts contacts = Contacts.builder()
                .name("Alex"+i)
                .lastName("Smal")
                .phone("80935"+i)
                .email("Jon"+i+"@ukr.net")
                .address("Kiev, Ukraine")
                .build();
        app.getContacts().openContactForm();
        app.getContacts().fillContactRequiredForm(contacts);
        app.getContacts().saveContact();

        Assert.assertTrue(app.getContacts().isContactAddedByName(contacts.getName()));
        Assert.assertTrue(app.getContacts().isContactAddedByPhone(contacts.getPhone()));

        logger.info("Tests start with data ---> " + contacts.toString());

    }
    @Test
    public void addNewContactWrongName(){

        Contacts contacts = Contacts.builder()

                .lastName("Snow")
                .phone("80935455789")
                .email("Zoa@ukr.net")
                .address("Haifa")
                .build();


        app.getContacts().openContactForm();
        app.getContacts().fillContactRequiredForm(contacts);
        app.getContacts().saveContact();

        Assert.assertTrue(app.getContacts().isAddPageStillDisplayed());




    }



}
