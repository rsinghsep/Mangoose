package com.omdb.rohksin.Mangoose;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.omdb.rohksin.Mangoose.Adaters.ViewAllMoviesAdapter;
import com.omdb.rohksin.Mangoose.NewSearch.POJO.MovieRole;

import java.util.ArrayList;

/**
 * Created by Illuminati on 6/28/2017.
 */
public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setAnimation();
        setContentView(R.layout.list_layout);

        getSupportActionBar().setTitle("Roles");

        //setAnimation();

        ArrayList<MovieRole> movieRoles = (ArrayList<MovieRole>)getIntent().getSerializableExtra("allMoviesListActivity");

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(ListActivity.this);
        recyclerView.setLayoutManager(llm);

        ViewAllMoviesAdapter adapter = new ViewAllMoviesAdapter(movieRoles,ListActivity.this);
        recyclerView.setAdapter(adapter);


    }

    public void setAnimation()
    {

            if(Build.VERSION.SDK_INT>20) {
                Slide slide = new Slide();
                slide.setSlideEdge(Gravity.LEFT);
                slide.setDuration(400);
                slide.setInterpolator(new AccelerateDecelerateInterpolator());
                getWindow().setExitTransition(slide);
                getWindow().setEnterTransition(slide);
            }

    }
}
