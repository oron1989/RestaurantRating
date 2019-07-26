package com.oron.restaurantrating.Data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oron.restaurantrating.Model.Restaurant;
import com.oron.restaurantrating.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class ArchivesRecyclerAdapter extends RecyclerView.Adapter<ArchivesRecyclerAdapter.ViewHolder>{
    private Context context;
    private List<Restaurant> restaurantList;

//    private DatabaseReference myForm;
//    private FirebaseUser myUser;
//    private FirebaseAuth myAuth;
//
//    private AlertDialog.Builder alertDialogBuilder;
//    private AlertDialog dialog;
//    private LayoutInflater inflater;

    public ArchivesRecyclerAdapter(Context context, List<Restaurant> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.archives_row, viewGroup, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Restaurant restaurant = restaurantList.get(i);

        viewHolder.restaurantName.setText(restaurant.getRestaurantName());
        viewHolder.cityName.setText(restaurant.getCity());
        viewHolder.grade.setText(restaurant.getGrade());
        viewHolder.score.setText(restaurant.getScore());

        DateFormat dateFormat = DateFormat.getDateInstance();
        String formattedDate = dateFormat.format(new Date(Long.valueOf(restaurant.getTimestamp())).getTime());

        viewHolder.timestamp.setText(formattedDate);

//        viewHolder.inspector.setText(restaurant.getUserId());
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView restaurantName;
        public TextView cityName;
        public TextView grade;
        public TextView score;
        public TextView timestamp;
//        public TextView inspector;

        public ViewHolder(@NonNull View view, Context ctx) {
            super(view);
            context = ctx;

            restaurantName = view.findViewById(R.id.restaurantNameArchivesTextView);
            cityName = view.findViewById(R.id.cityArchivesTextView);
            grade = view.findViewById(R.id.gradeArchivesTextView);
            score = view.findViewById(R.id.totalScoreArchivesTextView);
            timestamp = view.findViewById(R.id.inspectionDayArchivesTextView);
//            inspector = view.findViewById(R.id.inspectorNameArchivesTextView);


            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            //Get position of the row clicked or tapped
        }
    }
}
