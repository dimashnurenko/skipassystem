package dmitry.shnurenko.system;

import com.google.inject.Singleton;
import dmitry.shnurenko.skipass.SkiPass;
import dmitry.shnurenko.skipass.SkiPass.Type;
import dmitry.shnurenko.turnslite.SkiPassScanListener;
import dmitry.shnurenko.turnslite.Turnslite;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * @author Dmitry Shnurenko
 */
@Singleton
final class SystemImpl implements System, SkiPassScanListener {

    private final SkiPassCreator              skiPassCreator;
    private final Turnslite                   turnslite;
    private final Map<LocalDateTime, SkiPass> successPassages;
    private final Map<LocalDateTime, SkiPass> failPassages;
    private final List<SkiPass>               blockedSkiPasses;
    private final List<SkiPass>               createdSkiPasses;

    @Inject
    public SystemImpl(Turnslite turnslite, SkiPassCreator skiPassCreator) {
        turnslite.addScanListener(this);

        this.skiPassCreator = skiPassCreator;
        this.turnslite = turnslite;
        this.successPassages = new HashMap<>();
        this.failPassages = new HashMap<>();
        this.blockedSkiPasses = new ArrayList<>();
        this.createdSkiPasses = new ArrayList<>();
    }

    /** {inheritDoc} */
    @Override
    public SkiPass createSkiPass(Type type) {
        SkiPass skiPass = skiPassCreator.create(type);

        createdSkiPasses.add(skiPass);

        return skiPass;
    }

    /** {inheritDoc} */
    @Override
    public void blockSkiPass(SkiPass skiPass) {
        blockedSkiPasses.add(skiPass);
    }

    /** {inheritDoc} */
    @Override
    public List<SkiPass> getSuccessPassagesForPeriod(LocalDateTime from, LocalDateTime until, Type type) {
        return getPassagesForPeriod(successPassages, from, until, type);
    }

    private List<SkiPass> getPassagesForPeriod(Map<LocalDateTime, SkiPass> passagesMap,
                                               LocalDateTime from,
                                               LocalDateTime until,
                                               Type type) {
        Predicate<LocalDateTime> mapToPeriod = date -> date.isAfter(from) && date.isBefore(until);

        List<LocalDateTime> localDateTimes = passagesMap.keySet().stream().filter(mapToPeriod).collect(toList());

        return passagesMap.entrySet().stream()
                          .filter(entry -> localDateTimes.contains(entry.getKey()))
                          .filter(entry -> entry.getValue().getType().equals(type))
                          .map(Map.Entry::getValue)
                          .collect(toList());
    }

    /** {inheritDoc} */
    @Override
    public List<SkiPass> getFailPassagesForPeriod(LocalDateTime from, LocalDateTime until, Type type) {
        return getPassagesForPeriod(failPassages, from, until, type);
    }

    /** {inheritDoc} */
    @Override
    public List<SkiPass> getCreatedPassesForPeriod(LocalDateTime from, LocalDateTime until) {
        Predicate<SkiPass> createdInPeriodPredicate = skiPass -> {
            LocalDateTime createdDate = skiPass.getCreationDate();

            return createdDate.isAfter(from) && createdDate.isBefore(until);
        };

        return createdSkiPasses.stream()
                               .filter(createdInPeriodPredicate)
                               .collect(toList());
    }

    /** {inheritDoc} */
    @Override
    public void onSkiPassScanSuccess(SkiPass skiPass) {
        if (blockedSkiPasses.contains(skiPass)) {
            onSkiPassScanFail(skiPass);

            return;
        }
        successPassages.put(LocalDateTime.now(), skiPass);
        turnslite.turnGreenLight();
    }

    /** {inheritDoc} */
    @Override
    public void onSkiPassScanFail(SkiPass skiPass) {
        failPassages.put(LocalDateTime.now(), skiPass);
        turnslite.turnRedLight();
    }
}
