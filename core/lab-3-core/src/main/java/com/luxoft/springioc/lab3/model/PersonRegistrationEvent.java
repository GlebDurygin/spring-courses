package com.luxoft.springioc.lab3.model;

import org.springframework.context.ApplicationEvent;

public class PersonRegistrationEvent extends ApplicationEvent {

    private static final long serialVersionUID = 2545745837098118673L;

    public PersonRegistrationEvent(Person person) {
        super(person);
    }

    public Person getPerson() {
        return (Person) getSource();
    }
}
