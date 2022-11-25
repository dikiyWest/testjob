package kz.uco.ruslan.testjob.screen.account;

import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.core.metamodel.annotation.JmixProperty;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.Screens;
import io.jmix.ui.UiComponents;
import io.jmix.ui.UiProperties;
import io.jmix.ui.component.*;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import kz.uco.ruslan.testjob.app.OrderOptions;
import kz.uco.ruslan.testjob.entity.Account;
import kz.uco.ruslan.testjob.entity.Contact;
import kz.uco.ruslan.testjob.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@UiController("Account.browse")
@UiDescriptor("account-browse.xml")
@LookupComponent("accountsTable")
public class AccountBrowse extends StandardLookup<Account> {
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private CollectionLoader<Account> accountsDl;
    @Autowired
    private Screens screens;
    @Autowired
    private ScreenBuilders screenBuilders;

    @Autowired
    private Button orderBtn;
    private Account account;


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
        String orderScreenId = "Account.Order_.browse";
        screenBuilders.screen(this)
                .withScreenId(orderScreenId)
                .withOptions(new OrderOptions(account))
                .build()
                .show();
    }


}