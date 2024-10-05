import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class SumFloat {


    public static void main(String[] args) {
        float sum = 0;
        StringBuilder currentNumber = new StringBuilder();

        StringBuilder resultString = new StringBuilder();

        for (String arg : args) {
            for (char c : arg.toCharArray()) {
                if (!Character.isWhitespace(c)) {
                    resultString.append(String.valueOf(c));
                } else {
                    resultString.append(' ');
                }
            }
            resultString.append(' ');
        }
        String[] parts = resultString.toString().trim().split("\\s+");

        for (String part : parts) {
            if (!part.isEmpty()) {

                currentNumber.append(part);
                try {
                    sum += (float) Double.parseDouble(currentNumber.toString());
                } catch (NumberFormatException e) {
                    System.err.println();
                    for (char c : currentNumber.toString().toCharArray()) {
                        System.err.println(c + " " + Byte.decode(String.valueOf(c)));
                    }
                    System.err.println();
                    System.err.println("Smth wrong with string: "
                            + currentNumber.toString()
                            + ". Error: " + e.toString());
                }

                currentNumber.setLength(0);

            }
        }
        System.err.println(resultString);

//        for (char c : resultString.toString().toCharArray()){
//
//        }
//        {
//            // https://robotdreams.cc/blog/238-regulyarnye-vyrazheniya-v-java
//            String[] parts = resultString.toString().trim().split("\\s+");
//
//            for (String part : parts) {
//                if (!part.isEmpty()) {
//                    if (part.equals("-") || part.equals("+")) {
//                        currentNumber.append(part);
//                    } else {
//                        currentNumber.append(part);
//                        try {
//                            sum += Integer.parseInt(currentNumber.toString());
//                        } catch (NumberFormatException e) {
//                            System.err.println();
//                            for (char c : currentNumber.toString().toCharArray()){
//                                System.err.println(c + " " + Byte.decode(String.valueOf(c)));
//                            }
//                            System.err.println();
//                            System.err.println("Smth wrong with string: "
//                                    + currentNumber.toString()
//                                    + ". Error: " + e.toString());
//                        }
//
//                        currentNumber.setLength(0);
//                    }
//                }
//            }
//        }
        System.out.println(sum);
    }
}


