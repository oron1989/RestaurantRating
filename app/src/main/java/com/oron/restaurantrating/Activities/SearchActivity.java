package com.oron.restaurantrating.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

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

        EditText serchEditText = findViewById(R.id.searchEditText);
        serchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    private void filter(String  text) {
        ArrayList<Restaurant> filteredList = new ArrayList<>();

        for (Restaurant item : restaurantList) {
            if (item.getRestaurantName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        searchRecyclerAdapter.filterList(filteredList);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_account_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_back_button:
                startActivity(new Intent(SearchActivity.this, UserAccountActivity.class));
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
