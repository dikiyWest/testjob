package kz.uco.ruslan.testjob.app;

import io.jmix.ui.screen.ScreenOptions;
import kz.uco.ruslan.testjob.entity.Account;


public class OrderOptions implements ScreenOptions {
    private Account account;

    public OrderOptions(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}