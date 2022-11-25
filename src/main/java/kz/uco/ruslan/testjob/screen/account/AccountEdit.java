package kz.uco.ruslan.testjob.screen.account;

import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.Screens;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.component.LayoutClickNotifier;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.entity.Account;
import kz.uco.ruslan.testjob.entity.Contact;
import kz.uco.ruslan.testjob.entity.ContactTemp;
import kz.uco.ruslan.testjob.entity.ContactsService;
import kz.uco.ruslan.testjob.screen.contact.ContactEdit;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private CollectionLoader<Contact> contactsesDl;
    @Autowired
    private ScreenBuilders screenBuilders;

    @Autowired
    private GroupTable<Contact> contactsesTable;
    @Autowired
    private CollectionContainer<Contact> contactsesDc;

    @Install(to = "contactsesDl", target = Target.DATA_LOADER)
    private List<Contact> contactsesDlLoadDelegate(LoadContext<Contact> loadContext) {
        contactsesTable.getItems().getItems().forEach(System.out::println);
        return accountDc.getItem().getContacts();
    }

    @Subscribe("openCreateContactBtn")
    public void onOpenCreateContactBtnClick(Button.ClickEvent event) {
        screenBuilders.editor(contactsesTable)
                .newEntity()
                .build()
                .show();
    }

    @Subscribe
    public void onBeforeClose(BeforeCloseEvent event) {

    }

    @Subscribe("commitAndCloseBtn")
    public void onCommitAndCloseBtnClick(Button.ClickEvent event) {
        Account account = accountDc.getItem();
        for (Contact item : contactsesTable.getItems().getItems()) {
            item.setAccount(account);
            accountDc.getItem().getContacts().add(dataManager.save(item));
        }
    }


}