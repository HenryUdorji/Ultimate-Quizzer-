package com.codemountain.ultimatequizzer.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.codemountain.ultimatequizzer.R;
import com.codemountain.ultimatequizzer.model.ModelMCQuizQuestions;
import com.codemountain.ultimatequizzer.model.ModelTrueFalseQuizQuestions;
import com.codemountain.ultimatequizzer.utils.SharedPref;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "QuizDbHelper";
    public static final String DATABASE_NAME = "quizApp.db";
    public static final int DATABASE_VERSION = 1;

    private  static QuizDbHelper instance;
    private SQLiteDatabase db;
    private Resources resources;

    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        if (context != null) {
            resources = context.getResources();
        }
        db = this.getWritableDatabase();
    }

    //Checking if there is an instance of the database already
    public static synchronized QuizDbHelper getInstance(Context context){
        if(instance == null){
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        //SQL query to create Table for Multi choice quiz questions
        final String SQL_CREATE_MC_QUIZ_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.McQuizQuestionsTable.TABLE_NAME + " ( " +
                QuizContract.McQuizQuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.McQuizQuestionsTable.COLUMN_QUESTIONS + " TEXT, " +
                QuizContract.McQuizQuestionsTable.COLUMN_A + " TEXT, " +
                QuizContract.McQuizQuestionsTable.COLUMN_B + " TEXT, " +
                QuizContract.McQuizQuestionsTable.COLUMN_C + " TEXT, " +
                QuizContract.McQuizQuestionsTable.COLUMN_D + " TEXT, " +
                QuizContract.McQuizQuestionsTable.COLUMN_ANSWER + " TEXT " +
                " )";

        //SQL query to create Table for True / False quiz questions
        final String SQL_CREATE_TRUE_FALSE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.TrueFalseQuestionsTable.TABLE_NAME + " ( " +
                QuizContract.TrueFalseQuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.TrueFalseQuestionsTable.COLUMN_QUESTIONS + " TEXT, " +
                QuizContract.TrueFalseQuestionsTable.COLUMN_ANSWER + " TEXT " +
                " )";

        db.execSQL(SQL_CREATE_MC_QUIZ_QUESTIONS_TABLE);
        db.execSQL(SQL_CREATE_TRUE_FALSE_QUESTIONS_TABLE);
        Log.d(TAG, "onCreate: DB created " + db);

        try{
            readMcQuizQuestionDataToDb(db);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            readTrueFalseQuizQuestionDataToDb(db);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.McQuizQuestionsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.TrueFalseQuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void readMcQuizQuestionDataToDb(SQLiteDatabase db)  {

        try{
            String jsonDataString = readMcQuizJsonDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < jsonArray.length(); ++i){
                String questions, a, b, c, d, answers;

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                questions = jsonObject.getString(QuizContract.McQuizQuestionsTable.COLUMN_QUESTIONS);
                a = jsonObject.getString(QuizContract.McQuizQuestionsTable.COLUMN_A);
                b = jsonObject.getString(QuizContract.McQuizQuestionsTable.COLUMN_B);
                c = jsonObject.getString(QuizContract.McQuizQuestionsTable.COLUMN_C);
                d = jsonObject.getString(QuizContract.McQuizQuestionsTable.COLUMN_D);
                answers = jsonObject.getString(QuizContract.McQuizQuestionsTable.COLUMN_ANSWER);

                ContentValues contentValues = new ContentValues();
                contentValues.put(QuizContract.McQuizQuestionsTable.COLUMN_QUESTIONS, questions);
                contentValues.put(QuizContract.McQuizQuestionsTable.COLUMN_A, a);
                contentValues.put(QuizContract.McQuizQuestionsTable.COLUMN_B, b);
                contentValues.put(QuizContract.McQuizQuestionsTable.COLUMN_C, c);
                contentValues.put(QuizContract.McQuizQuestionsTable.COLUMN_D, d);
                contentValues.put(QuizContract.McQuizQuestionsTable.COLUMN_ANSWER, answers);
                db.insert(QuizContract.McQuizQuestionsTable.TABLE_NAME, null, contentValues);
                Log.d(TAG, "readQuestionDataToDb: Data inserted " + db);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private String readMcQuizJsonDataFromFile() throws Exception {
        InputStream stream = null;
        StringBuilder builder = new StringBuilder();

        try{
            String jsonDataString;
            stream = resources.openRawResource(R.raw.mc_quiz_question);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            while ((jsonDataString = reader.readLine()) != null){
                builder.append(jsonDataString);
            }
        }
        finally {
            if(stream != null){
                stream.close();
            }
        }
        return new String(builder);
    }

    private void readTrueFalseQuizQuestionDataToDb(SQLiteDatabase db)  {

        try{
            String jsonDataString = readTrueFalseQuizJsonDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < jsonArray.length(); ++i){
                String questions, answers;

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                questions = jsonObject.getString(QuizContract.TrueFalseQuestionsTable.COLUMN_QUESTIONS);
                answers = jsonObject.getString(QuizContract.TrueFalseQuestionsTable.COLUMN_ANSWER);

                ContentValues contentValues = new ContentValues();
                contentValues.put(QuizContract.TrueFalseQuestionsTable.COLUMN_QUESTIONS, questions);
                contentValues.put(QuizContract.TrueFalseQuestionsTable.COLUMN_ANSWER, answers);
                db.insert(QuizContract.TrueFalseQuestionsTable.TABLE_NAME, null, contentValues);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private String readTrueFalseQuizJsonDataFromFile() throws Exception {
        InputStream stream = null;
        StringBuilder builder = new StringBuilder();

        try{
            String jsonDataString;
            stream = resources.openRawResource(R.raw.true_false);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            while ((jsonDataString = reader.readLine()) != null){
                builder.append(jsonDataString);
            }
        }
        finally {
            if(stream != null){
                stream.close();
            }
        }
        return new String(builder);
    }

    public ArrayList<ModelMCQuizQuestions> getAllMcQuizQuestion(){
        ArrayList<ModelMCQuizQuestions> questionsList = new ArrayList<>();
        int questionTotal = SharedPref.getSharedPrefInstance().getTotalQuestions();
        Log.d(TAG, "getAllQuestion: Question Total--> " + questionTotal);
        db = getReadableDatabase();
        Cursor cursor = null;
        switch (questionTotal) {
            case 10:
                cursor = db.rawQuery("SELECT * FROM " + QuizContract.McQuizQuestionsTable.TABLE_NAME + " ORDER BY RANDOM() LIMIT 10 ", null);
                break;
            case 20:
                cursor = db.rawQuery("SELECT * FROM " + QuizContract.McQuizQuestionsTable.TABLE_NAME + " ORDER BY RANDOM() LIMIT 20 ", null);
                break;
            case 30:
                cursor = db.rawQuery("SELECT * FROM " + QuizContract.McQuizQuestionsTable.TABLE_NAME + " ORDER BY RANDOM() LIMIT 30 ", null);
                break;
            case 40:
                cursor = db.rawQuery("SELECT * FROM " + QuizContract.McQuizQuestionsTable.TABLE_NAME + " ORDER BY RANDOM() LIMIT 40 ", null);
                break;
        }
        if (cursor != null && cursor.moveToFirst()) {
            do {
                ModelMCQuizQuestions questions = new ModelMCQuizQuestions();
                questions.setId(cursor.getInt(cursor.getColumnIndex(QuizContract.McQuizQuestionsTable._ID)));
                questions.setQuestion(cursor.getString(cursor.getColumnIndex(QuizContract.McQuizQuestionsTable.COLUMN_QUESTIONS)));
                questions.setA(cursor.getString(cursor.getColumnIndex(QuizContract.McQuizQuestionsTable.COLUMN_A)));
                questions.setB(cursor.getString(cursor.getColumnIndex(QuizContract.McQuizQuestionsTable.COLUMN_B)));
                questions.setC(cursor.getString(cursor.getColumnIndex(QuizContract.McQuizQuestionsTable.COLUMN_C)));
                questions.setD(cursor.getString(cursor.getColumnIndex(QuizContract.McQuizQuestionsTable.COLUMN_D)));
                questions.setAnswer(cursor.getString(cursor.getColumnIndex(QuizContract.McQuizQuestionsTable.COLUMN_ANSWER)));
                questionsList.add(questions);
            }
            while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return questionsList;
    }

    public ArrayList<ModelTrueFalseQuizQuestions> getAllTrueFalseQuizQuestion(){
        ArrayList<ModelTrueFalseQuizQuestions> questionsList = new ArrayList<>();
        int questionTotal = SharedPref.getSharedPrefInstance().getTotalQuestions();
        Log.d(TAG, "getAllQuestion: Question Total--> " + questionTotal);
        db = getReadableDatabase();
        Cursor cursor = null;
        switch (questionTotal) {
            case 10:
                cursor = db.rawQuery("SELECT * FROM " + QuizContract.TrueFalseQuestionsTable.TABLE_NAME + " ORDER BY RANDOM() LIMIT 10 ", null);
                break;
            case 20:
                cursor = db.rawQuery("SELECT * FROM " + QuizContract.TrueFalseQuestionsTable.TABLE_NAME + " ORDER BY RANDOM() LIMIT 20 ", null);
                break;
            case 30:
                cursor = db.rawQuery("SELECT * FROM " + QuizContract.TrueFalseQuestionsTable.TABLE_NAME + " ORDER BY RANDOM() LIMIT 30 ", null);
                break;
            case 40:
                cursor = db.rawQuery("SELECT * FROM " + QuizContract.TrueFalseQuestionsTable.TABLE_NAME + " ORDER BY RANDOM() LIMIT 40 ", null);
                break;
        }
        if (cursor != null && cursor.moveToFirst()) {
            do {
                ModelTrueFalseQuizQuestions questions = new ModelTrueFalseQuizQuestions();
                questions.setId(cursor.getInt(cursor.getColumnIndex(QuizContract.TrueFalseQuestionsTable._ID)));
                questions.setQuestion(cursor.getString(cursor.getColumnIndex(QuizContract.TrueFalseQuestionsTable.COLUMN_QUESTIONS)));
                questions.setAnswer(cursor.getString(cursor.getColumnIndex(QuizContract.TrueFalseQuestionsTable.COLUMN_ANSWER)));
                questionsList.add(questions);
            }
            while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return questionsList;
    }

}
