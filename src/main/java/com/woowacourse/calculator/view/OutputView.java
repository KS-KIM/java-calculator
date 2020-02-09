package com.woowacourse.calculator.view;

/**
 * 클래스 이름 : OutputView.java
 *
 * @author S.W.PARK
 * @author K.S.KIM
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/06
 */
public class OutputView {
    private static final String RETRY_INPUT_NOTICE_MESSAGE = "잘못된 입력입니다. 다시 입력해주세요";
    private static final String ANSWER_MESSAGE = "연산 결과는 %s 입니다.\n";
    private static final String DIVIDE_BY_ZERO_MESSAGE = "0으로 나눌 수 없습니다.";

    // 묵시적 생성자 방지
    private OutputView() {}

    public static void printRetryMessage() {
        System.out.println(RETRY_INPUT_NOTICE_MESSAGE);
    }

    public static void printAnswer(final double answer) {
        System.out.printf(ANSWER_MESSAGE, answer);
    }

    public static void printDivideByZero() {
        System.out.println(DIVIDE_BY_ZERO_MESSAGE);
    }
}
