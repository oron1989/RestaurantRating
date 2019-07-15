package com.oron.restaurantrating.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.oron.restaurantrating.R;

public class MainActivity extends AppCompatActivity implements
        CreateAccountFragment.OnFragmentInteractionListener,
        LoginFragment.OnFragmentInteractionListener {

    FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager(); //creates the leader of the fragments

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
//        ((Activity) getActivity()).overridePendingTransition(0, 0);

    }


}
