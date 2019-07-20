package com.oron.restaurantrating.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oron.restaurantrating.Data.QuestionRecyclerAdapter;
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.user_account_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.action_add:
//                if (mAuth != null && mUser != null) {
//                    startActivity(new Intent(PostListActivity.this, AddPostActivity.class));
//                    finish();
//                }
//               break;
//            case R.id.action_signout:
//                if (mAuth != null && mUser != null) {
//                    mAuth.signOut();
//                    startActivity(new Intent(PostListActivity.this, MainActivity.class));
//                    finish();
//                }
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

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
