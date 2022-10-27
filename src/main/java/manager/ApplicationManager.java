package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    WebDriver wd;
    HelperUser helperUser;
    HelperContacts helperContacts;
    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init(){

        if(browser.equals(Browser.CHROME.browserName())){
            wd = new ChromeDriver();
            logger.info("All tests start in  ChromeDriver");
        }else if (browser.equals(Browser.FIREFOX.browserName())){
            wd= new FirefoxDriver();
            logger.info("All tests start in  Firefox");
        }else if (browser.equals(Browser.EDGE.browserName())){
            wd= new EdgeDriver();
            logger.info("All tests start in  EdgeDriver");
        }


        WebDriverListener listener = new ListenerWD();
       // wd = new ChromeDriver();
       // logger.info("Chrome Driver was opened");
        wd  = new EventFiringDecorator<>(listener).decorate(wd);
        wd.manage().window().maximize(); //open full screen
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wd.navigate().to("https://contacts-app.tobbymarshall815.vercel.app/");
        logger.info("Current URL is --->" +wd.getCurrentUrl());
        helperUser = new HelperUser(wd);
        helperContacts = new HelperContacts(wd);

    }

    public void stop(){
        wd.quit();
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperContacts getContacts() {
        return helperContacts;
    }
}
