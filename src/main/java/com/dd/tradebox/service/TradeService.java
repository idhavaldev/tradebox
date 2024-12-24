package com.dd.tradebox.service;

import com.dd.tradebox.repo.TradeMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TradeService {

    private static final Logger log = LoggerFactory.getLogger(TradeService.class);


    public final TradeMasterRepository tradeMasterRepo;

    public TradeService(TradeMasterRepository tradeMasterRepo) {
        this.tradeMasterRepo = tradeMasterRepo;
    }

    public BigDecimal getIncomingUSDAmountForDate(LocalDate date) {
        Set<LocalDate> worldDate = new HashSet<>();
        Set<LocalDate> arabDate = new HashSet<>();
        if(isDateAWeekday(date)) {
            worldDate = createDateSetForStartOfWeek(date);
        }
        if(isDateAnArabWeekday(date)) {
            arabDate = createDateSetForArabStartOfWeek(date);
        }
        // For weekend date set will be empty because there is no actual settlement
        return tradeMasterRepo.findSumOfAmountBySettlementDate('B', worldDate, arabDate);
    }

    public BigDecimal getOutgoingUSDAmountForDate(LocalDate date) {
        Set<LocalDate> worldDate = new HashSet<>();
        Set<LocalDate> arabDate = new HashSet<>();
        if(isDateAWeekday(date)) {
            log.info("{date} is a weekday");
            worldDate = createDateSetForStartOfWeek(date);
        }
        if(isDateAnArabWeekday(date)) {
            log.info("{date} is an Arab weekday");
            arabDate = createDateSetForArabStartOfWeek(date);
        }
        return tradeMasterRepo.findSumOfAmountBySettlementDate('S', worldDate, arabDate);
    }

    public List<Object[]> findRankedEntitiesByYear(int year) {
        return tradeMasterRepo.findRankedEntitiesByYear(year);
    }


    private boolean isDateAWeekday(LocalDate date) {
        return !date.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    private boolean isDateAnArabWeekday(LocalDate date) {
        return !date.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !date.getDayOfWeek().equals(DayOfWeek.FRIDAY);
    }

    private Set<LocalDate> createDateSetForStartOfWeek(LocalDate date) {
        if(date.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
            return Set.of(date, date.minusDays(1), date.minusDays(2));
        }
        return Set.of(date);
    }

    private Set<LocalDate> createDateSetForArabStartOfWeek(LocalDate date) {
        if(date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            return Set.of(date, date.minusDays(1), date.minusDays(2));
        }
        return Set.of(date);
    }
}
