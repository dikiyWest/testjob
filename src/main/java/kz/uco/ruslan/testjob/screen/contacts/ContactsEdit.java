package kz.uco.ruslan.testjob.screen.contacts;

import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.entity.Contacts;

@UiController("Contacts.edit")
@UiDescriptor("contacts-edit.xml")
@EditedEntityContainer("contactsDc")
public class ContactsEdit extends StandardEditor<Contacts> {
}