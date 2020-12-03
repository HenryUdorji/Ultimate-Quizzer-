package com.codemountain.ultimatequizzer.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codemountain.ultimatequizzer.R;
import com.codemountain.ultimatequizzer.helper.NavigationViewHelper;
import com.codemountain.ultimatequizzer.utils.SharedPref;
import com.google.android.material.navigation.NavigationView;

import static com.codemountain.ultimatequizzer.utils.Constants.MODE_SWITCH;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private long backPressedTime;
    private boolean isNightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isNightMode = SharedPref.getSharedPrefInstance().getMode();
        initViews();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        Button startQuizApp = findViewById(R.id.startQuizApp);
        startQuizApp.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_select_quiz_type);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            LinearLayout mcqLayout = dialog.findViewById(R.id.mcqLayout);
            LinearLayout trueFalseLayout = dialog.findViewById(R.id.trueFalseLayout);

            mcqLayout.setOnClickListener(v1 -> {
                startActivity(new Intent(MainActivity.this, MultiChoiceQuizActivity.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            });
            trueFalseLayout.setOnClickListener(v12 -> {
                startActivity(new Intent(MainActivity.this, TrueFalseActivity.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            });

            dialog.show();

        });

        //Navigation view
        navigationView = findViewById(R.id.navigateView);
        NavigationViewHelper.enableNavigation(MainActivity.this, navigationView);
        View headerView = navigationView.getHeaderView(0);
        TextView modeText = headerView.findViewById(R.id.nightModeText);
        SwitchCompat modeSwitch = headerView.findViewById(R.id.modeSwitch);
        if (isNightMode) {
            modeSwitch.setChecked(true);
            modeText.setText("Night mode");
            SharedPref.getSharedPrefInstance().putBooleanInPref(MODE_SWITCH, true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            modeText.setText("Light mode");
            SharedPref.getSharedPrefInstance().putBooleanInPref(MODE_SWITCH, false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        modeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                modeText.setText("Night mode");
                SharedPref.getSharedPrefInstance().putBooleanInPref(MODE_SWITCH, true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            else {
                modeText.setText("Light mode");
                SharedPref.getSharedPrefInstance().putBooleanInPref(MODE_SWITCH, false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            finish();
        }
        else {
            Toast.makeText(this, "Press back again to close app", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }
}