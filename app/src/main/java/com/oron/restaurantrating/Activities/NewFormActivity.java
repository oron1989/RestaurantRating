package com.oron.restaurantrating.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.oron.restaurantrating.R;

public class NewFormActivity extends AppCompatActivity {

    private EditText name;
    private EditText city;
    private Button createNewFormButton;
    private ProgressDialog myProgress;

    private DatabaseReference mPostDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_form);

        myProgress = new ProgressDialog(this);

        mPostDatabase = FirebaseDatabase.getInstance().getReference().child("Restaurants");

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

        }
    }
}
