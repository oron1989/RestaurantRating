package com.oron.restaurantrating.Model;

public class Form {
    private String question;
    private String selectedAnswer;
    private String score;

    public Form(String question, String selectedAnswer, String score) {
        this.question = question;
        this.selectedAnswer = selectedAnswer;
        this.score = score;
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
}
