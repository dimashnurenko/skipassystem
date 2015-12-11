package dmitry.shnurenko.skipass.type;

import java.time.LocalDateTime;

/**
 * @author Dmitry Shnurenko
 */
public interface DayTimeUtil {

    /**
     * Creates today date with defined time.
     *
     * @param hours   hours which will be set to time
     * @param minutes minutes which will be set to time
     * @return an instance of {@link LocalDateTime}
     */
    static LocalDateTime todayDateWithTime(int hours, int minutes) {
        LocalDateTime now = LocalDateTime.now();

        return LocalDateTime.of(now.getYear(),
                                now.getMonth(),
                                now.getDayOfMonth(),
                                hours,
                                minutes);
    }
}
