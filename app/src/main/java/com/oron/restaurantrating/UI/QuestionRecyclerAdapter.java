package com.oron.restaurantrating.UI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oron.restaurantrating.Model.QuestionView;
import com.oron.restaurantrating.R;

import java.util.List;

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<QuestionView> questionList;



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
        }
    }
}
