package com.example.alisovets;

import java.text.ParseException;

/**
 * Class is intended to parse String expressions containing mathematical
 * expressions and calculate their values
 * 
 * @author Oleksandr_Lisovets
 * 
 */
public class MathParser extends Thread {
	private String expression;
	private double result;
	private ParseException exception;

	/**
	 * Constructs parser for String expresssion
	 * 
	 * @param expression
	 */
	public MathParser(String expression) {
		this.expression = expression;
	}

	@Override
	public void run() {
		try {
			result = parse(expression);
		} catch (ParseException e) {
			exception = e;
		}
	}

	/**
	 * Returns the result of parsing
	 * 
	 * @return result of parsing
	 * @throws Exception
	 *             if parsing is fail
	 */
	public double getResult() throws ParseException {
		if (exception != null) {
			throw exception;
		}
		return result;
	}

	/**
	 * Parses the string with which the object was created.
	 * 
	 * @return result of parsing
	 * @throws Exception
	 *             if parsing is fail
	 */
	public double parse() throws Exception {
		return parse(expression);
	}

	/*
	 * Parses a string that is passed as a parameter. return result of parsing
	 * throws Exception if parsing is fail
	 */
	private double parse(String expression) throws ParseException {
		System.out.println("Start parsing expression: '" + expression + "'");
		expression = expression.trim();
		
		if(expression.length() == 0 ){
			throw new ParseException("expression is incorrect", 0);
		}

		// delete surplus parenthesis if they there are.
		String cutExpression = deleteSurplusParentheses(expression);
		if (!cutExpression.equals(expression)) {
			System.out.println("Deleted couple of ()");
			return parse(cutExpression);
		}

		// check if expression is number
		if (isNumber(expression)) {
			System.out.println("It is number");
			return Double.parseDouble(expression);
		}

		// check operators + and -
		String regex = "[-\\+]";
		int position = findLastOperator(expression, regex);
		if (position > 0) {
			return parseOperator(expression, position);
		}

		// check operators * and /
		regex = "[/\\*]";
		position = findLastOperator(expression, regex);
		if (position > 0) {
			return parseOperator(expression, position);
		}

		// check trigonometric functions
		position = findTrigonometry(expression);
		if (position > -1) {
			return parseTrigonometry(expression);
		}

		throw new ParseException("expression is incorrect", 0);
	}

	/*
	 * Checks if expression is a number
	 */
	private boolean isNumber(String expression) {
		String regex = "\\d*(\\.\\d+)?";
		return expression.matches(regex);

	}

	/*
	 * finds the last operator in an expression that matches the regular
	 * expression.
	 */
	private int findLastOperator(String expression, String regex)
			throws ParseException {
		for (int i = expression.length() - 1; i >= 0; i--) {
			i = findOpeningParenthesis(expression, i);
			if (expression.substring(i, i + 1).matches(regex)) {
				return i;
			}
		}
		return -1;

	}

	/*
	 * parses an expression that contains a operator at the specified position.
	 */
	private double parseOperator(String expression, int operatorPosition)
			throws ParseException {
		char operator = expression.charAt(operatorPosition);
		System.out.println("Operator " + operator);
		MathParser parser1 = new MathParser(expression.substring(0,
				operatorPosition));
		MathParser parser2 = new MathParser(
				expression.substring(operatorPosition + 1));
		parser1.start();
		parser2.start();
		
		try {
			parser1.join();
			parser2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new ParseException("The thread was interrupted ", 0);
		}
		double res;
		if (operator == '+') {
			res = parser1.getResult() + parser2.getResult();
		} else if (operator == '-') {
			res = parser1.getResult() - parser2.getResult();
		} else if (operator == '*') {
			res = parser1.getResult() * parser2.getResult();
		} else { // '/'
			res = parser1.getResult() / parser2.getResult();
		}
		return res;
	}

	/*
	 * Checks if expression is a trigonometric function
	 */
	private int findTrigonometry(String expression) {
		String regex = "[acinost]{3,4}\\(.+";
		if (expression.matches(regex)) {
			return 0;
		}
		return -1;
	}

	/*
	 * parses an expression that is a trigonometric function
	 */
	private double parseTrigonometry(String expression) throws ParseException{
		if (expression.substring(0, 4).equalsIgnoreCase("sin(")) {
			System.out.println("Function sin");
			return Math.sin(parse(expression.substring(3)));
		}

		if (expression.substring(0, 4).equalsIgnoreCase("cos(")) {
			System.out.println("Function cos");
			return Math.cos(parse(expression.substring(3)));
		}

		if (expression.substring(0, 4).equalsIgnoreCase("tan(")) {
			System.out.println("Function tan");
			return Math.tan(parse(expression.substring(3)));
		}

		if (expression.substring(0, 5).equalsIgnoreCase("asin(")) {
			System.out.println("Function asin");
			return Math.asin(parse(expression.substring(4)));
		}

		if (expression.substring(0, 5).equalsIgnoreCase("acos(")) {
			System.out.println("Function acos");
			return Math.acos(parse(expression.substring(4)));
		}	

		if (expression.substring(0, 5).equalsIgnoreCase("atan(")) {
			System.out.println("Function atan");
			return Math.atan(parse(expression.substring(4)));
		}

		throw new ParseException(
				"Wrong function " + expression.substring(0, 4), 0);
	}

	/*
	 * Finds an returns position in expression opening parenthesis if a
	 * character in the specified offset position is closing parenthesis. if it
	 * is not closing parenthesis method returns unchanged offset
	 */
	private int findOpeningParenthesis(String expression, int offset)
			throws ParseException {
		if (expression.charAt(offset) != ')') {
			return offset;
		}

		int parenthesisCount = 1;
		for (int i = offset - 1; i >= 0; i--) {
			if (expression.charAt(i) == ')') {
				parenthesisCount++;
			}
			if (expression.charAt(i) == '(') {
				parenthesisCount--;
				if (parenthesisCount == 0) {
					return i;
				}
			}
		}
		throw new ParseException("missing opening bracket", offset);

	}

	/*
	 * Removes the outer parentheses if there is
	 */
	String deleteSurplusParentheses(String expression) {
		if (expression.charAt(0) != '(') {
			return expression;
		}

		if (expression.charAt(expression.length() - 1) != ')') {
			return expression;
		}

		int parenthesisCount = 1;
		for (int i = 1; i < expression.length(); i++) {
			if (expression.charAt(i) == '(') {
				parenthesisCount++;
			}
			if (expression.charAt(i) == ')') {
				parenthesisCount--;
				if ((parenthesisCount == 0) && (i < expression.length() - 1)) {
					return expression;
				}
			}
		}
		return expression.substring(1, expression.length() - 1);
	}

}
