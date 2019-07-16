package com.oron.restaurantrating.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oron.restaurantrating.Model.User;
import com.oron.restaurantrating.R;

public class UserAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView userName;
    private ImageView userPic;
    private ImageButton archives;
    private ImageButton search;
    private ImageButton utilities;
    private ImageButton newForm;

    private DatabaseReference myDatabaseReference;
    private FirebaseDatabase myDatabase;
    private FirebaseUser myUser;
    private FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        myAuth = FirebaseAuth.getInstance();
        myUser = myAuth.getCurrentUser();

        myDatabase = FirebaseDatabase.getInstance();
        myDatabaseReference = myDatabase.getReference().child("MUsers");
        myDatabaseReference.keepSynced(true);

        userName = findViewById(R.id.userAccountNameTextView);
        userPic = findViewById(R.id.userAccountProfilePicImageView);
        archives = findViewById(R.id.userAccountArchivesImageButton);
        search = findViewById(R.id.userAccountSearchImageButton);
        utilities = findViewById(R.id.userAccountUtilitiesImageButton);
        newForm = findViewById(R.id.userAccountNewFileImageButton);

        archives.setOnClickListener(this);
        search.setOnClickListener(this);
        utilities.setOnClickListener(this);
        newForm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(UserAccountActivity.this, "click", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_account_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
//            case R.id.action_add:
//                if (mAuth != null && mUser != null) {
//                    startActivity(new Intent(PostListActivity.this, AddPostActivity.class));
//                    finish();
//                }
//                break;
            case R.id.action_sign_out:
                if (myAuth != null && myUser != null) {
                    myAuth.signOut();
                    startActivity(new Intent(UserAccountActivity.this, MainActivity.class));
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
// Read from the database
        myDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User user = dataSnapshot.getValue(User.class);

                Log.d("1", "Value is: " + user.getFirstName());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("2", "Failed to read value.", error.toException());
            }
        });

    }
}
