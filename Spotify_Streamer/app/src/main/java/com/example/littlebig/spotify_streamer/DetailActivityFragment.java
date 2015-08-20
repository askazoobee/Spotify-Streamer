package com.example.littlebig.spotify_streamer;

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

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public static String artist_name_extra;

    public static TrackAdapter trackAdapter;
public static ArrayList<TrackData> trackList = new ArrayList<TrackData>();

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        // The detail Activity called via intent.  Inspect the intent for artist data.
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            artist_name_extra = intent.getStringExtra(Intent.EXTRA_TEXT);
        }


        trackAdapter = new TrackAdapter(getActivity(), trackList);

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.listview_topten);
        listView.setAdapter(trackAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String trackname = adapterView.getItemAtPosition(i).toString();
                // getActivity instead of ..adapterView.getContext()
                Toast.makeText(getActivity(), "Artist: " + trackname, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), PlayerActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, trackname);
                startActivity(intent);

            }
        });



        return rootView;
    }





}
