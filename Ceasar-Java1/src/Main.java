import java.util.Scanner;

public class Main {
    public static void bruteForce(String cipherText) {
        for (int i = 0; i <= 25; i++) {
            System.out.println("Celesi i perdorur eshte: " + i);
            System.out.println("Teksti i dekriptuar me te eshte " + Ceasar.decrypt(cipherText, i));
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Te lutem na jep plaintextin: ");
        String plainText = scanner.nextLine();

        System.out.println("Te lutem jepe celesin");
        int key = scanner.nextInt();

        String cipherText = Ceasar.encrypt(plainText, key);
        System.out.println("Teksti i enkriptuar: " + cipherText);

        String cipherToPlain = Ceasar.decrypt(cipherText, key);
        System.out.println("Teksti i dekriptuar: " + cipherToPlain);

        System.out.println("Analiza nga brute-force");
        bruteForce(cipherText);

    }
}