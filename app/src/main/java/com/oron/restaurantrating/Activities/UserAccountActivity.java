package com.oron.restaurantrating.Activities;

import android.app.SearchManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oron.restaurantrating.R;

public class UserAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView userName;
    private ImageView userPic;
    private ImageButton archives;
    private ImageButton search;
    private ImageButton utilities;
    private ImageButton newForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

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

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.action_signout) {
//            myAuth.signOut();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_account_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
