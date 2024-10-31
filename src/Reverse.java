import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Reverse {
    public static void main(String[] args) {
        List<int[]> allLines = new ArrayList<>();

        try (MyScanner myScanner = new MyScanner(System.in)) {
            String line;
            while ((line = myScanner.nextLine()) != null) {
                List<Integer> currentLine = new ArrayList<>();

                MyScanner lineScanner = new MyScanner(line);

                while (lineScanner.hasNextInt()) {
                    int number = (int) lineScanner.nextInt();
                    currentLine.add(number);
                }

                int[] lineArray = new int[currentLine.size()];
                for (int i = 0; i < currentLine.size(); i++) {
                    lineArray[i] = currentLine.get(i);
                }

                allLines.add(lineArray);
            }
        } catch (IOException | NoSuchElementException e) {
            e.printStackTrace();
        }

        for (int i = allLines.size() - 1; i >= 0; i--) {
            int[] currentLine = allLines.get(i);
            for (int j = currentLine.length - 1; j >= 0; j--) {
                System.out.print(currentLine[j]);
                if (j > 0) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
