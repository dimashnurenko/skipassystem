package dmitry.shnurenko.skipass;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * The class contains general business logic of all existing ski passes.
 *
 * @author Dmitry Shnurenko
 */
public abstract class AbstractSkiPass implements SkiPass {

    private final String        id;
    private final LocalDateTime creationTime;

    public AbstractSkiPass() {
        this.id = UUID.randomUUID().toString();
        this.creationTime = LocalDateTime.now();
    }

    @Override
    @Nonnull
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (!(otherObject instanceof AbstractSkiPass)) {
            return false;
        }

        AbstractSkiPass that = (AbstractSkiPass) otherObject;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
