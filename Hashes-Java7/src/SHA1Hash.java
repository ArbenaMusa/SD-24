import java.security.MessageDigest;

public class SHA1Hash extends Hashes{
    @Override
    MessageDigest getAlgorithm() throws Exception {
        return MessageDigest.getInstance("SHA1");
    }
}
