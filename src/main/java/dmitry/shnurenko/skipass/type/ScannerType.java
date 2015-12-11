package dmitry.shnurenko.skipass.type;

/**
 * The type is necessary to define ski pass scanner. To add new custom scanner you have to define new scanner
 * type or use existing. To register new scanner type you have to add new scanner associated to new type and
 * register it in configuration see {@link dmitry.shnurenko.guiceconfig.GuiceModuleConfiguration}
 *
 * @author Dmitry Shnurenko
 */
public enum ScannerType {
    /**
     * The type which defines lifts scanner to scan ski passes. The scan result depends on count of lifts on
     * ski pass.
     */
    LIFTS_SCANNER,
    /**
     * The type which defines lifts scanner to scan ski passes. The scan result depends on time when ski pass
     * was scanned.
     */
    TIME_SCANNER
}
