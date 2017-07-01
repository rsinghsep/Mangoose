package com.omdb.rohksin.omdb;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.omdb.rohksin.omdb.NewSearch.POJO.DetailMovie;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.Impl.DetailMovieMapper;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.ResponseMapper;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl.TVShowIDURLBuilder;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.URLBuilder;
import com.omdb.rohksin.omdb.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.Buffer;

/**
 * Created by Illuminati on 6/29/2017.
 */
public class TVShowDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceSstate)
    {
        super.onCreate(savedInstanceSstate);
        setContentView(R.layout.tv_show_activity);
        String tvShowId = (String)getIntent().getStringExtra("tvshowdetailactivity");

        final TextView responseTextView = (TextView)findViewById(R.id.response);
        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TVShowDetailActivity.this,SeasonDetailActivity.class);
                i.putExtra("tv_id","1399");
                i.putExtra("season_num","1");
                startActivity(i);
            }
        });

        URLBuilder urlBuilder = new TVShowIDURLBuilder(tvShowId);
        String endpoint = urlBuilder.bulidURL();

        Log.d("TVURL",endpoint);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,endpoint,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("ENDPOINT", response.toString());

                        responseTextView.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }

        );

        requestQueue.add(request);


    }
}
