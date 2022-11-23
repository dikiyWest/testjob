package kz.uco.ruslan.testjob.entity;

import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsService {
    @Autowired
    private DataManager dataManager;

    public List<Contacts> getContactsByAccount(Account account) {
        return dataManager.load(Contacts.class)
                .query(
                        "select c from Contacts c where c.account =:account"
                )
                .parameter("account", account)
                .list();
    }
}