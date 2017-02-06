import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by Rakshith on 2/5/2017.
 */
public class ScheduleTest {

    @Test
    public void scheduleConstructorTest(){
        Time t1 = new Time(3,45,true);
        Course c1 = new Course("EGR 222",4, EnumSet.of(Weekday.FRIDAY,Weekday.THURSDAY),t1,50);
        Schedule schedule = new Schedule();
        schedule.add(c1);

        Assert.assertEquals("Course not Added!", 4,schedule.totalCredits());
    }


    @Test
    public void scheduleConstructorNegativeTest(){
        Time t1 = new Time(2,45,true);
        Course c1 = new Course("ECR 222",3, EnumSet.of(Weekday.TUESDAY,Weekday.THURSDAY),t1,65);
        Schedule schedule = new Schedule();
        schedule.add(c1);

        Assert.assertFalse(schedule.totalCredits()==5);
    }

    @Test
    public void AddTest(){

        Time t1 = new Time(6,45,false);
        Time t2 = new Time(8,45,true);
        Course c1 = new Course("WEB 222",3, EnumSet.of(Weekday.MONDAY,Weekday.TUESDAY),t1,610);
        Course c2 = new Course("DESIGN 212",2, EnumSet.of(Weekday.FRIDAY,Weekday.THURSDAY),t2,10);
        Schedule schedule = new Schedule();
        schedule.add(c1);
        schedule.add(c2);

        Assert.assertFalse(schedule.getCourse(Weekday.MONDAY,new Time(6,45,false)).
                equals(schedule.getCourse(Weekday.FRIDAY,new Time(3,45,false))));
    }


}
