package com.omdb.rohksin.omdb.Adaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.omdb.rohksin.omdb.NewSearch.Utility.MovieUtils;
import com.omdb.rohksin.omdb.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Illuminati on 4/22/2017.
 */
public class ListAdaper extends RecyclerView.Adapter<ListAdaper.MediaViewHolder> {


    List<String> list;
    Context context;

    public ListAdaper(List<String> list, Context context) {
        this.list = list;
        this.context = context;

    }


    @Override
    public MediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.landing_image, parent, false);
        MediaViewHolder mediaHolder = new MediaViewHolder(view);
        return mediaHolder;
    }

    @Override
    public void onBindViewHolder(MediaViewHolder holder, int position) {

        String thumb = list.get(position);
        Log.d("ImagePICASSO",thumb);
        Picasso.with(context)
                .load(MovieUtils.imageURL(thumb))
                .into(holder.mediaImage);
        //holder.mediaImage.setImageResource(R.drawable.actor2);
    }

    @Override
    public int getItemCount() {
        //return list.size();
        return list.size();
    }

    public class MediaViewHolder extends RecyclerView.ViewHolder{

        private ImageView mediaImage;

        public MediaViewHolder(View itemView) {
            super(itemView);
            //mediaImage = (ImageView)itemView.findViewById(R.id.MediaImage);
            mediaImage = (ImageView)itemView.findViewById(R.id.landing_image);
        }
    }
}
