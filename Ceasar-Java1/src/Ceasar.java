public class Ceasar {
    private static int modulo(int x, int n) {
        if(x < 0) {
            return n - Math.abs(x) % n;
        }

        return x % n;
    }

    private static char shift(char ch, int key) {
        if(Character.isUpperCase(ch)) {
            return (char) ('A' + modulo(ch - 'A' + key, 26));
        }

        return (char) ('a' + modulo(ch - 'a' + key, 26));
    }


    public static String encrypt(String plaintext, int key) {
        char [] cipherText = new char[plaintext.length()];

        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);

            if (Character.isLetter(ch)) {
                cipherText[i] = shift(ch, key);
            }
            else {
                cipherText[i] = ch;
            }
        }
        return new String(cipherText);
    }

    public static String decrypt(String cipherText, int key) {
        return encrypt(cipherText, -key);
    }
}
