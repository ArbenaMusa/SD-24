import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public abstract class Hashes {
    abstract MessageDigest getAlgorithm() throws Exception;

    String getHash(String text) {
        try {
            MessageDigest md = getAlgorithm();
            md.update(text.getBytes());
            byte[] digest = md.digest();

            Formatter formatter = new Formatter();

            for(byte b : digest) {
                formatter.format("%02x", b);
            }

            String result = formatter.toString();

            return result;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
