package com.omdb.rohksin.omdb;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.omdb.rohksin.omdb.ObjectMaps.MovieResponse;
import com.squareup.picasso.Picasso;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


/**
 * Created by Illuminati on 5/8/2017.
 */
public class MovieActivity extends AppCompatActivity{

    private ImageView moviePoster;
    private CollapsingToolbarLayout title;
    private TextView moviePlot;

    private LinearLayout non_actors;
    private TextView director;
    private TextView writer;
    private TextView production;

    private LinearLayout movie_specs;
    private TextView genre;
    private TextView releaseDate;
    private TextView runtime;

    private LinearLayout records;
    private TextView awards;
    private TextView boxOffice;
    private TextView website;


    private LinearLayout rating_pane;
    private LinearLayout rating_tab;

    private LinearLayout movie_cast;

    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.movie_cord_layout);

        moviePoster = (ImageView)findViewById(R.id.moviePoster);
        title =(CollapsingToolbarLayout)findViewById(R.id.title);
        moviePlot =(TextView)findViewById(R.id.movie_plot);

        non_actors =(LinearLayout)findViewById(R.id.brains);
        director =(TextView)non_actors.findViewById(R.id.director);
        writer =(TextView)non_actors.findViewById(R.id.writer);
        production =(TextView)non_actors.findViewById(R.id.production);

        movie_specs = (LinearLayout)findViewById(R.id.movie_specs);
        genre =(TextView)movie_specs.findViewById(R.id.genre);
        releaseDate =(TextView)movie_specs.findViewById(R.id.release_date);
        runtime =(TextView)movie_specs.findViewById(R.id.runtime);

        records = (LinearLayout)findViewById(R.id.records);
        awards =(TextView)records.findViewById(R.id.awards);
        boxOffice =(TextView)records.findViewById(R.id.boxoffice);
        website =(TextView)records.findViewById(R.id.website);

        rating_pane = (LinearLayout)findViewById(R.id.rating_pane);

        movie_cast = (LinearLayout)findViewById(R.id.movie_cast);
        //rating_tab = (LinearLayout)findViewById(R.id.rating_tab);


         inflater = (LayoutInflater) MovieActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        /*LinearLayout parent = (LinearLayout)findViewById(R.id.rating_pane);
        //parent.setGravity(Gravity.);
        parent.setOrientation(LinearLayout.HORIZONTAL);
        rating_tab = (LinearLayout)inflater.inflate(R.layout.rating_tab,null);

         rating_tab.setGravity(Gravity.RIGHT);
         ImageView rating_com_icon = (ImageView)rating_tab.findViewById(R.id.rating_com_icon);
         TextView ratingScore = (TextView)rating_tab.findViewById(R.id.rating_score);
         rating_com_icon.setImageResource(R.drawable.rottentomatoes);
         ratingScore.setText("8.6");

        LinearLayout rating_tab1 = (LinearLayout)inflater.inflate(R.layout.rating_tab,null);
        rating_tab1.setGravity(Gravity.LEFT);


        ImageView rating_com_icon1 = (ImageView)rating_tab1.findViewById(R.id.rating_com_icon);
        TextView ratingScore1 = (TextView)rating_tab.findViewById(R.id.rating_score);
        rating_com_icon.setImageResource(R.drawable.metacritic);
        ratingScore.setText("74");


        parent.addView(rating_tab,0);
        parent.addView(rating_tab1,1);
        //inflater.inflate(R.layout.rating_tab, parent);

      */



        MovieResponse response = (MovieResponse)getIntent().getSerializableExtra(ResposeObserver.RESPONSE);

        Picasso.with(MovieActivity.this)
                .load(response.getPoster())
                .into(moviePoster);

        String movieNDate = response.getTitle()+ (response.getYear()!=null ? "("+response.getYear()+")" : "");
        title.setTitle(movieNDate);
        moviePlot.setText(response.getFullPlot());
        director.setText("Director: "+ getAllInOne(response.getDirector()));
        writer.setText("Writers:"+ getAllInOne(response.getWriter()));
        production.setText(response.getProduction());

        genre.setText(response.getGenre());
        runtime.setText(response.getRuntime());
        releaseDate.setText(response.getReleasedDate());

        awards.setText(response.getAwards());
        boxOffice.setText(response.getBoxoffice());
        website.setText(response.getWebsite());

        LinearLayout tab1 = getRatingTab(R.drawable.rottentomatoes,8.6+"");
        LinearLayout tab2 = getRatingTab(R.drawable.imdb,74+"%");

        buildRatingPane(response.getRatings());
        buildActorSection(response.getActors());

    }


    public void buildRatingPane(Map<String,String> rating)
    {

        Set<String> keys = rating.keySet();
        Iterator<String> iterator = keys.iterator();

        while(iterator.hasNext())
        {
            String name = iterator.next();
            String value = rating.get(name);

            int drawable = 0;
            if(name.equals("Internet Movie Database"))
            {
               drawable = R.drawable.imdb;
            }
            else if(name.equals("Rotten Tomatoes"))
            {
               drawable = R.drawable.rottentomatoes;
            }
            else{
               drawable = R.drawable.metacritic;
            }

            Log.d("Rohit",name+""+value);

            rating_pane.addView(getRatingTab(drawable,value));
        }
    }

    public LinearLayout getRatingTab(int drawable,String rating)
    {
        LinearLayout tab = (LinearLayout)inflater.inflate(R.layout.rating_tab,null);
        ImageView imageView = (ImageView)tab.findViewById(R.id.rating_com_icon);
        TextView textView = (TextView)tab.findViewById(R.id.rating_score);
        imageView.setImageResource(drawable);
        textView.setText(rating);
        return tab;
    }

    public void buildActorSection(String actorsString)
    {

        ArrayList<String> actors = getAll(actorsString);

         for(int i=0;i<actors.size();i++) {

             TextView textView = (TextView) inflater.inflate(R.layout.text, null);
             textView.setText(actors.get(i));
             movie_cast.addView(textView);
         }


    }

    public String getAllInOne(String name)
    {
        StringTokenizer tokenizer = new StringTokenizer(name,",");
        StringBuffer buffer = new StringBuffer();

        while (tokenizer.hasMoreTokens())
        {
            buffer.append(tokenizer.nextToken()+"\n");
        }

        Log.d("Rohit",new String(buffer));

        return new String(buffer);

    }

    public ArrayList<String> getAll(String name)
    {
        StringTokenizer tokenizer = new StringTokenizer(name,",");

        ArrayList<String> list = new ArrayList<String>();

        while (tokenizer.hasMoreTokens())
        {
            list.add(tokenizer.nextToken());
        }

        return list;
    }
}
