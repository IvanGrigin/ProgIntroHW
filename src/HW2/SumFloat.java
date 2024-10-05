package HW2;

public class SumFloat {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg).append('?');
        }

        String input = sb.toString();
        float sum = 0;
        int currentSign = 1;
        float currentNumber = 0;
        boolean inNumber = false;
        int pointNumber = 0;
        int eNumber = 0;
        int degreeNumber = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                if (eNumber != 0){

                } else {
                    if (pointNumber == 0) {
                        currentNumber = currentNumber * 10 + (c - '0');
                    } else {
                        float addSymbol = (c - '0');
                        for (int j = 0; j < pointNumber; j++) {
                            addSymbol /= 10;
                        }
                        currentNumber = currentNumber + addSymbol;
                        pointNumber += 1;
                    }
                }
                inNumber = true;
            } else {
                if (c == '.') {
                    if (pointNumber == 0) {
                        pointNumber += 1;
                    } else {
                        pointNumber = 0;
                        sum += currentNumber * currentSign;
                        inNumber = false;
                        currentNumber = 0;
                    }
                } else if (c == 'e') {
                    eNumber += 1;
                } else {
                    pointNumber = 0;
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
        }

        if (inNumber) {
            sum += currentNumber * currentSign;
        }

        System.out.println(sum);
    }
}
