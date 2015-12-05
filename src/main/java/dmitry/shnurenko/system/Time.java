package dmitry.shnurenko.system;

/**
 * The enum represents time in format hours:minutes
 *
 * @author Dmitry Shnurenko
 */
enum Time {
    MORNING_FROM(9, 0), MORNING_UNTIL(13, 0), EVENING_FROM(13, 0), EVENING_UNTIL(17, 0), END_SKI_DAY(20, 0);

    private final int hours;
    private final int minutes;

    Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    /** Returns hours of time. */
    public int hours() {
        return hours;
    }

    /** Returns minutes of time. */
    public int minutes() {
        return minutes;
    }
}
