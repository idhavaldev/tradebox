INSERT INTO TRADE_MASTER (
    ENTITY, BUY_OR_SALE, AGREED_FX, CURRENCY, INSTRUCTION_DATE,
    SETTLEMENT_DATE, UNITS, PRICE_PER_UNIT
)
VALUES (
    'Foo', 'B', 0.50, 'SGP',
    DATE '2016-01-01', DATE '2016-01-02',
    200, 100.25
);


INSERT INTO TRADE_MASTER (
    ENTITY, BUY_OR_SALE, AGREED_FX, CURRENCY, INSTRUCTION_DATE,
    SETTLEMENT_DATE, UNITS, PRICE_PER_UNIT
)
VALUES (
    'Bar', 'S', 0.22, 'AED',
    DATE '2016-01-05', DATE '2016-01-07',
    450, 150.5
);

INSERT INTO TRADE_MASTER (ENTITY, BUY_OR_SALE, AGREED_FX, CURRENCY, INSTRUCTION_DATE, SETTLEMENT_DATE, UNITS, PRICE_PER_UNIT)
VALUES
('Entity1', 'B', 0.5, 'USD', '2016-01-01', '2016-02-01', 100, 200.00),
('Entity2', 'B', 0.6, 'USD', '2016-01-01', '2016-03-01', 150, 150.00),
('Entity3', 'S', 0.7, 'USD', '2016-01-01', '2016-04-01', 200, 100.00),
('Entity1', 'B', 0.8, 'USD', '2016-01-01', '2016-05-01', 50, 300.00);