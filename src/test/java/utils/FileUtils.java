package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileUtils {

    public static void writeIdCartToFile(String pathFile, int idCart) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(pathFile);
        byte[] strToBytes = String.valueOf(idCart).getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }

    public static String readIdCartFromFile(String pathToFile) throws IOException {
        Path path = Paths.get(pathToFile);
        List<String> allLines = Files.readAllLines(path, UTF_8);
        return allLines.get(0);
    }
}
