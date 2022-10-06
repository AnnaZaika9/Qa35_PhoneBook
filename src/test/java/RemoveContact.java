import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContact extends TestBase {

    @BeforeMethod
    public void precondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("Nik@gmail.com").withPassword("123589$Nik"));
        }
        app.getContacts().pause(2000);
        if (app.getContacts().isNotContact()) {
            app.getContacts().addContacts();
        }
    }

    @Test
    public void removeFirstContact() {
        logger.info("User login with data: email Nik@gmail.com & password 123589$Nik");
        int contactCountBeforeRemove = app.getContacts().getNumberOfContacts();
        app.getContacts().removeContact();
        app.getContacts().pause(2000);
        int contactCountAfterRemove = app.getContacts().getNumberOfContacts();

        Assert.assertEquals(contactCountAfterRemove, contactCountBeforeRemove - 1);
        logger.info("User remove first contact");


    }

    @Test
    public void removeAllContact() {
        app.getContacts().removeAllContact();

        Assert.assertEquals(app.getContacts().getMessage(), "No Contacts here!");
        logger.info("User remove all contacts");
    }
}
