import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }

    }


    @Test
    public void loginSuccessModel() {
//        User user = new User();
//        user.setEmail("noa");
//        user.setPassword("Nnoa");
        User user = new User().withEmail("Nik@gmail.com").withPassword("123589$Nik");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
       // app.getHelperUser().pause(2000);
        Assert.assertTrue(app.getHelperUser().isLogged());

    }
    @Test
    public void loginSuccess() {

        logger.info("Test start with name ----> loginSuccess");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("Nik@gmail.com", "123589$Nik");
        logger.info("User login with data: email Nik@gmail.com & password 123589$Nik ");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert passed");


    }


    @Test
    public void loginNegativeWrongEmailFormat() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(new User().withEmail("Nikgmail.com").withPassword("123589$Nik"));
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
        Assert.assertTrue(app.getHelperUser().isErrorWrongFormat());

    }

    @Test
    public void loginNegativeWrongPasswordFormat() {


    }
}
