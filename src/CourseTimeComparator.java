import java.util.Comparator;

/**
 * Created by Rakshith on 1/30/2017.
 * Used after the CourseNameComparator
 * & CourseCreditComparator.
 * If the CourseNameComparator  &
 * CourseCreditComparator returns 0,
 * then this class breaks the tie.
 */
public class CourseTimeComparator implements Comparator<Course> {

    //compare the 2 Courses's Time object.
    @Override
    public int compare(Course o1, Course o2) {

        //As long as there is no tie in startTime, returns
        //an integer based on their comparision.
        //Difference can't be used because, StartTimes are
        //not primitive integers.
      if(!o1.getStartTime().equals(o2.getStartTime())){
          return o1.getStartTime().compareTo(o2.getStartTime());
      }

        //As long as there is no tie in durations, returns
        // an integer based on their difference.
      if(o1.getDuration()!=o2.getDuration()){
          return o1.getDuration()-o2.getDuration();
      }


        //Tie breaking process now,
        // returns an integer based on their compared values.
        // Returns 0 only if the objects are truly common, even after
        // passing the other 2 comparators.
      return o1.getName().compareTo(o2.getName());
    }
}