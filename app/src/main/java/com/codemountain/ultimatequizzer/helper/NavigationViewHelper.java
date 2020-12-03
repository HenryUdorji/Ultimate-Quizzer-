package com.codemountain.ultimatequizzer.helper;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.codemountain.ultimatequizzer.R;
import com.codemountain.ultimatequizzer.activities.MainActivity;
import com.codemountain.ultimatequizzer.activities.SettingsActivity;
import com.codemountain.ultimatequizzer.utils.SharedPref;
import com.google.android.material.navigation.NavigationView;

import static com.codemountain.ultimatequizzer.utils.Constants.MC_QUIZ_HIGH_SCORE;
import static com.codemountain.ultimatequizzer.utils.Constants.TRUE_FALSE_QUIZ_HIGH_SCORE;

public class NavigationViewHelper {

    public static void enableNavigation(final Context context, NavigationView view){
        view.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navContactUs:
                    final Intent feedBackIntent = new Intent(Intent.ACTION_SEND);
                    feedBackIntent.setType("message/rfc822");
                    feedBackIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{context.getString(R.string.feedback_email)});
                    feedBackIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.feedback_subject));
                    feedBackIntent.setPackage("com.google.android.gm");
                    context.startActivity(feedBackIntent);
                    return true;

                case R.id.navViewHighScore:
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialog_view_high_score);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    ImageButton clearHighScore = dialog.findViewById(R.id.clearHighScoreBtn);
                    TextView mcqScoreText = dialog.findViewById(R.id.mcqScoreText);
                    TextView mcqTotalQuestionText = dialog.findViewById(R.id.mcqTotalQuestionText);
                    TextView trueFalseScoreText = dialog.findViewById(R.id.trueFalseScoreText);
                    TextView trueFalseTotalQuestionText = dialog.findViewById(R.id.trueFalseTotalQuestionText);

                    mcqScoreText.setText("Score: " + SharedPref.getSharedPrefInstance().getMcQuizHighScore());
                    mcqTotalQuestionText.setText("Total Questions: " + SharedPref.getSharedPrefInstance().getMCQuizTotalQuestion());
                    trueFalseScoreText.setText("Score: " + SharedPref.getSharedPrefInstance().getTrueFalseQuizHighScore());
                    trueFalseTotalQuestionText.setText("Total Questions: " + SharedPref.getSharedPrefInstance().getTrueFalseQuizTotalQuestion());

                    clearHighScore.setOnClickListener(v -> {
                        SharedPref.getSharedPrefInstance().putIntInPref(MC_QUIZ_HIGH_SCORE, 0);
                        SharedPref.getSharedPrefInstance().putIntInPref(TRUE_FALSE_QUIZ_HIGH_SCORE, 0);
                        SharedPref.getSharedPrefInstance().getMCQuizTotalQuestion();
                        SharedPref.getSharedPrefInstance().getTrueFalseQuizTotalQuestion();
                        Toast.makeText(context, "HighScore cleared", Toast.LENGTH_SHORT).show();
                    });
                    dialog.show();
                    return true;

                case R.id.navShareApp:
                    Toast.makeText(context, "Enjoy this app with friends", Toast.LENGTH_SHORT).show();
                    return true;

                case R.id.navSettings:
                    context.startActivity(new Intent(context, SettingsActivity.class));
                    ((MainActivity)context).overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    return true;

                case R.id.navRateApp:
                    try{
                        Uri rateApp = Uri.parse("market://details?id=" + context.getPackageName());
                        Intent rateAppIntent = new Intent(Intent.ACTION_VIEW, rateApp);
                        context.startActivity(rateAppIntent);
                    }
                    catch (ActivityNotFoundException e){
                        Uri rateApp = Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName());
                        Intent rateAppIntent = new Intent(Intent.ACTION_VIEW, rateApp);
                        context.startActivity(rateAppIntent);
                    }
                    return true;

            }
            return false;
        });
    }
}
