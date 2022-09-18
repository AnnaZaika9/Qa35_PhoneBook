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
    public void loginSuccess() {


        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("Nik@gmail.com", "123589$Nik");
        app.getHelperUser().submitLogin();

    }


    @Test
    public void loginNegativeWrongEmailFormat() {

    }

    @Test
    public void loginNegativeWrongPasswordFormat() {


    }
}
