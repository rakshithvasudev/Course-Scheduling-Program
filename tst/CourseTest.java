import org.junit.Assert;
import org.junit.Test;

import java.util.EnumSet;

/**
 * Created by Rakshith on 2/1/2017.
 */
public class CourseTest {

    @Test
    public void courseConstructorTest(){
        Time t1 = new Time(2,30,true);
        Course course = new Course("math Prime",3, EnumSet.of(Weekday.FRIDAY,Weekday.WEDNESDAY),t1,20);
        Assert.assertEquals("Constructor Not loaded",3,course.getCredits());
    }

    @Test(expected = IllegalArgumentException.class)
    public void courseConstructorExceptionTest(){
        Time t1 = new Time(2,30,true);
        Course course = new Course(null,3, EnumSet.of(Weekday.FRIDAY,Weekday.WEDNESDAY),t1,20);
        Assert.assertEquals("Constructor Not loaded",3,course.getCredits());
    }

    @Test(expected = IllegalArgumentException.class)
    public void courseConstructorIllegalArgumentExceptionTest(){
        Time t1 = new Time(2,30,true);
        Course course = new Course("Weekly Class",9, EnumSet.of(Weekday.FRIDAY,Weekday.WEDNESDAY),t1,20);
        Assert.assertEquals("Constructor Not loaded",3,course.getCredits());
    }

    @Test
    public void conflictsWithTest(){
        Time t1 = new Time(2,30,true);
        Time t2 = new Time(2,45,true);
        Course course1 = new Course("Engg Research",3,
                EnumSet.of(Weekday.MONDAY,Weekday.WEDNESDAY),t1,30);
        Course course2 = new Course("Engg Core",3,
                EnumSet.of(Weekday.MONDAY,Weekday.WEDNESDAY),t1,45);


        Assert.assertEquals("Conflicts occurred",false,course1.conflictsWith(course2));

    }


    @Test
    public void equalsTest(){
        Time t1 = new Time(2,35,true);
        Time t2 = new Time(2,34,true);
        Course course1 = new Course("Software Engg",3,EnumSet.of(Weekday.MONDAY,Weekday.WEDNESDAY),t1,30);
        Course course2 = new Course("Software Engg",3,EnumSet.of(Weekday.MONDAY,Weekday.WEDNESDAY),t2,50);

        Assert.assertEquals("Equals not true",false,course1.equals(course2));

    }

    @Test
    public void getNameTest(){
        Time t1 = new Time(2,35,true);
        Course course1 = new Course("Software Engg",3,EnumSet.of(Weekday.MONDAY,Weekday.WEDNESDAY),t1,30);
        Assert.assertEquals("Equals not true","Software Engg",course1.getName());
    }


    @Test
    public void getStartTimeTest(){
        Time t1 = new Time(3,35,true);
        Course course1 = new Course("Mech Engg",3,EnumSet.of(Weekday.MONDAY,Weekday.WEDNESDAY),t1,30);
        Assert.assertEquals("Equals not true",t1,course1.getStartTime());
    }

    @Test
    public void getEndTimeTest(){
        Time t1 = new Time(3,40,true);
        t1.shift(30);
        Course course1 = new Course("Arch Engg",3,EnumSet.of(Weekday.MONDAY,Weekday.WEDNESDAY),t1,30);
        Assert.assertEquals("Equals not true",t1,course1.getEndTime());
    }

    @Test
    public void getCreditTest(){
        Time t1 = new Time(5,40,true);
        Course course1 = new Course("Arch Engg",3,EnumSet.of(Weekday.MONDAY,Weekday.WEDNESDAY),t1,30);
        Assert.assertEquals("Equals not true",3,course1.getCredits());
    }

    @Test
    public void CourseShiftTest(){
        Time t1 = new Time(5,40,true);
        Course course1 = new Course("Arch Engg",3,EnumSet.of(Weekday.MONDAY,Weekday.WEDNESDAY),t1,20);
        Assert.assertEquals("Equals not true",new Time(6,00,true),course1.getEndTime());
    }


}
