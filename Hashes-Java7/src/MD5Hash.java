import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hash extends Hashes{
    @Override
    MessageDigest getAlgorithm() throws Exception {
        return MessageDigest.getInstance("MD5");
    }
}
