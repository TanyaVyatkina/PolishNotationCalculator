import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ReversePolishNotationCalculatorTest {
    ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();

    @Test
    public void shouldCalculateAddition() {
        String str = "4 3 +";
        int result = calculator.calculatePolishNotation(str);
        assertEquals(7, result);
    }

    @Test
    public void shouldCalculateSubtraction() {
        String str = "4 3 -";
        int result = calculator.calculatePolishNotation(str);
        assertEquals(1, result);
    }

    @Test
    public void shouldCalculateMultiplication() {
        String str = "4 3 *";
        int result = calculator.calculatePolishNotation(str);
        assertEquals(12, result);
    }

    @Test
    public void shouldCalculateWithSpacesBeforeOperand() {
        String str = "4   3 +";
        int result = calculator.calculatePolishNotation(str);
        assertEquals(7, result);
    }

    @Test
    public void shouldCalculateWithSpacesBeforeOperation() {
        String str = "4 3   +";
        int result = calculator.calculatePolishNotation(str);
        assertEquals(7, result);
    }

    @Test
    public void shouldCalculateAdditionWithNegativeNumber() {
        String str = "-4 -3   +";
        int result = calculator.calculatePolishNotation(str);
        assertEquals(-7, result);
    }

    @Test
    public void shouldCalculateSubtractionWithNegativeNumber() {
        String str = "-4 -3 -";
        int result = calculator.calculatePolishNotation(str);
        assertEquals(-1, result);
    }

    @Test
    public void shouldCalculateMultiplicationWithNegativeNumber()  {
        String str = "-4 -3 *";
        int result = calculator.calculatePolishNotation(str);
        assertEquals(12, result);
    }

    @Test
    public void shouldCalculateWithMultipleOperations()  {
        String str = "4 3 2 1 * + -";
        int result = calculator.calculatePolishNotation(str);
        assertEquals(-1, result);
    }
}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
} 