import java.util.Comparator;

/**
 * Created by Rakshith on 1/30/2017.
 */
public class CourseTimeComparator implements Comparator<Course> {
    @Override
    public int compare(Course o1, Course o2) {

        CourseNameComparator courseNameComparator = new CourseNameComparator();
        int breakTieHelper=courseNameComparator.compare(o1, o2);
        int breakTie= compareIntegerHelper(o1.getCredits(),o2.getCredits());




    }

    private int compareIntegerHelper(int a, int b){
        if(a>b){
            return 1;
        }else if(a==b){
            return 0;
        }
    return -1;
    }


}
