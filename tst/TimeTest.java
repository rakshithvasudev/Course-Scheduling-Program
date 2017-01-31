import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rakshith on 1/27/2017.
 */
public class TimeTest {

    @Test
    public void constructorTest() {
        Time time = new Time(5, 23, true);

        System.out.println(time.toString());

        Assert.assertEquals("The time object was not loaded",
                5, time.getHour());

    }

    @Test
    public void compareToCornerTest() {
        Time time1 = new Time(11, 59, true);
        Time time2 = new Time(12, 00, false);

        System.out.println(time1.toString());
        System.out.println(time2.toString());

        Assert.assertEquals("Time Matching has an issue",
                1, time1.compareTo(time2));

    }

    @Test
    public void compareToTest() {
        Time time1 = new Time(8, 21, false);
        Time time2 = new Time(6, 23, true);

        System.out.println(time1.toString());
        System.out.println(time2.toString());

        Assert.assertEquals("Time Matching has an issue",
                -1, time1.compareTo(time2));

    }


    @Test
    public void compareToNegativeTest() {
        Time time1 = new Time(4, 21, false);
        Time time2 = new Time(5, 23, true);

        System.out.println(time1.toString());
        System.out.println(time2.toString());

        Assert.assertFalse("Time matching has an issue",
                time1.compareTo(time2) == 0);
    }


    @Test
    public void CompareToPositiveTest() {
        Time time1 = new Time(9, 21, false);
        Time time2 = new Time(5, 23, true);

        System.out.println(time1.toString());
        System.out.println(time2.toString());

        Assert.assertTrue("Time matching has an issue",
                time1.compareTo(time2) == -1);
    }

    @Test
    public void CompareToCornerTest() {
        Time time1 = new Time(5, 21, false);
        Time time2 = new Time(9, 00, false);

        System.out.println(time1.toString());
        System.out.println(time2.toString());

        Assert.assertEquals("Time matching has an issue", -1,
                time1.compareTo(time2));
    }

    @Test
    public void fromStringTest() {
        String str = "05:25 PM";

        System.out.println(str);

        Assert.assertFalse("The Time was not parsed!", new Time(5, 25, true)==
                Time.fromString(str));
    }

    @Test
    public void fromStringCornerTest() {
        String str = "05:00 AM";

        System.out.println(str);

        Assert.assertFalse("The Time was not parsed!", new Time(5, 00, false)==
                Time.fromString(str));
    }

    @Test
    public void fromStringPositiveCornerTest() {
        String str = "05:05 AM";

        System.out.println(str);

        System.out.println(Time.fromString(str));

        Assert.assertFalse(new Time(5, 05, false)== Time.fromString(str));
    }

    @Test
    public void cloneTest() {
        Time t1 = new Time(2, 20, true);

        System.out.println(t1);

        Assert.assertFalse("Clone not successful.", t1== t1.clone());
    }

    @Test
    public void EqualsTest() {
        Time t1 = new Time(3, 45, false);
        Time t2 = new Time(3, 45, false);

        System.out.println(t1.toString());
        System.out.println(t2.toString());

        Assert.assertEquals("Equals test failed!", true, t1.equals(t2));
    }


    @Test
    public void getMinuteTest() {
        Time t1 = new Time(1, 43, true);

        System.out.println(t1);

        Assert.assertEquals("Get Minute failed", 43, t1.getMinute());
    }


    @Test
    public void getHourTest() {
        Time t1 = new Time(1, 43, true);

        System.out.println(t1);

        Assert.assertEquals("Get Minute failed", 1, t1.getHour());
    }


    @Test
    public void isPMTest() {
        Time t1 = new Time(2, 52, false);

        System.out.println(t1);

        Assert.assertTrue("isPM() failed!", !t1.isPM());
    }

    @Test
    public void isPMCornerTest() {
        Time t1 = new Time(5, 5, true);

        System.out.println(t1.toString());

        Assert.assertTrue("isPM() failed!", t1.isPM());
    }



}
