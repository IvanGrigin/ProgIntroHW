import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStatInput {
    public static void main(String[] args) {
        String inputFileName = args[0];
        String outputFileName = args[1];

        // Лучший из Map для этой задачи. Тк сохраняет порядок
        Map<String, Integer> wordCounts = new LinkedHashMap<>();

        try (
            Reader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inputFileName), StandardCharsets.UTF_8)
            )
        ) {
            int bufferSize = 8192;
            char[] buffer = new char[bufferSize];
            int read;
            StringBuilder currentWord = new StringBuilder();

            while ((read = reader.read(buffer)) != -1) {
                for (int i = 0; i < read; i++) {
                    char c = buffer[i];
                    if (isWordCharacter(c)) {
                        currentWord.append(c);
                    } else {
                        if (!currentWord.isEmpty()) {
                            String word = currentWord.toString().toLowerCase();
                            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                            currentWord.setLength(0);
                        }
                    }
                }
            }

            if (!currentWord.isEmpty()) {
                String word = currentWord.toString().toLowerCase();
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }

        } catch (IOException e) {
            System.err.println("Ошибка при чтении входного файла: " + e.getMessage());
            return;
        }

        try (
            Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8)
            )
        ) {
            for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
                writer.write(entry.getKey());
                writer.write(" ");
                writer.write(entry.getValue().toString());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в выходной файл: " + e.getMessage());
        }
    }

    private static boolean isWordCharacter(char c) {
        if (Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION){
            return true;
        } else {
            return false;
        }
    }
}
