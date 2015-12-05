package dmitry.shnurenko.turnslite;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dmitry.shnurenko.skipass.SkiPass;
import dmitry.shnurenko.skipass.SkiPass.LimitType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static dmitry.shnurenko.turnslite.TurnsliteImpl.ScanStatus.FAIL;
import static dmitry.shnurenko.turnslite.TurnsliteImpl.ScanStatus.SUCCESS;

/**
 * @author Dmitry Shnurenko
 */
@Singleton
final class TurnsliteImpl implements Turnslite {

    private final List<SkiPassScanListener>      listeners;
    private final Map<LimitType, SkiPassChecker> checkers;

    @Inject
    public TurnsliteImpl(Map<LimitType, SkiPassChecker> checkers) {
        this.listeners = new ArrayList<>();
        this.checkers = checkers;
    }

    /** {inheritDoc} */
    @Override
    public void addScanListener(SkiPassScanListener scanListener) {
        listeners.add(scanListener);
    }

    /** {inheritDoc} */
    @Override
    public void scan(SkiPass skiPass) {
        LimitType limitType = skiPass.getLimitType();

        SkiPassChecker checker = checkers.get(limitType);

        boolean isSkiPassActive = checker.check(skiPass);

        notifyListeners(skiPass, isSkiPassActive ? SUCCESS : FAIL);
    }

    /** {inheritDoc} */
    @Override
    public void turnGreenLight() {
        System.out.println("THE PASSAGE ALLOWED|____________GREEN LIGHT___________|");
    }

    /** {inheritDoc} */
    @Override
    public void turnRedLight() {
        System.out.println("THE PASSAGE FORBIDDEN|____________RED LIGHT___________|");
    }

    private void notifyListeners(SkiPass skiPass, ScanStatus scanStatus) {
        for (SkiPassScanListener listener : listeners) {
            if (SUCCESS.equals(scanStatus)) {
                listener.onSkiPassScanSuccess(skiPass);
            }

            if (FAIL.equals(scanStatus)) {
                listener.onSkiPassScanFail(skiPass);
            }
        }
    }

    enum ScanStatus {
        SUCCESS, FAIL
    }
}
