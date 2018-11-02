package com.omdb.rohksin.Mangoose.Adaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omdb.rohksin.Mangoose.NewSearch.POJO.Crew;
import com.omdb.rohksin.Mangoose.NewSearch.Utility.MovieUtils;
import com.omdb.rohksin.Mangoose.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Illuminati on 6/21/2017.
 */
public class CrewListAdapter extends RecyclerView.Adapter<CrewListAdapter.CrewViewHolder> {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    List<Crew> list;
    Context context;


    /*/////////////////////////////////////////////////
    //CONSTRUCTOR
    /*/////////////////////////////////////////////////
    public CrewListAdapter(List<Crew> list, Context context) {
        this.list = list;
        this.context = context;

    }


    /*/////////////////////////////////////////////////
    //LIFECYCLE METHODS
    /*/////////////////////////////////////////////////
    @Override
    public CrewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crew_landing_single_item, parent, false);
        CrewViewHolder actorViewHolder = new CrewViewHolder(view);
        return actorViewHolder;
    }
    @Override
    public void onBindViewHolder(CrewViewHolder holder, int position) {

        Crew crew = list.get(position);

        Log.d("ImagePICASSO", crew.toString());

        holder.crewName.setText(crew.getName());
        holder.roleName.setText(crew.getJob());


        Picasso.with(context)
                .load(MovieUtils.imageURL(crew.getProfileImage())).error(R.drawable.placeholder)
                .into(holder.crewImage);

    }
    @Override
    public int getItemCount() {
        return list.size();
    }


    /*/////////////////////////////////////////////////
    //View Holder class
    /*/////////////////////////////////////////////////
    public class CrewViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView crewImage;
        private TextView crewName;
        private TextView roleName;

        public CrewViewHolder(View itemView) {
            super(itemView);
            //mediaImage = (ImageView)itemView.findViewById(R.id.MediaImage);
            crewImage = (CircleImageView) itemView.findViewById(R.id.crewImage);
            crewName = (TextView) itemView.findViewById(R.id.crewName);
            roleName = (TextView) itemView.findViewById(R.id.crewRole);

        }
    }

}
