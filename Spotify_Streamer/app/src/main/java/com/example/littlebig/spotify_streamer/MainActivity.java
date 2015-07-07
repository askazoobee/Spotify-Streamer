package com.example.littlebig.spotify_streamer;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText search_artist = (EditText) findViewById(R.id.edit_artist);
        search_artist.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //...
                    String search = search_artist.getText().toString();
                    onSearch(search);
                    // ...
                    return true;
                }
                return false;
            }
        });
    }

    public void onStart() {
        super.onStart();
    }

    public void onSearch(String name) {
        FetchArtistTask fetch_artist = new FetchArtistTask();
        fetch_artist.execute(name);

/*    public void searchArtist(View v) {
        EditText search = (EditText) findViewById(R.id.edit_artist);
        String search_artist = search.getText().toString();
        updateArtist(search_artist);
    }*/

    }
}

