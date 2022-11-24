package kz.uco.ruslan.testjob.screen.contacttemp;

import io.jmix.core.LoadContext;
import io.jmix.core.SaveContext;
import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.entity.ContactTemp;

import java.util.Collections;
import java.util.Set;

@UiController("ContactTemp.edit")
@UiDescriptor("contact-temp-edit.xml")
@EditedEntityContainer("contactTempDc")
public class ContactTempEdit extends StandardEditor<ContactTemp> {

    @Install(to = "contactTempDl", target = Target.DATA_LOADER)
    private ContactTemp contactTempDlLoadDelegate(LoadContext<ContactTemp> loadContext) {
        // Here you can load entity from an external store by ID passed in LoadContext
        return getEditedEntity();
    }

    @Install(target = Target.DATA_CONTEXT)
    private Set<Object> commitDelegate(SaveContext saveContext) {
        // Here you can save the edited entity or the whole SaveContext in an external store.
        // Return the set of saved instances.
        return Collections.singleton(getEditedEntity());
    }
}