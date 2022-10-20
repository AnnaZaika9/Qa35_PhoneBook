package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {


    @DataProvider
    public Iterator<Object[]> loginData() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Nik@gmail.com","123589$Nik"});
        list.add(new Object[]{"shu@ukr.net","$987987SHu"});
        list.add(new Object[]{"tanya@mail.ru","Ta12587$nya"});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginDataModel() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("Nik2701@gmail.com").withPassword("123589$Nik")});
        list.add(new Object[]{new User().withEmail("Nik2703@gmail.com").withPassword("123589$Nik")});
        list.add(new Object[]{new User().withEmail("Nik2704@gmail.com").withPassword("123589$Nik")});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> dataloginWrongEmail() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail(null).withPassword("123589$Nik")});
        list.add(new Object[]{new User().withEmail("Nikgmail.com").withPassword("123589$Nik")});
        list.add(new Object[]{new User().withEmail("Nik@gmailcom").withPassword("123589$Nik")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> dataloginWrongEmailError500() {
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{new User().withEmail("@gmail.com").withPassword("123589$Nik")});
        list.add(new Object[]{new User().withEmail("k@gmail.com").withPassword("123589$Nik")});
        list.add(new Object[]{new User().withEmail("Ник@gmail.com").withPassword("123589$Nik")});
        list.add(new Object[]{new User().withEmail(" Nik@gmail.com").withPassword("123589$Nik")});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> dataloginWrongPassword() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("Nik@gmail.com").withPassword(null)});
        list.add(new Object[]{new User().withEmail("Nik@gmail.com").withPassword("1235890Nik")});
        list.add(new Object[]{new User().withEmail("Nik@gmail.com").withPassword("123589$nik")});
        list.add(new Object[]{new User().withEmail("Nik@gmail.com").withPassword("123589$NIK")});
        list.add(new Object[]{new User().withEmail("Nik@gmail.com").withPassword("$Nikiknik")});
        list.add(new Object[]{new User().withEmail("Nik@gmail.com").withPassword("589$Nik")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> dataloginWrongPasswordError500() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("Nik@gmail.com").withPassword("123589$Ник")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> registrationDataValid() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/registrationSuccess.csv"));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[]{new User().withEmail(split[0]).withPassword(split[1])});
            line = reader.readLine();
        }

        return list.iterator();
    }


}
