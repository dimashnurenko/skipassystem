package dmitry.shnurenko.skipass;

import dmitry.shnurenko.skipass.type.LiftsLimitType;
import dmitry.shnurenko.skipass.type.TimeLimitType;
import dmitry.shnurenko.skipass.type.Type;

import javax.annotation.Nonnull;

/**
 * @author Dmitry Shnurenko
 */
public interface SkiPassCreator {

    @Nonnull
    static SkiPass create(@Nonnull Type type) {
        switch (type.getScannerType()) {
            case TIME_SCANNER:
                return new TimeLimitSkiPass((TimeLimitType) type);
            case LIFTS_SCANNER:
                return new LiftsLimitSkiPass((LiftsLimitType) type);
            default:
                throw new IllegalArgumentException(SkiPassCreator.class + "Scanner type is not registered for this" +
                                                           "type. Register new scanner type.");
        }
    }
}
