package com.example.karthikkumaravel.rotango.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karthikkumaravel.rotango.MyApplication;
import com.example.karthikkumaravel.rotango.R;
import com.example.karthikkumaravel.rotango.model.MovieItem;
import com.squareup.picasso.Picasso;
import java.security.PublicKey;

/**
 * Created by karthikkumaravel on 8/9/14.
 */
public class MovieDetailsAdapter extends BaseAdapter {
    public MovieItem movieItem;

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.view_movie_details, null);

            viewHolder.movieImageView = (ImageView) convertView.findViewById(R.id.movie_image_view);

            viewHolder.movieTextView = (TextView) convertView.findViewById(R.id.movie_details_text_view);
            viewHolder.movieYearView = (TextView) convertView.findViewById(R.id.movie_details_year_text_view);
            viewHolder.movieRuntimeTextView = (TextView) convertView.findViewById(R.id.movie_details_runtime_text_view);
            viewHolder.movieDescriptionTextView = (TextView) convertView.findViewById(R.id.movie_details_description_text_view);
            viewHolder.movieRatingTextView = (TextView) convertView.findViewById(R.id.movie_details_rating_text_view);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(MyApplication.getContext()).load(movieItem.posters.original).into(viewHolder.movieImageView);
        viewHolder.movieTextView.setText(movieItem.title);
        String runtimeText = String.format("%d minutes", movieItem.runtime);
        viewHolder.movieRuntimeTextView.setText(runtimeText);
        viewHolder.movieYearView.setText(String.valueOf(movieItem.year));
        viewHolder.movieDescriptionTextView.setText(movieItem.synopsis);

        String ratingText = "Rated "+ movieItem.mpaa_rating;

        viewHolder.movieRatingTextView.setText(ratingText);

        return convertView;
    }

    public void setMovieItem(MovieItem _movieItem) {

        this.movieItem = _movieItem;

    }

    class ViewHolder {
        ImageView movieImageView;

        TextView movieTextView;
        TextView movieYearView;
        TextView movieRuntimeTextView;
        TextView movieDescriptionTextView;
        TextView movieRatingTextView;

    }
}
