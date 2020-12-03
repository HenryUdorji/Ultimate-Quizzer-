package com.codemountain.ultimatequizzer.database;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract(){
    }

    public static class McQuizQuestionsTable implements BaseColumns {

        public static final String TABLE_NAME = "mc_quiz_questions";
        public static final String COLUMN_QUESTIONS = "question";
        public static final String COLUMN_A = "A";
        public static final String COLUMN_B = "B";
        public static final String COLUMN_C = "C";
        public static final String COLUMN_D = "D";
        public static final String COLUMN_ANSWER = "answer";
    }

    public static class TrueFalseQuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "true_false_quiz_questions";
        public static final String COLUMN_QUESTIONS = "question";
        public static final String COLUMN_ANSWER = "answer";
    }
}
