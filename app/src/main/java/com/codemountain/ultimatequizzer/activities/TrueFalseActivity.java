package com.codemountain.ultimatequizzer.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codemountain.ultimatequizzer.R;
import com.codemountain.ultimatequizzer.database.QuizDbHelper;
import com.codemountain.ultimatequizzer.interfaces.Quiz;
import com.codemountain.ultimatequizzer.model.ModelMCQuizQuestions;
import com.codemountain.ultimatequizzer.model.ModelTrueFalseQuizQuestions;

import java.util.ArrayList;

import static com.codemountain.ultimatequizzer.utils.Constants.COUNTDOWN_IN_MILLIS;
import static com.codemountain.ultimatequizzer.utils.Constants.FROM;
import static com.codemountain.ultimatequizzer.utils.Constants.KEY_ANSWERED;
import static com.codemountain.ultimatequizzer.utils.Constants.KEY_MILLIS_LEFT;
import static com.codemountain.ultimatequizzer.utils.Constants.KEY_QUESTION_COUNT;
import static com.codemountain.ultimatequizzer.utils.Constants.KEY_QUESTION_LIST;
import static com.codemountain.ultimatequizzer.utils.Constants.KEY_SCORE;
import static com.codemountain.ultimatequizzer.utils.Constants.QUIZ_SCORE;

public class TrueFalseActivity extends AppCompatActivity implements Quiz {
    private static final String TAG = "MultiChoiceQuizActivity";
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private TextView questionNum, quizQuestion, quizCountdownTime;
    private RadioGroup radioGroup;
    private ProgressBar quizProgressBar;
    private RadioButton optionTrue, optionFalse;
    private Button confirmBtn;
    private ArrayList<ModelTrueFalseQuizQuestions> questionsList;
    private ColorStateList textDefaultColorRb;
    private ColorStateList defaultColorCountDownTimer;
    private int questionCounter, questionCountTotal, score;
    private ModelTrueFalseQuizQuestions currentQuestion;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false);

        initViews();
        if(savedInstanceState == null){
            QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
            questionsList = dbHelper.getAllTrueFalseQuizQuestion();
            questionCountTotal = questionsList.size();
            //Collections.shuffle(questionsList);

            showNextQuestion();
        }
        else {
            questionsList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            if (questionsList != null) {
                questionCountTotal = questionsList.size();
            }
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionsList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);
            timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);

            if(!answered){
                startCountDown();
            }
            else {
                updateCountDownText();
                showSolution();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionsList);
    }

    @Override
    public void initViews() {
        questionNum = findViewById(R.id.quizQuestionNum);
        quizQuestion = findViewById(R.id.quizQuestion);
        quizCountdownTime = findViewById(R.id.countDownTime);
        radioGroup = findViewById(R.id.radioGroup);
        optionTrue = findViewById(R.id.optionTrue);
        optionFalse = findViewById(R.id.optionFalse);
        quizProgressBar = findViewById(R.id.countDownProgressBar);
        confirmBtn = findViewById(R.id.quizConfirmBtn);

        defaultColorCountDownTimer = quizCountdownTime.getTextColors();
        textDefaultColorRb = optionTrue.getTextColors();

        confirmBtn.setOnClickListener(v -> {
            if(!answered){
                if(optionTrue.isChecked() || optionFalse.isChecked()) {
                    checkAnswer();
                }
                else {
                    Toast.makeText(TrueFalseActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                showNextQuestion();
            }
        });
    }

    @Override
    public void checkAnswer() {
        String answerOption;
        answered = true;
        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        String answerText = rbSelected.getText().toString();;
        switch (answerText){
            case "True":
                answerOption = "true";
                if(answerOption.equals(currentQuestion.getAnswer())){
                    score++;
                }
                break;
            case "False":
                answerOption = "false";
                if(answerOption.equals(currentQuestion.getAnswer())){
                    score++;
                }
                break;
        }
        showSolution();
    }

    @Override
    public void showSolution() {
        optionTrue.setTextColor(Color.RED);
        optionFalse.setTextColor(Color.RED);

        switch (currentQuestion.getAnswer()){
            case "true":
                optionTrue.setTextColor(Color.GREEN);
                //option1.setChecked(true);
                //quizQuestion.setText("Option A is correct");
                break;
            case "false":
                optionFalse.setTextColor(Color.GREEN);
                //option2.setChecked(true);
                //quizQuestion.setText("Option B is correct");
                break;
        }

        if(questionCounter < questionCountTotal) {
            confirmBtn.setText("Next");
        }
        else {
            confirmBtn.setEnabled(true);
            confirmBtn.setText("Finish");
        }
    }

    @Override
    public void showNextQuestion() {
        optionTrue.setTextColor(textDefaultColorRb);
        optionFalse.setTextColor(textDefaultColorRb);
        radioGroup.clearCheck();

        if(questionCounter < questionCountTotal){
            currentQuestion = questionsList.get(questionCounter);
            quizQuestion.setText(currentQuestion.getQuestion());
            optionTrue.setText("True");
            optionFalse.setText("False");

            questionCounter++;
            questionNum.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            confirmBtn.setText("Confirm");
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        }
        else {
            finishQuiz();
        }
    }

    @Override
    public void startCountDown() {
        quizProgressBar.setVisibility(View.VISIBLE);
        countDownTimer = new CountDownTimer(timeLeftInMillis, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                if(!answered) {
                    if (!(optionTrue.isChecked() && optionFalse.isChecked())) {
                        showSolution();
                        showNextQuestion();
                    }
                }
            }
        }.start();
    }

    @Override
    public void updateCountDownText() {
        //set progressBar
        quizProgressBar.setVisibility(View.VISIBLE);
        long percent = timeLeftInMillis / 200;
        Log.d(TAG, "updateCountDownText: Percent " + percent);
        quizProgressBar.setProgress((int) percent);

        quizCountdownTime.setText(timeLeftInMillis / 1000 + "");
        if(timeLeftInMillis < 10000){
            quizCountdownTime.setTextColor(Color.RED);
        }
        else {
            quizCountdownTime.setTextColor(defaultColorCountDownTimer);
        }
    }

    @Override
    public void finishQuiz() {
        startActivity(new Intent(TrueFalseActivity.this, ResultActivity.class)
                .putExtra(FROM, "true_false")
                .putExtra(QUIZ_SCORE, score));
        finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.exit_quiz);
        builder.setMessage(R.string.are_you_sure_to_exit);
        builder.setPositiveButton("Exit", (dialog, which) -> {
            Intent intent = new Intent(TrueFalseActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }).setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}