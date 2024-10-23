import java.io.*;
import java.util.*;

public class WsppCountPosition {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("2 args: <input file> <output file>");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        Map<String, List<Position>> wordStats = new LinkedHashMap<>();
        int lineIndex = 1;

        try (MyScanner scanner = new MyScanner(inputFileName)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                extractWords(line, wordStats, lineIndex);
                lineIndex++;
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }

        List<Map.Entry<String, List<Position>>> sortedEntries = new ArrayList<>(wordStats.entrySet());
        sortedEntries.sort((a, b) -> {
            int cmp = Integer.compare(a.getValue().size(), b.getValue().size());
            if (cmp == 0) {
                return Integer.compare(a.getValue().getFirst().line, b.getValue().getFirst().line);
            }
            return cmp;
        });

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (Map.Entry<String, List<Position>> entry : sortedEntries) {
                writer.write(entry.getKey() + " " + entry.getValue().size() + " " + positionsToString(entry.getValue()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + e.getMessage());
        }
    }

    private static void extractWords(String line, Map<String, List<Position>> wordStats, int lineIndex) {
        StringBuilder word = new StringBuilder();
        int wordInLineIndex = 1;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (isWordCharacter(c)) {
                word.append(Character.toLowerCase(c));
            } else if (!word.isEmpty()) {
                addWord(word.toString(), wordStats, new Position(lineIndex, wordInLineIndex));
                word.setLength(0);
                wordInLineIndex++;
            }
        }
        if (!word.isEmpty()) {
            addWord(word.toString(), wordStats, new Position(lineIndex, wordInLineIndex));
        }
    }

    private static void addWord(String word, Map<String, List<Position>> wordStats, Position position) {
        wordStats.putIfAbsent(word, new ArrayList<>());
        wordStats.get(word).add(position);
    }

    private static String positionsToString(List<Position> positions) {
        StringBuilder sb = new StringBuilder();
        for (Position position : positions) {
            sb.append(position).append(" ");
        }
        return sb.toString().trim();
    }


    private static boolean isWordCharacter(char c) {
        return Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION;
    }
}

class Position {
    int line;
    int index;

    public Position(int line, int index) {
        this.line = line;
        this.index = index;
    }

    @Override
    public String toString() {
        return line + ":" + index;
    }
}
