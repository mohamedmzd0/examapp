package com.example.summerproject.summerproject;


import java.util.ArrayList;

public class QuizModel {

    public String quiz ,currect_ans ;
    public ArrayList<String> answer ;

    public QuizModel() {
    }

    public QuizModel(String quiz, ArrayList<String> answer,String currect_ans) {
        this.quiz = quiz;
        this.answer = answer;
        this.currect_ans=currect_ans;
    }
}
