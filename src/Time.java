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

    //Don't accept illegalArguments.
    public Time(int hour, int minute, boolean PM) throws IllegalArgumentException {
        if (hour > 12 || hour <= 0 || minute >= 60 || minute < 0) {
            throw new IllegalArgumentException("Incorrect Input to Time constructor");
        }
        this.hour = hour;
        this.minute = minute;
        this.PM = PM;

    }


    public static Time fromString(String str) {
        //hh:hh am -> 8 chars
        if (str.length() != 8)
            throw new IllegalArgumentException("length should be 8");
        //Colons at 2nd position of the String.
        if (str.charAt(2) != ':' || str.charAt(5) != ' ')
            throw new IllegalArgumentException("missing colon or space in the right place");
        //is it having "AM" or "PM"?
        if (!str.substring(6).equals("PM") && !str.substring(6).equals("AM"))
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

    /**
     * Returns an integer value based on comparision.
     * If this Time is less than other time, returns -1.
     * If they're equal, returns 0.
     * If this Time is larger than the other, returns 1.
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Time o) {
        int thisMinute = 0;
        int otherObjectMinute = 0;

        if (o.isPM()) {
            otherObjectMinute += 12 * 60;
        }
        otherObjectMinute += o.getMinute();
        if (this.isPM()) {
            thisMinute += 12 * 60;
        }
        if (this.hour != 12) {
            thisMinute += this.hour * 60;
        }
        if (o.hour != 12) {
            otherObjectMinute += o.hour * 60;
        }
        thisMinute += this.getMinute();
        if (thisMinute > otherObjectMinute)
            return 1;
        if (thisMinute == otherObjectMinute)
            return 0;
        return -1;
    }

    /**
     * A Non Primitive must be overridden and checked for state matches.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == Time.class) {
            Time other = (Time) obj;
            return this.isPM() == other.isPM() && this.minute == other.minute && this.hour == other.hour;
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

    /**
     * Shifts the time to a new time equal to the minutes specified.
     *
     * @param minutes
     */
    public void shift(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("No Negative Values Allowed");
        }
        boolean originalPM = this.isPM();
        int totalMinutes = 0;
        if (this.isPM()) {
            totalMinutes = this.hour * 60 + this.minute + minutes;
            if (this.hour != 12) {
                totalMinutes += 12 * 60;
            }
        }

        if (!this.isPM()) {
            totalMinutes = this.hour * 60 + this.minute + minutes;
            if (this.hour == 12) {
                totalMinutes -= 12 * 60;
            }

        }

        int timeInMinutes = totalMinutes % 60;
        int timeInHours = (totalMinutes - timeInMinutes) / 60;
        this.PM = false;

        this.hour = timeInHours % 24;
        this.minute = timeInMinutes;

        if (this.hour >= 12) {
            this.hour = this.hour % 12;
            this.PM = true;
        }

        if (this.hour == 0) {
            this.hour = 12;
        }

    }

    /**
     * Outputs in the required format.
     * hh:mm am
     *
     * @return
     */
    @Override
    public String toString() {
        return (((this.hour >= 10) ? "" : "0") + this.getHour() + ":" + ((this.minute) >= 10 ? "" : "0") + this.minute + (this.isPM() ? " PM" : " AM"));
    }

    /**
     * Has to be overridden after equals is overridden.
     *
     * @return
     */
    @Override
    public int hashCode() {
        int a = 31;
        a = 31 * a + this.hour;
        a = 31 * a + this.minute;
        a = 31 * a + (this.PM ? 1 : 0);
        return a;
    }
}



