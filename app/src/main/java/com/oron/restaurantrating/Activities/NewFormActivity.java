package com.oron.restaurantrating.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.oron.restaurantrating.R;

import java.util.HashMap;
import java.util.Map;

public class NewFormActivity extends AppCompatActivity {

    private EditText name;
    private EditText city;
    private Button createNewFormButton;
    private ProgressDialog myProgress;

    private DatabaseReference myInspction;
    private FirebaseUser myUser;
    private FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_form);

        myProgress = new ProgressDialog(this);

        myInspction = FirebaseDatabase.getInstance().getReference().child("Restaurants");
        myAuth = FirebaseAuth.getInstance();
        myUser = myAuth.getCurrentUser();

        name = findViewById(R.id.restaurantNameEditText);
        city = findViewById(R.id.cityEditText);
        createNewFormButton = findViewById(R.id.createNewFormButton);

        createNewFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewForm();
            }
        });

    }

    private void createNewForm() {
        myProgress.setMessage("Loading the File...");
        myProgress.show();

        String nameVal = name.getText().toString().trim();
        String cityVal = city.getText().toString().trim();

        if (!TextUtils.isEmpty(nameVal) && !TextUtils.isEmpty(cityVal)) {
            DatabaseReference newInspctio = myInspction.push();

            Map<String , String > dataToSave = new HashMap<>();
            dataToSave.put("name", nameVal);
            dataToSave.put("city", cityVal);
            dataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));
            dataToSave.put("userId", myUser.getUid());
            dataToSave.put("score", "null");
            dataToSave.put("grade", "null");

            newInspctio.setValue(dataToSave);

            myProgress.dismiss();

            startActivity(new Intent(NewFormActivity.this, FormActivity.class));
            finish();
        }
    }
}
