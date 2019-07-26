package com.oron.restaurantrating.Model;

public class Form {
    private String question;
    private String selectedAnswer;
    private String score;
    private String note;

    public Form(String question, String selectedAnswer, String score, String note) {
        this.question = question;
        this.selectedAnswer = selectedAnswer;
        this.score = score;
        this.note = note;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
