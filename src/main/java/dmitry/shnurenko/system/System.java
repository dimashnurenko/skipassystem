package dmitry.shnurenko.system;

import com.google.inject.ImplementedBy;
import dmitry.shnurenko.skipass.SkiPass;
import dmitry.shnurenko.skipass.type.Type;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The system representation which provides methods for creating ski passes with different types, block existing ski
 * passes and get information about created ski passes and about count of success and fail passages in defined
 * period.
 *
 * @author Dmitry Shnurenko
 */
@ImplementedBy(SystemImpl.class)
public interface System {

    /**
     * Creates ski pass with passed type.
     *
     * @param type of ski pass which will be created
     * @return an instance of {@link SkiPass}
     */
    @Nonnull SkiPass createSkiPass(Type type);

    /**
     * Blocks ski pass. If ski pass is blocked the passage is not allowed.
     *
     * @param skiPass ski pass which will be blocked
     */
    void blockSkiPass(SkiPass skiPass);

    /**
     * Returns list of ski passes which had success passages during defined period and which have defined type.
     *
     * @param from  the start of period
     * @param until the end of period
     * @param type  ski pass type
     * @return list of ski passes
     * @throws NullPointerException if type is null
     */
    @Nonnull List<SkiPass> findSuccessPassagesForPeriod(LocalDateTime from, LocalDateTime until, Type type);

    /**
     * Returns list of ski passes which had fail passages during defined period and which have defined type.
     *
     * @param from  the start of period
     * @param until the end of period
     * @param type  ski pass type
     * @return list of ski passes
     * @throws NullPointerException if type is null
     */
    @Nonnull List<SkiPass> findFailPassagesForPeriod(LocalDateTime from, LocalDateTime until, Type type);

    /**
     * Returns list of created ski passes during defined period.
     *
     * @param from  the start of period
     * @param until the end of period
     * @return list of ski passes
     */
    @Nonnull List<SkiPass> findCreatedPassesForPeriod(LocalDateTime from, LocalDateTime until);
}
