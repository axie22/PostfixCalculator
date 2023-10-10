import java.util.*;

public class ParserHelper {

	public static List<String> parse(char[] input) {
		List<String> parsed = new ArrayList<String>();
		for (int i = 0; i < input.length; ++i) {
			char c = input[i];
			if (Character.isDigit(c)) {
				String number = input[i] + "";
				for (int j = i + 1; j < input.length; ++j) {
					if (Character.isDigit(input[j])) {
						number += input[j];
						i = j;
					} else {
						break;
					}
				}
				parsed.add(number);
			} else if (c == '*' || c == '/' ||
					c == '+' || c == '^' ||
					c == '-' || c == '(' || c == ')') {
				parsed.add(c + "");
			}
		}
		return parsed;
	}

	public static void main(String[] args) {
		char[] test = { '5', '8', '+', '(', '7', '*', '8', ')', };
		List<String> result = parse(test);
		for (String str : result) {
			System.out.println(str);
		}
		String infix = "(300+23)*(43-21)/(84+7)";
		char[] chars = infix.toCharArray();
		for (char c : chars) {
			System.out.println(c);
		}

	}
}
