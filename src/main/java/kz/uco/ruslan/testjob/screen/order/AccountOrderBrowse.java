package kz.uco.ruslan.testjob.screen.order;

import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.Button;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.app.OrderOptions;
import kz.uco.ruslan.testjob.entity.Account;
import kz.uco.ruslan.testjob.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("Account.Order_.browse")
@UiDescriptor("account-order-browse.xml")
@LookupComponent("table")
public class AccountOrderBrowse extends MasterDetailScreen<Order> {

    @Autowired
    private CollectionLoader<Order> ordersDl;

    private Account account;

    @Autowired
    private DataManager dataManager;
    @Autowired
    private ScreenBuilders screenBuilders;

    @Subscribe
    public void onInit(InitEvent event) {
        ScreenOptions options = event.getOptions();
        if (options instanceof OrderOptions) {
            Account account = ((OrderOptions) options).getAccount();
            if (account != null)
                this.account = account;
        }
    }

    @Install(to = "ordersDl", target = Target.DATA_LOADER)
    private List<Order> ordersDlLoadDelegate(LoadContext<Order> loadContext) {
        if(account == null)
            return dataManager.load(Order.class).all().list();
        return dataManager.load(Order.class)
                .query("select o from Order_ o where o.account = :account")
                .parameter("account",account)
                .list();
    }


    private void screenBuilderWithOption() {
        screenBuilders.editor(Order.class,this)
                .withOptions(new OrderOptions(account))
                .build()
                .show();
    }

    @Subscribe("createBtn")
    public void onCreateBtnClick(Button.ClickEvent event) {
        screenBuilderWithOption();
    }

    @Subscribe("editBtn")
    public void onEditBtnClick(Button.ClickEvent event) {
        screenBuilderWithOption();
    }


}