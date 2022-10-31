import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContact extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void precondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("Nik@gmail.com").withPassword("123589$Nik"));
        }
 //       app.getContacts().pause(2000);
//        if (app.getContacts().isNotContact()) {
//            app.getContacts().addContacts();
//        }
        app.getContacts().providerOfContacts();
    }

   @Test(priority = 1,groups = {"smoke"})
    public void removeFirstContact() {
//        hw
//        logger.info("User login with data: email Nik@gmail.com & password 123589$Nik");
//        int contactCountBeforeRemove = app.getContacts().getNumberOfContacts();
//        app.getContacts().removeContactHW();
//        app.getContacts().pause(2000);
//        int contactCountAfterRemove = app.getContacts().getNumberOfContacts();
//
//        Assert.assertEquals(contactCountAfterRemove, contactCountBeforeRemove - 1);
//        logger.info("User remove first contact");
         Assert.assertEquals(app.getContacts().removeOneContact(),1);
    }

    @Test(priority = 2)
    public void removeAllContact() {

//        hw
//        app.getContacts().removeAllContactHW();
//
//        Assert.assertEquals(app.getContacts().getMessage(), "No Contacts here!");
//        logger.info("User remove all contacts");
        app.getContacts().removeAllContacts();
        Assert.assertTrue(app.getContacts().isNoContactHere());
    }
}
