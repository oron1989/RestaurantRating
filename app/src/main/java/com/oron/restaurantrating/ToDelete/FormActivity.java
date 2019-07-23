package com.oron.restaurantrating.ToDelete;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oron.restaurantrating.Model.QuestionView;
import com.oron.restaurantrating.R;

import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private QuestionRecyclerAdapter questionRecyclerAdapter;
    private List<QuestionView> questionViewList;

    private DatabaseReference myDatabaseReference;
    private FirebaseDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        myDatabase = FirebaseDatabase.getInstance();
        myDatabaseReference = myDatabase.getReference().child("Question");
        myDatabaseReference.keepSynced(true);

        questionViewList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycleViewForm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        myDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                QuestionView questionView = dataSnapshot.getValue(QuestionView.class);

                questionViewList.add(questionView);

                questionRecyclerAdapter = new QuestionRecyclerAdapter(FormActivity.this, questionViewList);
                recyclerView.setAdapter(questionRecyclerAdapter);
                questionRecyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
