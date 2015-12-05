package dmitry.shnurenko.turnslite;

import dmitry.shnurenko.skipass.SkiPass;

import java.time.LocalDateTime;

/**
 * @author Dmitry Shnurenko
 */
public final class TimeLimitChecker implements SkiPassChecker {

    @Override
    public boolean check(SkiPass skiPass) {
        LocalDateTime currentTime = LocalDateTime.now();

        LocalDateTime startActiveTime = skiPass.getStartActiveTime();
        LocalDateTime endActiveTime = skiPass.getEndActiveTime();

        return currentTime.isAfter(startActiveTime) && currentTime.isBefore(endActiveTime);
    }
}
