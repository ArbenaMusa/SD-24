import java.math.BigInteger;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Formatter;

public class Main {
    public static void main(String[] args) {
        KeyPair generatedKeys = generateKeys(1024);

        BigInteger e = generatedKeys.publicKey;
        BigInteger n = generatedKeys.modulus;
        BigInteger d = generatedKeys.privateKey;

        String text = "Teksti i cili do te nenshkruhet";

        String hashedText = generateHash(text);

        System.out.println("Hash i tekstit eshte:  " + hashedText);


        BigInteger signed = sign(hashedText, d, n);

        boolean isTheRightSignature = verifySignature(text, e, n, signed);

        System.out.println("Verified signature: " +isTheRightSignature);
    }

    public static class KeyPair {
        private final BigInteger publicKey;
        private final BigInteger modulus;
        private final BigInteger privateKey;

        public KeyPair(BigInteger publicKey, BigInteger modulus, BigInteger privateKey) {
            this.publicKey = publicKey;
            this.modulus = modulus;
            this.privateKey = privateKey;
        }

        public BigInteger getPublicKey() {
            return publicKey;
        }

        public BigInteger getModulus() {
            return modulus;
        }

        public BigInteger getPrivateKey() {
            return privateKey;
        }

    }

    public static KeyPair generateKeys(int keyLength) {
        SecureRandom random = new SecureRandom();

        BigInteger p = BigInteger.probablePrime(keyLength / 2, random);
        BigInteger q = BigInteger.probablePrime(keyLength / 2, random);

        BigInteger n = p.multiply(q);

        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        BigInteger e;

        do {
            e = BigInteger.probablePrime(keyLength, random);
        } while (e.compareTo(BigInteger.ONE) <= 0 || e.compareTo(phi) >= 0 || e.gcd(phi).compareTo(BigInteger.ONE) > 0);


        BigInteger d = e.modInverse(phi);

        return new KeyPair(e, n, d);
    }

    public static String generateHash( String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(text.getBytes());
            byte[] hashedText = digest.digest();

            Formatter formatter = new Formatter();
            for(byte b : hashedText) {
                formatter.format("%02x", b);
            }

            String result = formatter.toString();

            return result;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static BigInteger sign(String text, BigInteger d, BigInteger n) {
        BigInteger textToBigInt = new BigInteger(text.getBytes());

        BigInteger signedText = textToBigInt.modPow(d, n); // signed

        return signedText;
    }

    public static  boolean verifySignature(String text, BigInteger e, BigInteger n, BigInteger signedText) {
        BigInteger initialText = signedText.modPow(e, n); // unsigned

        String hashedText = generateHash(text);

        return initialText.equals(new BigInteger(hashedText.getBytes()));
    }

}