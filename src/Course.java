import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Rakshith on 1/29/2017.
 */
public class Course implements Cloneable {
    private String name;
    private int credits;
    private Set<Weekday> days;
    private Time startTime;
    private int duration;

    public Course(String name, int credits, Set<Weekday> days, Time startTime, int duration) throws IllegalArgumentException {
        if (name != null && name != "" &&
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
            throw new IllegalArgumentException("This is an Illegal Argument for Course Constructor!");
        }

    }


    public boolean conflictsWith(Course course) {
        for (Weekday currentDay : days) {
            if (course.days.contains(currentDay)) {
                if (course.startTime.compareTo(this.startTime) < 0
                        && course.getEndTime().compareTo(this.getEndTime()) < 0)
                    return true;
            }
        }
        return false;
    }


    public boolean contains(Weekday day, Time time) {
        return (this.days.equals(EnumSet.of(day)) && this.startTime.equals(time));

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
        StringBuilder sb = new StringBuilder();
        for (Weekday currentDay : this.days) {
            sb.append(currentDay.toShortName());
        }
        return this.name + "," + this.credits + "," + sb + "," + this.startTime + "," + this.duration;
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
        int a = 31;
        a = 31 * this.startTime.getMinute() + this.startTime.getMinute();
        a = 31 * this.credits + this.credits;
        a = 31 * this.duration + this.duration;
        a = 31 * this.days.size() + this.days.size();
        return a;

    }
}
