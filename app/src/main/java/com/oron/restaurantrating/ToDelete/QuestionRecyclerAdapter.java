package com.oron.restaurantrating.ToDelete;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oron.restaurantrating.Model.Form;
import com.oron.restaurantrating.Model.QuestionView;
import com.oron.restaurantrating.R;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionRecyclerAdapter.ViewHolder> {

    private final String answer1 = "Performed as required";
    private final String answer2 = "improvement required";
    private final String answer3 = "Not executed as required";

    private Context context;
    private List<QuestionView> questionList;
    private List<Form> formList = new ArrayList<>();

    private DatabaseReference myForm;
    private FirebaseUser myUser;
    private FirebaseAuth myAuth;

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private LayoutInflater inflater;

    public QuestionRecyclerAdapter(Context context, List<QuestionView> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public QuestionRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.questions_row, viewGroup, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionRecyclerAdapter.ViewHolder viewHolder, int i) {
        QuestionView questionView = questionList.get(i);

        viewHolder.question.setText(questionView.getQuestion());

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView question;
        public TextView scoreText;
        public TextView selectedAnswer;

        public ViewHolder(@NonNull View view, Context ctx) {
            super(view);
            context = ctx;

            view.setOnClickListener(this);

            question = view.findViewById(R.id.questionTextView);
            scoreText = view.findViewById(R.id.totalScoreTextView);
            selectedAnswer = view.findViewById(R.id.selectedAnswerTextView);

        }

        @Override
        public void onClick(View v) {
            //Get position of the row clicked or tapped
            int position = getAdapterPosition();
            QuestionView question = questionList.get(position);

            FilledAnswer(question);

        }

        public void FilledAnswer(final QuestionView question) {
            alertDialogBuilder = new AlertDialog.Builder(context);
            inflater = LayoutInflater.from(context);

            final View view = inflater.inflate(R.layout.popup_question, null);

            TextView questionText = view.findViewById(R.id.questionFillFormTextView);

            myForm = FirebaseDatabase.getInstance().getReference().child("Form");
            myAuth = FirebaseAuth.getInstance();
            myUser = myAuth.getCurrentUser();
            myForm.keepSynced(true);
            final DatabaseReference newForm = myForm.push();

            final RadioGroup radioGroup = view.findViewById(R.id.fillFromradioGroup);
            final RadioButton answer1RadioButton = view.findViewById(R.id.radioButtonOption1FillForm);
            final RadioButton answer2RadioButton = view.findViewById(R.id.radioButtonOption2FillForm);
            final RadioButton answer3RadioButton = view.findViewById(R.id.radioButtonOption3FillForm);
            Button okButton = view.findViewById(R.id.nextButton);
            Button cancelButton = view.findViewById(R.id.cancelButton);

            questionText.setText(question.getQuestion());
            answer1RadioButton.setText(answer1);
            answer2RadioButton.setText(answer2);
            answer3RadioButton.setText(answer3);

            alertDialogBuilder.setView(view);
            dialog = alertDialogBuilder.create();
            dialog.show();

            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Form form;
                    if (answer1RadioButton.isChecked()) {
                        dialog.dismiss();
                        selectedAnswer.setText(answer1);
                        scoreText.setText("0");
                    } else if (answer2RadioButton.isChecked()) {
                        dialog.dismiss();
                        selectedAnswer.setText(answer2);
                        scoreText.setText("3");
                    } else if (answer3RadioButton.isChecked()) {
                        dialog.dismiss();
                        selectedAnswer.setText(answer3);
                        scoreText.setText("5");
                    }
                    form = new Form(question.getQuestion(), selectedAnswer.getText().toString(),scoreText.getText().toString());
                    formList.add(form);

                    newForm.setValue(formList).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(context, "save", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, e.toString());
                        }
                    });
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });


        }

    }
}
