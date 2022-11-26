package kz.uco.ruslan.testjob.screen.contact;

import io.jmix.ui.component.*;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.entity.Contact;
import kz.uco.ruslan.testjob.entity.TypeContact;
import org.springframework.beans.factory.annotation.Autowired;

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
    private MaskedField<String> phoneField;
    @Autowired
    private TextField<String> valueField;
    @Autowired
    private TextField<String>  emailField;


    private final String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private final String regexPhone = "^(\\+\\d||8)(\\s||)\\(\\d{3}\\)(\\s||)\\d{3}\\-\\d{2}\\-\\d{2}";


    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        extracted();
    }


    @Subscribe("typeContactField")
    public void onTypeContactFieldValueChange(HasValue.ValueChangeEvent<TypeContact> event) {
        extracted();
    }

    private void extracted() {
        if (typeContactField.getValue() != null) {
            switch (typeContactField.getValue()) {
                case EMAIL -> {
                    phoneField.setVisible(false);
                    emailField.setVisible(true);
                    setValueOnTheField(emailField);
                }
                case PHONE -> {
                    emailField.setVisible(false);
                    phoneField.setVisible(true);
                    setValueOnTheField(phoneField);
                }
            }
        } else typeContactField.setValue(TypeContact.EMAIL);
    }

    private void setValueOnTheField(TextInputField<String> field) {
        if(valueField.getValue() !=null){
            field.setValue(valueField.getValue());
        }
    }


    @Subscribe
    public void onValidation(ValidationEvent event) {
        Pattern patternEmail = Pattern.compile(regexEmail);
        Pattern patternPhone = Pattern.compile(regexPhone);
        Matcher matcher;
        String message;
        if (typeContactField.getValue() != null) {
            if (typeContactField.getValue().equals(TypeContact.PHONE)) {
                valueField.setValue(phoneField.getValue());
                matcher = getMatcher(patternPhone);
                message = "invalid phone number";
            } else {
                valueField.setValue(emailField.getValue());
                matcher = getMatcher(patternEmail);
                message = "invalid email format";
            }

            if (!matcher.find()) {
                ValidationErrors errors = new ValidationErrors();
                errors.add(valueField, message);
                event.addErrors(errors);
            }

        }
    }


    private Matcher getMatcher(Pattern pattern) {
        return pattern.matcher(Objects.requireNonNull(valueField.getValue()));
    }


}