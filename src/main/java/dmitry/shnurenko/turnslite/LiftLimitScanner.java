package dmitry.shnurenko.turnslite;

import dmitry.shnurenko.skipass.LiftsLimitSkiPass;
import dmitry.shnurenko.turnslite.Turnslite.ScanStatus;

import javax.annotation.Nonnull;

/**
 * The class contains business logic which checks activity of lifts limit ski passes.
 *
 * @author Dmitry Shnurenko
 */
public class LiftLimitScanner implements SkiPassScanner<LiftsLimitSkiPass> {

    @Nonnull
    @Override
    public ScanStatus scan(@Nonnull LiftsLimitSkiPass skiPass) {
        int remainingLiftCounts = skiPass.reduceLifts();
        return ScanStatus.of(remainingLiftCounts >= 0);
    }
}
