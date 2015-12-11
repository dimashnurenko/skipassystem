package dmitry.shnurenko.skipass.type;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

import static dmitry.shnurenko.skipass.type.DayTimeUtil.todayDateWithTime;
import static dmitry.shnurenko.skipass.type.ScannerType.TIME_SCANNER;
import static dmitry.shnurenko.skipass.type.Time.*;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;

/**
 * The ski passes types which represents ski passes which depend on time when ski pass is scanned. There ski passes
 * which is active in day or evening time and etc. Each ski pass of current type has to have time which defines
 * start active period and end active period.
 *
 * @author Dmitry Shnurenko
 */
public enum TimeLimitType implements Type {

    /** Ski pass which works during whole the year from 1.01.current_year until 31.12.current_year. */
    FULL_SEASON {
        @Nonnull
        public LocalDateTime getStartActivePeriod() {
            int currentYear = LocalDateTime.now().getYear();

            return LocalDateTime.of(currentYear, JANUARY, 1, 0, 0);
        }

        @Nonnull
        public LocalDateTime getEndActivePeriod() {
            int currentYear = LocalDateTime.now().getYear();
            return LocalDateTime.of(currentYear, DECEMBER, 31, 23, 59);
        }
    },
    /** Ski pass which works during half day from 9.00 until 13.00. */
    HALF_DAY_MORNING {
        @Nonnull
        public LocalDateTime getStartActivePeriod() {
            return todayDateWithTime(MORNING_FROM.hours(), MORNING_FROM.minutes());
        }

        @Nonnull
        public LocalDateTime getEndActivePeriod() {
            return todayDateWithTime(MORNING_UNTIL.hours(), MORNING_UNTIL.minutes());
        }
    },
    /** Ski pass which works during half day from 13.00 until 17.00. */
    HALF_DAY_EVENING {
        @Nonnull
        public LocalDateTime getStartActivePeriod() {
            return todayDateWithTime(EVENING_FROM.hours(), EVENING_FROM.minutes());
        }

        @Nonnull
        public LocalDateTime getEndActivePeriod() {
            return todayDateWithTime(EVENING_UNTIL.hours(), EVENING_UNTIL.minutes());
        }
    },
    /** Ski pass which works during one day from the date of sail. */
    ONE_DAY {
        @Nonnull
        public LocalDateTime getStartActivePeriod() {
            return todayDateWithTime(MORNING_FROM.hours(), MORNING_FROM.minutes());
        }

        @Nonnull
        public LocalDateTime getEndActivePeriod() {
            return todayDateWithTime(END_SKI_DAY.hours(), END_SKI_DAY.minutes());
        }
    },
    /** Ski pass which works during two days from the date of sail. */
    TWO_DAYS {
        @Nonnull
        public LocalDateTime getStartActivePeriod() {
            return todayDateWithTime(MORNING_FROM.hours(), MORNING_FROM.minutes());
        }

        @Nonnull
        public LocalDateTime getEndActivePeriod() {
            LocalDateTime currentDate = getStartActivePeriod();

            return currentDate.plusDays(2).withHour(END_SKI_DAY.hours()).withMinute(END_SKI_DAY.minutes());
        }
    },
    /** Ski pass which works during five days from the date of sail. */
    FIVE_DAYS {
        @Nonnull
        public LocalDateTime getStartActivePeriod() {
            return todayDateWithTime(MORNING_FROM.hours(), MORNING_FROM.minutes());
        }

        @Nonnull
        public LocalDateTime getEndActivePeriod() {
            LocalDateTime currentDate = getStartActivePeriod();

            return currentDate.plusDays(5).withHour(END_SKI_DAY.hours()).withMinute(END_SKI_DAY.minutes());
        }
    };

    /** Returns time from which ski pass is active. */
    @Nonnull
    public abstract LocalDateTime getStartActivePeriod();

    /** Returns time гтешд which ski pass is active. */
    @Nonnull
    public abstract LocalDateTime getEndActivePeriod();

    @Nonnull
    @Override
    public ScannerType getScannerType() {
        return TIME_SCANNER;
    }
}
