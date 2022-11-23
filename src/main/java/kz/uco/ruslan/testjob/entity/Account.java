package kz.uco.ruslan.testjob.entity;

import io.jmix.core.FileRef;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@JmixEntity
@Table(name = "ACCOUNT")
@Entity
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "PHOTO", length = 1024)
    private FileRef photo;

    @InstanceName
    @Column(name = "FIRST_NAME", nullable = false)
    @NotNull
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    @NotNull
    private String lastName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Composition
    @OneToMany(mappedBy = "account")
    private List<Contacts> contacts;

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
    }


    public List<Contacts> getContacts() {
        return contacts;
    }

    public FileRef getPhoto() {
        return photo;
    }

    public void setPhoto(FileRef photo) {
        this.photo = photo;
    }

    @JmixProperty
    @DependsOnProperties({"lastName", "firstName", "middleName"})
    public String getFullName() {
        return String.format("%s %s %s", lastName, firstName, (middleName != null ? middleName : "")).trim();
    }

    @JmixProperty
    public String getContactsMapedValueCollect() {
        if (contacts != null)
            return contacts.stream()
                    .map(Contacts::getValue)
                    .collect(Collectors.joining(", "));
        return "";
    }


    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}