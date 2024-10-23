import java.io.*;
import java.util.*;

public class Wspp {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("2 args: <input file> <output file>");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        Map<String, List<Integer>> wordStats = new LinkedHashMap<>();
        int wordIndex = 1;

        try (MyScanner scanner = new MyScanner(inputFileName)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                wordIndex = extractWords(line, wordStats, wordIndex);
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (Map.Entry<String, List<Integer>> entry : wordStats.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue().size() + " " + entry.getValue().toString().replaceAll("[\\[\\],]", ""));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + e.getMessage());
        }
    }

    private static int extractWords(String line, Map<String, List<Integer>> wordStats, int wordIndex) {
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (isWordCharacter(c)) {
                word.append(Character.toLowerCase(c));
            } else if (!word.isEmpty()) {
                addWord(word.toString(), wordStats, wordIndex);
                word.setLength(0);
                wordIndex++;
            }
        }
        if (!word.isEmpty()) {
            addWord(word.toString(), wordStats, wordIndex);
            wordIndex++;
        }
        return wordIndex;
    }

    private static void addWord(String word, Map<String, List<Integer>> wordStats, int wordIndex) {
        wordStats.putIfAbsent(word, new ArrayList<>());
        wordStats.get(word).add(wordIndex);
    }

    private static boolean isWordCharacter(char c) {
        return Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION;
    }
}