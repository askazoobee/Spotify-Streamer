package com.example.littlebig.spotify_streamer;

import android.content.Intent;
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


public class MainActivity extends ActionBarActivity implements MainActivityFragment.Callbacks{
    private String search;
    boolean mIsLargeLayout;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIsLargeLayout = getResources().getBoolean(R.bool.large_layout);

            if (savedInstanceState == null || !savedInstanceState.containsKey("artists")) {

                final EditText search_artist = (EditText) findViewById(R.id.edit_artist);
                search_artist.setOnKeyListener(new View.OnKeyListener() {
                    public boolean onKey(View view, int keyCode, KeyEvent keyevent) {


                        //If the keyevent is a key-down event on the "enter" button
                        if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                            //...
                            search = search_artist.getText().toString();
                            onSearch(search);
                            // ...
                            return true;
                        }
                        return false;
                    }
                });
            } else {

                MainActivityFragment.artistList = savedInstanceState.getParcelableArrayList("artists");
            }
        }




    public void onStart() {
        super.onStart();
    }

    public void onSearch(String name) {
        FetchArtistTask fetch_artist = new FetchArtistTask(getApplicationContext());
        fetch_artist.execute(name);
    }




    @Override
    public void onItemSelected(String artist) {
        if (mIsLargeLayout) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString("ARTIST",artist);
          //  savedInstanceState.getParcelableArrayList("tracks");
            DetailActivityFragment fragment = new DetailActivityFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.artist_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            // getActivity instead of ..adapterView.getContext()
            Toast.makeText(getApplicationContext(), "Artist: " + artist, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class)
                    .putExtra(Intent.EXTRA_TEXT, artist);
            startActivity(intent);
        }
    }




/*    public void searchArtist(View v) {
        EditText search = (EditText) findViewById(R.id.edit_artist);
        String search_artist = search.getText().toString();
        updateArtist(search_artist);
    }*/


}

