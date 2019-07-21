package com.oron.restaurantrating.UI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.oron.restaurantrating.Model.QuestionView;
import com.oron.restaurantrating.R;

import java.util.List;

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<QuestionView> questionList;

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

        public ViewHolder(@NonNull View view, Context ctx) {
            super(view);
            context = ctx;

            view.setOnClickListener(this);

            question = view.findViewById(R.id.questinTextView);

        }

        @Override
        public void onClick(View v) {
            //Get position of the row clicked or tapped
            int position = getAdapterPosition();
            QuestionView question = questionList.get(position);

            editItem(question);

        }

        public void editItem(QuestionView question) {
            alertDialogBuilder = new AlertDialog.Builder(context);
            inflater = LayoutInflater.from(context);

            final View view = inflater.inflate(R.layout.popup_question, null);

            TextView questionText = view.findViewById(R.id.textAlert);
            RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
            RadioButton answer1 = view.findViewById(R.id.radioButtonOption1);
            RadioButton answer2 = view.findViewById(R.id.radioButtonOption2);
            RadioButton answer3 = view.findViewById(R.id.radioButtonOption3);
            RadioButton answer4 = view.findViewById(R.id.radioButtonOption4);
            RadioButton answer5 = view.findViewById(R.id.radioButtonOption5);
            Button okButton = view.findViewById(R.id.okButton);
            Button cancelButton = view.findViewById(R.id.cancelButton);

            questionText.setText(question.getQuestion());

            alertDialogBuilder.setView(view);
            dialog = alertDialogBuilder.create();
            dialog.show();

            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
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
