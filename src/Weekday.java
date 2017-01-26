import java.util.regex.Pattern;

/**
 * Created by Rakshith on 1/25/2017.
 */
public enum Weekday {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY;

    /**
     *
     * @param s
     * @return
     */

    public static Weekday fromString(String s) {
        if (Pattern.matches("[M m][O o][N n][D d][A a][Y y]", s)) {
            return Weekday.MONDAY;
        } else if (Pattern.matches("[T t][U u][E e][S s][D d][A a][Y y]", s)) {
            return Weekday.TUESDAY;
        } else if (Pattern.matches("[W w][E e][D d][N n][E e][S s][D d][A a][Y y]", s)) {
            return Weekday.WEDNESDAY;
        } else if (Pattern.matches("[T t][H h][U u][R r][S s][D d][A a][Y y]", s)) {
            return Weekday.THURSDAY;
        } else if (Pattern.matches("[F f][R r][I i][D d][A a][Y y]", s)) {
            return Weekday.FRIDAY;
        } else {
            try {
                throw new IllegalArgumentException("Threw an IllegalArgumentException");
            } catch (IllegalArgumentException e) {
                System.out.println("Caught an IllegalArgumentException" + e.getMessage());
            }
        }
        return Weekday.MONDAY;

    }

    /**
     *
     * @return
     */
    public String toShortName() {
        if(this.equals(Weekday.MONDAY)){
            return "M";
        }else if(this.equals(Weekday.TUESDAY)){
            return "T";
        }else if(this.equals(Weekday.WEDNESDAY)){
            return "W";
        }else if(this.equals(Weekday.THURSDAY)){
            return "R";
        }else if(this.equals(Weekday.FRIDAY)){
            return "F";
        }
        return "M";
    }

    //Return the title case for Weekday Enum
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
