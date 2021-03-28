package com.luxoft.springcore.objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("operators")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Operators {

    @Value("#{russian.age < bulgarian.age}")
    private boolean ageComparisonTest;

    @Value("#{russian.distanceTravelled > bulgarian.distanceTravelled}")
    private boolean distanceComparisonTest;

    public boolean isAgeComparisonTest() {
        return ageComparisonTest;
    }

    public void setAgeComparisonTest(boolean ageComparisonTest) {
        this.ageComparisonTest = ageComparisonTest;
    }

    public boolean isDistanceComparisonTest() {
        return distanceComparisonTest;
    }

    public void setDistanceComparisonTest(boolean distanceComparisonTest) {
        this.distanceComparisonTest = distanceComparisonTest;
    }
}
