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
        return true;
    }

    public boolean contains(Weekday day, Time time){
        return false;
    }

    public boolean equals(Object o){
        return (Course.class==o.getClass());
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

    public Time getEndTime(){
        return new Time(2,30,true);
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

}
