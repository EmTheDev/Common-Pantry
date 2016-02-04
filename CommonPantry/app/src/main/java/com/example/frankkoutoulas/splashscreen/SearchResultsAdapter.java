package com.example.frankkoutoulas.splashscreen;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by miles on 12/9/15.
 */
public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context mContext;

    ArrayList<RecipeAPIResult> searchResults;

    public SearchResultsAdapter(Context context, ArrayList<RecipeAPIResult> resultsList) {
        searchResults = resultsList;
        mContext = context;
        inflater = LayoutInflater.from(mContext);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_list, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        System.out.println("Inside onBindViewHolder");
        RecipeAPIResult currentResult = searchResults.get(position);
        System.out.println("At RecyclerView position " + position);
        holder.recipeTitle.setText(currentResult.getTitle());
        System.out.println("Title: " + currentResult.getTitle());
        //Picasso.with(context).load(currentResult.getImageURL()).resize(64,64).into(holder.recipePhoto);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setSearchResults (ArrayList<RecipeAPIResult> recipeResults){
        searchResults = recipeResults;
        notifyItemRangeChanged(0,searchResults.size());
        return;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView recipeTitle;

        ImageView recipePhoto;

        public MyViewHolder(View itemView) {
            super(itemView);
            recipeTitle = (TextView) itemView.findViewById(R.id.recipe_name);
            recipePhoto = (ImageView) itemView.findViewById(R.id.recipe_photo);
        }
    }
}
