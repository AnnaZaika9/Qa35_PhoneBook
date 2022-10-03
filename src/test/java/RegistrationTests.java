import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {


    @BeforeMethod
    public void precondition() {
        if (app.getHelperUser().isLogged())
            app.getHelperUser().logout();
    }

    @Test
    public void registrationSuccess(){//hw

        int i = (int)(System.currentTimeMillis()/1000)%3600;

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("Nik"+i +"@gmail.com", "123589$Nik");
        app.getHelperUser().submitRegistration();
        app.getHelperUser().pause(2000);
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

   @Test
    public void registrationSuccessModels(){

        int i = (int)(System.currentTimeMillis()/1000)%3600;

        User user = new User().withEmail("Nik"+i +"@gmail.com").withPassword("123589$Nik");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
       // app.getHelperUser().pause(2000);
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
    }

    @Test
    public void registrationWrongEmail(){
        // @ . null ru hew
        User user = new User().withEmail("Nikgmail.com").withPassword("123589$Nik");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isAlertWithErrorPresent("Wrong email or password format"));
      //  Assert.assertTrue(app.getHelperUser().isAlertError());//hw
    }

    @Test
    public void registrationWrongPassword(){
        User user = new User().withEmail("Nim@gmail.com").withPassword("nik");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isAlertWithErrorPresent("Wrong email or password format"));
       // Assert.assertTrue(app.getHelperUser().isAlertError());//hw

    }


    @Test
    public void registrationUserAlreadyExists() {

        User user = new User().withEmail("noa@mail.com").withPassword("Nnoa12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isAlertWithErrorPresent("User already exist"));
    }


}
