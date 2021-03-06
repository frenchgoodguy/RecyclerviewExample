package com.techcrevices.recyclerviewexample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private int lastPosition = -1;
    int row_index = -1;

    private List<MovieModel> movieModels;
    Context context;
    public MovieAdapter(List<MovieModel> movieModels, Context context) {
        this.movieModels = movieModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        MovieModel model = movieModels.get(position);
        holder.movieTitle.setText(model.getMovieName());
        holder.movieType.setText(model.getMovieType());
        holder.movieYear.setText(model.getMovieYear());
        holder.movieYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              /*  Gson gson = new Gson();
                String json = gson.toJson(movieModels);

                Log.e("SSSSS",String.valueOf(movieModels));
                Intent i = new Intent(context, Main2Activity.class);
                i.putExtra("listitem",  json);
                i.putExtra("position",String.valueOf(position));
                context.startActivity(i);
*/

            }
        });

        holder.movieType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              /*  BlankFragment bf = new BlankFragment();
                bf.openDialog();*/
            }
        });


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=position;
                notifyDataSetChanged();
            }
        });

        if (row_index==position) {
            holder.linearLayout.setBackgroundColor(Color.RED);
        } else {
            holder.linearLayout.setBackgroundColor(Color.parseColor("#dddddd"));
        }


        setAnimation(holder.itemView, position);


    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView movieTitle, movieType, movieYear;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = (TextView)itemView.findViewById(R.id.movieNameTV);
            movieType = (TextView)itemView.findViewById(R.id.movieType);
            movieYear = (TextView)itemView.findViewById(R.id.movieYear);

            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);
        }
    }


    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            //TranslateAnimation anim = new TranslateAnimation(0,-1000,0,-1000);
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            //anim.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)
            anim.setDuration(550);//to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim);
            lastPosition = position;

        }
    }
}
