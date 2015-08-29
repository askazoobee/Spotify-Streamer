package com.example.littlebig.spotify_streamer;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private Toast mAppToast;
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


        if(!mIsLargeLayout) {
    // The detail Activity called via intent.  Inspect the intent for artist data.
    Intent intent = getActivity().getIntent();
    if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
        artist_name_extra = intent.getStringExtra(Intent.EXTRA_TEXT);
    }

if(isNetworkAvailable()) {
    onSearch(artist_name_extra);
}else{
    if(mAppToast!=null){
        mAppToast.cancel();
    }
    mAppToast = Toast.makeText(getActivity(), "No network, Connect and try again.", Toast.LENGTH_LONG);
    mAppToast.show();
    //clear adapter. no point in displaying this content if there is no network
   DetailActivityFragment.trackAdapter.clear();
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
                    .putExtra(Intent.EXTRA_TEXT, pos);
            startActivity(intent);

        }
    });
    return rootView;
}
        else{

           Bundle arguments = getArguments();
          if (arguments != null) {
                   artist_name_extra = arguments.getString("ARTIST");
               }

        }

        onSearch(artist_name_extra);


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
                        .putExtra(Intent.EXTRA_TEXT, pos);
                startActivity(intent);

            }
        });




        return rootView;

    }



    public void onSearch(String name) {
        FetchTopTracksTask fetch_top_tracks = new FetchTopTracksTask(getActivity());
        fetch_top_tracks.execute(name);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("tracks", MainActivityFragment.artistList);
        super.onSaveInstanceState(outState);
    }

    //Based on a stackoverflow snippet
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



}
