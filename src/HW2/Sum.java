package HW2;//import java.lang.reflect.Array;
//import java.util.ArrayList;
//
//public class Sum {
//    public static void main(String[] args) {
//        int sum = 0;
//        StringBuilder currentNumber = new StringBuilder();
//
//        for (String arg : args) {
//            // https://robotdreams.cc/blog/238-regulyarnye-vyrazheniya-v-java
//            String[] parts = arg.trim().split("\\s+");
//
//            for (String part : parts) {
//                if (!part.isEmpty()) {
//
//
//                    // Код для предотварщения 12 теста со знаками вопроса.
//                    ArrayList<Integer> numbers = new ArrayList<>();
//                    StringBuilder currentNum = new StringBuilder();
//                    for (char c : part.toCharArray()){
//                        if ((c >= '0') && (c <= '9')){
//                            currentNum.append(c);
//                        } else if ((c == '-')||(c == '+')){
//                            numbers.add(Integer.parseInt(currentNumber.toString()));
//                            currentNumber.setLength(0);
//                            currentNumber.append(c);
//                        } else {
//                            numbers.add(Integer.parseInt(currentNumber.toString()));
//                            currentNumber.setLength(0);
//                        }
//                    }
//                    for (int num : numbers) {
//                        sum += num;
//                    }
//
//
//
//
//
//
////
////                    if (part.equals("-") || part.equals("+")) {
////                        currentNumber.append(part);
////                    } else {
////                        currentNumber.append(part);
////
////                        try {
////                            sum += Integer.parseInt(currentNumber.toString());
////                        } catch (NumberFormatException e) {
////                            System.err.println("Smth wrong with string: "
////                                    + currentNumber.toString()
////                                    + ". Error: " + e.toString());
////                        }
////
////                        currentNumber.setLength(0);
////                    }
//                }
//            }
//        }
//        System.out.println(sum);
//    }
//}

public class Sum {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg).append('?');
        }

        String input = sb.toString();
        int sum = 0;
        int currentSign = 1;
        int currentNumber = 0;
        boolean inNumber = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0');
                inNumber = true;
            } else {
                sum += currentNumber * currentSign;
                 if (c == '-') {
                     currentSign = -1;
                 } else {
                     currentSign = 1;
                 }
                 inNumber = false;
                 currentNumber = 0;
            }
        }

        if (inNumber) {
            sum += currentNumber * currentSign;
        }

        System.out.println(sum);
    }
}
