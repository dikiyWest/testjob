package kz.uco.ruslan.testjob.screen.account;

import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.entity.Account;

@UiController("Account.browse")
@UiDescriptor("account-browse.xml")
@LookupComponent("accountsTable")
public class AccountBrowse extends StandardLookup<Account> {
}