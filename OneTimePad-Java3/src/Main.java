import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Jepni plaintext qe do te enkriptohet me One Time Pad:");
        String plainText = scanner.nextLine();

        System.out.println("Jepni key :");
        String key = scanner.nextLine();

        String cipherText = OneTimePad.encrypt(plainText, key);
        System.out.println("Teksti i enkriptuar eshte: " + cipherText);

        String cipherToPlain = OneTimePad.decrypt(cipherText, key);
        System.out.println("Teksti i dekriptuar eshte: " + cipherToPlain);
    }
}