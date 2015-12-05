package dmitry.shnurenko.skipass;

/**
 * The factory which allows create different types of ski passes.
 *
 * @author Dmitry Shnurenko
 */
public class SkiPassFactory {

    /**
     * Creates sky pass.
     *
     * @param parameters parameters contains information for time limit ski pass: start active time and end active
     *                   time and also type of ski pass.
     * @return an instance of {@link SkiPass}
     */
    public SkiPass createSkiPass(SkiPassParameters parameters) {
        return new SkiPassImpl(parameters);
    }
}
