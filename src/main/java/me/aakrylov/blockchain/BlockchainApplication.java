package me.aakrylov.blockchain;

import me.aakrylov.blockchain.algorithm.Blockchain;
import me.aakrylov.blockchain.model.Block;
import me.aakrylov.blockchain.model.BlockData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class BlockchainApplication {

    private static final Logger logger = LoggerFactory.getLogger(BlockchainApplication.class);

    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        blockchain.addBlock(new Block(LocalDateTime.now(), new BlockData("Andrew", "Alex", "17000.53")));
        blockchain.addBlock(new Block(LocalDateTime.now(), new BlockData("Alex", "Mary", "9147.15")));
        blockchain.addBlock(new Block(LocalDateTime.now(), new BlockData("Andrew", "Helen", "5000.00")));
        blockchain.log();
        if (blockchain.isValid()) {
            logger.info("Blockchain is OK");
        } else {
            logger.warn("Blockchain is INVALID");
        }
    }
}
