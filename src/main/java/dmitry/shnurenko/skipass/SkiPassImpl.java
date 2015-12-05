package dmitry.shnurenko.skipass;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Objects;

import static dmitry.shnurenko.skipass.SkiPass.LimitType.LIFTS_LIMIT;
import static dmitry.shnurenko.skipass.SkiPass.LimitType.TIME_LIMIT;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.util.UUID.randomUUID;

/**
 * @author Dmitry Shnurenko
 */
class SkiPassImpl implements SkiPass {
    private final String            id;
    private final boolean           isDayOffSkiPass;
    private final LocalDateTime     createdDate;
    private final LimitType         limitType;
    private final SkiPassParameters parameters;

    private int liftCounts;

    SkiPassImpl(SkiPassParameters parameters) {
        this.id = randomUUID().toString();
        this.parameters = parameters;
        this.createdDate = LocalDateTime.now();

        DayOfWeek dayOfWeek = createdDate.getDayOfWeek();

        isDayOffSkiPass = SATURDAY.equals(dayOfWeek) || SUNDAY.equals(dayOfWeek);

        liftCounts = parameters.getLiftCount();

        limitType = liftCounts > 0 ? LIFTS_LIMIT : TIME_LIMIT;
    }

    /** {inheritDoc} */
    @Override
    public String getId() {
        return id;
    }

    /** {inheritDoc} */
    @Override
    public boolean isDayOffSkiPass() {
        return isDayOffSkiPass;
    }

    /** {inheritDoc} */
    @Override
    public LocalDateTime getCreationDate() {
        return createdDate;
    }

    /** {inheritDoc} */
    @Override
    public Type getType() {
        return parameters.getType();
    }

    /** {inheritDoc} */
    @Override
    public LimitType getLimitType() {
        return limitType;
    }

    /** {inheritDoc} */
    @Override
    public LocalDateTime getStartActiveTime() {
        return parameters.getStartActiveTime();
    }

    /** {inheritDoc} */
    @Override
    public LocalDateTime getEndActiveTime() {
        return parameters.getEndActiveTime();
    }

    /** {inheritDoc} */
    @Override
    public int getLiftCounts() {
        return liftCounts;
    }

    /** {inheritDoc} */
    @Override
    public void reduceLiftCounts() {
        liftCounts--;
    }

    /** {inheritDoc} */
    @Override
    public boolean equals(Object otherSkiPass) {
        return this == otherSkiPass || otherSkiPass instanceof SkiPassImpl &&
                Objects.equals(id, ((SkiPassImpl) otherSkiPass).getId());
    }

    /** {inheritDoc} */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
