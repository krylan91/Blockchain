package me.aakrylov.blockchain.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;

@Getter
@Setter
public class Block {

    private LocalDateTime timestamp;
    private BlockData data;
    private String hash;
    private String prevHash;
    private int nonce;

    public Block(BlockData data) {
        this.timestamp = LocalDateTime.now();
        this.data = data;
        this.hash = createHash();
        this.prevHash = "";
    }

    public String createHash() {
        return DigestUtils.sha256Hex(this.prevHash + this.timestamp + this.data + this.nonce);
    }

    @Override
    public String toString() {
        return "Block{" +
                "timestamp=" + timestamp +
                ", data=" + data +
                ", hash='" + hash + '\'' +
                ", prevHash='" + prevHash + '\'' +
                '}';
    }
}
