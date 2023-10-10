/*
 * Submitted by Alex Xie, anx201
 * October 5th, 2023
 * Given a postfix String, the evaluate function evaluates the String using an ArrayStack 
 */

import java.util.List;

public class PostfixCalculator {

    /**
     * Returns true if the String is a number and false otherwise
     * 
     * @param: operand is the String containing the token to be evaluated
     * @return: true if the operand is number and false otherwise
     */
    public static boolean isNum(String operand) {
        // convert to char array
        for (char c : operand.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Evaluates the given postfix String and returns the answer as a double
     * 
     * @param: postfix is the postfix expression as a String
     * @return: the result from the stack
     */
    public static double evaluate(String postfix) {
        ArrayStack<Double> result = new ArrayStack<>();
        char[] postfixArray = postfix.toCharArray();
        List<String> parsedExpression = ParserHelper.parse(postfixArray);
        for (String token : parsedExpression) {
            // if it's a number, push it
            if (isNum(token)) {
                double value = Double.valueOf(token);
                result.push(value);
            } else {
                // pop off two operands from the stack to evaluate
                double val1 = result.pop();
                double val2 = result.pop();

                // evaluate based off of expression and push result back on stack
                if (token.equals("+")) {
                    // System.out.println("Addding" + val1 + " + " + val2);
                    // double res = val1 + val2;
                    // System.out.println("Result: " + res);
                    result.push(val1 + val2);
                } else if (token.equals("-")) {
                    // System.out.println("Addding" + val2 + " - " + val1);
                    // double res = val2 - val1;
                    // System.out.println("Result: " + res);
                    result.push(val2 - val1);
                } else if (token.equals("*")) {
                    // System.out.println("Addding" + val1 + " * " + val2);
                    // double res = val1 * val2;
                    // System.out.println("Result: " + res);
                    result.push(val1 * val2);
                } else if (token.equals("/")) {
                    // System.out.println("Addding" + val2 + " / " + val1);
                    // double res = val2 / val1;
                    // System.out.println("Result: " + res);
                    result.push(val2 / val1);
                } else if (token.equals("^")) {
                    // System.out.println(val2 + " to the power of " + val1);
                    // double res = Math.pow(val2, val1);
                    // System.out.println("Result: " + res);
                    result.push(Math.pow(val2, val1));
                }
                // System.out.println();
            }
        }
        return result.pop();
    }
}
