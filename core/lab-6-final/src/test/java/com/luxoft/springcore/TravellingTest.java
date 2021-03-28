package com.luxoft.springcore;

import com.luxoft.springcore.objects.City;
import com.luxoft.springcore.objects.Operators;
import com.luxoft.springcore.objects.UsualPerson;
import com.luxoft.springcore.travel.TravelManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class TravellingTest {

    @Autowired
    private City moscow;
    @Autowired
    private City warsaw;
    @Autowired
    private City krakow;
    @Autowired
    private City sofia;
    @Autowired
    private City vienna;

    @Autowired
    private UsualPerson russian;
    @Autowired
    private UsualPerson bulgarian;

    @Autowired
    private ObjectProvider<Operators> operatorsObjectProvider;
    @Autowired
    private TravelManager travelManager;

    @Test
    public void testInitPersons() {
        UsualPerson expectedRussianPerson = new UsualPerson("Viktor Ponedelnik", 35, moscow);
        assertEquals(expectedRussianPerson, russian);

        UsualPerson expectedBulgarianPerson = new UsualPerson("Emil Kostadinov", 37, sofia);
        assertEquals(expectedBulgarianPerson, bulgarian);

        Operators operators = operatorsObjectProvider.getObject();
        assertTrue(operators.isAgeComparisonTest());
    }

    @Test
    public void testPersonsTravelling() {
        travelManager.travel(russian, warsaw);
        travelManager.travel(russian, krakow);
        assertEquals(russian.getDistanceTravelled(), 1386);

        travelManager.travel(bulgarian, vienna);
        travelManager.travel(bulgarian, krakow);
        assertEquals(bulgarian.getDistanceTravelled(), 1166);

        Operators operators = operatorsObjectProvider.getObject();
        assertTrue(operators.isDistanceComparisonTest());
    }
}
