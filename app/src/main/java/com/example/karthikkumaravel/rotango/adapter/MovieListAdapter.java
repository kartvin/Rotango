package com.example.karthikkumaravel.rotango.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.example.karthikkumaravel.rotango.DataCache;
import com.example.karthikkumaravel.rotango.MyApplication;
import com.example.karthikkumaravel.rotango.R;
import com.example.karthikkumaravel.rotango.activities.MovieDetailsActivity;
import com.example.karthikkumaravel.rotango.model.MovieItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by karthikkumaravel on 8/3/14.
 */
public class MovieListAdapter extends BaseAdapter {
    public ArrayList<MovieItem> moviesArray = DataCache.getInstance().getMovieItemList().movieItems;

    @Override
    public int getCount() {
        return moviesArray.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.view_movie_list, null);

            viewHolder.movieImageView = (ImageView) convertView.findViewById(R.id.movie_image_view);

            viewHolder.movieTextView = (TextView) convertView.findViewById(R.id.movie_title_text_view);
            viewHolder.movieYearView = (TextView) convertView.findViewById(R.id.movie_year_text_view);
            viewHolder.movieRuntimeView = (TextView) convertView.findViewById(R.id.movie_runtime_text_view);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final MovieItem movieItem = moviesArray.get(position);
        Picasso.with(MyApplication.getContext()).load(movieItem.posters.original).into(viewHolder.movieImageView);
        viewHolder.movieTextView.setText(movieItem.title);
        String runtimeText = String.format("%d minutes", movieItem.runtime);
        viewHolder.movieRuntimeView.setText(runtimeText);
        viewHolder.movieYearView.setText(String.valueOf(movieItem.year));

        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent detailsIntent = new Intent(MyApplication.getContext(), MovieDetailsActivity.class);
                int idx = DataCache.getInstance().getMovieItemList().movieItems.indexOf(movieItem);
                detailsIntent.putExtra("movieItemObj", idx);
                detailsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(detailsIntent);

                Toast.makeText(MyApplication.getContext(), "Loading ...",
                        Toast.LENGTH_LONG).show();

                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public void filterArray(String text) {
        if (text.length()<= 0) {
            moviesArray = DataCache.getInstance().getMovieItemList().movieItems;
            notifyDataSetChanged();
            return;
        }

        ArrayList<MovieItem> arrayList = new ArrayList<MovieItem>(); //your arraylist, that you will filter
        for(MovieItem movieItem:moviesArray){
            if(movieItem.title.toLowerCase().contains(text.toLowerCase())){
                //add this item to your new list
                arrayList.add(movieItem);
            }
        }
        moviesArray = arrayList;

        notifyDataSetChanged();
    }

    class ViewHolder {
        ImageView movieImageView;

        TextView movieTextView;
        TextView movieYearView;
        TextView movieRuntimeView;
    }

}
