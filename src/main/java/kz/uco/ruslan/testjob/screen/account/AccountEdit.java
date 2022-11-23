package kz.uco.ruslan.testjob.screen.account;

import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.component.Button;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.app.AccountService;
import kz.uco.ruslan.testjob.entity.Account;
import kz.uco.ruslan.testjob.entity.Contacts;
import kz.uco.ruslan.testjob.entity.ContactsService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@UiController("Account.edit")
@UiDescriptor("account-edit.xml")
@EditedEntityContainer("accountDc")
public class AccountEdit extends StandardEditor<Account> {
    @Autowired
    protected DataManager dataManager;
    @Autowired
    private InstanceContainer<Account> accountDc;
    @Autowired
    private ContactsService contactsService;
    @Autowired
    private CollectionLoader<Contacts> contactsesDl;
    @Autowired
    private ScreenBuilders screenBuilders;

    @Install(to = "contactsesDl", target = Target.DATA_LOADER)
    private List<Contacts> contactsesDlLoadDelegate(LoadContext<Contacts> loadContext) {
        if(accountDc.getItem().getContacts() == null)
             accountDc.getItem().setContacts(new ArrayList<>());
        return accountDc.getItem().getContacts();
    }



    @Subscribe("openCreateEditBtn")
    public void onOpenCreateEditBtnClick(Button.ClickEvent event) {
        Contacts contact = dataManager.create(Contacts.class);
        System.out.println(accountDc.getItem());

        Screen screen = screenBuilders.editor(Contacts.class, this)
                .editEntity(contact)
                .build();

        List<Contacts> contacts = accountDc.getItem().getContacts();
        contacts.add(contact);
        System.out.println(accountDc.getItem());


        screen.addAfterCloseListener(afterCloseEvent -> {
            if (afterCloseEvent.closedWith(StandardOutcome.COMMIT)) {
                contactsesDl.load();
            }
        });

        screen.show();
    }



}