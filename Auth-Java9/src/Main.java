import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Formatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sign up
        System.out.println("Jepni username ");
        String username = scanner.nextLine();

        System.out.println("Jepni passwordin");
        String password = scanner.nextLine();

        // TODO: Ruaj ne DB, username, salt, saltedHashPassword

        SecureRandom sr = new SecureRandom();
        byte [] saltBytes = new byte[16];
        sr.nextBytes(saltBytes);
        Formatter formatter = new Formatter();
        for(byte b : saltBytes) {
            formatter.format("%02x", b);
        }

        String salt = formatter.toString();

        String saltedHashPassword = generateSaltedHash(password, salt);

        System.out.println("Salted Hash " + saltedHashPassword);



        // Log in

        // TODO: Supozojme qe salt dhe saltedHashPassword jane marre nga databaza

        System.out.println("Jepni username ");
        String usernameLogin = scanner.nextLine();

        System.out.println("Jepni passwordin");
        String passwordLogin = scanner.nextLine();

        boolean isAuthenticated = checkIfPasswordIsCorrect(passwordLogin, salt, saltedHashPassword);

        System.out.println("Statusi i kthyer nga tentimi per tu autentikuar eshte " +isAuthenticated);
    }

    static String generateSaltedHash(String password, String salt) {
        String saltPassword = salt + password;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(saltPassword.getBytes());

            byte [] digest = md.digest();

            Formatter formatter = new Formatter();

            for(byte b : digest) {
                formatter.format("%02x", b);
            }

            return formatter.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    static boolean checkIfPasswordIsCorrect(String passwordLogin, String salt, String saltedHashPassword) {
        String newSaltedHash = generateSaltedHash(passwordLogin, salt);
        return newSaltedHash.equals(saltedHashPassword);
    }
}