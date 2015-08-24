package com.example.littlebig.spotify_streamer;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;

/**
 * Created by littleBIG on 8/20/2015.
 */
public class PlayerDialogFragment extends DialogFragment {
    public static String pos_extra;

    ImageButton playButton;
    /** The system calls this to get the DialogFragment's layout, regardless
     of whether it's being displayed as a dialog or an embedded fragment. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.activity_player, container, false);
        playButton = (ImageButton)rootview.findViewById(R.id.play_button);




        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            pos_extra = intent.getStringExtra(Intent.EXTRA_TEXT);
        }



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
        return dialog;
    }



    public void onPlayandPause(View v){
        //String message = "Launching the " + ((Button) v).setBackground();
        playButton.setImageResource(android.R.drawable.ic_media_pause);


        PlayerTask fetch_song = new PlayerTask();
       // DetailActivityFragment.trackList.get(PlayerDialogFragment.pos_extra)

        fetch_song.execute();
    }


    public void onBack(View v){

        //String message = "Launching the " + ((Button) v).setBackground(R.drawable.);
    }

    public void onNext(View v){
        //String message = "Launching the " + ((Button) v).setBackground(R.drawable.);
    }




}
