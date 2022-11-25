package kz.uco.ruslan.testjob.screen.order;

import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.entity.Order;

@UiController("Order_.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
public class OrderBrowse extends StandardLookup<Order> {
}