package dmitry.shnurenko.guiceconfig;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import dmitry.shnurenko.skipass.type.ScannerType;
import dmitry.shnurenko.turnslite.LiftLimitScanner;
import dmitry.shnurenko.turnslite.SkiPassScanner;
import dmitry.shnurenko.turnslite.TimeLimitScanner;

import static com.google.inject.multibindings.MapBinder.newMapBinder;
import static dmitry.shnurenko.skipass.type.ScannerType.LIFTS_SCANNER;
import static dmitry.shnurenko.skipass.type.ScannerType.TIME_SCANNER;

/**
 * @author Dmitry Shnurenko
 */
public class GuiceModuleConfiguration extends AbstractModule {

    /** {inheritDoc} */
    @Override
    protected void configure() {
        MapBinder<ScannerType, SkiPassScanner> mapBinder = newMapBinder(binder(), ScannerType.class,
                                                                        SkiPassScanner.class);
        mapBinder.addBinding(LIFTS_SCANNER).to(LiftLimitScanner.class);
        mapBinder.addBinding(TIME_SCANNER).to(TimeLimitScanner.class);
    }
}
