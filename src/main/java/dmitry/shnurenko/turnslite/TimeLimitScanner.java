package dmitry.shnurenko.turnslite;

import dmitry.shnurenko.skipass.TimeLimitSkiPass;
import dmitry.shnurenko.turnslite.Turnslite.ScanStatus;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

/**
 * The class contains business logic which checks activity of time limit ski passes.
 *
 * @author Dmitry Shnurenko
 */
public class TimeLimitScanner implements SkiPassScanner<TimeLimitSkiPass> {

    @Nonnull
    @Override
    public ScanStatus scan(@Nonnull TimeLimitSkiPass skiPass) {
        LocalDateTime currentTime = LocalDateTime.now();

        LocalDateTime startActivity = skiPass.getStartActivePeriod();
        LocalDateTime endActivity = skiPass.getEndActivePeriod();

        return ScanStatus.of(currentTime.isAfter(startActivity) && currentTime.isBefore(endActivity));
    }
}
