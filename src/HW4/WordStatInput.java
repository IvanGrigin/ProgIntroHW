package HW4;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordStatInput {
    public static void main(String[] args) {
        String inputFileName = args[0];
        String outputFileName = args[1];

        // LinkedHM сохраняет порядо в отличие Map
        // Но можно самому использовать массив с проверкой на совпадение + массив повторений
        Map<String, Integer> wordCountMap = new LinkedHashMap<>();

        // Pd: https://en.wikipedia.org/wiki/Template:General_Category_(Unicode)
        Pattern wordPattern = Pattern.compile("[\\p{L}'\\p{Pd}]+");

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inputFileName), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = wordPattern.matcher(line);
                while (matcher.find()) {
                    String word = matcher.group().toLowerCase();
                    wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Входной файл не найден: " + inputFileName);
            return;
        } catch (IOException e) {
            System.err.println("Ошибка чтения входного файла: " + e.getMessage());
            return;
        }

        // Запись результатов в выходной файл
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8))) {

            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Ошибка записи в выходной файл: " + e.getMessage());
        }
    }
}
