package com.example.littlebig.spotify_streamer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class DetailActivity extends ActionBarActivity {
   // private String myArtist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(savedInstanceState == null || !savedInstanceState.containsKey("tracks")) {

            onSearch(DetailActivityFragment.artist_name_extra);

        }
        else {
            DetailActivityFragment.trackList = savedInstanceState.getParcelableArrayList("tracks");
        }

    }

/*    public void onStart() {
        super.onStart();
    }*/

    public void onSearch(String name) {
        FetchTopTracksTask fetch_top_tracks = new FetchTopTracksTask(getApplicationContext());
        fetch_top_tracks.execute(name);

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("tracks", MainActivityFragment.artistList);
        super.onSaveInstanceState(outState);
    }



}
