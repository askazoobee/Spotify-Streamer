package com.example.littlebig.spotify_streamer;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;

/**
 * Created by littleBIG on 7/6/2015.
 */
public class FetchArtistTask extends AsyncTask<String,Void,ArtistData[]> {

    private final String LOG_TAG = FetchArtistTask.class.getSimpleName();



    protected ArtistData[] doInBackground(String... params) {

        if (params.length == 0) {
            return null;
        }else {
            try {


                SpotifyApi api = new SpotifyApi();
                SpotifyService service = api.getService();

                ArtistsPager results = service.searchArtists(params[0]);
                List<Artist> artists = results.artists.items;
                ArtistData[] data_artist = new ArtistData[artists.size()];


                for (int i = 0; i < artists.size(); i++) {

                    Artist artist = artists.get(i);

                    Log.i(LOG_TAG, i + " " + artist.name);

                    //for (int g = 0; g < artist.images.size(); g++) {
                    if (artist.images.size() != 0) {
                        ArtistData artist_data = new ArtistData(
                                artist.name,
                                artist.images.get(0).url
                        );
                        data_artist[i] = artist_data;
                    } else {
                        ArtistData artist_data = new ArtistData(
                                artist.name,
                                "http://www.sitindia.com/res/img/img-not-found.png"
                        );
                        data_artist[i] = artist_data;
                    }
                }
                //  }
                for (ArtistData s : data_artist) {
                    Log.v(LOG_TAG, "ARTIST entry: " + s);
                }

                return data_artist;

            } catch (Exception e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the data, there's no point in attempting
                // to parse it.
                return null;
            }
        }
    }

    @Override
    protected void onPostExecute(ArtistData[] result) {
        if(result != null){
                MainActivityFragment.artistAdapter.clear();
            for ( ArtistData artist : result){
                MainActivityFragment.artistAdapter.add(artist);
            }
        }
    }









}
