package kz.uco.ruslan.testjob.app;

import io.jmix.core.DataManager;
import kz.uco.ruslan.testjob.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private DataManager dataManager;

    public String getContactsToBrowser(){
        dataManager.load(Account.class).id().fetchPlan("")

    }
}