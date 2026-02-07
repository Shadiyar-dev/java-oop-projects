import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class KissExamples {

    public void processNumbers(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return;
        }

        for (int number : numbers) {
            if (number > 0) {
                System.out.println(number);
            }
        }
    }

    public void processNumbersOld(int[] numbers) {
        if (numbers != null) {
            if (numbers.length > 0) {
                for (int number : numbers) {
                    if (number > 0) {
                        System.out.println(number);
                    }
                }
            }
        }
    }

    public void printPositiveNumbers(int[] numbers) {
        for (int number : numbers) {
            if (number > 0) {
                System.out.println(number);
            }
        }
    }

    public void printPositiveNumbersComplex(int[] numbers) {
        Arrays.stream(numbers)
                .filter(n -> n > 0)
                .sorted()
                .forEach(System.out::println);
    }

    public int divide(int a, int b) {
        if (b == 0) {
            return 0;
        }
        return a / b;
    }

    public int divideWithException(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            return 0;
        }
    }
}
