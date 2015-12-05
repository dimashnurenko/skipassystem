package dmitry.shnurenko.skipass;

import java.time.LocalDateTime;

/**
 * The common abstraction for ski pass representation. There two main types of ski passes. Time limit ski passes
 * are ski passes which works in predefined time or date, and lift limit ski passes which have predefined count
 * of lifts. Each ski pass contains special object {@link dmitry.shnurenko.skipass.SkiPassParameters} which
 * contains information about created ski pass.
 *
 * @author Dmitry Shnurenko
 */
public interface SkiPass {

    /** Returns string representation of ski pass id. The id generates using {@link java.util.UUID} */
    String getId();

    /** Returns date and time from which ski pass is active. */
    LocalDateTime getStartActiveTime();

    /** Returns date and time until which ski pass is active. */
    LocalDateTime getEndActiveTime();

    /** Returns count of lifts. */
    int getLiftCounts();

    /** Reduces count of lift counts. */
    void reduceLiftCounts();

    /**
     * Returns <code>true</code> if ski pass created in day off (SATURDAY or SUNDAY), and <code>false</code>
     * for other days.
     */
    boolean isDayOffSkiPass();

    /**
     * Returns an instance of {@link java.time.LocalDateTime} which contains information about date and time
     * when ski pass was created.
     */
    LocalDateTime getCreationDate();

    /** Returns type of ski pass. For more information see {@link dmitry.shnurenko.skipass.SkiPass.Type}. */
    Type getType();

    /**
     * Returns limit type. There are two limit types. TIME_LIMIT for ski passes which is active in predefined
     * time and LIFTS_LIMIT for ski passes which have limited count of lifts.
     */
    LimitType getLimitType();

    enum Type {
        /** Ski pass which works during whole the year from 1.01.current_year until 31.12.current_year. */
        FULL_SEASON,
        /** Ski pass which works during half day from 9.00 until 13.00. */
        HALF_DAY_MORNING,
        /** Ski pass which works during half day from 13.00 until 17.00. */
        HALF_DAY_EVENING,
        /** Ski pass which works during one day from the date of sail. */
        ONE_DAY,
        /** Ski pass which works during two days from the date of sail. */
        TWO_DAYS,
        /** Ski pass which works during five days from the date of sail. */
        FIVE_DAYS,
        /** Ski pass which allows 10 lifts. */
        TEN_LIFTS,
        /** Ski pass which allows 20 lifts. */
        TWENTY_LIFTS,
        /** Ski pass which allows 100 lifts. */
        ONE_HUNDRED_LIFTS
    }

    enum LimitType {
        /**Ski pass activity depends on time. For example HALF_DAY_MORNING ski pass see {@link Type}*/
        TIME_LIMIT,
        /**Ski pass activity depends on count of lifts. For example TEN_LIFTS ski pass see {@link Type}*/
        LIFTS_LIMIT
    }
}
