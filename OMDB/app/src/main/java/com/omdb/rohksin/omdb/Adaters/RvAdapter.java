package com.omdb.rohksin.omdb.Adaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omdb.rohksin.omdb.Movie;
import com.omdb.rohksin.omdb.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Illuminati on 5/23/2017.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MovieViewHolder> {




    private List<Movie> list;
    private Context context;

    public RvAdapter(List<Movie> list,Context context)
    {
        this.list = list;
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);
        MovieViewHolder pvh = new MovieViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
       // holder.personName.setText(list.get(position).getName());
        holder.title.setText(list.get(position).getName());
        holder.releaseYear.setText(list.get(position).getReleaseYear());


        String thumb = list.get(position).getPosterThumbnail();
        thumb = "http://image.tmdb.org/t/p/w185"+thumb;
        Log.d("Thumb",thumb);
        Picasso.with(context)
                .load(thumb)
                .into(holder.posterThumbnail);
       // holder.posterThumbnail.setImageResource();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        ImageView posterThumbnail;
        TextView title;
        TextView releaseYear;

        public MovieViewHolder(View itemView) {
            super(itemView);
            posterThumbnail = (ImageView)itemView.findViewById(R.id.posterThumbnail);
            title = (TextView)itemView.findViewById(R.id.title);
            releaseYear = (TextView)itemView.findViewById(R.id.release_date);
        }
    }
}