package dmitry.shnurenko.skipass.type;

import javax.annotation.Nonnull;

import static dmitry.shnurenko.skipass.type.ScannerType.LIFTS_SCANNER;

/**
 * The ski pass type which represents ski passes which depend on count of lifts. There are ski passes for 10,
 * 20 and etc. lifts.
 *
 * @author Dmitry Shnurenko
 */
public enum LiftsLimitType implements Type {
    /** Ski pass which allows 10 lifts. */
    TEN_LIFTS(10),
    /** Ski pass which allows 20 lifts. */
    TWENTY_LIFTS(20),
    /** Ski pass which allows 100 lifts. */
    ONE_HUNDRED_LIFTS(100);

    private final int countLifts;

    LiftsLimitType(int countLifts) {
        this.countLifts = countLifts;
    }

    /** Returns count of lifts. */
    public int getCountLifts() {
        return countLifts;
    }

    @Nonnull
    @Override
    public ScannerType getScannerType() {
        return LIFTS_SCANNER;
    }
}
