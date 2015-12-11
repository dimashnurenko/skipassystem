package dmitry.shnurenko.skipass;

import dmitry.shnurenko.skipass.type.ScannerType;
import dmitry.shnurenko.skipass.type.TimeLimitType;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

/**
 * The class contains information about ski passes which depend on time when ski pass was scanned.
 *
 * @author Dmitry Shnurenko
 */
public class TimeLimitSkiPass extends AbstractSkiPass {

    private final TimeLimitType type;
    private final LocalDateTime startActivePeriod;
    private final LocalDateTime endActivePeriod;

    TimeLimitSkiPass(@Nonnull TimeLimitType type) {
        this.type = type;
        this.startActivePeriod = type.getStartActivePeriod();
        this.endActivePeriod = type.getEndActivePeriod();
    }

    /** Returns time from which ski pass is active. */
    @Nonnull
    public LocalDateTime getStartActivePeriod() {
        return startActivePeriod;
    }

    /** Returns time until which ski pass is active. */
    @Nonnull
    public LocalDateTime getEndActivePeriod() {
        return endActivePeriod;
    }

    @Nonnull
    @Override
    public TimeLimitType getType() {
        return type;
    }

    @Nonnull
    @Override
    public ScannerType getScannerType() {
        return type.getScannerType();
    }
}
