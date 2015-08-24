package com.example.littlebig.spotify_streamer;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerV2Fragment extends DialogFragment {

    public static int pos_extra;
    private View rootview;
    ImageButton playButton;
    ImageButton prevButton;
    ImageButton fwrdButton;
    ImageView albumImage;
    TextView artistText;
    TextView albumText;
    TextView trackText;


    public PlayerV2Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.fragment_player_v2, container, false);

        playButton = (ImageButton)rootview.findViewById(R.id.play_button);
        prevButton = (ImageButton)rootview.findViewById(R.id.back_button);
        fwrdButton = (ImageButton)rootview.findViewById(R.id.next_button);

        albumImage = (ImageView)rootview.findViewById(R.id.album_play_image);
        albumText = (TextView)rootview.findViewById(R.id.album_name);
        artistText = (TextView)rootview.findViewById(R.id.artist_name);
        trackText = (TextView)rootview.findViewById((R.id.song_name));

        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            pos_extra = Integer.parseInt(getActivity().getIntent().getStringExtra(Intent.EXTRA_TEXT));
        }

        SetUp();
        // Inflate the layout to use as dialog or embedded fragment
        return rootview;

    }

    /** The system calls this only when creating the layout in a dialog. */
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

    public void SetUp(){



      albumText.setText(DetailActivityFragment.trackList.get(pos_extra).album_name);
        artistText.setText(DetailActivityFragment.artist_name_extra);
        trackText.setText(DetailActivityFragment.trackList.get(pos_extra).track_name);
        Picasso.with(rootview.getContext()).load(DetailActivityFragment.trackList.get(pos_extra).album_image).into(albumImage);



        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


        PlayerTask fetch_song = new PlayerTask();
        String song = DetailActivityFragment.trackList.get(pos_extra).track_name;

        fetch_song.execute(song);

/*                if (!isPlaying) {
                    freePlayer.start();
                    playButton.setImageResource(R.drawable.ic_pause);
                    isPlaying = true;
                } else {
                    freePlayer.pause();
                    isPlaying = false;
                    playButton.setImageResource(R.drawable.ic_play);
                }*/
            }
        });


        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        fwrdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





       // playButton.setImageResource(android.R.drawable.ic_media_pause);

/*        PlayerTask fetch_song = new PlayerTask();
        String song = DetailActivityFragment.trackList.get(pos_extra).track_name;

        fetch_song.execute(song);*/






    }






}
