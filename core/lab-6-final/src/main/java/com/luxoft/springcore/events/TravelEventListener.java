package com.luxoft.springcore.events;

import com.luxoft.springcore.objects.City;
import com.luxoft.springcore.objects.UsualPerson;
import com.luxoft.springcore.travel.Connection;
import com.luxoft.springcore.travel.TravellingOpportunities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("TravelEventListener")
public class TravelEventListener {

    private TravellingOpportunities travellingOpportunities;

    @Autowired
    public TravelEventListener(TravellingOpportunities travellingOpportunities) {
        this.travellingOpportunities = travellingOpportunities;
    }

    @EventListener
    public void arrivalToDestination(TravelEvent travelEvent) {
        UsualPerson person = (UsualPerson) travelEvent.getPerson();
        City source = person.getCity();
        City destination = travelEvent.getTravellingDestination();
        int distance = getDistance(source, destination);
        person.setCity(destination);
        person.setDistanceTravelled(person.getDistanceTravelled() + distance);
    }

    protected int getDistance(City source, City destination) {
        return travellingOpportunities.getConnectionsList().stream()
                .filter(connection -> source.equals(connection.getSource())
                        && destination.equals(connection.getDestination()))
                .map(Connection::getDistance)
                .findFirst()
                .orElse(0);
    }
}
