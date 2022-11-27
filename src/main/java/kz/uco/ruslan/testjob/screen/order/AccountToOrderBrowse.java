package kz.uco.ruslan.testjob.screen.order;

import io.jmix.core.LoadContext;
import io.jmix.ui.component.TextField;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionPropertyContainer;
import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.app.OrderOptions;
import kz.uco.ruslan.testjob.entity.Account;
import kz.uco.ruslan.testjob.entity.Order;
import kz.uco.ruslan.testjob.app.OrderService;
import kz.uco.ruslan.testjob.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@UiController("AccountToOrder_.browse")
@UiDescriptor("from-account-to-order-browse.xml")
@LookupComponent("table")
public class AccountToOrderBrowse extends MasterDetailScreen<Order> {
    private Account account;

    @Autowired
    private TextField<Double> amountField;

    @Autowired
    private CollectionPropertyContainer<Product> productsDc;

    @Autowired
    private OrderService orderService;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Order> event) {
        event.getEntity().setDate(LocalDateTime.now());
        event.getEntity().setAccount(account);
    }

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
        if (account == null)
            return orderService.getAllOrderList();
        return orderService.getOrderListByAccount(account);
    }


    @Subscribe(id = "productsDc", target = Target.DATA_CONTAINER)
    public void onProductsDcCollectionChange(CollectionContainer.CollectionChangeEvent<Product> event) {
        amountField.setValue(orderService.getSumProduct(productsDc.getItems()));
    }


}