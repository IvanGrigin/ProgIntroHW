import java.io.*;
import java.util.*;


public class Reverse {
    public static void main(String[] args) {
        List<List<Integer>> lines = new ArrayList<>();

        try (MyScanner scanner = new MyScanner(System.in)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line != null) {
                    List<Integer> numbers = new ArrayList<>();
                    String[] parts = line.trim().split("\\s+");
                    for (int i = parts.length - 1; i >= 0; i--) {
                        if (!parts[i].isEmpty()) {
                            numbers.add(Integer.parseInt(parts[i]));
                        }
                    }
                    lines.add(numbers);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }

        Collections.reverse(lines);
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            for (List<Integer> line : lines) {
                for (int i = 0; i < line.size(); i++) {
                    if (i > 0) {
                        writer.write(" ");
                    }
                    writer.write(String.valueOf(line.get(i)));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing output: " + e.getMessage());
        }
    }
}
