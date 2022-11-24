package kz.uco.ruslan.testjob.screen.contacttemp;

import io.jmix.core.LoadContext;
import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.entity.ContactTemp;

import java.util.Collections;
import java.util.List;

@UiController("ContactTemp.browse")
@UiDescriptor("contact-temp-browse.xml")
@LookupComponent("contactTempsTable")
public class ContactTempBrowse extends StandardLookup<ContactTemp> {

    @Install(to = "contactTempsDl", target = Target.DATA_LOADER)
    private List<ContactTemp> contactTempsDlLoadDelegate(LoadContext<ContactTemp> loadContext) {
        // Here you can load entities from an external store
        return Collections.emptyList();
    }
}