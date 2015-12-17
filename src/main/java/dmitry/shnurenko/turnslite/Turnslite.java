package dmitry.shnurenko.turnslite;

import com.google.inject.ImplementedBy;
import dmitry.shnurenko.skipass.SkiPass;

/**
 * Represent real turnslite which have ability scan ski passes and turn green light if passage is success and
 * red light if passage is fail.
 *
 * @author Dmitry Shnurenko
 */
@ImplementedBy(TurnsliteImpl.class)
public interface Turnslite {

    /**
     * Adds listeners to turnslite which perform some actions when user scan ski pass.
     *
     * @param listener listener which will be added
     */
    void addScanListener(SkiPassScanListener listener);

    /**
     * Scan ski pass and send scan result to {@link dmitry.shnurenko.system.System}
     *
     * @param skiPass ski pass which was scanned
     */
    void scan(SkiPass skiPass);

    /** Turns green light on turnslite. */
    void turnGreenLight();

    /** Turns red light on turnslite. */
    void turnRedLight();

    public enum ScanStatus {
        SUCCESS, FAIL;

        static ScanStatus of(boolean status) {
            return status ? SUCCESS : FAIL;
        }
    }
}
