import java.security.MessageDigest;

public class SHA256 extends Hashes {

    @Override
    MessageDigest getAlgorithm() throws Exception {
        return MessageDigest.getInstance("SHA-256");
    }
}
