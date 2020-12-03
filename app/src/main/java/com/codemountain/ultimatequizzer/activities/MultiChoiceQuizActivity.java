package com.codemountain.ultimatequizzer.activities;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.codemountain.ultimatequizzer.R;
import com.codemountain.ultimatequizzer.database.QuizDbHelper;
import com.codemountain.ultimatequizzer.interfaces.Quiz;
import com.codemountain.ultimatequizzer.model.ModelMCQuizQuestions;
import com.codemountain.ultimatequizzer.utils.SharedPref;

import java.util.ArrayList;
import java.util.Collections;

import static com.codemountain.ultimatequizzer.utils.Constants.COUNTDOWN_IN_MILLIS;
import static com.codemountain.ultimatequizzer.utils.Constants.FROM;
import static com.codemountain.ultimatequizzer.utils.Constants.KEY_ANSWERED;
import static com.codemountain.ultimatequizzer.utils.Constants.KEY_MILLIS_LEFT;
import static com.codemountain.ultimatequizzer.utils.Constants.KEY_QUESTION_COUNT;
import static com.codemountain.ultimatequizzer.utils.Constants.KEY_QUESTION_LIST;
import static com.codemountain.ultimatequizzer.utils.Constants.KEY_SCORE;
import static com.codemountain.ultimatequizzer.utils.Constants.QUIZ_SCORE;

public class MultiChoiceQuizActivity extends AppCompatActivity implements Quiz {
    private static final String TAG = "MultiChoiceQuizActivity";
    private ColorStateList defaultColorCountDownTimer;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private TextView questionNum, quizQuestion, quizCountdownTime;
    private RadioGroup radioGroup;
    private ProgressBar quizProgressBar;
    private RadioButton option1, option2, option3, option4;
    private Button confirmBtn;
    private ArrayList<ModelMCQuizQuestions> questionsList;
    private ColorStateList textDefaultColorRb;
    private int questionCounter, questionCountTotal, score;
    private ModelMCQuizQuestions currentQuestion;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_choice_quiz);
        initViews();

        if(savedInstanceState == null){
            QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
            questionsList = dbHelper.getAllMcQuizQuestion();
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
    public void initViews() {
        questionNum = findViewById(R.id.quizQuestionNum);
        quizQuestion = findViewById(R.id.quizQuestion);
        quizCountdownTime = findViewById(R.id.countDownTime);
        radioGroup = findViewById(R.id.radioGroup);
        option1 = findViewById(R.id.optionBtnA);
        option2 = findViewById(R.id.optionBtnB);
        option3 = findViewById(R.id.optionBtnC);
        option4 = findViewById(R.id.optionBtnD);
        quizProgressBar = findViewById(R.id.countDownProgressBar);
        confirmBtn = findViewById(R.id.quizConfirmBtn);

        defaultColorCountDownTimer = quizCountdownTime.getTextColors();
        textDefaultColorRb = option1.getTextColors();

        confirmBtn.setOnClickListener(v -> {
            if(!answered){
                if(option1.isChecked() || option2.isChecked() || option3.isChecked() || option4.isChecked()){
                    checkAnswer();
                }
                else {
                    Toast.makeText(MultiChoiceQuizActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                showNextQuestion();
            }
        });
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
    public void checkAnswer() {
        String answerOption;
        answered = true;
        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNum = radioGroup.indexOfChild(rbSelected);
        switch (answerNum + 1){
            case 1:
                answerOption = "A";
                if(answerOption.equals(currentQuestion.getAnswer())){
                    score++;
                }
                break;
            case 2:
                answerOption = "B";
                if(answerOption.equals(currentQuestion.getAnswer())){
                    score++;
                }
                break;
            case 3:
                answerOption = "C";
                if(answerOption.equals(currentQuestion.getAnswer())){
                    score++;
                }
                break;
            case 4:
                answerOption = "D";
                if(answerOption.equals(currentQuestion.getAnswer())){
                    score++;
                }
                break;
        }
        showSolution();
    }

    @Override
    public void showSolution() {
        option1.setTextColor(Color.RED);
        option2.setTextColor(Color.RED);
        option3.setTextColor(Color.RED);
        option4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswer()){
            case "A":
                option1.setTextColor(Color.GREEN);
                //option1.setChecked(true);
                //quizQuestion.setText("Option A is correct");
                break;
            case "B":
                option2.setTextColor(Color.GREEN);
                //option2.setChecked(true);
                //quizQuestion.setText("Option B is correct");
                break;
            case "C":
                option3.setTextColor(Color.GREEN);
                //option3.setChecked(true);
                //quizQuestion.setText("Option C is correct");
                break;
            case "D":
                option4.setTextColor(Color.GREEN);
                //option4.setChecked(true);
                //quizQuestion.setText("Option D is correct");
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
        option1.setTextColor(textDefaultColorRb);
        option2.setTextColor(textDefaultColorRb);
        option3.setTextColor(textDefaultColorRb);
        option4.setTextColor(textDefaultColorRb);
        radioGroup.clearCheck();

        if(questionCounter < questionCountTotal){
            currentQuestion = questionsList.get(questionCounter);
            quizQuestion.setText(currentQuestion.getQuestion());
            option1.setText(currentQuestion.getA());
            option2.setText(currentQuestion.getB());
            option3.setText(currentQuestion.getC());
            option4.setText(currentQuestion.getD());

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
                    if (!(option1.isChecked() && option2.isChecked() && option3.isChecked() && option4.isChecked())) {
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
        startActivity(new Intent(MultiChoiceQuizActivity.this, ResultActivity.class)
        .putExtra(FROM, "mcq")
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
            Intent intent = new Intent(MultiChoiceQuizActivity.this, MainActivity.class);
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