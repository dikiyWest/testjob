package kz.uco.ruslan.testjob.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "CONTACTS", indexes = {
        @Index(name = "IDX_CONTACTS_ACCOUNT", columnList = "ACCOUNT_ID")
})
@Entity
public class Contacts {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "TYPE_CONTACT")
    private String typeContact;

    @Column(name = "VALUE_")
    private String value;
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "ACCOUNT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TypeContact getTypeContact() {
        return typeContact == null ? null : TypeContact.fromId(typeContact);
    }

    public void setTypeContact(TypeContact typeContact) {
        this.typeContact = typeContact == null ? null : typeContact.getId();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}