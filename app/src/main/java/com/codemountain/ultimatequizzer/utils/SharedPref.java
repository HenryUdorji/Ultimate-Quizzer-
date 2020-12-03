package com.codemountain.ultimatequizzer.utils;

import android.content.SharedPreferences;

import com.codemountain.ultimatequizzer.UltimateQuizzerApplication;

import static com.codemountain.ultimatequizzer.utils.Constants.MCQ_CURRENT_TOTAL_QUESTION;
import static com.codemountain.ultimatequizzer.utils.Constants.MC_QUIZ_HIGH_SCORE;
import static com.codemountain.ultimatequizzer.utils.Constants.MODE_SWITCH;
import static com.codemountain.ultimatequizzer.utils.Constants.TOTAL_QUESTIONS;
import static com.codemountain.ultimatequizzer.utils.Constants.TRUE_FALSE_CURRENT_TOTAL_QUESTION;
import static com.codemountain.ultimatequizzer.utils.Constants.TRUE_FALSE_QUIZ_HIGH_SCORE;

public class SharedPref {
    private static SharedPref instance;

    public SharedPref() {
    }

    public static SharedPref init() {
        if (instance == null){
            instance = new SharedPref();
        }
        return instance;
    }

    public static SharedPref getSharedPrefInstance() {
        return instance;
    }

    public void putStringInPref(String key, String value) {
        SharedPreferences.Editor editor = UltimateQuizzerApplication.getSharedPref().edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void putIntInPref(String key, int value) {
        SharedPreferences.Editor editor = UltimateQuizzerApplication.getSharedPref().edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void putBooleanInPref(String key, boolean value) {
        SharedPreferences.Editor editor = UltimateQuizzerApplication.getSharedPref().edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /////////////// Night-Light Mode /////////////////
    public boolean getMode() {
        return  UltimateQuizzerApplication.getSharedPref().getBoolean(MODE_SWITCH, false);
    }

    /////////////// Total Quiz Questions /////////////////
    public int getTotalQuestions() {
        return  UltimateQuizzerApplication.getSharedPref().getInt(TOTAL_QUESTIONS, 20);
    }

    /////////////// Set Quiz HighScore /////////////////
    public int getMcQuizHighScore() {
        return  UltimateQuizzerApplication.getSharedPref().getInt(MC_QUIZ_HIGH_SCORE, 0);
    }

    public int getTrueFalseQuizHighScore() {
        return  UltimateQuizzerApplication.getSharedPref().getInt(TRUE_FALSE_QUIZ_HIGH_SCORE, 0);
    }

    public int getMCQuizTotalQuestion() {
        return  UltimateQuizzerApplication.getSharedPref().getInt(MCQ_CURRENT_TOTAL_QUESTION, 0);
    }

    public int getTrueFalseQuizTotalQuestion() {
        return  UltimateQuizzerApplication.getSharedPref().getInt(TRUE_FALSE_CURRENT_TOTAL_QUESTION, 0);
    }
}
