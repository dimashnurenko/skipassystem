package dmitry.shnurenko.guiceconfig;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import dmitry.shnurenko.skipass.SkiPass.LimitType;
import dmitry.shnurenko.turnslite.LiftLimitChecker;
import dmitry.shnurenko.turnslite.SkiPassChecker;
import dmitry.shnurenko.turnslite.TimeLimitChecker;

import static com.google.inject.multibindings.MapBinder.newMapBinder;
import static dmitry.shnurenko.skipass.SkiPass.LimitType.LIFTS_LIMIT;
import static dmitry.shnurenko.skipass.SkiPass.LimitType.TIME_LIMIT;

/**
 * @author Dmitry Shnurenko
 */
public class GuiceModuleConfiguration extends AbstractModule {

    /** {inheritDoc} */
    @Override
    protected void configure() {
        MapBinder<LimitType, SkiPassChecker> mapBinder = newMapBinder(binder(), LimitType.class, SkiPassChecker.class);
        mapBinder.addBinding(LIFTS_LIMIT).to(LiftLimitChecker.class);
        mapBinder.addBinding(TIME_LIMIT).to(TimeLimitChecker.class);
    }
}
