package kz.uco.ruslan.testjob.screen.contact;

import io.jmix.ui.component.ComboBox;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.TextField;
import io.jmix.ui.component.validation.EmailValidator;
import io.jmix.ui.component.validation.RegexpValidator;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.entity.Contact;
import kz.uco.ruslan.testjob.entity.TypeContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

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


    @Autowired
    protected ApplicationContext applicationContext;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {

        typeContactField.setValue(TypeContact.PHONE);
    }

    @Subscribe("typeContactField")
    public void onTypeContactFieldValueChange(HasValue.ValueChangeEvent<TypeContact> event) {
        // System.out.println("onChange" + typeContactField.getValue());

        RegexpValidator regexpValidatorEmail = new RegexpValidator("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        RegexpValidator regexpValidatorPhone = new RegexpValidator("^(\\+\\d||8)(\\s||)\\(\\d{1,3}\\)(\\s||)\\d{1,3}\\-\\d{1,2}\\-\\d{1,2}");


        System.out.println(contactDc.getItem());
        if (typeContactField.getValue() != null)
            switch (typeContactField.getValue()) {
                case EMAIL -> {
                    System.out.println("mail");

                    valueField.getValidators().forEach(System.out::println);
                    valueField.addValidator(regexpValidatorEmail);
                    valueField.setValue("test@uco.kz");
                }
                case PHONE -> {
                    System.out.println("phone");
                    valueField.getValidators().forEach(System.out::println);
                    valueField.addValidator(regexpValidatorPhone);
                    valueField.setValue("+7 (701) 111-11-11");
                }
            }
    }

}