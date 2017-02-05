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


    /**
     * Clone the startTime variable because it'd get
     * shifted later in the program.
     *
     * @param name       : Check for any improper formats in name.
     * @param credits    : Accept only values from 1 to 5.
     * @param days       : Set of days for the course.
     * @param startTime: start Time for the courses.
     * @param duration:  Length of the Class.
     */
    public Course(String name, int credits, Set<Weekday> days, Time startTime, int duration) {
        if (name != null && !name.equals("") &&
                name.contains(" ") && days != null &&
                credits >= 1 && credits <= 5 &&
                credits != 0 && days.size() != 0 &&
                startTime != null && startTime.getHour() > 0 &&
                duration != 0) {

            this.name = name;
            this.credits = credits;
            this.days = days;
            this.startTime = startTime.clone();
            this.duration = duration;

        } else {
            throw new IllegalArgumentException("This is an Illegal Argument" +
                    " for Course Constructor!");
        }

    }


    /**
     * Checks to see if there is any potential
     * Conflict between two courses.
     *
     * @param course : Accepts the Course.
     * @returns the possiblity of conflict, boolean.
     */
    public boolean conflictsWith(Course course) {
        for (Weekday currentDay : course.days) {
            if (this.days.contains(currentDay)) {
                if (this.startTime.compareTo(course.startTime) <= 0 ||
                        course.getEndTime().compareTo(this.getEndTime()) <= 0) {
                    if (this.startTime.compareTo(course.getEndTime()) == 0) {
                        return false;
                    } else if (this.startTime.compareTo(course.getEndTime()) == 0) {
                        return false;
                    }
                    if (course.getEndTime().compareTo(this.startTime) < 0) {
                        return false;
                    }
                    return true;
                }
            }

        }
        return false;
    }


    /**
     * Checks the presence of a course with the given
     * day and time.
     *
     * @param day
     * @param time
     * @return
     */
    public boolean contains(Weekday day, Time time) {
        return (this.startTime.compareTo(time) <= 0 &&
                this.getEndTime().compareTo(time) > 0 && this.days.contains(day));
    }


    /**
     * Returns if the Course objects are the same
     * in value. Non primitive data types are
     * making use of equals() method from the object class.
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == Course.class) {
            Course course = (Course) obj;
            return (this.name.equals(course.name) &&
                    this.credits == course.credits &&
                    this.days.equals(course.days) &&
                    this.startTime.equals(course.startTime) &&
                    this.duration == course.duration);
        }
        return false;
    }


    public String getName() {
        return this.name;
    }

    public int getCredits() {
        return this.credits;
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


    /**
     * Performs a deep copy of the Course object.
     *
     * @return
     */
    @Override
    public Course clone() {
        try {
            Course course = (Course) super.clone();
            course.days = new LinkedHashSet<>();
            Time startTimeCloned = this.startTime.clone();
            Time t1 = new Time(startTimeCloned.getHour(), startTimeCloned.getMinute(), startTimeCloned.isPM());
            for (Weekday currentDay : this.days) {
                course.days.add(currentDay);
            }
            return new Course(course.name, course.credits, days, t1, course.duration);
        } catch (CloneNotSupportedException e) {
            return null;
        }

    }

    @Override
    public int hashCode() {
        int a = 31;
        a = 31 * this.startTime.getMinute() + this.startTime.getMinute() + 37;
        a = 31 * this.credits + this.credits;
        a = 31 * this.duration + this.duration;
        a = 31 * this.days.size() + this.days.size();
        return a;

    }
}
