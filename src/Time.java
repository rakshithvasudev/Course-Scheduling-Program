import java.util.IllegalFormatCodePointException;
import java.util.regex.Pattern;

/**
 * Created by Rakshith on 1/26/2017.
 * This Class is responsible for creating
 * the time object. Implements Cloneable to
 * override the time object.
 */
public class Time implements Cloneable, Comparable<Time> {

    private int hour;
    private int minute;
    private boolean PM;

    public Time(int hour, int minute, boolean PM) throws IllegalArgumentException {
        if (hour > 12 || hour <=0 || minute >=60 || minute <0 ) {
            throw new IllegalArgumentException("Incorrect Input to Time constructor");
        }
        this.hour = hour;
        this.minute = minute;
        this.PM = PM;

    }



    public static Time fromString(String str) {
        //hh:hh am
        if(str.length() != 8)
            throw new IllegalArgumentException("length should be 8");

        if(str.charAt(2) != ':' || str.charAt(5) != ' ')
            throw new IllegalArgumentException("missing colon or space in the right place");

        if(!str.substring(6).equals("PM") && !str.substring(6).equals("AM"))
            throw new IllegalArgumentException("Missing AM or PM at the end");

        String[] splitArray = str.split(":|\\s");

        int hour = 0;
        int minute = 0;
        try {
            hour = Integer.parseInt(splitArray[0]);
            minute = Integer.parseInt(splitArray[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }

        boolean PM = splitArray[2].equals("PM");
        return new Time(hour, minute, PM);
    }


    /**
     *
     * @returns: Cloned Time Object.
     */
    @Override
    public Time clone() {
        try {
            return (Time) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public int compareTo(Time o) {
        if (this.isPM() && !(o.isPM())) {
            return 1;
        } else if (this.isPM() == o.isPM()) {
            if (this.hour == o.hour) {
                if (this.minute == o.minute) {
                    return 0;
                }
                //add another if minute comparision
                if(this.minute>o.minute){
                    return 1;
                }
            }
            if(this.hour>o.hour){
                return 1;
            }
        }

        return -1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj!=null && obj.getClass() == Time.class) {
           Time other = (Time) obj;
           return this.isPM()==other.isPM() && this.minute==other.minute && this.hour==other.hour ;
        }
        return false;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public boolean isPM() {
        if (this.PM) {
            return true;
        }
        return false;
    }

    public void shift(int minutes) {
        if(minutes<0){
            throw new IllegalArgumentException("No Negative Values Allowed");
        }

        boolean originalPM = this.isPM();
        int totalMinutes=0;
        if(this.isPM()) {
            totalMinutes = this.hour * 60 + this.minute + minutes;
            if (this.hour != 12) {
                totalMinutes += 12 * 60;
            }
        }

        if(!this.isPM()) {
            totalMinutes = this.hour * 60 + this.minute + minutes;
            if (this.hour == 12) {
                totalMinutes -= 12 * 60;
            }

        }

        int timeInMinutes = totalMinutes%60;
        int timeInHours = (totalMinutes-timeInMinutes)/60;
        this.PM = false;

        this.hour = timeInHours%24;
        this.minute=timeInMinutes;

        if(this.hour>=12){
            this.hour=this.hour%12;
            this.PM=true;
        }

        if(this.hour==0){
            this.hour=12;
        }

    }

    @Override
    public String toString() {
        return (((this.hour>=10)?"":"0")+ this.getHour() + ":" +((this.minute)>=10?"":"0")+this.minute + (this.isPM() ? " PM" : " AM"));
    }

    @Override
    public int hashCode() {
        int a =31;
        a = 31 * a + this.hour;
        a = 31 * a + this.minute;
        a = 31 * a + (this.PM ? 1:0);
        return a;
    }
}



