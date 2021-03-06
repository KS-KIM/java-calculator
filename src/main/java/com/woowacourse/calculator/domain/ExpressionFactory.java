package com.woowacourse.calculator.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 클래스 이름 : ExpressionFactory.java
 *
 * @author S.W.PARK
 * @author K.S.KIM
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/06
 */
public class ExpressionFactory {
	private static final String EXPRESSION_DELIMITER = " ";
	private static final String CONSECUTIVE_EMPTY_TOKEN_EXCEPTION_MESSAGE =
			"수식에 연속된 공백이 두 개 이상 포함되어 있습니다.";
	private static final int NUMBER_START_INDEX = 0;
	private static final int OPERATION_START_INDEX = 1;
	private static final int LOOP_JUMP_COUNT = 2;

	private ExpressionFactory() {}

	public static Expression create(final String input) {
		validateEndsWithEmptyString(input);
		List<String> tokens = Arrays.asList(input.split(EXPRESSION_DELIMITER));
		return makeExpression(tokens);
	}

	private static Expression makeExpression(final List<String> tokens) {
		List<Double> numbers = makeNumbers(tokens);
		List<OperatorType> operators = makeOperators(tokens);
		return new Expression(numbers, operators);
	}

	private static List<Double> makeNumbers(final List<String> tokens) {
		List<Double> numbers = new ArrayList<>();
		for (int index = NUMBER_START_INDEX, end = tokens.size(); index < end; index += LOOP_JUMP_COUNT) {
			validateEmptyString(tokens.get(index));
			numbers.add(Double.valueOf(tokens.get(index)));
		}
		return numbers;
	}

	private static void validateEndsWithEmptyString(final String input) {
		if (input.endsWith(EXPRESSION_DELIMITER)) {
			throw new IllegalArgumentException(CONSECUTIVE_EMPTY_TOKEN_EXCEPTION_MESSAGE);
		}
	}

	private static void validateEmptyString(final String token) {
		if (token.isEmpty()) {
			throw new IllegalArgumentException(CONSECUTIVE_EMPTY_TOKEN_EXCEPTION_MESSAGE);
		}
	}

	private static List<OperatorType> makeOperators(final List<String> tokens) {
		List<OperatorType> operators = new ArrayList<>();
		for (int index = OPERATION_START_INDEX, end = tokens.size(); index < end; index += LOOP_JUMP_COUNT) {
			operators.add(OperatorType.of(tokens.get(index)));
		}
		return operators;
	}
}
