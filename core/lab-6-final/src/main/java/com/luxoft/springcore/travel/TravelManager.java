package com.luxoft.springcore.travel;

import com.luxoft.springcore.events.TravelEvent;
import com.luxoft.springcore.objects.City;
import com.luxoft.springcore.objects.Person;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class TravelManager implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void travel(Person person, City destination) {
        applicationContext.publishEvent(new TravelEvent(person, destination));
    }
}
