package com.oron.restaurantrating.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.oron.restaurantrating.R;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity implements
        CreateAccountFragment.OnFragmentInteractionListener,
        LoginFragment.OnFragmentInteractionListener {

    private FirebaseAuth myAuth;
    private FirebaseAuth.AuthStateListener myAuthListener;
    private FirebaseUser myUser;

    FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager(); //creates the leader of the fragments

        myAuth = FirebaseAuth.getInstance();

        if (fragment == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragment = new LoginFragment();
            fragmentTransaction.add(R.id.box_fragment, fragment).commit();
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void getCreateAccountFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction(); // starts transaction
        fragment = new CreateAccountFragment(); //create new one
        fragmentTransaction.replace(R.id.box_fragment,fragment).commit(); //replace
    }

    public void getUserAccountActivity() {
        Toast.makeText(MainActivity.this, "Signed in", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, UserAccountActivity.class);
        startActivity(intent);
        finish();
    }

    public void getLogout() {
        Toast.makeText(MainActivity.this, "Not Signed In", Toast.LENGTH_LONG).show();
    }

    public void getLoginError() {
        Toast.makeText(MainActivity.this, "One of the fields is incorrect", Toast.LENGTH_LONG).show();
    }

    public void getNotFull() {
        Toast.makeText(MainActivity.this, "One or more fields are incomplete", Toast.LENGTH_LONG).show();
    }

}
