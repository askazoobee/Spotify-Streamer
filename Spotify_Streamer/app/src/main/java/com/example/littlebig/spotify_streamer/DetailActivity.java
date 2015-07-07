package com.example.littlebig.spotify_streamer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    public void onStart() {
        super.onStart();
        onSearch(DetailActivityFragment.artist_name_extra);
    }

    public void onSearch(String name) {
        FetchTopTracksTask fetch_top_tracks = new FetchTopTracksTask();
        fetch_top_tracks.execute(name);

    }
}
