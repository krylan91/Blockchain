package me.aakrylov.blockchain.algorithm;

import me.aakrylov.blockchain.model.Block;
import me.aakrylov.blockchain.model.BlockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class BlockchainTest {

    private Blockchain blockchain;
    private BlockData data;

    @BeforeEach
    void setUp() {
        blockchain = new Blockchain();
        data = mock(BlockData.class);

    }

    @Test
    void shouldAddNewBlockAndRemainValid() {
        blockchain.addBlock(new Block(LocalDateTime.now(), data));

        assertTrue(blockchain.isValid());
    }

    @Test
    void shouldAddNewBlockAndBecomeInvalid() {
        Block newBlock = new Block(LocalDateTime.now(), data);
        blockchain.addBlock(newBlock);
        newBlock.setHash("123");

        assertFalse(blockchain.isValid());
    }

}