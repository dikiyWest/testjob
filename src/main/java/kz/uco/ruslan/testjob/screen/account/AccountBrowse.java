package kz.uco.ruslan.testjob.screen.account;

import io.jmix.ui.UiComponents;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.FileStorageResource;
import io.jmix.ui.component.Image;
import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Account.browse")
@UiDescriptor("account-browse.xml")
@LookupComponent("accountsTable")
public class AccountBrowse extends StandardLookup<Account> {
    @Autowired
    private UiComponents uiComponents;

    @Install(to = "accountsTable.photo", subject = "columnGenerator")
    private Component accountsTablePhotoColumnGenerator(Account account) {
        if (account.getPhoto() != null) {
            Image image = uiComponents.create(Image.class);
            image.setScaleMode(Image.ScaleMode.CONTAIN);
            image.setSource(FileStorageResource.class)
                    .setFileReference(account.getPhoto());
            image.setWidth("30px");
            image.setHeight("30px");
            return image;
        } else {
            return null;
        }
    }
}