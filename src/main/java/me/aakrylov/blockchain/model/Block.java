package me.aakrylov.blockchain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Block {

    private LocalDateTime timestamp;
    private BlockData data;
    private String hash;
    private String prevHash;
    private int nonce;

    public Block(LocalDateTime timestamp, BlockData data) {
        this.timestamp = timestamp;
        this.data = data;
        this.hash = createHash();
        this.prevHash = "";
    }

    public String createHash() {
        return DigestUtils.sha256Hex(this.prevHash + this.timestamp + this.data + this.nonce);
    }
}
