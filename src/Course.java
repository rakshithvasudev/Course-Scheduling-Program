/**
 * Created by Rakshith on 1/29/2017.
 */
public class Course {
    private String name;
    private int credits;
    private Weekday days;
    private Time startTime;
    private int duration;

    public Course(String name, int credits, Weekday days, Time startTime, int duration) {
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
}
