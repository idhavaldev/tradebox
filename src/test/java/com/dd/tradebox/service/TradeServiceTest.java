package com.dd.tradebox.service;

import com.dd.tradebox.repo.TradeMasterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
class TradeServiceTest {

    // TODO: These are basic test cases we can enhance for a quality coverage like spying some value
    @MockitoBean
    private TradeMasterRepository tradeMasterRepo;

    @Autowired
    private TradeService tradeService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetIncomingUSDAmountForDate_weekday() {
        LocalDate date = LocalDate.of(2024, 12, 23);
        Mockito.when(tradeMasterRepo.findSumOfAmountBySettlementDate(eq('B'), any(Set.class), any(Set.class)))
                .thenReturn(BigDecimal.valueOf(1500.75));

        BigDecimal result = tradeService.getIncomingUSDAmountForDate(date);

        assertEquals(BigDecimal.valueOf(1500.75), result);
        Mockito.verify(tradeMasterRepo).findSumOfAmountBySettlementDate(eq('B'), any(Set.class), any(Set.class));
    }

    @Test
    void testGetOutgoingUSDAmountForDate_arabWeekday() {
        LocalDate date = LocalDate.of(2024, 12, 22); // Sunday (Arab weekday)
        Mockito.when(tradeMasterRepo.findSumOfAmountBySettlementDate(eq('S'), any(Set.class), any(Set.class)))
                .thenReturn(BigDecimal.valueOf(2000.50));

        BigDecimal result = tradeService.getOutgoingUSDAmountForDate(date);

        assertEquals(BigDecimal.valueOf(2000.50), result);
        Mockito.verify(tradeMasterRepo).findSumOfAmountBySettlementDate(eq('S'), any(Set.class), any(Set.class));
    }

    @Test
    void testFindRankedEntitiesByYear() {
        int year = 2024;
        List<Object[]> mockResult = List.of(new Object[]{"EntityA", BigDecimal.valueOf(5000.00)},
                new Object[]{"EntityB", BigDecimal.valueOf(4000.00)});
        Mockito.when(tradeMasterRepo.findRankedEntitiesByYear(year)).thenReturn(mockResult);

        List<Object[]> result = tradeService.findRankedEntitiesByYear(year);

        assertEquals(mockResult, result);
        Mockito.verify(tradeMasterRepo).findRankedEntitiesByYear(year);
    }
}