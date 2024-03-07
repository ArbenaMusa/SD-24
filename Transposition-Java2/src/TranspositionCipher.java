public class TranspositionCipher {
    public static String encrypt(String plainText, int key) {
        int col = key;
        int row = (plainText.length() + key - 1) / key;

        System.out.println("Gjatesia e plaintextit eshte " + plainText.length());
        System.out.println("Numri i rreshtave eshte " + row);

        char [][] cipherMatrix = new char[row][col];

        int pointer = 0;

        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++){
                if(pointer < plainText.length()) {
                    cipherMatrix[r][c] = plainText.charAt(pointer);
                } else {
                    cipherMatrix[r][c] = '0';
                }
                pointer++;
            }
        }

        StringBuilder cipherBuilder = new StringBuilder();

        for(int c = 0; c < col; c++) {
            for(int r = 0; r < row; r++) {
                cipherBuilder.append(cipherMatrix[r][c]);
            }
        }

        return cipherBuilder.toString();
    }

    public static String decrypt(String cipherText, int key) {
        int col = key;
        int row = cipherText.length() / key;
        int pointer = 0;

        char [][] plainTextMatrix = new char [row][col];

        for(int c = 0; c < col; c++) {
            for(int r = 0; r < row; r++) {
                plainTextMatrix[r][c] = cipherText.charAt(pointer);
                pointer++;
            }
        }

        StringBuilder plaintextBuilder = new StringBuilder();

        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++) {
                plaintextBuilder.append(plainTextMatrix[r][c]);
            }
        }
        return plaintextBuilder.toString();
    }
}
