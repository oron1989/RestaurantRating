package com.oron.restaurantrating.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oron.restaurantrating.Model.Form;
import com.oron.restaurantrating.Model.QuestionView;
import com.oron.restaurantrating.R;

import java.util.ArrayList;
import java.util.List;

public class FormFillActivity extends AppCompatActivity {

    private final String answer1 = "Performed as required";
    private final String answer2 = "improvement required";
    private final String answer3 = "Not executed as required";

    //Statement of variables view
    private TextView questionCountTextView;
    private TextView totalScoreTextView;
    private TextView questionTextView;
    private RadioGroup radioGroup;
    private RadioButton answerRB1;
    private RadioButton answerRB2;
    private RadioButton answerRB3;
    private EditText noteEditText;
    private Button nextButton;
    //   private Button backButton;

    //Statement of variables
    private int score;
    private String grade;
    private int questionCounter;
    private int questionCountTotal;
    private QuestionView currentQuestion;
    private Boolean answered;
    private List<QuestionView> questionViewList;
    private List<Form> formList;

    //Statement of variables Firebase
    private DatabaseReference myDatabaseReference;
    private FirebaseDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_fill);

        setUI();

//        myDatabase = FirebaseDatabase.getInstance();
//        myDatabaseReference = myDatabase.getReference().child("Question");
//        myDatabaseReference.keepSynced(true);

        //get the
        formList = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        questionViewList = bundle.getParcelableArrayList("myList");

        questionCountTotal = questionViewList.size();

        showNextQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (answerRB1.isChecked() || answerRB2.isChecked() || answerRB3.isChecked()) {
                        saveAnswer();
                    } else {
                        Toast.makeText(FormFillActivity.this, "Select Answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });

    }

    private void setUI() {
        questionCountTextView = findViewById(R.id.questionCountTextView);
        totalScoreTextView = findViewById(R.id.totalScoreTextView);
        questionTextView = findViewById(R.id.questionFillFormTextView);
        radioGroup = findViewById(R.id.fillFromRadioGroupNew);
        answerRB1 = findViewById(R.id.radioButtonOption1FillForm);
        answerRB2 = findViewById(R.id.radioButtonOption2FillForm);
        answerRB3 = findViewById(R.id.radioButtonOption3FillForm);
        noteEditText = findViewById(R.id.noteEditText);
        nextButton = findViewById(R.id.nextButton);
//        backButton = findViewById(R.id.backButton);

        answerRB1.setText(answer1);
        answerRB2.setText(answer2);
        answerRB3.setText(answer3);
    }

    private void showNextQuestion() {
        radioGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionViewList.get(questionCounter);
            questionTextView.setText(currentQuestion.getQuestion());
            questionCounter++;
            questionCountTextView.setText("Question: " + questionCounter + " / " + questionCountTotal);
            answered = false;
            nextButton.setText("save");
        } else {
            finishFillForm();
        }
    }

    private void saveAnswer() {
        answered = true;

        RadioButton selectedRB = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNumber = radioGroup.indexOfChild(selectedRB) + 1;
        score += (answerNumber - 1);
        totalScoreTextView.setText("score: " + score);

        Form f = new Form(currentQuestion.getQuestion(), Integer.toString(answerNumber), Integer.toString(answerNumber));
        formList.add(f);

        if (questionCounter < questionCountTotal) {
            nextButton.setText("next question");
        } else {
            nextButton.setText("finish");
        }
    }

    private void finishFillForm() {

        finish();
    }

}
