package com.example.bottomnavigationbar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bottomnavigationbar.Constants.Constants;
import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.models.Movie;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UpcomingMovieAdapter extends RecyclerView.Adapter<UpcomingMovieAdapter.MyViewHolder> {

    private ArrayList<Movie> mMovies;
    private Context mContext;

    public UpcomingMovieAdapter(ArrayList<Movie> movies, Context context) {
        mMovies = movies;
        mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_view,parent,false);
        return new UpcomingMovieAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingMovieAdapter.MyViewHolder holder, int position) {
        Glide.with(mContext).load(Constants.IMAGE_BASE_URL+mMovies.get(position).getPosterPath()).into(holder.mMovieAvatar);
        holder.mMovieTitle.setText(mMovies.get(position).getTitle());
        holder.mMovieOriginalTitle.setText(mMovies.get(position).getOriginalTitle());
        holder.mMovieDescription.setText(mMovies.get(position).getOverview());
        holder.mMovieReleaseDate.setText(mMovies.get(position).getReleaseDate());
        holder.mMovieRating.setText(mMovies.get(position).getVoteAverage().toString());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mMovieAvatar;
        TextView mMovieTitle;
        TextView mMovieOriginalTitle;
        TextView mMovieDescription;
        TextView mMovieReleaseDate;
        TextView mMovieRating;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mMovieAvatar = itemView.findViewById(R.id.img_movie_avatar);
            mMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            mMovieOriginalTitle = itemView.findViewById(R.id.tv_movie_original_title);
            mMovieDescription = itemView.findViewById(R.id.tv_overview);
            mMovieReleaseDate = itemView.findViewById(R.id.tv_release_date);
            mMovieRating = itemView.findViewById(R.id.tv_movie_rating);
        }
    }
    public void update(ArrayList<Movie> movieList){
        mMovies.addAll(movieList);
        notifyDataSetChanged();
    }
}
