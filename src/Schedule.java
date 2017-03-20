import java.io.PrintStream;
import java.util.*;

/**
 * Created by Rakshith on 1/29/2017.
 * Using LinkedHashSet dataStructure
 * because, it would allow to keep the
 * track of order addition of elements.
 */
public class Schedule implements Cloneable {
    private Set<Course> classSchedules;
    private int totalCredits;


    /**
     * Initialize the classSchedules to nothing.
     */
    public Schedule() {
        this.classSchedules = new LinkedHashSet<>();
        this.totalCredits = 0;
    }

    /**
     * In the event that there is a conflict during
     * addition of the course, a RuntimeException is thrown.
     * uses for each loop to check within the list of
     * elements of the classSchedules Hash set.
     *
     * @param course : Added
     * @throws RuntimeException : when there's a conflict.
     */
    public void add(Course course) throws RuntimeException {

        for (Course currentCourse : this.classSchedules) {
            if (currentCourse.conflictsWith(course)) {
                throw new ScheduleConflictException(currentCourse, course);
            }
        }
        //Add credits upon the addition of
        // the course to the schedule.
        this.classSchedules.add(course);
        this.totalCredits += course.getCredits();
    }

    /**
     * Creates a copy of the schedule inclusive of its primitive
     * elements such as totalCredits.
     *
     * @return
     */
    @Override
    public Schedule clone() {
        try{
            Schedule copy = (Schedule)super.clone();
            copy.classSchedules = new LinkedHashSet<>(this.classSchedules);
            return copy;
        } catch (CloneNotSupportedException e){
           e.printStackTrace();
        }
        return null;
    }



    /**
     * Checks to see if there is a course that runs on the given
     * the day and time.
     *
     * @param day
     * @param time
     * @return
     */
    public Course getCourse(Weekday day, Time time) {
        for (Course checkForCourse : this.classSchedules) {
            if (checkForCourse.contains(day, time)) {
                return checkForCourse;
            }
        }
        return null;
    }

    /**
     * Uses comparator and a printStream element.
     * Implements a temporary arrayList to copy the
     * elements to sort from the comparator.
     *
     * @param printStream : Allows to write to the txt file.
     * @param comparator  : Supports for the sorting the list.
     */
    public void save(PrintStream printStream, Comparator<Course> comparator) {

        //Copy hash set contents into class schedules ArrayList.
        //The copied elements can be further sorted.
        List<Course> classSchedulesList = new ArrayList<>(classSchedules);
        classSchedulesList.sort(comparator);

        for (Course currentCourse : classSchedulesList) {
            printStream.append(currentCourse.toString());
            printStream.format("%n");
        }

        //close the writing stream.
        printStream.close();


    }

    /**
     * Returns the totalCredits from the HashSet.
     *
     * @return
     */

    public int totalCredits() {
        int totalCredits = 0;
        for (Course currentCourse : classSchedules) {
            totalCredits += currentCourse.getCredits();
        }
        return totalCredits;
    }


    /**
     * Removes the element from the Set.
     * Updates the credits before the removing of the element.
     *
     * @param day   : if the course element contains the same day,
     * @param time: and time as the element in the classSchedule,
     *              then that element is removed.
     */
    public void remove(Weekday day, Time time) {
        for (Course currentCourse : this.classSchedules) {
            if (currentCourse.contains(day, time)) {
                int credits = currentCourse.getCredits();
                totalCredits -= credits;
                classSchedules.remove(currentCourse);
                break;
            }
        }
    }

    /**
     * Prints the list of class Schedules.
     *
     * @return
     */
    @Override
    public String toString() {
        return this.classSchedules.toString();
    }

    /**
     * Only for testing purpose
     * @return
     */
    public Collection<Course> getAllCourses(){
        return new LinkedHashSet<>(classSchedules);
    }
}



