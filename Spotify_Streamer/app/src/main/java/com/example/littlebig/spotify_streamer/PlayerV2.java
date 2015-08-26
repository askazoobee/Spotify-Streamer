package com.example.littlebig.spotify_streamer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PlayerV2 extends AppCompatActivity {

    boolean mIsLargeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_v2);

        mIsLargeLayout = getResources().getBoolean(R.bool.large_layout);

        if(savedInstanceState == null || !savedInstanceState.containsKey("seekLoc")) {

        }
        else {
            PlayerV2Fragment.seekLocation = savedInstanceState.getInt("seekLoc");
        }

      //  showDialog();

    }
/*

    public void showDialog() {
        FragmentManager fragmentManager = getFragmentManager();
        PlayerV2Fragment newFragment = new PlayerV2Fragment();

        if (mIsLargeLayout) {
            // The device is using a large layout, so show the fragment as a dialog
            newFragment.show(fragmentManager, "dialog");
        } else {
            // The device is smaller, so show the fragment fullscreen
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            // For a little polish, specify a transition animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // To make it fullscreen, use the 'content' root view as the container
            // for the fragment, which is always the root view for the activity
            transaction.add(android.R.id.content, newFragment)
                    .addToBackStack(null).commit();
        }
    }*/



    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("seekLoc", PlayerV2Fragment.seekLocation);
        super.onSaveInstanceState(outState);
    }


}
