package dmitry.shnurenko.skipass;

import dmitry.shnurenko.skipass.type.LiftsLimitType;
import dmitry.shnurenko.skipass.type.ScannerType;

import javax.annotation.Nonnull;

/**
 * The class contains information about ski passes which depend on count of lifts.
 *
 * @author Dmitry Shnurenko
 */
public class LiftsLimitSkiPass extends AbstractSkiPass {

    private final LiftsLimitType type;

    private int liftsCount;

    LiftsLimitSkiPass(@Nonnull LiftsLimitType type) {
        this.type = type;
        this.liftsCount = type.getCountLifts();
    }

    /**
     * Reduce count of lifts and returns remaining count of lifts.
     *
     * @return remaining count of lifts.
     */
    public int reduceLifts() {
        liftsCount--;

        return liftsCount;
    }

    @Nonnull
    @Override
    public LiftsLimitType getType() {
        return type;
    }

    @Nonnull
    @Override
    public ScannerType getScannerType() {
        return type.getScannerType();
    }
}
