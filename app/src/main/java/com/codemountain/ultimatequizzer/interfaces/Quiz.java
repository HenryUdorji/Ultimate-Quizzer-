package com.codemountain.ultimatequizzer.interfaces;

public interface Quiz {
    void initViews();
    void checkAnswer();
    void showSolution();
    void showNextQuestion();
    void startCountDown();
    void updateCountDownText();
    void finishQuiz();
}
