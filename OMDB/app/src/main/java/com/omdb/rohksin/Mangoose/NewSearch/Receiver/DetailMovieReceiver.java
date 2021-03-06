package com.omdb.rohksin.Mangoose.NewSearch.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.omdb.rohksin.Mangoose.BlankActivity;
import com.omdb.rohksin.Mangoose.NewSearch.POJO.DetailMovie;
import com.omdb.rohksin.Mangoose.NewSearch.Utility.MovieUtils;
import com.omdb.rohksin.Mangoose.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Illuminati on 6/17/2017.
 */
public class DetailMovieReceiver extends BroadcastReceiver {

    private View view;
    private DetailMovie movie;

    public DetailMovieReceiver(View v)
    {
        this.view = view;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equalsIgnoreCase(BlankActivity.OBJECTMAPPED))
        {
            movie = (DetailMovie)intent.getSerializableExtra("BlankActivityMovie");

            Log.d("BR", "2 view" + (view == null) +movie.getTitle());
            CollapsingToolbarLayout layout = (CollapsingToolbarLayout)view.findViewById(R.id.title);
            layout.setTitle(movie.getTitle());

            ImageView imageView = (ImageView)layout.findViewById(R.id.moviePoster);


            String imgSrc = MovieUtils.imageURL(movie.getBackDropImage());

            TextView rating =(TextView)layout.findViewById(R.id.rating);
            Log.d("hghgh",movie.getVoteAgerage());
            rating.setText(movie.getVoteAgerage());

            Picasso.with(context)
                    .load(imgSrc)
                    .into(imageView);



        }
    }
}
