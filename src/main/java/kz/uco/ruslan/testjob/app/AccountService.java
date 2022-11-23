package kz.uco.ruslan.testjob.app;

import io.jmix.core.DataManager;
import kz.uco.ruslan.testjob.entity.Account;
import kz.uco.ruslan.testjob.entity.Contacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private DataManager dataManager;


    public String getContactsMapedValueCollect(@NotNull List<Contacts> contacts){
        return contacts.stream()
                .map(Contacts::getValue)
                .collect(Collectors.joining(", "));
    }
}