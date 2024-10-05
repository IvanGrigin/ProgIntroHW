import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> lines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            ArrayList<Integer> numbers = new ArrayList<>();

            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                if (lineScanner.hasNextInt()) {
                    numbers.add(lineScanner.nextInt());
                } else {
                    // If next element is not Int
                    lineScanner.next();
                }
            }
            lineScanner.close();
            lines.add(numbers);
        }
        scanner.close();

        for (int i = lines.size() - 1; i >= 0; i--) {
            List<Integer> reversedNumbers = lines.get(i);
            for (int j = reversedNumbers.size() - 1; j >= 0; j--) {
                System.out.print(reversedNumbers.get(j));
                if (j > 0) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
