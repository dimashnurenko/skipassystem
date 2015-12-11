package dmitry.shnurenko.skipass;

import dmitry.shnurenko.skipass.type.ScannerType;
import dmitry.shnurenko.skipass.type.Type;
import junitx.extensions.EqualsHashCodeTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractSkiPassTest {

    @Test
    public void skiPassCreationDateShouldBeReturned() {
        LocalDateTime creationTimeExpected = LocalDateTime.now();

        DummySkiPass skiPass = new DummySkiPass();

        LocalDateTime creationTimeActual = skiPass.getCreationTime();

        assertThat(creationTimeActual.getYear(), is(equalTo(creationTimeExpected.getYear())));
        assertThat(creationTimeActual.getMonth(), is(equalTo(creationTimeExpected.getMonth())));
        assertThat(creationTimeActual.getDayOfMonth(), is(equalTo(creationTimeExpected.getDayOfMonth())));
        assertThat(creationTimeActual.getHour(), is(equalTo(creationTimeExpected.getHour())));
        assertThat(creationTimeActual.getMinute(), is(equalTo(creationTimeExpected.getMinute())));
    }

    public static class EqualsAndHashCodeSkiPassTest extends EqualsHashCodeTestCase {

        public EqualsAndHashCodeSkiPassTest(String name) {
            super(name);
        }

        @Override
        protected Object createInstance() throws Exception {
            return new DummySkiPass();
        }

        @Override
        protected Object createNotEqualInstance() throws Exception {
            return new DummySkiPass();
        }
    }

    private static class DummySkiPass extends AbstractSkiPass {
        public DummySkiPass() {
        }

        @Nonnull
        @Override
        public Type getType() {
            throw new UnsupportedOperationException("The method unsupported in this mode");
        }

        @Nonnull
        @Override
        public ScannerType getScannerType() {
            throw new UnsupportedOperationException("The method unsupported in this mode");
        }
    }
}