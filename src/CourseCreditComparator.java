import java.util.Comparator;

/**
 * Created by Rakshith on 1/30/2017.
 */
public class CourseCreditComparator implements Comparator<Course> {

    @Override
    public int compare(Course o1, Course o2) {
        if(o1.getCredits()!=o2.getCredits()){
            return o1.getCredits()-o2.getCredits();
        }
        return o1.getName().compareTo(o2.getName());
    }

}
