import java.util.regex.Pattern;

/**
 * Created by Rakshith on 1/25/2017.
 * This Enum Contains the method for
 * weekdays and contains the actual weekdays.
 */
public enum Weekday {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY;

    /**
     * Checks to see if the text passed is an Equivalent
     * Weekday. Converts to that into its respective Enum for that Week.
     * Returns that Enum object. If String passed is not matching, then it
     * throws IllegalArgumentException.
     *
     * Uses Regular Expression matching to see if text provided matches.
     * @param s :String s
     * @return : Equivalent Enum Object.
     */

    public static Weekday fromString(String s) {
        if (Pattern.matches("[M m][O o][N n][D d][A a][Y y]", s) || Pattern.matches("[mM]",s)) {
            return Weekday.MONDAY;
        } else if (Pattern.matches("[T t][U u][E e][S s][D d][A a][Y y]", s)|| Pattern.matches("[Tt]",s)) {
            return Weekday.TUESDAY;
        } else if (Pattern.matches("[W w][E e][D d][N n][E e][S s][D d][A a][Y y]", s)|| Pattern.matches("[Ww]",s)) {
            return Weekday.WEDNESDAY;
        } else if (Pattern.matches("[T t][H h][U u][R r][S s][D d][A a][Y y]", s)|| Pattern.matches("[Rr]",s)) {
            return Weekday.THURSDAY;
        } else if (Pattern.matches("[F f][R r][I i][D d][A a][Y y]", s)|| Pattern.matches("[Ff]",s)) {
            return Weekday.FRIDAY;
        } else {
            throw new IllegalArgumentException("This is an Illegal Argument Exception");
        }
    }

    /**
     * @returns the short form of the objects passed to the method.
     * if not appropriate, then, it returns and IllegalArgumentException.
     */
    public String toShortName() {
        if (this.equals(Weekday.MONDAY)) {
            return "M";
        } else if (this.equals(Weekday.TUESDAY)) {
            return "T";
        } else if (this.equals(Weekday.WEDNESDAY)) {
            return "W";
        } else if (this.equals(Weekday.THURSDAY)) {
            return "R";
        } else if (this.equals(Weekday.FRIDAY)) {
            return "F";
        } else {
            throw new IllegalArgumentException("This is an Illegal Arguement Exception");
        }

    }



    /**
     * Return the title case for Weekday Enum
      * @return
     */
    @Override
    public String toString() {
        char[] runnableString = this.name().toCharArray();
        StringBuilder titleCase = new StringBuilder();

        for (int i = 0; i < runnableString.length; i++) {
            if (i == 0) {
                runnableString[i] = Character.toUpperCase(runnableString[i]);
                titleCase = titleCase.append(runnableString[i]);
            } else if (i > 0) {
                runnableString[i] = Character.toLowerCase(runnableString[i]);
                titleCase = titleCase.append(runnableString[i]);
            }
        }
        return String.valueOf(titleCase);
    }


}
