package com.codemountain.ultimatequizzer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codemountain.ultimatequizzer.R;
import com.codemountain.ultimatequizzer.utils.Constants;
import com.codemountain.ultimatequizzer.utils.SharedPref;

import static com.codemountain.ultimatequizzer.utils.Constants.FROM;
import static com.codemountain.ultimatequizzer.utils.Constants.MCQ_CURRENT_TOTAL_QUESTION;
import static com.codemountain.ultimatequizzer.utils.Constants.MC_QUIZ_HIGH_SCORE;
import static com.codemountain.ultimatequizzer.utils.Constants.QUIZ_SCORE;
import static com.codemountain.ultimatequizzer.utils.Constants.TRUE_FALSE_CURRENT_TOTAL_QUESTION;
import static com.codemountain.ultimatequizzer.utils.Constants.TRUE_FALSE_QUIZ_HIGH_SCORE;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";
    private TextView resultQuizType, resultTotalQuestions, resultCountDownTimer,correctAnswerNum, wrongAnswerNum;
    private ProgressBar resultProgressBar;
    private int score;
    private String intentFrom;
    private int TOTAL_QUESTION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        intentFrom = intent.getStringExtra(FROM);
        score = intent.getIntExtra(QUIZ_SCORE, 0);
        TOTAL_QUESTION = SharedPref.getSharedPrefInstance().getTotalQuestions();
        initViews();
    }

    private void initViews() {
        resultQuizType = findViewById(R.id.resultQuizType);
        resultCountDownTimer = findViewById(R.id.resultCountDownTimer);
        resultTotalQuestions = findViewById(R.id.resultTotalQuestions);
        correctAnswerNum = findViewById(R.id.resultCorrectAnswerNum);
        wrongAnswerNum = findViewById(R.id.resultWrongAnswerNum);
        resultProgressBar = findViewById(R.id.resultProgressBar);
        Button goBackHomeBtn = findViewById(R.id.goToHomeBtn);

        if (intentFrom.equals("mcq")) {
            resultQuizType.setText(R.string.multi_choice_quiz);
            if (score > SharedPref.getSharedPrefInstance().getMcQuizHighScore()) {
                SharedPref.getSharedPrefInstance().putIntInPref(MC_QUIZ_HIGH_SCORE, score);
                SharedPref.getSharedPrefInstance().putIntInPref(MCQ_CURRENT_TOTAL_QUESTION, TOTAL_QUESTION);
                Toast.makeText(this, "New HighScore", Toast.LENGTH_SHORT).show();
            }
        }
        else if (intentFrom.equals("true_false")) {
            resultQuizType.setText(R.string.true_false);
            if (score > SharedPref.getSharedPrefInstance().getTrueFalseQuizHighScore()) {
                SharedPref.getSharedPrefInstance().putIntInPref(TRUE_FALSE_QUIZ_HIGH_SCORE, score);
                SharedPref.getSharedPrefInstance().putIntInPref(TRUE_FALSE_CURRENT_TOTAL_QUESTION, TOTAL_QUESTION);
                Toast.makeText(this, "New HighScore", Toast.LENGTH_SHORT).show();
            }
        }

        resultTotalQuestions.setText(TOTAL_QUESTION + " ");
        resultProgressBar.setVisibility(View.VISIBLE);

        if (TOTAL_QUESTION == 10) {
            setResult10();
        }
        else if (TOTAL_QUESTION == 20) {
            setResult20();
        }
        else if (TOTAL_QUESTION == 30) {
            setResult30();
        }
        else if (TOTAL_QUESTION == 40) {
            setResult40();
        }

        goBackHomeBtn.setOnClickListener(v -> {
            startActivity(new Intent(ResultActivity.this, MainActivity.class));
            finish();
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        });

    }

    private void setResult10() {
        switch (score){
            case 0:
                resultProgressBar.setProgress(score);
                resultCountDownTimer.setText(score +"%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 1:
                resultProgressBar.setProgress(score + 9);
                resultCountDownTimer.setText(score + 9 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 2:
                resultProgressBar.setProgress(score + 18);
                resultCountDownTimer.setText(score + 18 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 3:
                resultProgressBar.setProgress(score + 27);
                resultCountDownTimer.setText(score + 27 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 4:
                resultProgressBar.setProgress(score + 36);
                resultCountDownTimer.setText(score + 36 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 5:
                resultProgressBar.setProgress(score + 45);
                resultCountDownTimer.setText(score + 45 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 6:
                resultProgressBar.setProgress(score + 54);
                resultCountDownTimer.setText(score + 54 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 7:
                resultProgressBar.setProgress(score + 63);
                resultCountDownTimer.setText(score + 63 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 8:
                resultProgressBar.setProgress(score + 72);
                resultCountDownTimer.setText(score + 72 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 9:
                resultProgressBar.setProgress(score + 81);
                resultCountDownTimer.setText(score + 81 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 10:
                resultProgressBar.setProgress(score + 90);
                resultCountDownTimer.setText(score + 90 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
        }
    }
    private void setResult20() {
        switch (score){
            case 0:
                resultProgressBar.setProgress(score);
                resultCountDownTimer.setText(score +"%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 1:
                resultProgressBar.setProgress(score + 4);
                resultCountDownTimer.setText(score + 4 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 2:
                resultProgressBar.setProgress(score + 8);
                resultCountDownTimer.setText(score + 8 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 3:
                resultProgressBar.setProgress(score + 12);
                resultCountDownTimer.setText(score + 12 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 4:
                resultProgressBar.setProgress(score + 16);
                resultCountDownTimer.setText(score + 16 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 5:
                resultProgressBar.setProgress(score + 20);
                resultCountDownTimer.setText(score + 20 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 6:
                resultProgressBar.setProgress(score + 24);
                resultCountDownTimer.setText(score + 24 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 7:
                resultProgressBar.setProgress(score + 28);
                resultCountDownTimer.setText(score + 28 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 8:
                resultProgressBar.setProgress(score + 32);
                resultCountDownTimer.setText(score + 32 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 9:
                resultProgressBar.setProgress(score + 36);
                resultCountDownTimer.setText(score + 36 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 10:
                resultProgressBar.setProgress(score + 40);
                resultCountDownTimer.setText(score + 40 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 11:
                resultProgressBar.setProgress(score + 44);
                resultCountDownTimer.setText(score + 44 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 12:
                resultProgressBar.setProgress(score + 48);
                resultCountDownTimer.setText(score + 48 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 13:
                resultProgressBar.setProgress(score + 52);
                resultCountDownTimer.setText(score + 52 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 14:
                resultProgressBar.setProgress(score + 56);
                resultCountDownTimer.setText(score + 56 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 15:
                resultProgressBar.setProgress(score + 60);
                resultCountDownTimer.setText(score + 60 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 16:
                resultProgressBar.setProgress(score + 64);
                resultCountDownTimer.setText(score + 64 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 17:
                resultProgressBar.setProgress(score + 68);
                resultCountDownTimer.setText(score + 68 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 18:
                resultProgressBar.setProgress(score + 72);
                resultCountDownTimer.setText(score + 72 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 19:
                resultProgressBar.setProgress(score + 76);
                resultCountDownTimer.setText(score + 76 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 20:
                resultProgressBar.setProgress(score + 80);
                resultCountDownTimer.setText(score + 80 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
        }
    }
    private void setResult30() {
        switch (score){
            case 0:
                resultProgressBar.setProgress(score);
                resultCountDownTimer.setText(score +"%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 1:
                resultProgressBar.setProgress(score + 2);
                resultCountDownTimer.setText(score + 2 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 2:
                resultProgressBar.setProgress(score + 4);
                resultCountDownTimer.setText(score + 4 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 3:
                resultProgressBar.setProgress(score + 6);
                resultCountDownTimer.setText(score + 6 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 4:
                resultProgressBar.setProgress(score + 8);
                resultCountDownTimer.setText(score + 8 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 5:
                resultProgressBar.setProgress(score + 10);
                resultCountDownTimer.setText(score + 10 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 6:
                resultProgressBar.setProgress(score + 12);
                resultCountDownTimer.setText(score + 12 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 7:
                resultProgressBar.setProgress(score + 14);
                resultCountDownTimer.setText(score + 14 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 8:
                resultProgressBar.setProgress(score + 16);
                resultCountDownTimer.setText(score + 16 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 9:
                resultProgressBar.setProgress(score + 18);
                resultCountDownTimer.setText(score + 18 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 10:
                resultProgressBar.setProgress(score + 20);
                resultCountDownTimer.setText(score + 20 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 11:
                resultProgressBar.setProgress(score + 22);
                resultCountDownTimer.setText(score + 22 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 12:
                resultProgressBar.setProgress(score + 24);
                resultCountDownTimer.setText(score + 24 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 13:
                resultProgressBar.setProgress(score + 26);
                resultCountDownTimer.setText(score + 26 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 14:
                resultProgressBar.setProgress(score + 28);
                resultCountDownTimer.setText(score + 28 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 15:
                resultProgressBar.setProgress(score + 35);
                resultCountDownTimer.setText(score + 35 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 16:
                resultProgressBar.setProgress(score + 36);
                resultCountDownTimer.setText(score + 36 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 17:
                resultProgressBar.setProgress(score + 37);
                resultCountDownTimer.setText(score + 37 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 18:
                resultProgressBar.setProgress(score + 38);
                resultCountDownTimer.setText(score + 38 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 19:
                resultProgressBar.setProgress(score + 39);
                resultCountDownTimer.setText(score + 39 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 20:
                resultProgressBar.setProgress(score + 40);
                resultCountDownTimer.setText(score + 40 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 21:
                resultProgressBar.setProgress(score + 42);
                resultCountDownTimer.setText(score + 42 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 22:
                resultProgressBar.setProgress(score + 44);
                resultCountDownTimer.setText(score + 44 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 23:
                resultProgressBar.setProgress(score + 46);
                resultCountDownTimer.setText(score + 46 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 24:
                resultProgressBar.setProgress(score + 48);
                resultCountDownTimer.setText(score + 48 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 25:
                resultProgressBar.setProgress(score + 50);
                resultCountDownTimer.setText(score + 50 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 26:
                resultProgressBar.setProgress(score + 55);
                resultCountDownTimer.setText(score + 55 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 27:
                resultProgressBar.setProgress(score + 59);
                resultCountDownTimer.setText(score + 59 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 28:
                resultProgressBar.setProgress(score + 64);
                resultCountDownTimer.setText(score + 64 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 29:
                resultProgressBar.setProgress(score + 68);
                resultCountDownTimer.setText(score + 68 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 30:
                resultProgressBar.setProgress(score + 70);
                resultCountDownTimer.setText(score + 70 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
        }
    }
    private void setResult40() {
        switch (score){
            case 0:
                resultProgressBar.setProgress(score);
                resultCountDownTimer.setText(score +"%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 1:
                resultProgressBar.setProgress(score + 1);
                resultCountDownTimer.setText(score + 1 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 2:
                resultProgressBar.setProgress(score + 3);
                resultCountDownTimer.setText(score + 3 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 3:
                resultProgressBar.setProgress(score + 4);
                resultCountDownTimer.setText(score + 4 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 4:
                resultProgressBar.setProgress(score + 5);
                resultCountDownTimer.setText(score + 5 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 5:
                resultProgressBar.setProgress(score + 6);
                resultCountDownTimer.setText(score + 6 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 6:
                resultProgressBar.setProgress(score + 7);
                resultCountDownTimer.setText(score + 7 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 7:
                resultProgressBar.setProgress(score + 8);
                resultCountDownTimer.setText(score + 8 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 8:
                resultProgressBar.setProgress(score + 9);
                resultCountDownTimer.setText(score + 9 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 9:
                resultProgressBar.setProgress(score + 10);
                resultCountDownTimer.setText(score + 10 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 10:
                resultProgressBar.setProgress(score + 15);
                resultCountDownTimer.setText(score + 15 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 11:
                resultProgressBar.setProgress(score + 16);
                resultCountDownTimer.setText(score + 16 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 12:
                resultProgressBar.setProgress(score + 17);
                resultCountDownTimer.setText(score + 17 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 13:
                resultProgressBar.setProgress(score + 18);
                resultCountDownTimer.setText(score + 18 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 14:
                resultProgressBar.setProgress(score + 19);
                resultCountDownTimer.setText(score + 19 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 15:
                resultProgressBar.setProgress(score + 20);
                resultCountDownTimer.setText(score + 20 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 16:
                resultProgressBar.setProgress(score + 22);
                resultCountDownTimer.setText(score + 22 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 17:
                resultProgressBar.setProgress(score + 24);
                resultCountDownTimer.setText(score + 24 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 18:
                resultProgressBar.setProgress(score + 26);
                resultCountDownTimer.setText(score + 26 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 19:
                resultProgressBar.setProgress(score + 28);
                resultCountDownTimer.setText(score + 28 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 20:
                resultProgressBar.setProgress(score + 30);
                resultCountDownTimer.setText(score + 30 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 21:
                resultProgressBar.setProgress(score + 4);
                resultCountDownTimer.setText(score + 4 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 22:
                resultProgressBar.setProgress(score + 8);
                resultCountDownTimer.setText(score + 8 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 23:
                resultProgressBar.setProgress(score + 12);
                resultCountDownTimer.setText(score + 12 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 24:
                resultProgressBar.setProgress(score + 16);
                resultCountDownTimer.setText(score + 16 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 25:
                resultProgressBar.setProgress(score + 20);
                resultCountDownTimer.setText(score + 20 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 26:
                resultProgressBar.setProgress(score + 24);
                resultCountDownTimer.setText(score + 24 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 27:
                resultProgressBar.setProgress(score + 28);
                resultCountDownTimer.setText(score + 28 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 28:
                resultProgressBar.setProgress(score + 32);
                resultCountDownTimer.setText(score + 32 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 29:
                resultProgressBar.setProgress(score + 36);
                resultCountDownTimer.setText(score + 36 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 30:
                resultProgressBar.setProgress(score + 45);
                resultCountDownTimer.setText(score + 45 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 31:
                resultProgressBar.setProgress(score + 46);
                resultCountDownTimer.setText(score + 46 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 32:
                resultProgressBar.setProgress(score + 47);
                resultCountDownTimer.setText(score + 47 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 33:
                resultProgressBar.setProgress(score + 48);
                resultCountDownTimer.setText(score + 48 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 34:
                resultProgressBar.setProgress(score + 49);
                resultCountDownTimer.setText(score + 49 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 35:
                resultProgressBar.setProgress(score + 50);
                resultCountDownTimer.setText(score + 50 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 36:
                resultProgressBar.setProgress(score + 52);
                resultCountDownTimer.setText(score + 52 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 37:
                resultProgressBar.setProgress(score + 54);
                resultCountDownTimer.setText(score + 54 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 38:
                resultProgressBar.setProgress(score + 56);
                resultCountDownTimer.setText(score + 56 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 39:
                resultProgressBar.setProgress(score + 58);
                resultCountDownTimer.setText(score + 58 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
            case 40:
                resultProgressBar.setProgress(score + 60);
                resultCountDownTimer.setText(score + 60 + "%");
                correctAnswerNum.setText(score + "");
                wrongAnswerNum.setText(TOTAL_QUESTION - score + "");
                break;
        }
    }
}