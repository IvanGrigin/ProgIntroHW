import java.io.*;
import java.util.*;

public class WordStatWordsPrefix {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("2 args: <input file> <output file>");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        Map<String, Integer> prefixCountMap = new TreeMap<>(Collections.reverseOrder());

        try (MyScanner scanner = new MyScanner(new File(inputFileName))) {
            StringBuilder block = new StringBuilder();
            while (scanner.hasNext()) {
                block.append(scanner.nextLine()).append("\n");
            }
            extractPrefixes(block.toString(), prefixCountMap);
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (Map.Entry<String, Integer> entry : prefixCountMap.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + e.getMessage());
        }
    }

    private static void extractPrefixes(String input, Map<String, Integer> prefixCountMap) {
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isWordCharacter(c)) {
                word.append(Character.toLowerCase(c));
            } else if (!word.isEmpty()) {
                String prefix = getPrefix(word.toString());
                prefixCountMap.put(prefix, prefixCountMap.getOrDefault(prefix, 0) + 1);
                word.setLength(0);
            }
        }
        if (!word.isEmpty()) {
            String prefix = getPrefix(word.toString());
            prefixCountMap.put(prefix, prefixCountMap.getOrDefault(prefix, 0) + 1);
        }
    }

    private static String getPrefix(String word) {
        if (word.length() <= 3) {
            return word;
        } else {
            return word.substring(0, 3);
        }
    }

    private static boolean isWordCharacter(char c) {
        return Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION;
    }
}
