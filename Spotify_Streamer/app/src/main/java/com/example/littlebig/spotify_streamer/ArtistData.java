package com.example.littlebig.spotify_streamer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by littleBIG on 7/6/2015.
 */
public class ArtistData implements Parcelable{
    String artist_name;
    String artist_image;

    public ArtistData(String artist, String image){
        this.artist_name = artist;
        this.artist_image = image;
    }


    private ArtistData(Parcel in){
        artist_name = in.readString();
        artist_image = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

  //  public String toString() { return artist_name + "--" + artist_image; }
    public String toString() { return artist_name; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(artist_name);
        parcel.writeString(artist_image);
    }

    public static final Parcelable.Creator<ArtistData> CREATOR = new Parcelable.Creator<ArtistData>() {
        @Override
        public ArtistData createFromParcel(Parcel parcel) {
            return new ArtistData(parcel);
        }

        @Override
        public ArtistData[] newArray(int i) {
            return new ArtistData[i];
        }

    };
}




