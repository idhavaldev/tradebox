package com.dd.tradebox.data;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TRADE_MASTER")
@Builder
@Getter
public class TradeMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRADE_ID")
    private Integer tradeId;

    @Column(name = "ENTITY", nullable = false, length = 20)
    private String entity;

    @Column(name = "BUY_OR_SALE", nullable = false)
    private char buyOrSale;

    @Column(name = "AGREED_FX", nullable = false, precision = 10, scale = 2)
    private BigDecimal agreedFx;

    @Column(name = "CURRENCY", nullable = false, length = 3)
    private String currency;

    @Column(name = "INSTRUCTION_DATE", nullable = false)
    private LocalDate instructionDate;

    @Column(name = "SETTLEMENT_DATE", nullable = false)
    private LocalDate settlementDate;

    @Column(name = "UNITS", nullable = false)
    private Integer units;

    @Column(name = "PRICE_PER_UNIT", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerUnit;
}
