import java.io.*;
import java.util.*;

public class ReverseMaxOct {
    public static void main(String[] args) {
        List<int[]> lines = new ArrayList<>();
        int maxRows = 0;
        int maxCols = 0;

        try (MyScanner scanner = new MyScanner(System.in)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line != null) {
                    List<Integer> numbers = new ArrayList<>();
                    String[] parts = line.trim().split("\\s+");

                    for (String part : parts) {
                        if (!part.isEmpty()) {
                            try {
                                numbers.add(Integer.parseUnsignedInt(part, 8)); // Восьмеричная интерпретация
                            } catch (NumberFormatException e) {
                                System.err.println("Invalid number format: " + part);
                                return;
                            }
                        }
                    }

                    int[] numbersArray = new int[numbers.size()];
                    for (int i = 0; i < numbers.size(); i++) {
                        numbersArray[i] = numbers.get(i);
                    }

                    lines.add(numbersArray);
                    maxCols = Math.max(maxCols, numbersArray.length);
                }
            }
            maxRows = lines.size();
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }

        int[] maxInRows = new int[maxRows];
        int[] maxInCols = new int[maxCols];
        Arrays.fill(maxInRows, Integer.MIN_VALUE);
        Arrays.fill(maxInCols, Integer.MIN_VALUE);

        for (int i = 0; i < lines.size(); i++) {
            int[] row = lines.get(i);
            for (int j = 0; j < row.length; j++) {
                int value = row[j];
                maxInRows[i] = Math.max(maxInRows[i], value);
                maxInCols[j] = Math.max(maxInCols[j], value);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            for (int i = 0; i < lines.size(); i++) {
                int[] row = lines.get(i);
                for (int j = 0; j < row.length; j++) {
                    if (j > 0) {
                        writer.write(" ");
                    }
                    int maxValue = Math.max(maxInRows[i], maxInCols[j]);
                    writer.write(Integer.toOctalString(maxValue)); // Преобразование в восьмеричную систему
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing output: " + e.getMessage());
        }
    }
}
