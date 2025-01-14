import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BoardReader {

    public static String[][] getIndexes(String fileName) {
        String[][] indx = new String[8][8];
        try {
            File board = new File(fileName);
            Scanner reader = new Scanner(board);

            int i = 0;

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split(" ");

                indx[i] = data;
                i++;
            }
        } catch (FileNotFoundException noFile) {
            System.out.println("File not found!");
            return null;
        }
        return indx;
    }
}
