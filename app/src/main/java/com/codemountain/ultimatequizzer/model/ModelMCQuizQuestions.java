package com.codemountain.ultimatequizzer.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelMCQuizQuestions implements Parcelable {
    private String question, A, B, C, D, answer;
    private int id;

    public ModelMCQuizQuestions() {
    }

    public ModelMCQuizQuestions(String question, String a, String b, String c, String d, String answer) {
        this.question = question;
        this.A = a;
        this.B = b;
        this.C = c;
        this.D = d;
        this.answer = answer;
    }

    protected ModelMCQuizQuestions(Parcel in) {
        question = in.readString();
        A = in.readString();
        B = in.readString();
        C = in.readString();
        D = in.readString();
        answer = in.readString();
        id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(A);
        dest.writeString(B);
        dest.writeString(C);
        dest.writeString(D);
        dest.writeString(answer);
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ModelMCQuizQuestions> CREATOR = new Creator<ModelMCQuizQuestions>() {
        @Override
        public ModelMCQuizQuestions createFromParcel(Parcel in) {
            return new ModelMCQuizQuestions(in);
        }

        @Override
        public ModelMCQuizQuestions[] newArray(int size) {
            return new ModelMCQuizQuestions[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
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
