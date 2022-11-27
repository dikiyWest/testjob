package kz.uco.ruslan.testjob.entity;

import io.jmix.core.FileRef;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private List<Contact> contacts;

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        if (contacts == null)
            contacts = new ArrayList<>();
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
    @Transient
    private String contactsMapedValueCollect;

    public String getContactsMapedValueCollect() {
        return contactsMapedValueCollect;
    }

    public void setContactsMapedValueCollect(String contactsMapedValueCollect) {
        this.contactsMapedValueCollect = contactsMapedValueCollect;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    @Override
    public String toString() {
        return "Account{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}