package com.example.littlebig.spotify_streamer;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerV2Fragment extends DialogFragment {


    final MediaPlayer mediaPlayer = new MediaPlayer();
    // boolean isPlaying = true;
    private final String LOG_TAG = PlayerV2Fragment.class.getSimpleName();
    public static String song_url;
    public static int pos_extra;
    private View rootview;
    ImageButton playButton;
    ImageButton prevButton;
    ImageButton fwrdButton;
    ImageView albumImage;
    TextView artistText;
    TextView albumText;
    TextView trackText;

    PlayerTask fetch_song = new PlayerTask();
    String song = DetailActivityFragment.trackList.get(pos_extra).track_name;

    public PlayerV2Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.fragment_player_v2, container, false);

        playButton = (ImageButton) rootview.findViewById(R.id.play_button);
        prevButton = (ImageButton) rootview.findViewById(R.id.back_button);
        fwrdButton = (ImageButton) rootview.findViewById(R.id.next_button);

        albumImage = (ImageView) rootview.findViewById(R.id.album_play_image);
        albumText = (TextView) rootview.findViewById(R.id.album_name);
        artistText = (TextView) rootview.findViewById(R.id.artist_name);
        trackText = (TextView) rootview.findViewById((R.id.song_name));

        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            pos_extra = Integer.parseInt(getActivity().getIntent().getStringExtra(Intent.EXTRA_TEXT));
        }
        //  fetch_song.execute(song);
        SetUp();

        prepareSong();
        // Inflate the layout to use as dialog or embedded fragment
        return rootview;

    }

    /**
     * The system calls this only when creating the layout in a dialog.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        SetUp();

        return dialog;
    }

    public void SetUp() {


        albumText.setText(DetailActivityFragment.trackList.get(pos_extra).album_name);
        artistText.setText(DetailActivityFragment.artist_name_extra);
        trackText.setText(DetailActivityFragment.trackList.get(pos_extra).track_name);
        Picasso.with(rootview.getContext()).load(DetailActivityFragment.trackList.get(pos_extra).album_image).into(albumImage);


        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pos_extra = pos_extra - 1;
                if (pos_extra < 0) {
                    pos_extra = 0;
                }
                SetUp();
                playButton.setImageResource(android.R.drawable.ic_media_pause);
                //  if (mediaPlayer.!= null) {
                mediaPlayer.reset();
                //  }
                prepareSong();

            }
        });


        fwrdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pos_extra = pos_extra + 1;
                if (pos_extra > 10) {
                    pos_extra = 9;
                }
                SetUp();
                playButton.setImageResource(android.R.drawable.ic_media_pause);
                //  if (mediaPlayer.!= null) {
                mediaPlayer.reset();
                //  }
                prepareSong();
            }
        });

        // playButton.setImageResource(android.R.drawable.ic_media_pause);

/*        PlayerTask fetch_song = new PlayerTask();
        String song = DetailActivityFragment.trackList.get(pos_extra).track_name;

        fetch_song.execute(song);*/

    }

    public void prepareSong() {


        try {

            //  MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            Log.i(LOG_TAG, "tk " + song_url);
            mediaPlayer.setDataSource(DetailActivityFragment.trackList.get(pos_extra).track_url);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Error ", e);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Error ", e);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Error ", e);
        }

            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!mediaPlayer.isPlaying()) {
                        mediaPlayer.start();
                        playButton.setImageResource(android.R.drawable.ic_media_pause);
                        // isPlaying = true;
                    } else {
                        mediaPlayer.pause();
                        // isPlaying = false;
                        playButton.setImageResource(android.R.drawable.ic_media_play);
                    }
                }
            });


        }


    }

