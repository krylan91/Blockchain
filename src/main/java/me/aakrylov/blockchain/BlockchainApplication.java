package me.aakrylov.blockchain;

import me.aakrylov.blockchain.algorithm.Blockchain;
import me.aakrylov.blockchain.model.Block;
import me.aakrylov.blockchain.model.BlockData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockchainApplication {

    private static final Logger logger = LoggerFactory.getLogger(BlockchainApplication.class);

    public static void main(String[] args) {
        Blockchain blockchain = Blockchain.getInstance();
        blockchain.addBlock(new Block(new BlockData("Andrew", "Alex", "17000.53")));
        blockchain.addBlock(new Block(new BlockData("Alex", "Mary", "9147.15")));
        blockchain.addBlock(new Block(new BlockData("Andrew", "Helen", "5000.00")));
        blockchain.log();
        if (blockchain.isValid()) {
            logger.info("Blockchain is OK");
        } else {
            logger.warn("Blockchain is INVALID");
        }
    }
}
