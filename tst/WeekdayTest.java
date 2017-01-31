import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rakshith on 1/30/2017.
 */
public class WeekdayTest {

    @Test
    public void fromStringTest(){

        Weekday weekday = Weekday.FRIDAY;
        String friday = "Friday";

        Assert.assertEquals("Weekday mismatch",weekday, Weekday.fromString(friday));


    }

    @Test
    public void fromStringCornerTest(){
        Weekday weekday = Weekday.MONDAY;
        String weekDay = "M";

        Assert.assertEquals("Weekday mismatch",weekday, Weekday.fromString(weekDay));
    }


    @Test
    public void fromStringNegativeTest(){
        Weekday weekday = Weekday.WEDNESDAY;
        String weekDay = "wEdNeSdaY";

        Assert.assertFalse("Weekday mismatch",weekday!= Weekday.fromString(weekDay));
    }


    @Test
    public void shortNameTest(){
        Weekday weekday = Weekday.MONDAY;
        String weekDay = "M";

        Assert.assertEquals("Weekday mismatch",weekDay, weekday.toShortName());
    }

    @Test
    public void shortNameCornerTest(){
        Weekday weekday = Weekday.THURSDAY;
        String weekDay = "R";

        Assert.assertEquals("Weekday mismatch",weekDay, weekday.toShortName());
    }





}
