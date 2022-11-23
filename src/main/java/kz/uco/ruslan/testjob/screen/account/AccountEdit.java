package kz.uco.ruslan.testjob.screen.account;

import io.jmix.core.LoadContext;
import io.jmix.ui.component.Button;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.app.AccountService;
import kz.uco.ruslan.testjob.entity.Account;
import kz.uco.ruslan.testjob.entity.Contacts;
import kz.uco.ruslan.testjob.entity.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("Account.edit")
@UiDescriptor("account-edit.xml")
@EditedEntityContainer("accountDc")
public class AccountEdit extends StandardEditor<Account> {
    @Autowired
    private InstanceContainer<Account> accountDc;
    @Autowired
    private ContactsService contactsService;


    @Install(to = "contactsesDl", target = Target.DATA_LOADER)
    private List<Contacts> contactsesDlLoadDelegate(LoadContext<Contacts> loadContext) {
        return contactsService.getContactsByAccount(accountDc.getItem());
    }

    @Subscribe("contactsesTableCreateBtn")
    public void onContactsesTableCreateBtnClick(Button.ClickEvent event) {
        
    }



}