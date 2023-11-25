package task1;

import java.util.Arrays;
import java.util.Stack;

public class MyLIFO_App {

	public static <E> void reverse(E[] array) {
		Stack<E> stack = new Stack<>();

		for (E e : array) {
			stack.add(e);
		}

		for (int i = 0; i < array.length; i++) {
			array[i] = stack.pop();
		}
	}

	public static boolean isCorrect(String input) {
		Stack<Character> stack_mo = new Stack<>();
		for (Character character : input.toCharArray()) {
			if (character == '(' || character == '[' || character == '{') {
				stack_mo.push(character);
			} else {
				if (stack_mo.isEmpty()) {
					return false;
				}

				if ((character == ')' && stack_mo.pop() != '(') || (character == ']' && stack_mo.pop() != '[')
						|| (character == '}' && stack_mo.pop() != '{')) {
					return false;
				}
			}
		}

		if (stack_mo.isEmpty()) {
			return true;
		}

		return false;
	}

	public static int evaluateExpression(String expression) {
		String spacedExpression = addBlanks(expression);
		Stack<Integer> operandStack = new Stack<>();
		Stack<String> operatorStack = new Stack<>();
		String[] characters = spacedExpression.split("\\s+");

		for (String token : characters) {
			if (isNumeric(token)) {
				operandStack.push(Integer.parseInt(token));
			} else if (token.equals("(")) {
				operatorStack.push(token);
			} else if (token.equals(")")) {
				while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
					processOperator(operandStack, operatorStack);
				}
				operatorStack.pop(); // Pop the opening parenthesis
			} else if (isOperator(token)) {
				while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(token)) {
					processOperator(operandStack, operatorStack);
				}
				operatorStack.push(token);
			}
		}

		while (!operatorStack.isEmpty()) {
			processOperator(operandStack, operatorStack);
		}

		return operandStack.pop();
	}

	private static void processOperator(Stack<Integer> operandStack, Stack<String> operatorStack) {
		String operator = operatorStack.pop();
		int operand2 = operandStack.pop();
		int operand1 = operandStack.pop();
		int result = applyOperator(operand1, operand2, operator);
		operandStack.push(result);
	}

	private static int applyOperator(int operand1, int operand2, String operator) {
		switch (operator) {
		case "+":
			return operand1 + operand2;
		case "-":
			return operand1 - operand2;
		case "*":
			return operand1 * operand2;
		case "/":
			return operand1 / operand2;
		default:
			throw new IllegalArgumentException("Invalid operator: " + operator);
		}
	}

	private static int precedence(String operator) {
		switch (operator) {
		case "+":
		case "-":
			return 1;
		case "*":
		case "/":
			return 2;
		default:
			return 0;
		}
	}

	private static boolean isOperator(String token) {
		return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
	}

	private static boolean isNumeric(String token) {
		return token.matches("\\d+");
	}

	public static String addBlanks(String input) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char currentChar = input.charAt(i);
			if (isOperator(String.valueOf(currentChar)) || currentChar == '(' || currentChar == ')') {
				result.append(" ").append(currentChar).append(" ");
			} else {
				result.append(currentChar);
			}
		}
		return result.toString();
	}

	public static void main(String[] args) {
		Integer[] numbers = { 1, 5, 7, 0, 4 };
		System.out.println("Reverse: ");
		reverse(numbers);
		System.out.println(Arrays.toString(numbers));
		System.out.println(isCorrect("()(())[]{(())}"));
		System.out.println(isCorrect("){[]}()"));

		System.out.println(evaluateExpression("51+(54*(3+2))"));
		System.out.println(evaluateExpression("51-45+2*(54*(3+2))"));

	}
}
