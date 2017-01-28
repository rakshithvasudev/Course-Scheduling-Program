import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rakshith on 1/27/2017.
 */
public class TimeTest {

    @Test
    public void compareToTest(){
        Time time1 = new Time(3,23,true);//3:23 PM
        Time time2 = new Time(4,20,false);//4:20 AM

        Assert.assertEquals("Time Matching has an issue",
                -1,time1.compareTo(time2));

    }

}
