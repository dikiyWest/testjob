package kz.uco.ruslan.testjob.screen.contact;

import io.jmix.core.Messages;
import io.jmix.ui.component.*;
import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.app.ContactService;
import kz.uco.ruslan.testjob.entity.Contact;
import kz.uco.ruslan.testjob.entity.TypeContact;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Contact.edit")
@UiDescriptor("contact-edit.xml")
@EditedEntityContainer("contactDc")
public class ContactEdit extends StandardEditor<Contact> {
    @Autowired
    private ComboBox<TypeContact> typeContactField;
    @Autowired
    private MaskedField<String> phoneField;
    @Autowired
    private TextField<String> valueField;
    @Autowired
    private TextField<String> emailField;
    @Autowired
    private ContactService contactService;
    @Autowired
    private Messages messages;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        replaceFieldByContactType();
    }

    @Subscribe("typeContactField")
    public void onTypeContactFieldValueChange(HasValue.ValueChangeEvent<TypeContact> event) {
        replaceFieldByContactType();
    }

    //Создается 2 поля (emailField,phoneField). По результату typeContactField внедряется определенное поле.
    private void replaceFieldByContactType() {
        if (typeContactField.getValue() != null) {
            switch (typeContactField.getValue()) {
                case EMAIL -> {
                    phoneField.setVisible(false);
                    emailField.setVisible(true);
                    //при редактировании. Внедрить значение во временное поле
                    setValueOnTheParamField(emailField, valueField);
                }
                case PHONE -> {
                    emailField.setVisible(false);
                    phoneField.setVisible(true);
                    //при редактировании. Внедрить значение во временное поле
                    setValueOnTheParamField(phoneField, valueField);
                }
            }
        } else typeContactField.setValue(TypeContact.EMAIL);
    }

    private void setValueOnTheParamField(TextInputField<String> setField, TextInputField<String> valueField) {
        if (valueField.getValue() != null) {
            setField.setValue(valueField.getValue());
        }
    }

    @Subscribe
    public void onValidation(ValidationEvent event) {
        String message;
        if (typeContactField.getValue().equals(TypeContact.PHONE)) {
            setValueOnTheParamField(valueField, phoneField);
            message = messages.getMessage("kz.uco.ruslan.testjob.screen.contact/contactEdit.invalidphone");
        } else {
            setValueOnTheParamField(valueField, emailField);
            message = messages.getMessage("kz.uco.ruslan.testjob.screen.contact/contactEdit.invalidEmail");
        }

        if (contactService.isValidated(valueField.getValue())) {
            ValidationErrors errors = new ValidationErrors();
            errors.add(valueField, message);
            event.addErrors(errors);
        }
    }

}