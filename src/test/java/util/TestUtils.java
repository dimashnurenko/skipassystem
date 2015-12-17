package util;

import com.google.inject.Guice;
import com.google.inject.Injector;
import dmitry.shnurenko.guiceconfig.GuiceModuleConfiguration;
import dmitry.shnurenko.system.System;
import dmitry.shnurenko.system.SystemImpl;
import dmitry.shnurenko.turnslite.Turnslite;

/**
 * @author Dmitry Shnurenko
 */
public class TestUtils {

    private static Injector injector = Guice.createInjector(new GuiceModuleConfiguration());

    private TestUtils() {
        throw new UnsupportedOperationException(getClass() + " Can't create instance of util class.");
    }

    public static System createSystem() {
        return new SystemImpl(createTurnslite());
    }

    public static Turnslite createTurnslite() {
        return injector.getInstance(Turnslite.class);
    }
}
