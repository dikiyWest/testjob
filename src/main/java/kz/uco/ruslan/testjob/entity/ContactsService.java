package kz.uco.ruslan.testjob.entity;

import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsService {
    @Autowired
    private DataManager dataManager;

    public List<Contact> getContactsByAccount(Account account) {
        return dataManager.load(Contact.class)
                .query(
                        "select c from Contact c where c.account =:account"
                )
                .parameter("account", account)
                .list();
    }
}