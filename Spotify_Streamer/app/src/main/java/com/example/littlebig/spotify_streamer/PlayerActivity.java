package com.example.littlebig.spotify_streamer;

import android.app.DialogFragment;
import android.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class PlayerActivity extends ActionBarActivity {

    boolean mIsLargeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_player);
        mIsLargeLayout = getResources().getBoolean(R.bool.large_layout);

/*        ImageButton playButton = (ImageButton)findViewById(R.id.play_button);
        //String message = "Launching the " + ((Button) v).setBackground();
        playButton.setImageResource(android.R.drawable.ic_media_pause);*/


       showDialog();

    }



    public void showDialog() {
        FragmentManager fragmentManager = getFragmentManager();
        PlayerDialogFragment newFragment = new PlayerDialogFragment();

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
    }




}
