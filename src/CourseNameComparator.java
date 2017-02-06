import java.util.Comparator;

/**
 * Created by Rakshith on 1/30/2017.
 * Compares the Name of the 2 courses.
 */
public class CourseNameComparator implements Comparator<Course> {

    //Returns an integer based on the comparision made.
    @Override
    public int compare(Course o1, Course o2) {
        return o1.getName().compareTo(o2.getName());
    }

}