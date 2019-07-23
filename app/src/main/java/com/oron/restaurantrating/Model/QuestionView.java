package com.oron.restaurantrating.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionView  implements Parcelable {

    private String question;

    public QuestionView() {
    }

    public QuestionView(String question) {
        this.question = question;
    }

    protected QuestionView(Parcel in) {
        question = in.readString();
    }

    public static final Creator<QuestionView> CREATOR = new Creator<QuestionView>() {
        @Override
        public QuestionView createFromParcel(Parcel in) {
            return new QuestionView(in);
        }

        @Override
        public QuestionView[] newArray(int size) {
            return new QuestionView[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
    }

}
