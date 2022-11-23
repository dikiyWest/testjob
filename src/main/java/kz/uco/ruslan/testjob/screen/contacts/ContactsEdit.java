package kz.uco.ruslan.testjob.screen.contacts;

import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.entity.Contacts;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Contacts.edit")
@UiDescriptor("contacts-edit.xml")
@EditedEntityContainer("contactsDc")
public class ContactsEdit extends StandardEditor<Contacts> {
    @Autowired
    private InstanceContainer<Contacts> contactsDc;


}