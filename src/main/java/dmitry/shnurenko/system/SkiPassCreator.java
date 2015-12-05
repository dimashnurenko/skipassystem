package dmitry.shnurenko.system;

import com.google.inject.Inject;
import dmitry.shnurenko.skipass.SkiPass;
import dmitry.shnurenko.skipass.SkiPass.Type;
import dmitry.shnurenko.skipass.SkiPassFactory;
import dmitry.shnurenko.skipass.SkiPassParameters;

import java.time.LocalDateTime;

import static dmitry.shnurenko.skipass.SkiPass.Type.*;
import static dmitry.shnurenko.system.Time.*;

/**
 * The class contains business logic which allows us create ski passes with different parameters in depending on
 * ski pass type. Also there is methods for creating ski passes with custom type and parameters.
 *
 * @author Dmitry Shnurenko
 */
class SkiPassCreator {

    private final SkiPassFactory skiPassFactory;

    @Inject
    public SkiPassCreator(SkiPassFactory skiPassFactory) {
        this.skiPassFactory = skiPassFactory;
    }

    /**
     * Creates ski pass with custom parameters.
     *
     * @param type            ski pass type. Can not be <code>null</code>
     * @param startActiveTime time from which ski pass is active
     * @param endActiveTime   time until which ski pass is active
     * @param liftCounts      count of lifts
     * @return an instance of {@link SkiPass}
     */
    public SkiPass createCustomSkiPass(Type type,
                                       LocalDateTime startActiveTime,
                                       LocalDateTime endActiveTime,
                                       int liftCounts) {
        SkiPassParameters parameters = SkiPassParameters.getBuilder()
                                                        .withStartActiveTime(startActiveTime)
                                                        .withEndActiveTime(endActiveTime)
                                                        .withLiftCount(liftCounts)
                                                        .withType(type)
                                                        .build();
        return skiPassFactory.createSkiPass(parameters);
    }

    /**
     * Creates ski pass depending on ski pass type. For each type there is predefined {@link SkiPassParameters}.
     *
     * @param type type of ski pass. For detail information see {@link Type}
     * @return an instance of {@link SkiPass}
     */
    public SkiPass create(Type type) {
        switch (type) {
            case FULL_SEASON:
                return getFullSeasonSkiPass();
            case HALF_DAY_MORNING:
                return getHalfDayMorningSkiPass();
            case HALF_DAY_EVENING:
                return getHalfDayEveningSkiPass();
            case ONE_DAY:
                return getSkiPassForNDays(1, type);
            case TWO_DAYS:
                return getSkiPassForNDays(2, type);
            case FIVE_DAYS:
                return getSkiPassForNDays(5, type);
            case TEN_LIFTS:
                return getSkiPassForNLifts(10, type);
            case TWENTY_LIFTS:
                return getSkiPassForNLifts(20, type);
            case ONE_HUNDRED_LIFTS:
                return getSkiPassForNLifts(100, type);
            default:
                throw new IllegalArgumentException("Ski pass of this type " + type + " isn't defined.");
        }
    }

    private SkiPass getFullSeasonSkiPass() {
        int currentYear = LocalDateTime.now().getYear();

        LocalDateTime startActiveTime = LocalDateTime.of(currentYear, 1, 1, 0, 0);
        LocalDateTime endActiveTime = LocalDateTime.of(currentYear, 12, 31, 23, 59);

        return createTimeLimitSkiPass(FULL_SEASON, startActiveTime, endActiveTime);
    }

    private SkiPass createTimeLimitSkiPass(Type type, LocalDateTime startActiveTime, LocalDateTime endActiveTime) {
        SkiPassParameters parameters = SkiPassParameters.getBuilder()
                                                        .withType(type)
                                                        .withStartActiveTime(startActiveTime)
                                                        .withEndActiveTime(endActiveTime)
                                                        .build();
        return skiPassFactory.createSkiPass(parameters);
    }

    private SkiPass getHalfDayMorningSkiPass() {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime startActiveTime = LocalDateTime.of(now.getYear(),
                                                         now.getMonth(),
                                                         now.getDayOfMonth(),
                                                         MORNING_FROM.hours(),
                                                         MORNING_FROM.minutes());
        LocalDateTime endActiveTime = LocalDateTime.of(now.getYear(),
                                                       now.getMonth(),
                                                       now.getDayOfMonth(),
                                                       MORNING_UNTIL.hours(),
                                                       MORNING_UNTIL.minutes());
        return createTimeLimitSkiPass(HALF_DAY_MORNING, startActiveTime, endActiveTime);
    }

    private SkiPass getHalfDayEveningSkiPass() {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime startActiveTime = LocalDateTime.of(now.getYear(),
                                                         now.getMonth(),
                                                         now.getDayOfMonth(),
                                                         EVENING_FROM.hours(),
                                                         EVENING_FROM.minutes());
        LocalDateTime endActiveTime = LocalDateTime.of(now.getYear(),
                                                       now.getMonth(),
                                                       now.getDayOfMonth(),
                                                       EVENING_UNTIL.hours(),
                                                       EVENING_UNTIL.minutes());

        return createTimeLimitSkiPass(HALF_DAY_EVENING, startActiveTime, endActiveTime);
    }

    private SkiPass getSkiPassForNDays(int countDays, Type type) {
        LocalDateTime startActiveTime = LocalDateTime.now();
        LocalDateTime endActiveTime = LocalDateTime.of(startActiveTime.getYear(),
                                                       startActiveTime.getMonth(),
                                                       startActiveTime.plusDays(countDays).getDayOfMonth(),
                                                       END_SKI_DAY.hours(),
                                                       END_SKI_DAY.minutes());

        return createTimeLimitSkiPass(type, startActiveTime, endActiveTime);
    }

    private SkiPass getSkiPassForNLifts(int countLifts, Type type) {
        SkiPassParameters parameters = SkiPassParameters.getBuilder().withLiftCount(countLifts).withType(type).build();

        return skiPassFactory.createSkiPass(parameters);
    }
}
