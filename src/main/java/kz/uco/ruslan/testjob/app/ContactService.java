package kz.uco.ruslan.testjob.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ContactService {
    private final String regexEmail;
    private final String regexPhone;

    public ContactService(@Value("${contact.pattern.email}") String regexEmail,
                          @Value("${contact.pattern.phone}") String regexPhone) {
        this.regexEmail = regexEmail;
        this.regexPhone = regexPhone;
    }

    public boolean isValidated(String verefy) {
        Pattern pattern;
        pattern = getPattern(verefy);
        Matcher matcher = getMatcher(pattern, verefy);
        return matcher.find();
    }

    private Pattern getPattern(String verefy) {
        Pattern pattern;
        if (verefy.contains("@"))
            pattern = Pattern.compile(regexEmail);
        else
            pattern = Pattern.compile(regexPhone);
        return pattern;
    }

    private Matcher getMatcher(Pattern pattern, String verefy) {
        return pattern.matcher(Objects.requireNonNull(verefy));
    }

}