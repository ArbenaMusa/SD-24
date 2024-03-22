import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;

public class DHMClient {
    public static final BigInteger P = BigInteger.valueOf(11);
    public static final BigInteger G = BigInteger.valueOf(6);
    public static void main(String[] arg) {

        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream clientOut = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream clientIn = new ObjectInputStream(socket.getInputStream());

            System.out.println("Klienti po e dergon nje mesazh tek serveri");
            clientOut.writeObject("Mesazhi per serverin");

            // Komunikime dhe kalkulime

            BigInteger B = generateKey();

            BigInteger calculatedClientValue = G.modPow(B, P);

            BigInteger valueFromServer = (BigInteger) clientIn.readObject();
            System.out.println("Mesazhi i pranuar nga serveri eshte: "+ valueFromServer);

            System.out.println("Vlera e derguar tek serveri eshte: " + calculatedClientValue);
            clientOut.writeObject(calculatedClientValue);

            BigInteger exchagedKey = valueFromServer.modPow(B, P);
            System.out.println("Celesi i perbashket i shkembyer eshte: " + exchagedKey);

            socket.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static BigInteger generateKey() {
        return BigInteger.valueOf((long) (Math.random() * 1000));
    }
}
