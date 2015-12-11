package dmitry.shnurenko.turnslite;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dmitry.shnurenko.skipass.SkiPass;
import dmitry.shnurenko.skipass.type.ScannerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static dmitry.shnurenko.turnslite.Turnslite.ScanStatus.FAIL;
import static dmitry.shnurenko.turnslite.Turnslite.ScanStatus.SUCCESS;

/**
 * @author Dmitry Shnurenko
 */
@Singleton
final class TurnsliteImpl implements Turnslite {

    private final List<SkiPassScanListener>        listeners;
    private final Map<ScannerType, SkiPassScanner> scanners;

    @Inject
    public TurnsliteImpl(Map<ScannerType, SkiPassScanner> scanners) {
        this.listeners = new ArrayList<>();
        this.scanners = scanners;
    }

    @Override
    public void addScanListener(SkiPassScanListener scanListener) {
        listeners.add(scanListener);
    }

    @Override
    public void scan(SkiPass skiPass) {
        ScannerType scannerType = skiPass.getType().getScannerType();

        SkiPassScanner scanner = scanners.get(scannerType);

        if (scanner == null) {
            throw new IllegalArgumentException(
                    getClass() + "Scanner for scanner type " + scannerType + " is not defined. " +
                            "Register scanner for " + scannerType + " and try again.");
        }

        //noinspection unchecked
        notifyListeners(skiPass, scanner.scan(skiPass));
    }

    @Override
    public void turnGreenLight() {
        System.out.println("THE PASSAGE ALLOWED|____________GREEN LIGHT___________|");
    }

    @Override
    public void turnRedLight() {
        System.out.println("THE PASSAGE FORBIDDEN|____________RED LIGHT___________|");
    }

    private void notifyListeners(SkiPass skiPass, ScanStatus scanStatus) {
        for (SkiPassScanListener listener : listeners) {
            if (SUCCESS.equals(scanStatus)) {
                listener.onSkiPassScanSuccess(skiPass);

                continue;
            }

            if (FAIL.equals(scanStatus)) {
                listener.onSkiPassScanFail(skiPass);
            }
        }
    }
}
