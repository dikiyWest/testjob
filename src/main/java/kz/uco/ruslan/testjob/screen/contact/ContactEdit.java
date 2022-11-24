package kz.uco.ruslan.testjob.screen.contact;

import io.jmix.ui.component.*;
import io.jmix.ui.component.validation.EmailValidator;
import io.jmix.ui.component.validation.RegexpValidator;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.entity.Contact;
import kz.uco.ruslan.testjob.entity.TypeContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UiController("Contact.edit")
@UiDescriptor("contact-edit.xml")
@EditedEntityContainer("contactDc")
public class ContactEdit extends StandardEditor<Contact> {
    @Autowired
    private InstanceContainer<Contact> contactDc;
    @Autowired
    private ComboBox<TypeContact> typeContactField;
    @Autowired
    private TextField<String> valueField;

    private final String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private final String regexPhone = "^(\\+\\d||8)(\\s||)\\(\\d{3}\\)(\\s||)\\d{3}\\-\\d{2}\\-\\d{2}";



    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        contactDc.getItem().setTypeContact(TypeContact.EMAIL);
    }
    


    
    
    
    @Subscribe("typeContactField")
    public void onTypeContactFieldValueChange(HasValue.ValueChangeEvent<TypeContact> event) {
            if (contactDc.getItem().getAccount() == null) {
                switch (Objects.requireNonNull(typeContactField.getValue())) {
                    case EMAIL ->  valueField.setValue("test@uco.kz");
                    case PHONE ->  valueField.setValue("+7 (701) 111-11-11");
            }
        }
    }


    @Subscribe
    public void onValidation(ValidationEvent event) {
        Pattern patternEmail = Pattern.compile(regexEmail);
        Pattern patternPhone = Pattern.compile(regexPhone);
        Matcher matcher = getMatcher(patternEmail);

        if (typeContactField.getValue() != null) {

            if (typeContactField.getValue().equals(TypeContact.PHONE))
                matcher = getMatcher(patternPhone);

            if (!matcher.find()) {
                ValidationErrors errors = new ValidationErrors();
                errors.add(valueField, "Invalid name format");
                event.addErrors(errors);
            }

        }
    }


    private Matcher getMatcher(Pattern pattern) {
        return pattern.matcher(Objects.requireNonNull(valueField.getValue()));
    }


}