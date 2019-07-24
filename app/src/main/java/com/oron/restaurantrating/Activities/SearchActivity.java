package com.oron.restaurantrating.Activities;

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
import com.oron.restaurantrating.Data.SearchRecyclerAdapter;
import com.oron.restaurantrating.Model.Restaurant;
import com.oron.restaurantrating.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchRecyclerAdapter searchRecyclerAdapter;
    private List<Restaurant> restaurantList;

    private DatabaseReference myDatabaseReference;
    private FirebaseDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        myDatabase = FirebaseDatabase.getInstance();
        myDatabaseReference = myDatabase.getReference().child("Restaurants");
        myDatabaseReference.keepSynced(true);

        restaurantList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycleViewSearch);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        myDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);

                restaurantList.add(restaurant);

                searchRecyclerAdapter = new SearchRecyclerAdapter(SearchActivity.this, restaurantList);
                recyclerView.setAdapter(searchRecyclerAdapter);
                searchRecyclerAdapter.notifyDataSetChanged();

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
