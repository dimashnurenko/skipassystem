package dmitry.shnurenko.skipass.type;

import javax.annotation.Nonnull;

/**
 * The common interface which represents ski pass type and provides methods to get information about scanner type see
 * {@link ScannerType} and current type.
 *
 * @author Dmitry Shnurenko
 */
public interface Type {
    /**
     * Returns scanner type which associated to current type. For each scanner type is associated special scanner
     * which used to scan ski passes.
     */
    @Nonnull ScannerType getScannerType();
}
