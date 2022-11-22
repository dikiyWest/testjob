package kz.uco.ruslan.testjob.screen.account;

import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.entity.Account;

@UiController("Account.edit")
@UiDescriptor("account-edit.xml")
@EditedEntityContainer("accountDc")
public class AccountEdit extends StandardEditor<Account> {
}