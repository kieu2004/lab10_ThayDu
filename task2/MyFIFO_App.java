package task2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyFIFO_App {

	public static <E> void stutter(Queue<E> input) {
		Queue<E> queue= new LinkedList<>();
		
		while(!input.isEmpty()) {
			E number=input.poll();
			for (int i = 0; i < 2; i++) {
				queue.add(number);
			}
		}
		
		while(!queue.isEmpty()) {
			input.add(queue.poll());
		}
		
	}
	
	public static <E> void mirror(Queue<E> input) {
		Stack<E> stack= new Stack<>();
		Queue<E> queue= new LinkedList<>();
		while (!input.isEmpty()) {
			E next=input.poll();
			stack.add(next);
			queue.add(next);
		}

		while (!stack.isEmpty()) {
			queue.add(stack.pop());
		}
		
		while (!queue.isEmpty()) {
			input.add(queue.poll());
			
		}
	}
	
	public static void main(String[] args) {
		Queue<Integer> numbers= new LinkedList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		System.out.println("[1, 2, 3]: "+numbers);
		stutter(numbers);
		System.out.println("[1, 1, 2, 2, 3, 3]: "+numbers);
		
		Queue<Character> character= new LinkedList<>();
		character.add('a');
		character.add('b');
		character.add('c');
		System.out.println("[a, b, c]: "+character);
		mirror(character);
		System.out.println("[a, b, c, c, b, a]: "+character);
		
	}
}
