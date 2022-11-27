package kz.uco.ruslan.testjob.app;

import io.jmix.core.DataManager;
import kz.uco.ruslan.testjob.entity.Account;
import kz.uco.ruslan.testjob.entity.Order;
import kz.uco.ruslan.testjob.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private DataManager dataManager;

    public Double getSumProduct(List<Product> productList){
       return productList.stream()
                .map(m -> m.getPrice() * m.getQuantity())
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public List<Order> getAllOrderList() {
        return dataManager.load(Order.class).all().list();
    }

    public List<Order> getOrderListByAccount(Account account) {
        return dataManager.load(Order.class)
                .query("select o from Order_ o where o.account = :account")
                .parameter("account", account)
                .list();
    }
}