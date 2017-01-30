import java.io.PrintStream;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Rakshith on 1/29/2017.
 */
public class Schedule {
    private Map<String,Course> classSchedules;

    public Schedule() {
        this.classSchedules = new LinkedHashMap<>();
    }

    public void add(Course course){
        classSchedules.put(course.getName(),course);
    }

    @Override
    protected Schedule clone()  {

        try {
            return(Schedule)super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }

        return null;
    }


    public Course getCourse(Weekday day,Time time){
        return new Course("Math",3,Weekday.MONDAY,new Time(3,30,false),30);
    }

    public void save(PrintStream printStream, Comparator<Course> comparator){

    }


    public int totalCredits(){
        int totalCredits=0;
        for (int i = 0; i< classSchedules.size(); i++) {
            totalCredits+= classSchedules.get(i).getCredits();
        }

        return totalCredits;
    }

    public void remove(Weekday day,Time time){
        Course removedCourse = findCourseToRemove(day,time);
        classSchedules.remove(removedCourse);
    }

    private Course findCourseToRemove(Weekday day,Time time){
       Course c1=new Course("sample",3,Weekday.MONDAY,new Time(2,30,true),41);
        for(int i = 0; i< classSchedules.size(); i++){
            if(classSchedules.get(i).contains(day,time)){
                c1=classSchedules.get(i);
            }
        }
       return c1;
    }
}



