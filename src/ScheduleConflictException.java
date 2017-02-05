/**
 * Created by Rakshith on 1/30/2017.
 * RunTime Exception allows to throw exceptions during
 * the fly, instead of catching them.
 */
public class ScheduleConflictException extends RuntimeException{


    public ScheduleConflictException(Course course1, Course course2) throws RuntimeException  {
        super(course1.getName()+ " Conflicts with "+ course2.getName());
    }
}
