package com.codemountain.ultimatequizzer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.codemountain.ultimatequizzer.R;
import com.codemountain.ultimatequizzer.utils.SharedPref;

import static com.codemountain.ultimatequizzer.utils.Constants.TOTAL_QUESTIONS;

public class SettingsActivity extends AppCompatActivity {
    volatile int numberOfQuestions;
    private Toolbar toolbar;
    private LinearLayout totalQuestionsLayout;
    private TextView numberOfQuestionText;
    private RadioGroup radioGroup;
    private RadioButton option10, option20, option30, option40;
    private int totalQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViews();
        totalQuestion = SharedPref.getSharedPrefInstance().getTotalQuestions();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        numberOfQuestionText = findViewById(R.id.numberOfQuestionText);
        totalQuestionsLayout = findViewById(R.id.totalQuestionsLayout);
        totalQuestionsLayout.setOnClickListener(v -> {
            Dialog dialog = new Dialog(SettingsActivity.this);
            dialog.setContentView(R.layout.dialog_select_quiz_total_questions);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            radioGroup = dialog.findViewById(R.id.radioGroup);
            option10 = dialog.findViewById(R.id.option10);
            option20 = dialog.findViewById(R.id.option20);
            option30 = dialog.findViewById(R.id.option30);
            option40 = dialog.findViewById(R.id.option40);

            //set already checked radio button of dialog
            switch (totalQuestion) {
                case 10:
                    option10.setChecked(true);
                    numberOfQuestionText.setText(R.string._10_questions);
                    break;
                case 20:
                    option20.setChecked(true);
                    numberOfQuestionText.setText(R.string._20_questions);
                    break;
                case 30:
                    option30.setChecked(true);
                    numberOfQuestionText.setText(R.string._30_questions);
                    break;
                case 40:
                    option40.setChecked(true);
                    numberOfQuestionText.setText(R.string._40_questions);
                    break;
            }

            radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                switch (checkedId) {
                    case R.id.option10:
                        numberOfQuestions = 10;
                        option10.setChecked(true);
                        numberOfQuestionText.setText(R.string._10_questions);
                        SharedPref.getSharedPrefInstance().putIntInPref(TOTAL_QUESTIONS, numberOfQuestions);
                        dialog.dismiss();
                        break;
                    case R.id.option20:
                        numberOfQuestions = 20;
                        option20.setChecked(true);
                        numberOfQuestionText.setText(R.string._20_questions);
                        SharedPref.getSharedPrefInstance().putIntInPref(TOTAL_QUESTIONS, numberOfQuestions);
                        dialog.dismiss();
                        break;
                    case R.id.option30:
                        numberOfQuestions = 30;
                        option30.setChecked(true);
                        numberOfQuestionText.setText(R.string._30_questions);
                        SharedPref.getSharedPrefInstance().putIntInPref(TOTAL_QUESTIONS, numberOfQuestions);
                        dialog.dismiss();
                        break;
                    case R.id.option40:
                        numberOfQuestions = 40;
                        option40.setChecked(true);
                        numberOfQuestionText.setText(R.string._40_questions);
                        SharedPref.getSharedPrefInstance().putIntInPref(TOTAL_QUESTIONS, numberOfQuestions);
                        dialog.dismiss();
                        break;
                }
            });
            dialog.show();
        });
    }
}