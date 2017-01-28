/**
 * Created by Rakshith on 1/26/2017.
 */
public class Time implements Cloneable, Comparable<Time> {

    private int hour;
    private int minute;
    private boolean PM = false;

    public Time(int hour, int minute, boolean PM) {
        this.hour = hour;
        this.minute = minute;
        this.PM = false;
    }


    public static Time fromString(String Str) {

        String[] splitArray = Str.split(":|\\s");
        int hour = Integer.parseInt(splitArray[0]);
        int minute = Integer.parseInt(splitArray[1]);
        boolean AM = false;


        if (splitArray[2].equals("PM")) {
            AM = false;
        } else if (splitArray[2].equals("PM")) {
            AM = true;
        } else if (splitArray[0].isEmpty() || splitArray[1].isEmpty() || splitArray[2].isEmpty()) {
            throw new IllegalArgumentException("This is an Illegal Argument");
        }
        return new Time(hour, minute, AM);
    }

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

        //Variables this Time object
        boolean thisConvertTo24HoursFlag;
        int thisHoursTotalTime = 12;
        int toThisAddRemainingHours = this.hour % 12;
        int thisMinutes = this.getMinute();

        //Variables o Time object
        boolean objectConvertTo24HoursFlag;
        int objectHoursTotalTime = 12;
        int toObjectAddRemainingHours = o.hour % 12;
        int objectMinutes = o.getMinute();


        if (this.PM) {
            thisConvertTo24HoursFlag = true;
        } else {
            thisConvertTo24HoursFlag = false;
        }

        if (thisConvertTo24HoursFlag) {
            if (this.hour > 12) {
                thisHoursTotalTime += toThisAddRemainingHours;
            }
        }

        if (o.PM) {
            objectConvertTo24HoursFlag = true;
        } else {
            objectConvertTo24HoursFlag = false;
        }

        if (objectConvertTo24HoursFlag) {
            if (o.hour > 12) {
                objectHoursTotalTime += toObjectAddRemainingHours;
            }
        }


        if (thisHoursTotalTime > objectHoursTotalTime) {
            return 1;
        } else if (thisHoursTotalTime == objectHoursTotalTime) {
            if (thisMinutes == objectMinutes) {
                return 0;
            } else if (thisMinutes > objectMinutes) {
                return 1;
            } else if (thisMinutes < objectMinutes) {
                return -1;
            }
        } else {
            return -1;
        }

        return 22;
    }

    public boolean equals(Object o) {
        if (o.getClass() == Time.class) {
            return true;
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
        return "0"+this.hour+":"+this.minute+" "+this.PM;
    }
}



