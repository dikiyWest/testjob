package kz.uco.ruslan.testjob.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

@JmixEntity
public class ContactTemp extends Contact {
    @Override
    public String toString() {
        return "ContactTemp{} " + super.toString();
    }
}