package dmitry.shnurenko.turnslite;

import dmitry.shnurenko.skipass.SkiPass;

/**
 * @author Dmitry Shnurenko
 */
public final class LiftLimitChecker implements SkiPassChecker {

    @Override
    public boolean check(SkiPass skiPass) {
        skiPass.reduceLiftCounts();

        int liftCounts = skiPass.getLiftCounts();

        return liftCounts >= 0;
    }
}
