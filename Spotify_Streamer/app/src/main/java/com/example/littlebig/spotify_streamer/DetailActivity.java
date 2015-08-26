package com.example.littlebig.spotify_streamer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


public class DetailActivity extends ActionBarActivity {
   // private String myArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(savedInstanceState == null || !savedInstanceState.containsKey("tracks")) {

        }
        else {
            DetailActivityFragment.trackList = savedInstanceState.getParcelableArrayList("tracks");
        }

    }

/*    public void onStart() {
        super.onStart();
    }*/



}
