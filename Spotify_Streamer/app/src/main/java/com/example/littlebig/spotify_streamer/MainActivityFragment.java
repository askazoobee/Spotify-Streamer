package com.example.littlebig.spotify_streamer;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static ArtistAdapter artistAdapter;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        artistAdapter = new ArtistAdapter(getActivity(), new ArrayList<ArtistData>());

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.list_artist);
        listView.setAdapter(artistAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView artist_name = (TextView) rootView.findViewById(R.id.artist_txt);
                Toast.makeText(getActivity(), "Track: ", Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(), "Track: " + artist_name.getText().toString(), Toast.LENGTH_LONG).show();
                // getActivity instead of ..adapterView.getContext()

                String artistname = artist_name.getText().toString();
                Intent intent = new Intent(getActivity(), DetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, artistname);
                startActivity(intent);

            }
        });







        return rootView;

    }
}
