import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LoginTests extends TestBase {



    @BeforeMethod
    public void precondition() {
        logger.info("Start checking authorization");
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Test was needed in logout ");
        }
        else {
            logger.info("Test was not needed in logout ");
        }
    }


    @Test(dataProvider = "loginDataModel",dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user) {
//        User user = new User();
//        user.setEmail("noa");
//        user.setPassword("Nnoa");
//  User user = new User().withEmail("Nik@gmail.com").withPassword("123589$Nik");

        logger.info("Login scenario success was used data: "+user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());

    }
    @Test(dataProvider = "loginData",dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email,String password) {

        logger.info("Login scenario success was used data email: "+ email + "& password: "+password);

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is Sign Out button Present");

    }

    @Test(dataProvider = "dataloginWrongEmail",dataProviderClass = DataProviderUser.class)
    public void loginNegativeWrongEmailFormat(User user) {
        logger.info("Login negative scenario with wrong email was used data: "+user.toString());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
        Assert.assertTrue(app.getHelperUser().isErrorWrongFormat());

        logger.info("In assert checked error message 'Wrong email or password format' ");
    }
    @Test(dataProvider = "dataloginWrongEmailError500",dataProviderClass = DataProviderUser.class)
    public void loginNegativeWrongEmailFormat2(User user) {
        logger.info("Login negative scenario with wrong email was used data: "+user.toString());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
        Assert.assertTrue(app.getHelperUser().isErrorUndefined());

        logger.info("In assert checked error message 'Wrong email or password format' ");
    }

    @Test(dataProvider = "dataloginWrongPassword",dataProviderClass = DataProviderUser.class)
    public void loginNegativeWrongPasswordFormat(User user) {

        logger.info("Login negative scenario with wrong password was used data: "+user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
        Assert.assertTrue(app.getHelperUser().isErrorWrongFormat());

        logger.info("In assert checked error message 'Wrong email or password format' ");
    }

    @Test(dataProvider = "dataloginWrongPasswordError500",dataProviderClass = DataProviderUser.class)
    public void loginNegativeWrongPasswordFormat2(User user) {

        logger.info("Login negative scenario with wrong password was used data: "+user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
        Assert.assertTrue(app.getHelperUser().isErrorUndefined());

        logger.info("In assert checked error message 'Wrong email or password format' ");
    }
}
