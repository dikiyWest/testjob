package kz.uco.ruslan.testjob.screen.order;

import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.component.*;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionPropertyContainer;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.app.OrderOptions;
import kz.uco.ruslan.testjob.entity.Account;
import kz.uco.ruslan.testjob.entity.Order;
import kz.uco.ruslan.testjob.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@UiController("Order_.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
public class OrderEdit extends StandardEditor<Order> {
    @Autowired
    private InstanceContainer<Order> orderDc;

    @Autowired
    private CollectionPropertyContainer<Product> productsDc;

    @Autowired
    private TextField<Double> amountField;

    @Autowired
    private DateField<LocalDateTime> dateField;

    private Account account;

    @Autowired
    private EntityPicker<Account> accountField;


    @Subscribe
    public void onInit(InitEvent event) {
        ScreenOptions options = event.getOptions();
        if (options instanceof OrderOptions) {
            account = ((OrderOptions) options).getAccount();
        }
    }


    @Subscribe
    public void onInitEntity(InitEntityEvent<Order> event) {
        if (account != null) {
            accountField.setValue(account);
            accountField.setEnabled(false);
        }

        event.getEntity().setDate(LocalDateTime.now());
    }


    @Subscribe(id = "productsDc", target = Target.DATA_CONTAINER)
    public void onProductsDcCollectionChange(CollectionContainer.CollectionChangeEvent<Product> event) {
        double sum = productsDc.getItems().stream()
                .map(m -> m.getPrice() * m.getQuantity())
                .mapToDouble(Double::doubleValue)
                .sum();
        amountField.setValue(sum);
    }

    @Subscribe("accountField")
    public void onAccountFieldValueChange(HasValue.ValueChangeEvent<Account> event) {
        accountField.setValue(account);
    }


}

