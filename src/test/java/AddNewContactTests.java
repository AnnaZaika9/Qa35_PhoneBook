
import models.Contacts;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase{

    @BeforeMethod
    public void posCondition(){
       if(!app.getHelperUser().isLogged())
           app.getHelperUser().login(new User().withEmail("Nik@gmail.com").withPassword("123589$Nik"));

    }
    @Test
    public void addContactSuccess(){

        int i = (int)(System.currentTimeMillis()/1000)%3600;

        Contacts contacts = Contacts.builder()
                .name("Alex")
                .lastName("Smal")
                .phone("+380935455766")
                .email("Jon12"+i+"@ukr.net")
                .address("Kiev, Ukraine")
                .description("friend").build();

        int contactCountBeforeCreation = app.helperContacts().getNumberOfContacts();

        app.helperContacts().addNewContact();
        app.helperContacts().fillContactForm(contacts);
        app.helperContacts().save();

        int contactCountAfterCreation = app.helperContacts().getNumberOfContacts();

        Assert.assertEquals(contactCountAfterCreation, contactCountBeforeCreation + 1);


    }



}
