import java.io.*;
import java.util.*;

public class WordStatInput {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("2 args: <input file> <output file>");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        Map<String, Integer> wordCountMap = new LinkedHashMap<>();

        try (MyScanner scanner = new MyScanner(new File(inputFileName))) {
            StringBuilder block = new StringBuilder();
            while (scanner.hasNext()) {
                block.append(scanner.nextLine()).append("\n");
            }
            extractWords(block.toString(), wordCountMap);
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + e.getMessage());
        }
    }

    private static void extractWords(String input, Map<String, Integer> wordCountMap) {
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isWordCharacter(c)) {
                word.append(Character.toLowerCase(c));
            } else if (!word.isEmpty()) {
                String wordStr = word.toString();
                wordCountMap.put(wordStr, wordCountMap.getOrDefault(wordStr, 0) + 1);
                word.setLength(0);
            }
        }
        if (!word.isEmpty()) {
            String wordStr = word.toString();
            wordCountMap.put(wordStr, wordCountMap.getOrDefault(wordStr, 0) + 1);
        }
    }

    private static boolean isWordCharacter(char c) {
        return Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION;
    }
}