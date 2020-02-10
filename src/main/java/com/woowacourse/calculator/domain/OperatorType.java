package com.woowacourse.calculator.domain;

import java.util.Arrays;
import java.util.function.BinaryOperator;

/**
 * 클래스 이름 : OperatorType.java
 *
 * @author S.W.PARK
 * @author K.S.KIM
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/06
 */
public enum OperatorType {
	PLUS("+", Double::sum),
	MINUS("-", (firstOperand, secondOperand) -> firstOperand - secondOperand),
	DIVIDE("/", (firstOperand, secondOperand) -> {
		checkOperandIsZero(secondOperand);
		return firstOperand / secondOperand;
	}),
	MULTIPLY("*", (firstOperand, secondOperand) -> firstOperand * secondOperand);

	private static final String DIVIDE_BY_ZERO_EXCEPTION_MESSAGE = "0으로 나눌 수 없습니다.";
	private static final String NUMBER_OUT_OF_RANGE_EXCEPTION_MESSAGE = "값을 표현할 수 있는 범위를 벗어났습니다.";
	private static final String OPERATOR_NOT_FOUND_EXCEPTION_MESSAGE = "연산자에 포함되지 않습니다.";

	private String operator;
	private BinaryOperator<Double> expression;

	OperatorType(final String operator, final BinaryOperator<Double> expression) {
		this.operator = operator;
		this.expression = expression;
	}

	public static OperatorType of(final String operator) {
		return Arrays.stream(OperatorType.values())
				.filter(value -> value.isSameOperator(operator))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(OPERATOR_NOT_FOUND_EXCEPTION_MESSAGE));
	}

	private boolean isSameOperator(final String input) {
		return operator.equals(input);
	}

	public Double calculate(final Double firstOperand, final Double secondOperand) {
		Double result = expression.apply(firstOperand, secondOperand);
		if (result.isInfinite()) {
			throw new ArithmeticException(NUMBER_OUT_OF_RANGE_EXCEPTION_MESSAGE);
		}
		return result;
	}

	private static void checkOperandIsZero(final Double operand) {
		if (operand == 0) {
			throw new ArithmeticException(DIVIDE_BY_ZERO_EXCEPTION_MESSAGE);
		}
	}
}
