import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    private static Map<String, Hashes> hashes = new HashMap();

    static {
        hashes.put("MD5", new MD5Hash());
        hashes.put("SHA1", new SHA1Hash());
        hashes.put("SHA256", new SHA256());
    }

    public static void main(String[] args) {
        List<String> argsList = new ArrayList<>(Arrays.asList(args));

        String algId = "MD5";
        String command = null;

        Optional<String> algOption = argsList.stream().filter(x -> x.startsWith("--alg=")).findFirst();

        if(algOption.isPresent()) {
            algId = algOption.get().substring("--alg=".length());
            argsList.remove(algOption.get());
        }

        Optional<String> commandOption = argsList.stream().filter(x -> x.startsWith("--command=")).findFirst();

        if(commandOption.isPresent()) {
            command = commandOption.get().substring("--command=".length());
            argsList.remove(commandOption.get());
        }
        else {
            System.out.println("Nuk e keni dhene komanden");
            return;
        }

        Optional<String> dataOption = argsList.stream().filter(x -> x.startsWith("--data=")).findFirst();

        String data = null;
        if(dataOption.isPresent()) {
            data = dataOption.get().substring("--data=".length());
            argsList.remove(dataOption.get());
        }
        else {
            System.out.println("Nuk e keni dhene e dhena");
            return;
        }

        Hashes hashObj = hashes.get(algId);


        switch (command) {
            case "hash-text":
                String result = hashObj.getHash(data);
                System.out.println("Hash i tekstit te dhene eshte: " + result);
                break;
            case "hash-file":
                String filePath = data;
                try {
                    String fileContent = Files.readString(Paths.get(filePath));
                    System.out.println(hashObj.getHash(fileContent));
                } catch (IOException e) {
                    System.out.println("Failed to read file: " + e.getMessage());
                }
                break;
            case "hash-dir":
                String dirPath = data;
                try {
                    Path path = Paths.get(dirPath);
                    try (Stream<Path> paths = Files.walk(path)) {
                        paths
                                .filter(Files::isRegularFile)
                                .forEach(file -> {
                                    try {
                                        String fileContent = Files.readString(file);
                                        System.out.println(file.getFileName() + ": " + hashObj.getHash(fileContent));
                                    } catch (IOException e) {
                                        System.out.println("Failed to read file: " + e.getMessage());
                                    }
                                });
                    }
                } catch (IOException e) {
                    System.out.println("Failed to load directory: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Komanda e cila eshte dhene nuk ekziston");
                break;
        }
    }
}