
/**
 * Submitted by Alex Xie, anx201
 * October 5th, 2023
 * The Converter class is used to convert expressions from infix to postfix
 * The toPostFix function uses Djistrick's Shunting Yard Algorithm and takes an infix expresssion 
 * and returns the String as a postfix String
 */

import java.util.*;

public class Converter {
    private String infixExp;

    /**
     * Constructor for class
     * 
     * @param infixExp String containing the expression in infix notation
     */
    public Converter(String infixExp) {
        this.infixExp = infixExp;
    }

    /**
     * Returns true if the String is a number and false otherwise
     * 
     * @param: operand is the String containing the token to be evaluated
     * @return: true if the operand is number and false otherwise
     */
    public boolean isNum(String operand) {
        for (char c : operand.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a value based on the operator's precedence. The lower the value, the
     * higher the precedence.
     * Default value is 5
     * 
     * @param operator
     * @return -1 if null, 1 for exponents, 2 for mult and div, and 3 for add and
     *         sub
     */
    public int checkPrecedence(String operator) {
        // handle the case when it compares an empty stack value
        if (operator == null) {
            return -1;
        }
        switch (operator) {
            case "^":
                return 1;
            case "/":
            case "*":
                return 2;
            case "+":
            case "-":
                return 3;
            default:
                return 5;
        }
    }

    /**
     * Uses Djistrick's Shunting Yard Algorithm with an ArrayStack to keep track of
     * the operators
     * and a StringBuilder for the postfix expression
     * 
     * @return postFixResult is the infix expression converted to postFix notation
     */
    public String toPostFix() {
        StringBuilder postfix = new StringBuilder();
        ArrayStack<String> operations = new ArrayStack<>();
        char[] infixCharArray = infixExp.toCharArray(); // converting for parserHelper

        // for (char c : infixCharArray) {
        // System.out.println("char array: " + c);
        // }

        List<String> parsedExpression = ParserHelper.parse(infixCharArray);

        for (String token : parsedExpression) {
            // System.out.println("Token: " + token);
            if (isNum(token)) {
                // add it to the string if its a digit
                postfix.append(token + " ");
            } else if (token.equals("(")) {
                // push if its a left paren
                operations.push(token);
            } else if (token.equals(")")) {
                // if its a right paren, pop until left paren is reached
                while (!operations.isEmpty() && !operations.top().equals("(")) {
                    postfix.append(operations.pop() + " ");
                }
                // take out left paren
                operations.pop();
            } else { // it's a operator
                int operatorPrec = checkPrecedence(token);

                // System.out.println("Current Operator Prec:" + operatorPrec);
                // System.out.println("Top operator prec:" + checkPrecedence(operations.top()));

                // push if the precedence is less than the top operator
                if (operatorPrec < checkPrecedence(operations.top())) {
                    operations.push(token);
                } else {
                    // while the current operator precedence is higher or equal to the top pop and
                    // add to String
                    while (!operations.isEmpty() && operatorPrec >= checkPrecedence(operations.top())) {
                        // System.out.println("Popping " + operations.top() + " off");
                        postfix.append(operations.pop() + " ");
                    }
                    // push operator onto stack
                    operations.push(token);
                }
            }
        }

        // empty whatever is left in stack
        while (!operations.isEmpty()) {
            postfix.append(operations.pop() + " ");
        }

        // trim off extra spaces
        String postFixResult = postfix.toString().trim();
        return postFixResult;
    }

    public static void main(String[] args) {
        String test = "3 + 4 * 5";
        Converter converter = new Converter(test);
        // System.out.println(converter.infixExp);
        String result = converter.toPostFix();
        System.out.println("Result: " + result);
        System.out.println("Expected: 3 4 5 * +");

        // String test = " ";
        // Converter converter = new Converter(test);
        // System.out.println(converter.checkPrecedence(test));
    }
}
