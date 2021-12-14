package me.aakrylov.blockchain.model;

import lombok.ToString;

import java.math.BigDecimal;

@ToString
public class BlockData {

    private final String from;
    private final String to;
    private final BigDecimal amount;

    public BlockData(String from, String to, String amount) {
        this.from = from;
        this.to = to;
        this.amount = new BigDecimal(amount);
    }
}
