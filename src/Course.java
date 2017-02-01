import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Rakshith on 1/29/2017.
 */
public class Course implements Cloneable{
    private String name;
    private int credits;
    private Set<Weekday>  days;
    private Time startTime;
    private int duration;

    public Course(String name, int credits, Set<Weekday> days, Time startTime, int duration) {
        this.name = name;
        this.credits = credits;
        this.days = days;
        this.startTime = startTime;
        this.duration = duration;
    }


    public boolean conflictsWith(Course course){
//        Time thisEndTime = this.startTime;
//        Time courseEndTime = course.startTime;
//        thisEndTime.shift(this.duration);
//        courseEndTime.shift(course.duration);

        int netDuration = (this.duration>course.duration)?this.duration:course.duration;


          return false;




    }

    public boolean contains(Weekday day, Time time){
        return false;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj!=null && obj.getClass() == Course.class) {
            Course course = (Course) obj;
            return (this.name.compareTo(course.name) ==0 &&
                    this.credits == course.credits &&
                    this.days == course.days &&
                    this.startTime==course.startTime &&
                    this.duration == course.duration);
        }

        return false;

    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public Time getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public Time getEndTime() {
        Time endTime = this.startTime;
        endTime.shift(this.duration);
        return endTime;
    }

    @Override
    public String toString() {
        return this.name+", " + this.credits+", "+ this.days+", "+ this.startTime+", "+this.duration;
    }

    @Override
    public Course clone() {
        try {
            return (Course) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int hashCode() {
        int a=31;
        a =  31*this.startTime.getMinute()+this.startTime.getMinute();
        a = 31*this.credits+this.credits;
        a= 31*this.duration+this.duration;
        a=31*this.days.size()+this.days.size();
        return a;

    }
}
