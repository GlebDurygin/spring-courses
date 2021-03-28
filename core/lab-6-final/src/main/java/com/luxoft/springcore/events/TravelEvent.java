package com.luxoft.springcore.events;

import com.luxoft.springcore.objects.City;
import com.luxoft.springcore.objects.Person;
import org.springframework.context.ApplicationEvent;

public class TravelEvent extends ApplicationEvent {

    private static final long serialVersionUID = -4094227952486126831L;

    private final City travellingDestination;

    public TravelEvent(Person person, City travellingDestination) {
        super(person);
        this.travellingDestination = travellingDestination;
    }

    public Person getPerson() {
        return (Person) getSource();
    }

    public City getTravellingDestination() {
        return travellingDestination;
    }
}
