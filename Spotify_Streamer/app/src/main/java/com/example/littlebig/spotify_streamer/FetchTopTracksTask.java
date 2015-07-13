package com.example.littlebig.spotify_streamer;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;
import kaaes.spotify.webapi.android.models.TracksPager;

/**
 * Created by littleBIG on 7/7/2015.
 */
public class FetchTopTracksTask extends AsyncTask<String,Void,TrackData[]>{

    private final String LOG_TAG = FetchArtistTask.class.getSimpleName();

    private Context context;
    //context from DetailActivity for display of toast in asynctask
    public FetchTopTracksTask(Context context) {
        this.context = context;
    }

    protected TrackData[] doInBackground(String... params) {

        if (params.length == 0) {
            return null;
        }else {
            try {


                SpotifyApi api = new SpotifyApi();
                SpotifyService service = api.getService();

                ArtistsPager artist_results = service.searchArtists(params[0]);
                List<Artist> artists = artist_results.artists.items;
                String ID = artists.get(0).id;
                Map mapA = new HashMap();
                mapA.put("country","US");

                Tracks results = service.getArtistTopTrack(ID, mapA);



                List<Track> tracks= results.tracks.subList(0, results.tracks.size());

                if(tracks.size() == 0){
                    return null;
                }else {

                    TrackData[] data_track = new TrackData[tracks.size()];

                    for (int i = 0; i < tracks.size(); i++) {

                        Track track = tracks.get(i);
                        Log.i(LOG_TAG, i + " " + track.name);

                        if (track.album.images.size() != 0) {
                            TrackData track_data = new TrackData(
                                    track.name,
                                    track.album.name,
                                    track.album.images.get(0).url
                            );
                            data_track[i] = track_data;
                        } else {
                            TrackData track_data = new TrackData(
                                    track.name,
                                    track.album.name,
                                    "http://www.sitindia.com/res/img/img-not-found.png"
                            );
                            data_track[i] = track_data;
                        }
                    }
                    for (TrackData s : data_track) {
                        Log.v(LOG_TAG, "ARTIST entry: " + s);
                    }

                    return data_track;
                }

            } catch (Exception e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the data, there's no point in attempting
                // to parse it.
                return null;
            }
        }
    }

    @Override
    protected void onPostExecute(TrackData[] result) {
        if(result != null){
            DetailActivityFragment.trackAdapter.clear();
            for ( TrackData tracks : result){
                DetailActivityFragment.trackAdapter.add(tracks);
            }
        }else {
            Toast.makeText(context, "No top tracks found. Please pick another artist and try again.", Toast.LENGTH_LONG).show();
        }
    }




}
