package com.dd.tradebox.controller;

import com.dd.tradebox.service.TradeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/trades")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }



    /**
     * Amount in usd settled incoming everyday
     * @param date
     * @return
     */
    @GetMapping(value = "/incoming/{date}")
    public BigDecimal getIncomingUSDAmountForDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return tradeService.getIncomingUSDAmountForDate(date);
    }


    /**
     * Amount in usd settled outgoing everyday
     * @param date
     * @return
     */
    @GetMapping(value = "/outgoing/{date}")
    public BigDecimal getOutgoingUSDAmountForDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return tradeService.getOutgoingUSDAmountForDate(date);
    }


    /**
     * Ranking of entities
     * TODO: The data is not too large, and the best approach would be
     *      to keep yearly data stored in the table and retrieved when needed.
     *      only current year need to be calculated.
     *      Also, this can be returned in a class,
     *      so that it can be returned in json key value pair
     * @param year
     * @return
     */
    @GetMapping(value = "/ranking/{year}", produces = "application/json")
    public List<Object[]> getRankedEntitiesByYear(@PathVariable int year) {
        return tradeService.findRankedEntitiesByYear(year);
    }

}
