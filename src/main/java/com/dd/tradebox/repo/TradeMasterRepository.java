package com.dd.tradebox.repo;


import com.dd.tradebox.data.TradeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface TradeMasterRepository extends JpaRepository<TradeMaster, Integer> {

    @Query("""
    SELECT SUM(t.units * t.pricePerUnit * t.agreedFx)
      FROM TradeMaster t
     WHERE t.buyOrSale = :buyOrSale
       AND ((t.settlementDate IN (:settlementDateWorld) AND t.currency NOT IN ('SAR', 'AED'))
            OR
            (t.settlementDate IN (:settlementDateArabWorld) AND t.currency IN ('SAR', 'AED')))
    """)
    BigDecimal findSumOfAmountBySettlementDate(@Param("buyOrSale") char buyOrSale,
                                               @Param("settlementDateWorld") Set<LocalDate> settlementDateWorld,
                                               @Param("settlementDateArabWorld") Set<LocalDate> settlementDateArabWorld);

    @Query(value = """
        SELECT
            ENTITY,
            SUM(UNITS * PRICE_PER_UNIT * AGREED_FX) AS TOTAL_AMOUNT,
            RANK() OVER (ORDER BY SUM(UNITS * PRICE_PER_UNIT * AGREED_FX) DESC) AS RANK
        FROM TRADE_MASTER
        WHERE BUY_OR_SALE = 'B'
          AND YEAR(SETTLEMENT_DATE) = :year
        GROUP BY ENTITY
        ORDER BY RANK
    """, nativeQuery = true)
    List<Object[]> findRankedEntitiesByYear(@Param("year") int year);



}
