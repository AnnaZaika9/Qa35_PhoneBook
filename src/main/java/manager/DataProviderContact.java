package manager;

import models.Contacts;
import models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {

    @DataProvider
    public Iterator<Object[]> contactsDataValid() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contactsValidData.csv"));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(",");
            list.add(new Object[]{
                    Contacts.builder()
                            .name(split[0])
                            .lastName(split[1])
                            .phone(split[2])
                            .email(split[3])
                            .address(split[4])
                            .description(split[5])
                            .build()
            });
            line= reader.readLine();
        }

        return list.iterator();
    }

}
