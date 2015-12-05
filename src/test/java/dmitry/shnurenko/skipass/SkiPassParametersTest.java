package dmitry.shnurenko.skipass;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static dmitry.shnurenko.skipass.SkiPass.Type.TEN_LIFTS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class SkiPassParametersTest {

    @Test
    public void skiPassParametersShouldBeCreatedViaBuilder() {
        LocalDateTime startActiveTime = LocalDateTime.now();
        LocalDateTime endActiveTime = startActiveTime.plusDays(1);

        SkiPassParameters parameters = SkiPassParameters.getBuilder()
                                                        .withStartActiveTime(startActiveTime)
                                                        .withEndActiveTime(endActiveTime)
                                                        .withType(TEN_LIFTS)
                                                        .withLiftCount(10)
                                                        .build();
        SkiPass testSkiPass = new SkiPassImpl(parameters);

        assertThat(testSkiPass.getType(), is(equalTo(TEN_LIFTS)));
        assertThat(testSkiPass.getStartActiveTime(), is(equalTo(startActiveTime)));
        assertThat(testSkiPass.getEndActiveTime(), is(equalTo(endActiveTime)));
        assertThat(testSkiPass.getLiftCounts(), is(equalTo(10)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionShouldBeThrownWhenTypeIsNull() {
        SkiPassParameters.getBuilder().withLiftCount(10).build();
    }
}