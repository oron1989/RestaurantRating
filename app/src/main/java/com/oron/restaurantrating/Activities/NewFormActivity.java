package com.oron.restaurantrating.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oron.restaurantrating.Model.QuestionView;
import com.oron.restaurantrating.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public class NewFormActivity extends AppCompatActivity {

//    private EditText nameET;
//    private EditText cityET;
    private Spinner restaurantSpinner;
    private Spinner citySpinner;
    private Button createNewFormButton;
    private ProgressDialog myProgress;

    private String city;
    private String restaurant;
    private List<QuestionView> questionViewList;

    //Statement of variables Firebase
    private DatabaseReference myInspectorn;
    private FirebaseUser myUser;
    private FirebaseAuth myAuth;

    private DatabaseReference myDatabaseReference;
    private FirebaseDatabase myDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_form);

        myProgress = new ProgressDialog(this);

        myInspectorn = FirebaseDatabase.getInstance().getReference().child("Restaurants");
        myAuth = FirebaseAuth.getInstance();
        myUser = myAuth.getCurrentUser();

        myDatabase = FirebaseDatabase.getInstance();
        myDatabaseReference = myDatabase.getReference().child("Question");
        myDatabaseReference.keepSynced(true);
        questionViewList = new ArrayList<QuestionView>();

//        nameET = findViewById(R.id.restaurantNameEditText);
//        cityET = findViewById(R.id.cityEditText);
        restaurantSpinner = findViewById(R.id.restaurantListNewFormSpinner);
        citySpinner = findViewById(R.id.CityListNewFormSpinner);
        createNewFormButton = findViewById(R.id.createNewFormButton);

        ArrayAdapter<CharSequence> adapterR = ArrayAdapter.createFromResource(this, R.array.Restaurnt, android.R.layout.simple_spinner_item);
        adapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        restaurantSpinner.setAdapter(adapterR);
        restaurantSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                restaurant = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapterC = ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_item);
        adapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapterC);
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

//        String nameVal = nameET.getText().toString().trim();
//        String cityVal = cityET.getText().toString().trim();

//        if (!TextUtils.isEmpty(nameVal)) {
            DatabaseReference newInspector = myInspectorn.push();
            final String restaurantsUid = newInspector.getKey();

            Map<String , String > dataToSave = new HashMap<>();
            dataToSave.put("restaurantName", restaurant);
            dataToSave.put("city", city);
            dataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));
            dataToSave.put("userId", myUser.getUid());
            dataToSave.put("score", "null");
            dataToSave.put("grade", "null");

            newInspector.setValue(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(NewFormActivity.this, "save", Toast.LENGTH_SHORT).show();
                    myProgress.dismiss();
                    Intent intent = new Intent(NewFormActivity.this, FormFillActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("myList", (ArrayList<? extends Parcelable>) questionViewList);
                    intent.putExtras(bundle);
                    intent.putExtra("restaurantsUid", restaurantsUid);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(NewFormActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, e.toString());
                    myProgress.dismiss();
                }
            });
        }
//    }

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
                    startActivity(new Intent(NewFormActivity.this, MainActivity.class));
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        myDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                QuestionView q = dataSnapshot.getValue(QuestionView.class);
                questionViewList.add(q);
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
