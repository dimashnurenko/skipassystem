package dmitry.shnurenko.system;

import com.google.inject.Singleton;
import dmitry.shnurenko.skipass.SkiPass;
import dmitry.shnurenko.skipass.type.Type;
import dmitry.shnurenko.turnslite.SkiPassScanListener;
import dmitry.shnurenko.turnslite.Turnslite;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static dmitry.shnurenko.skipass.SkiPassCreator.create;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

/**
 * @author Dmitry Shnurenko
 */
@Singleton
public final class SystemImpl implements System, SkiPassScanListener {

    private final Turnslite                   turnslite;
    private final Map<LocalDateTime, SkiPass> successPassages;
    private final Map<LocalDateTime, SkiPass> failPassages;
    private final List<SkiPass>               blockedSkiPasses;
    private final List<SkiPass>               createdSkiPasses;

    @Inject
    public SystemImpl(Turnslite turnslite) {
        turnslite.addScanListener(this);

        this.turnslite = turnslite;
        this.successPassages = new HashMap<>();
        this.failPassages = new HashMap<>();
        this.blockedSkiPasses = new ArrayList<>();
        this.createdSkiPasses = new ArrayList<>();
    }

    @Nonnull
    @Override
    public SkiPass createSkiPass(Type type) {
        SkiPass skiPass = create(type);

        createdSkiPasses.add(skiPass);

        return skiPass;
    }

    @Override
    public void blockSkiPass(SkiPass skiPass) {
        blockedSkiPasses.add(skiPass);
    }

    @Nonnull
    @Override
    public List<SkiPass> findSuccessPassagesForPeriod(LocalDateTime from, LocalDateTime until, Type type) {
        return findPassagesForPeriod(successPassages, from, until, type);
    }

    private List<SkiPass> findPassagesForPeriod(Map<LocalDateTime, SkiPass> passagesMap,
                                                LocalDateTime from,
                                                LocalDateTime until,
                                                Type type) {
        requireNonNull(type);
        Predicate<LocalDateTime> mapToPeriod = date -> date.isAfter(from) && date.isBefore(until);

        List<LocalDateTime> localDateTimes = passagesMap.keySet().stream().filter(mapToPeriod).collect(toList());

        return passagesMap.entrySet().stream()
                          .filter(entry -> localDateTimes.contains(entry.getKey()))
                          .filter(entry -> type.equals(entry.getValue().getType()))
                          .map(Map.Entry::getValue)
                          .collect(toList());
    }

    @Nonnull
    @Override
    public List<SkiPass> findFailPassagesForPeriod(LocalDateTime from, LocalDateTime until, Type type) {
        return findPassagesForPeriod(failPassages, from, until, type);
    }

    @Nonnull
    @Override
    public List<SkiPass> findCreatedPassesForPeriod(LocalDateTime from, LocalDateTime until) {
        Predicate<SkiPass> createdInPeriodPredicate = skiPass -> {
            LocalDateTime createdDate = skiPass.getCreationTime();

            return createdDate.isAfter(from) && createdDate.isBefore(until);
        };

        return createdSkiPasses.stream()
                               .filter(createdInPeriodPredicate)
                               .collect(toList());
    }

    @Override
    public void onSkiPassScanSuccess(SkiPass skiPass) {
        if (blockedSkiPasses.contains(skiPass)) {
            onSkiPassScanFail(skiPass);

            return;
        }
        successPassages.put(LocalDateTime.now(), skiPass);
        turnslite.turnGreenLight();
    }

    @Override
    public void onSkiPassScanFail(SkiPass skiPass) {
        failPassages.put(LocalDateTime.now(), skiPass);
        turnslite.turnRedLight();
    }
}
