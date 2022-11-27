package kz.uco.ruslan.testjob.screen.account;

import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.app.ContactService;
import kz.uco.ruslan.testjob.app.OrderOptions;
import kz.uco.ruslan.testjob.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Account.browse")
@UiDescriptor("account-browse.xml")
@LookupComponent("accountsTable")
public class AccountBrowse extends StandardLookup<Account> {
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private Button orderBtn;

    private Account account;

    @Autowired
    private ContactService contactService;

    @Install(to = "accountsTable.photo", subject = "columnGenerator")
    private Component accountsTablePhotoColumnGenerator(Account account) {
        if (account.getPhoto() != null) {
            Image image = uiComponents.create(Image.class);
            image.setScaleMode(Image.ScaleMode.CONTAIN);
            image.setSource(FileStorageResource.class)
                    .setFileReference(account.getPhoto());
            image.setWidth("30px");
            image.setHeight("30px");
            return image;
        } else {
            return null;
        }
    }

    @Install(to = "accountsTable.contactsMapedValueCollect", subject = "columnGenerator")
    private Component accountsTableContactsMapedValueCollectColumnGenerator(Account account) {
        Label label = uiComponents.create(Label.class);
        if (!account.getContacts().isEmpty())
            label.setValue(contactService.getCollectContactsByAccount(account));
        return label;
    }

    @Subscribe("accountsTable")
    public void onAccountsTableSelection(Table.SelectionEvent<Account> event) {
        if (event.getSelected().isEmpty()) {
            orderBtn.setEnabled(false);
        } else {
            account = event.getSelected().stream().findFirst().get();
            orderBtn.setEnabled(true);
        }
    }

    @Subscribe("orderBtn")
    public void onOrderBtnClick(Button.ClickEvent event) {
        String orderScreenId = "AccountToOrder_.browse";
        screenBuilders.screen(this)
                .withScreenId(orderScreenId)
                .withOptions(new OrderOptions(account))
                .build()
                .show();
    }
}