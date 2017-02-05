import java.io.PrintStream;
import java.util.*;

/**
 * Created by Rakshith on 1/29/2017.
 * Using LinkedHashSet dataStructure
 * because, it would allow to keep the
 * track of order addition of elements.
 */
public class Schedule {
    private Set<Course> classSchedules;
    private int totalCredits;

    public Schedule() {
        this.classSchedules = new LinkedHashSet<>();
        this.totalCredits=0;
    }

    public void add(Course course){

        for (Course currentCourse: this.classSchedules) {
            if (currentCourse.conflictsWith(course)) {
                throw new ScheduleConflictException(currentCourse, course);
            }
        }

        this.classSchedules.add(course);
        this.totalCredits+= course.getCredits();
    }

    @Override
    public Schedule clone(){
        try {
            Schedule schedule=(Schedule)super.clone();
            schedule.classSchedules = new HashSet<Course>();
            for (Course currentCourse: classSchedules) {
                schedule.classSchedules.add(currentCourse);
            }
            return schedule;
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }


    public Course getCourse( Weekday  day, Time time){
        for (Course checkForCourse: this.classSchedules) {
            if(checkForCourse.contains(day,time)){
               return checkForCourse;
            }
        }
        return null;
    }

    public void save(PrintStream printStream, Comparator<Course> comparator){


    }


    public int totalCredits(){
        int totalCredits=0;
        for (Course currentCourse: classSchedules) {
            totalCredits+= currentCourse.getCredits();
        }
        return totalCredits;
    }

    public void remove(Weekday day,Time time){
        for (Course currentCourse: this.classSchedules) {
            if(currentCourse.contains(day, time)){
                int credits = currentCourse.getCredits();
                   totalCredits-=credits;
                    classSchedules.remove(currentCourse);
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return this.classSchedules.toString();
    }
}



