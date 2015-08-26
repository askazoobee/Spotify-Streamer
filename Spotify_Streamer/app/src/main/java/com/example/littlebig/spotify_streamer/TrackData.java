package com.example.littlebig.spotify_streamer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by littleBIG on 7/6/2015.
 */
public class TrackData implements Parcelable{

    String track_name;
    String album_name;
    String album_image; //drawable ID
    String track_url;

    public TrackData(String track,String album, String image, String trackurl){
        this.track_name = track;
        this.album_name = album;
        this.album_image = image;
        this.track_url = trackurl;
        }

    private TrackData(Parcel in){
        track_name = in.readString();
        album_name = in.readString();
        album_image = in.readString();
        track_url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String toString() { return track_name; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(track_name);
        parcel.writeString(album_name);
        parcel.writeString(album_image);
        parcel.writeString(track_url);
    }

    public final Parcelable.Creator<TrackData> CREATOR = new Parcelable.Creator<TrackData>() {
        @Override
        public TrackData createFromParcel(Parcel parcel) {
            return new TrackData(parcel);
        }

        @Override
        public TrackData[] newArray(int i) {
            return new TrackData[i];
        }

    };
}






