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
import com.oron.restaurantrating.Data.ArchivesRecyclerAdapter;
import com.oron.restaurantrating.Model.Restaurant;
import com.oron.restaurantrating.R;

import java.util.ArrayList;
import java.util.List;

public class ArchivesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArchivesRecyclerAdapter archivesRecyclerAdapter;
    private List<Restaurant> restaurantList;

    private DatabaseReference myDatabaseReference;
    private FirebaseDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archives);

        myDatabase = FirebaseDatabase.getInstance();
        myDatabaseReference = myDatabase.getReference().child("Restaurants");
        myDatabaseReference.keepSynced(true);

        restaurantList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycleViewArchives);
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

                archivesRecyclerAdapter = new ArchivesRecyclerAdapter(ArchivesActivity.this, restaurantList);
                recyclerView.setAdapter(archivesRecyclerAdapter);
                archivesRecyclerAdapter.notifyDataSetChanged();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_account_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_back_button:
                startActivity(new Intent(ArchivesActivity.this, UserAccountActivity.class));
                finish();
                break;
//            case R.id.action_sign_out:
//                if (myAuth != null && myUser != null) {
//                    myAuth.signOut();
//                    startActivity(new Intent(NewFormActivity.this, MainActivity.class));
//                    finish();
//                }
//                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
