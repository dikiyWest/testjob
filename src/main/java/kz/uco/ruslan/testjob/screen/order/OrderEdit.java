package kz.uco.ruslan.testjob.screen.order;

import io.jmix.ui.component.EntityPicker;
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

@UiController("Order_.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
public class OrderEdit extends StandardEditor<Order> {
    @Autowired
    private CollectionPropertyContainer<Product> productsDc;

    @Autowired
    private TextField<Double> amountField;

    private Account account;

    @Autowired
    private EntityPicker<Account> accountField;

    @Autowired
    private OrderService orderService;

    @Subscribe
    public void onInit(InitEvent event) {
        ScreenOptions options = event.getOptions();
        if (options instanceof OrderOptions) {
            account = ((OrderOptions) options).getAccount();
        }
    }

    @Subscribe
    public void onInitEntity(InitEntityEvent<Order> event) {
        event.getEntity().setDate(LocalDateTime.now());
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if (account != null) {
            accountField.setValue(account);
        }
    }

    @Subscribe(id = "productsDc", target = Target.DATA_CONTAINER)
    public void onProductsDcCollectionChange(CollectionContainer.CollectionChangeEvent<Product> event) {
        amountField.setValue(orderService.getSumProduct(productsDc.getItems()));
    }


}

