package com.woowacourse.calculator;

import com.woowacourse.calculator.domain.Expression;
import com.woowacourse.calculator.domain.ExpressionFactory;
import com.woowacourse.calculator.view.InputView;
import com.woowacourse.calculator.view.OutputView;

/**
 * 클래스 이름 : Application.java
 *
 * @author S.W.PARK
 * @author K.S.KIM
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/06
 */
public class CalculatorApplication {
	private static final int UNEXPECTED_EXIT = 1;

	public static void main(String[] args) {
		try {
			String input = InputView.inputEquation();
			Expression expression = ExpressionFactory.create(input);
			OutputView.printAnswer(expression.calculate());
		} catch (IllegalArgumentException | ArithmeticException e) {
			OutputView.printErrorMessage(e.getMessage());
			System.exit(UNEXPECTED_EXIT);
		}
	}
}
