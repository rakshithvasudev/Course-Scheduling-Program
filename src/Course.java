import javax.swing.text.html.HTMLDocument;
import java.util.*;

/**
 * This class implements the cloneable interface so
 * the clone object can be overridden.
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
     * shift() 'ed later in the program.
     *
     * @param name      : Check for any improper formats in name.
     * @param credits   : Accept only values from 1 to 5.
     * @param days      : Set of days for the course from the Weekday Enum.
     * @param startTime : start Time for the courses.
     * @param duration  :  Length of the Class.
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
     * @returns the possibility of conflict, boolean.
     */
    public boolean conflictsWith(Course course) {
        Time courseStartTime = course.startTime;
        Time courseEndTime = course.getEndTime();
        for (Weekday currentDay : course.days) {
            if (this.days.contains(currentDay)) {

                //Returns false if the start times and end time are not the same and
                //if there is an intersection point for both the times.
                // For ex: Refer line 160 in CourseInstructorTest.java
                // file .
                if (this.startTime.compareTo(courseStartTime) != 0 &&
                        this.getEndTime().compareTo(courseEndTime) != 0 &&
                        (this.getEndTime().compareTo(courseStartTime) == 0 ||
                                this.startTime.compareTo(courseEndTime) == 0)) {
                    return false;
                }

                // Return true if there is a course during the the Start time or End time.
                //Uses a private helper method containsHelper(Day,Time)
                // that is defined in this class.
                if (containsHelper(currentDay, courseStartTime) ||
                        containsHelper(currentDay, courseEndTime)) {
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
     * A private helper method used for the  conflictsWith() method.
     *
     * @param day
     * @param time
     * @returns true if there is a course that matches the day and time.
     */
    private boolean containsHelper(Weekday day, Time time) {
        return (this.startTime.compareTo(time) <= 0 &&
                this.getEndTime().compareTo(time) >= 0 && this.days.contains(day));
    }

    /**
     * Returns if the Course objects are the same
     * in value. Non primitive data types are
     * making use of equals() method from the object class.
     *
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

    /**
     * Return a cloned copy, because a private object shouldn't
     * be allowed freely to access.
     *
     * @return
     */
    public Time getStartTime() {
        return this.startTime.clone();
    }

    public int getDuration() {
        return duration;
    }

    /**
     * Returns the EndTime after shifting.
     * startTime variable has to be cloned.
     *
     * @return
     */
    public Time getEndTime() {
        Time endTime = this.startTime.clone();
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
            Course courseCopy = (Course) super.clone();
            courseCopy.days = new LinkedHashSet<>(days);
            courseCopy.startTime = startTime.clone();
            return courseCopy;

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int hashCode() {
        int a = 31;
        a+= 31 * this.startTime.getMinute() + this.startTime.getMinute() + 37;
        a+= 31 * this.credits + this.credits;
        a+= 31 * this.duration + this.duration;
        a+= 31 * this.days.size() + this.days.size();
        return a;

    }
}
