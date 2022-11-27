package kz.uco.ruslan.testjob.app;

import kz.uco.ruslan.testjob.entity.Account;
import kz.uco.ruslan.testjob.entity.Contact;
import kz.uco.ruslan.testjob.entity.TypeContact;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ContactService {
    private final String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private final String regexPhone = "^(\\+\\d||8)(\\s||)\\(\\d{3}\\)(\\s||)\\d{3}\\-\\d{2}\\-\\d{2}";

    public boolean isValidated(String verify, TypeContact typeContact) {
        if (verify != null) {
            Pattern pattern;
            pattern = getPattern(typeContact);
            Matcher matcher = getMatcher(pattern, verify);
            return !matcher.find();
        }else return true;
    }

    private Pattern getPattern(TypeContact typeContact) {
        Pattern pattern = null;
        if (typeContact.equals(TypeContact.EMAIL))
            pattern = Pattern.compile(regexEmail);
        else if(typeContact.equals(TypeContact.PHONE))
            pattern = Pattern.compile(regexPhone);
        return pattern;
    }

    private Matcher getMatcher(Pattern pattern, String verefy) {
        return pattern.matcher(Objects.requireNonNull(verefy));
    }

    public String getCollectContactsByAccount(Account account) {
        return account.getContacts().stream()
                .map(Contact::getValue)
                .collect(Collectors.joining(", "));
    }
}