package dmitry.shnurenko.skipass;

import dmitry.shnurenko.skipass.type.ScannerType;
import dmitry.shnurenko.skipass.type.Type;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

/**
 * @author Dmitry Shnurenko
 */
public interface SkiPass {

    /** Returns ski pass type. */
    @Nonnull Type getType();

    /** Returns scanner type which associated to special scanner which is used to scan ski pass. */
    @Nonnull ScannerType getScannerType();

    /** Returns date and time when ski pass was created. */
    @Nonnull LocalDateTime getCreationTime();
}
