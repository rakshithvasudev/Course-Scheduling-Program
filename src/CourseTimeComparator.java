import java.util.Comparator;

/**
 * Created by Rakshith on 1/30/2017.
 */
public class CourseTimeComparator implements Comparator<Course> {
    @Override
    public int compare(Course o1, Course o2) {

      if(!o1.getStartTime().equals(o2.getStartTime())){
          return o1.getStartTime().compareTo(o2.getStartTime());
      }

      if(o1.getDuration()!=o2.getDuration()){
          return o1.getDuration()-o2.getDuration();
      }

      return o1.getName().compareTo(o2.getName());
    }
}