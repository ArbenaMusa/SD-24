import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Jepni tekstin te cilin duhet ta enkriptojme");
        String plainText = scanner.nextLine();

        System.out.println("Jepni celesin per enkriptim :");
        int key = scanner.nextInt();

        String cipherText = TranspositionCipher.encrypt(plainText, key);
        System.out.println("Teksti i enkriptuar eshte: " + cipherText);

        String cipherToPlain = TranspositionCipher.decrypt(cipherText, key);
        System.out.println("Teksti i dekriptuar eshte: " + cipherToPlain);
    }
}