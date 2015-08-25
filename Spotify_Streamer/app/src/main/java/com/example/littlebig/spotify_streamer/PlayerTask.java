package com.example.littlebig.spotify_streamer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class PlayerTask extends AsyncTask<String,Void,String> {

    private final String LOG_TAG = PlayerTask.class.getSimpleName();

    protected String doInBackground(String... params) {

        if (params.length == 0) {
            return null;
        } else {
            try {


                SpotifyApi api = new SpotifyApi();
                SpotifyService service = api.getService();

                TracksPager track_results = service.searchTracks(params[0]);

                //   if(track_results!=null) {
                List<Track> artists = track_results.tracks.items;
                String ID = artists.get(0).id;

                Track track = service.getTrack(ID);

                String url = track.preview_url;
                //String url = mapB.get("spotify").toString();

                /*Map mapB = track.external_urls;
                String url = mapB.get("spotify").toString();*/


                Log.i(LOG_TAG, "tk " + url);


  /*              ArtistsPager artist_results = service.searchArtists(params[0]);
                List<Artist> artists = artist_results.artists.items;

                Map mapB = new HashMap();

                mapB.equals(artists.get(0).external_urls);
                String url = mapB.get("spotify").toString();*/

/*
                if (artists.size() == 0) {
                    return null;
                } else {
*/
                  /*  try {

                        MediaPlayer mediaPlayer = new MediaPlayer();
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.setDataSource(url);
                        mediaPlayer.prepare();
                        mediaPlayer.start();

                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        Log.e(LOG_TAG, "Error ", e);
                        return null;
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        Log.e(LOG_TAG, "Error ", e);
                        return null;
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(LOG_TAG, "Error ", e);
                        return null;
                    }
                        return null;*/
                //   }//else{
                //      Log.i(LOG_TAG, "tk " + "no track found 400");
                //  return null;
                //  }
            return url;

            } catch (Exception e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the data, there's no point in attempting
                // to parse it.
                return null;
            }

        }

    }

    @Override
    protected void onPostExecute(String result) {
        if(result != null){
                PlayerV2Fragment.song_url = result;
        }else {
          //  Toast.makeText(context, "No top tracks found. Please pick another artist and try again.", Toast.LENGTH_LONG).show();
        }
    }



}



