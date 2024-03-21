public class OneTimePad {

    public static String encrypt(String plaintext, String key) {
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char keyChar = key.charAt(i);

            // plaintext XAND key
            char cipherChar  = (char) ((plainChar - 'A' + keyChar - 'A') % 26 + 'A');

            cipherText.append(cipherChar);
        }
        return cipherText.toString();
    }

    public static String decrypt(String cipherText, String key) {
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            char cipherChar = cipherText.charAt(i);
            char keyChar = key.charAt(i);

            // ((plainChar - 'A' + keyChar - 'A') % 26 + 'A')
            char plainChar = (char) ((cipherChar + 'A' - keyChar + 'A') % 26 + 'A');

            plainText.append(plainChar);

        }
        return plainText.toString();
    }
}
