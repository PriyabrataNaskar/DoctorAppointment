package com.blogspot.priyabratanaskar.firebaselogin;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Priyabrata Naskar on 08-04-2021.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    // Member variables.
    private ArrayList<News> mNewsData;
    private Context mContext;

    public NewsAdapter(ArrayList<News> mNewsData, Context mContext) {
        this.mNewsData = mNewsData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        // Get current sport.
        News currentNews = mNewsData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentNews);
    }

    @Override
    public int getItemCount() {
        return mNewsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Member Variables for the TextViews
        private TextView mNewsTitleText;
        private ImageView mNewsImage;
        private TextView mAuthorTitle;
        private TextView mDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize the views.
            mNewsTitleText = itemView.findViewById(R.id.news_title);
            mNewsImage = itemView.findViewById(R.id.newsImage);
            mAuthorTitle = itemView.findViewById(R.id.author_title);
            mDescription = itemView.findViewById(R.id.description_view);

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }

        public void bindTo(News currentNews) {
            mNewsTitleText.setText(currentNews.getNewsTitle());
            mAuthorTitle.setText("Author: " + currentNews.getNewsAuthorName());
            mDescription.setText(Html.fromHtml(currentNews.getNewsDescription()).toString());

            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext).load(
                    currentNews.getNewsImageResource()).into(mNewsImage);
            mNewsImage.setAlpha(0.5f);
        }
    }
}
