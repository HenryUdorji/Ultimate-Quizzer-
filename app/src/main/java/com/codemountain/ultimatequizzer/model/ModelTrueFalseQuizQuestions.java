package com.codemountain.ultimatequizzer.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelTrueFalseQuizQuestions implements Parcelable {
    private String question, answer;
    private int id;

    public ModelTrueFalseQuizQuestions() {
    }

    public ModelTrueFalseQuizQuestions(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    protected ModelTrueFalseQuizQuestions(Parcel in) {
        question = in.readString();
        answer = in.readString();
        id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(answer);
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ModelTrueFalseQuizQuestions> CREATOR = new Creator<ModelTrueFalseQuizQuestions>() {
        @Override
        public ModelTrueFalseQuizQuestions createFromParcel(Parcel in) {
            return new ModelTrueFalseQuizQuestions(in);
        }

        @Override
        public ModelTrueFalseQuizQuestions[] newArray(int size) {
            return new ModelTrueFalseQuizQuestions[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
