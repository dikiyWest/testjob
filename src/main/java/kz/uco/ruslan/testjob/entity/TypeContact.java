package kz.uco.ruslan.testjob.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum TypeContact implements EnumClass<String> {

    EMAIL("Email"),
    PHONE("Phone");

    private String id;

    TypeContact(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TypeContact fromId(String id) {
        for (TypeContact at : TypeContact.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}