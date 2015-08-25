package com.example.littlebig.spotify_streamer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;

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
    boolean mIsLargeLayout;

    public static TrackAdapter trackAdapter;
public static ArrayList<TrackData> trackList = new ArrayList<TrackData>();

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mIsLargeLayout = getResources().getBoolean(R.bool.large_layout);
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
                String pos = String.valueOf(i);
                // getActivity instead of ..adapterView.getContext()
                Toast.makeText(getActivity(), "Song: " + trackname, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(), PlayerV2.class)
                        .putExtra(Intent.EXTRA_TEXT,pos);
                startActivity(intent);

            }
        });



        return rootView;
    }






}
