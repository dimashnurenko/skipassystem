package dmitry.shnurenko.turnslite;

import dmitry.shnurenko.skipass.SkiPass;

/**
 * The special listener which calls methods when turnslite checks ski pass.
 *
 * @author Dmitry Shnurenko
 */
public interface SkiPassScanListener {

    /**
     * Performs some actions when ski pass checks is successful.
     *
     * @param skiPass ski pass which was checked
     */
    void onSkiPassScanSuccess(SkiPass skiPass);

    /**
     * Performs some actions when ski pass checks is fail.
     *
     * @param skiPass ski pass which was checked
     */
    void onSkiPassScanFail(SkiPass skiPass);
}
