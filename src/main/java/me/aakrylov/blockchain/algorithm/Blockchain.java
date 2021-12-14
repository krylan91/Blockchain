package me.aakrylov.blockchain.algorithm;

import lombok.val;
import me.aakrylov.blockchain.model.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;

public class Blockchain {

    private static final Logger logger = LoggerFactory.getLogger(Blockchain.class);

    private static final String LEADING_ZERO = "0";
    private static final Long AVERAGE_CALCULATION_DURATION_MS = 10L;

    private final List<Block> chain;
    private Integer complexityCoefficient = 1;

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.chain.add(new Block(LocalDateTime.now(), null));
    }

    public void addBlock(Block block) {
        block.setPrevHash(this.getLastBlock().getHash());
        long startTime = System.nanoTime();
        block.setHash(block.createHash());
        while (!block.getHash().startsWith(LEADING_ZERO.repeat(complexityCoefficient))) {
            logger.trace("Block hash is {}, nonce is {}, mining...", block.getHash(), block.getNonce());
            int nonce = block.getNonce();
            nonce += 1;
            block.setNonce(nonce);
            block.setHash(block.createHash());
        }
        logger.debug("Block hash is {}", block.getHash());
        long endTime = System.nanoTime();
        long hashCalculationTimeMs = (endTime - startTime) / 1000000;
        if (hashCalculationTimeMs < AVERAGE_CALCULATION_DURATION_MS) {
            increaseComplexity();
        }
        this.chain.add(block);
    }

    private Block getLastBlock() {
        return this.chain.get(this.chain.size() - 1);
    }

    private void increaseComplexity() {
        this.complexityCoefficient += 1;
    }

    public boolean isValid() {
        for (int i = 1; i < this.chain.size(); i++) {
            val currentBlock = this.chain.get(i);
            val prevBlock = this.chain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.createHash()) || !prevBlock.getHash().equals(currentBlock.getPrevHash())) {
                return false;
            }
        }
        return true;
    }

    public void log() {
        String msg = this.chain.toString();
        logger.info(msg);
    }
}
