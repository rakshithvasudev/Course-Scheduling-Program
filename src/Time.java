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


        this.hour = hour;
        this.minute = minute;
        this.PM = PM;

    }



    public static Time fromString(String Str) {

        String[] splitArray = Str.split(":|\\s");
        int hour = Integer.parseInt(splitArray[0]);
        int minute = Integer.parseInt(splitArray[1]);
        boolean PM = false;


        if (splitArray[2].equals("PM")) {
             PM=true;
        } else if (!(splitArray[2].equals("PM"))) {
            PM = false;
        } else if(splitArray[0].isEmpty() || splitArray[1].isEmpty() ||
                splitArray[2].isEmpty() ||Pattern.matches("[a-z]+",splitArray[0])
                || Pattern.matches("[a-z]+",splitArray[1])||
                !(Pattern.matches("[AP][M]",splitArray[2])))
        {
            throw new IllegalArgumentException("This is an Illegal Argument");
        }




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

    public boolean equals(Time o) {
        if (o.getClass() == Time.class) {
            if(this.isPM()==o.isPM()){
                return true;
            }

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
        this.minute += minutes;
        int addMinutesAfterHours = this.minute % 60;
        int factorHour = (int) Math.floor(this.minute / 60);
        boolean changedHourFlag = false;
        if (this.minute >= 60) {
            this.minute = addMinutesAfterHours;
            this.hour += factorHour;
            changedHourFlag = true;
        }
        if (this.PM && this.getHour() >= 12) {
            if (this.getMinute() >= 0) {
                this.PM = false;
            }
            this.hour = this.hour % 12;
            if (!changedHourFlag) {
                this.hour += factorHour;
            }
            if (this.hour == 0) {
                this.hour = 12;
            }
        } else if (!(this.PM) && this.getHour() >= 12) {
            if (this.getMinute() >= 0) {
                this.PM = true;
            }
            this.hour = this.hour % 12;
            if (!changedHourFlag) {
                this.hour += factorHour;
            }
            if (this.hour == 0) {
                this.hour = 12;
            }
        }

    }

    @Override
    public String toString() {
        return (((this.hour>=10)?"":"0")+ this.getHour() + ":" +((this.minute/10)>1?"":"0")+this.minute + (this.isPM() ? " PM" : " AM"));
    }

    @Override
    public int hashCode() {

        int a =31;
        a+=getHour()*17;
        a+=getMinute()*17;
        a+=(isPM()?7:13);
        return a;
    }
}



