package dmitry.shnurenko.turnslite;

import dmitry.shnurenko.skipass.SkiPass;
import dmitry.shnurenko.turnslite.Turnslite.ScanStatus;

import javax.annotation.Nonnull;

/**
 * The special class which provides methods for checking if ski pass is active(passage allowed) or
 * not(passage forbidden). To add new checker we need register checker in guice module.
 *
 * @author Dmitry Shnurenko
 */
public interface SkiPassScanner<T extends SkiPass> {

    /**
     * Checks if ski pass is active(passage allowed) or not(passage forbidden)
     *
     * @param skiPass ski pass which will be checked
     * @return scan status SUCCESS if scan successful and FAIL if scan is failed
     * active(passage forbidden)
     */
    @Nonnull ScanStatus scan(@Nonnull T skiPass);
}
