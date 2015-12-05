package dmitry.shnurenko.turnslite;

import dmitry.shnurenko.skipass.SkiPass;

/**
 * The special class which provides methods for checking if ski pass is active(passage allowed) or
 * not(passage forbidden). To add new checker we need register checker in guice module.
 *
 * @author Dmitry Shnurenko
 */
public interface SkiPassChecker {

    /**
     * Checks if ski pass is active(passage allowed) or not(passage forbidden)
     *
     * @param skiPass ski pass which will be checked
     * @return <code>true</code> if ski pass is active(passage allowed), or <code>false</code> if ski pass is not
     * active(passage forbidden)
     */
    boolean check(SkiPass skiPass);
}
