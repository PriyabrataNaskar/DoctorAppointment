package com.blogspot.priyabratanaskar.firebaselogin.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.priyabratanaskar.firebaselogin.R;
import com.blogspot.priyabratanaskar.firebaselogin.model.Article;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Priyabrata Naskar on 08-04-2021.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    // Member variables.
    private List<Article> mNewsData;
    private Context mContext;

    public NewsAdapter(List<Article> mNewsData, Context mContext) {
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
        Article currentNews = mNewsData.get(position);

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

        public void bindTo(Article currentNews) {
            mNewsTitleText.setText(currentNews.getNewsTitle());
            mAuthorTitle.setText(currentNews.getNewsAuthorName());
            mDescription.setText(Html.fromHtml(currentNews.getNewsDescription()).toString());

            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext).load(
                    currentNews.getNewsImageResource()).into(mNewsImage);
        }
    }
}
