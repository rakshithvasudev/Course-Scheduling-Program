import java.util.Comparator;

/**
 * Created by Rakshith on 1/30/2017.
 * Used after the NameComparator.
 * If the NameComparator returns 0,
 * then this class breaks the tie.
 */
public class CourseCreditComparator implements Comparator<Course> {

    // Supports tie-breaking.
    @Override
    public int compare(Course o1, Course o2) {

        //As long as there is no tie, returns
        //an integer based on their difference.
        if (o1.getCredits() != o2.getCredits()) {
            return o1.getCredits() - o2.getCredits();
        }

        //If there was a tie, then returns an
        //integer that compares their names.
        return o1.getName().compareTo(o2.getName());
    }

}
