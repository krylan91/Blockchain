package me.aakrylov.blockchain.algorithm;

import lombok.val;
import me.aakrylov.blockchain.model.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class representing model of blockchain technology.
 */
public class Blockchain {

    private static final Logger logger = LoggerFactory.getLogger(Blockchain.class);

    private static final String LEADING_ZERO = "0";

    private static Blockchain instance;
    private final List<Block> chain;
    private Integer complexityCoefficient = 1;

    private Blockchain() {
        this.chain = new ArrayList<>();
        this.chain.add(new Block(null));
    }

    /**
     * Singleton constructor.
     *
     * @return Instance of {@link Blockchain}
     */
    public static Blockchain getInstance() {
        if (instance == null) {
            instance = new Blockchain();
        }
        return instance;
    }

    /**
     * Adding new block to blockchain.
     * While adding new block it calculates its own hash and sets the hash of previous block into new block.
     * <br>
     * For emission purpose hash code must start with some number of leading zeros.
     * <br>
     * Complexity of calculation (i.e. number of leading zeros) increases every 100 blocks.
     * <br>
     * This process of calculation is in fact a proof-of-work because you must spend some time calculating the
     * so called <b>nonce</b> value, which makes hash code start with leading zeros.
     * While calculating you spend time but get some cryptocurrency. Or any other thing based on this blockchain.
     * <br>
     * Or nothing. This is just a sandbox, remember?
     *
     * @param block new block to add into blockchain.
     */
    public void addBlock(Block block) {
        block.setPrevHash(this.getLastBlock().getHash());
        block.setHash(block.createHash());
        while (!block.getHash().startsWith(LEADING_ZERO.repeat(complexityCoefficient))) {
            int nonce = block.getNonce();
            nonce += 1;
            block.setNonce(nonce);
            block.setHash(block.createHash());
            logger.trace("Block {}: Increasing nonce to {}", this.chain.size(), block.getNonce());
        }
        logger.debug("Calculated block hash! {}", block.getHash());
        if (this.chain.size() % 100 == 0) {
            increaseComplexity();
            logger.trace("Increasing complexity to {}", complexityCoefficient);
        }
        this.chain.add(block);
    }

    private Block getLastBlock() {
        return this.chain.get(this.chain.size() - 1);
    }

    private void increaseComplexity() {
        this.complexityCoefficient += 1;
    }

    /**
     * Checks if blockchain is valid or not.
     * Nobody fucks with blockchain.
     * <br>
     * Ya know, what does that mean, do you?
     *
     * @return is blockchain valid or not
     */
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
        String msg = this.chain.stream()
                .map(Block::toString)
                .collect(Collectors.joining(",\n"));
        logger.info(msg);
    }
}
