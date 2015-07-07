package com.example.littlebig.spotify_streamer;

/**
 * Created by littleBIG on 7/6/2015.
 */
public class TrackData {

    String track_name;
    String album_name;
    String album_image; //drawable ID

    public TrackData(String track,String album, String image){
        this.track_name = track;
        this.album_name = album;
        this.album_image = image;
        }
    }

