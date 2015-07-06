package com.example.littlebig.spotify_streamer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by littleBIG on 7/6/2015.
 */
public class TrackAdapter extends ArrayAdapter<TrackData>{

    private static final String LOG_TAG = TrackAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the List is the data we want
     * to populate into the lists
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param track_data   A List of AndroidFlavor objects to display in a list
     */
    public TrackAdapter(Activity context, List<TrackData> track_data) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, track_data);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The AdapterView position that is requesting a view
     * @param convertView The recycled view to populate.
     *                    (search online for "android view recycling" to learn more)
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gets the AndroidFlavor object from the ArrayAdapter at the appropriate position
        TrackData trackData = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.artist_list_item, parent, false);
        }

        ImageView iconView = (ImageView) convertView.findViewById(R.id.artist_img);
        iconView.setImageResource(trackData.album_image);

        TextView trackView = (TextView) convertView.findViewById(R.id.artist_txt);
        trackView.setText(trackData.track_name);

        TextView albumView = (TextView) convertView.findViewById(R.id.artist_txt);
        albumView.setText(trackData.album_name);

        return convertView;
    }
}

