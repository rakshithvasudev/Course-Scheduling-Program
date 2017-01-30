/**
 * Created by Rakshith on 1/30/2017.
 */
public class ScheduleConflictException extends Exception{

    public ScheduleConflictException(Course course1, Course course2) throws Exception {
       throw new Exception(course1+ " Conflicts with "+ course2);
    }
}
