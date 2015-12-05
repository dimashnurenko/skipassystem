package dmitry.shnurenko.skipass;

import dmitry.shnurenko.skipass.SkiPass.Type;

import java.time.LocalDateTime;

/**
 * The entity contains needed information for ski pass in depends on ski pass type. For time limit ski passes
 * the field liftCount is <code>null</code> and for lift limit ski passes the fields startActiveTime and endActiveTime
 * are <code>null</code>. The ski pass type never can be <code>null</code>. If type is <code>null</code>
 * {@link java.lang.IllegalArgumentException} should be thrown.
 *
 * @author Dmitry Shnurenko
 */
public final class SkiPassParameters {

    private int           liftCount;
    private LocalDateTime startActiveTime;
    private LocalDateTime endActiveTime;
    private Type          type;

    private SkiPassParameters() {
    }

    public int getLiftCount() {
        return liftCount;
    }

    public LocalDateTime getStartActiveTime() {
        return startActiveTime;
    }

    public LocalDateTime getEndActiveTime() {
        return endActiveTime;
    }

    public Type getType() {
        return type;
    }

    public static SkiPassParametersBuilder getBuilder() {
        return new SkiPassParameters().new SkiPassParametersBuilder();
    }

    public class SkiPassParametersBuilder {

        private SkiPassParametersBuilder() {
        }

        public SkiPassParametersBuilder withLiftCount(int liftCount) {
            SkiPassParameters.this.liftCount = liftCount;
            return this;
        }

        public SkiPassParametersBuilder withStartActiveTime(LocalDateTime startActiveTime) {
            SkiPassParameters.this.startActiveTime = startActiveTime;
            return this;
        }

        public SkiPassParametersBuilder withEndActiveTime(LocalDateTime endActiveTime) {
            SkiPassParameters.this.endActiveTime = endActiveTime;
            return this;
        }

        public SkiPassParametersBuilder withType(Type type) {
            SkiPassParameters.this.type = type;
            return this;
        }

        public SkiPassParameters build() {
            if (SkiPassParameters.this.type == null) {
                throw new IllegalArgumentException(SkiPassParametersBuilder.class + ": Ski pass type cannot be null");
            }

            return SkiPassParameters.this;
        }
    }
}
