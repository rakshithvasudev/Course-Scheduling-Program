import javax.swing.text.html.HTMLDocument;
import java.util.*;

/**
 * This class implements the clonable interface so
 * the clone object can be overriden.
 * Created by Rakshith on 1/29/2017.
 */

public class Course implements Cloneable {
    private String name;
    private int credits;
    private Set<Weekday> days;
    private Time startTime;
    private int duration;

    public Course(String name, int credits, Set<Weekday> days, Time startTime, int duration){
        if (name != null && !name.equals("") &&
                name.contains(" ")&& days != null &&
                credits >= 1 && credits <= 5 &&
                credits != 0 && days.size() != 0 &&
                startTime != null && startTime.getHour()>0 &&
                duration != 0) {

            this.name = name;
            this.credits = credits;
            this.days = days;
            this.startTime = startTime;
            this.duration = duration;

        } else {
            throw new IllegalArgumentException("This is an Illegal Argument" +
                    " for Course Constructor!");
        }

    }


    public boolean conflictsWith(Course course) {
        for (Weekday currentDay: course.days) {
            if (this.days.contains(currentDay)) {
                if(this.startTime.compareTo(course.getEndTime())< 0 &&
                        course.startTime.compareTo(this.getEndTime())< 0){
                    return true;
                }else if(this.startTime.compareTo(course.startTime)==0){
                    return true;
                }else if(this.getEndTime().compareTo(course.startTime)==0){
                    return true;
                }else if(this.getEndTime().compareTo(course.getStartTime())>0 || this.getStartTime().compareTo(course.getEndTime())==0){
                    return true;
                }
            }
        }
        return false;
    }


    public boolean contains(Weekday day, Time time) {
        return (this.startTime.compareTo(time) <= 0 && this.getEndTime().compareTo(time) > 0 && this.days.contains(day));
    }



    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj.getClass() == Course.class) {
            Course course = (Course) obj;
            return (this.name.compareTo(course.name) == 0 &&
                    this.credits == course.credits &&
                    this.days == course.days &&
                    this.startTime == course.startTime &&
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
        return this.startTime;
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
        StringBuilder sb = new StringBuilder();
        for (Weekday currentDay : this.days) {
            sb.append(currentDay.toShortName());
        }
        return this.name + "," + this.credits + "," + sb + "," + this.startTime + "," + this.duration;
    }

    @Override
    public Course clone() {
        try {
            Course courseClone = (Course)super.clone();
            Time timeObject = new Time(startTime.getHour(),startTime.getMinute(),startTime.isPM());


            courseClone.days=new HashSet<>();
            courseClone.startTime=timeObject;


            for (Weekday currentDay: this.days) {
                courseClone.days.add(currentDay);
            }

            courseClone.startTime=timeObject;
            return courseClone;
        } catch (CloneNotSupportedException e) {
            return null;
        }

    }

    @Override
    public int hashCode() {
        int a = 31;
        a = 31 * this.startTime.getMinute() + this.startTime.getMinute() +37;
        a = 31 * this.credits + this.credits;
        a = 31 * this.duration + this.duration;
        a = 31 * this.days.size() + this.days.size();
        return a;

    }
}
