package com.repo51.deploysample.HomeFeed;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.repo51.deploy.widgets.DeployImageView;
import com.repo51.deploysample.R;

import java.util.List;

/**
 * Created by ahmedmahmoud on 11/4/17.
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<FeedModel> feedModels;
    private Activity activity;
private ItemCLickListener itemCLickListener;
    public FeedAdapter(Context context, List<FeedModel> feedModels) {
        this.context = context;
        this.feedModels = feedModels;
        this.activity = (Activity) context;
    }

    @Override
    public FeedAdapter.FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_feed_item, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final FeedModel feedModel = feedModels.get(position);
        FeedViewHolder feedViewHolder= (FeedViewHolder) holder;
        feedViewHolder.userImage.loadImage(feedModel.getUserModel().getProfileIamge(), activity);
        feedViewHolder.userName.setBackgroundColor(Color.parseColor(feedModel.getColor()));
        feedViewHolder.userName.setTextColor(Color.parseColor(feedModel.getTextColor()));
        feedViewHolder.userName.setText(feedModel.getUserModel().getUserName());
        feedViewHolder.containerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemCLickListener!=null){
                    itemCLickListener.onItemCLickListener(position,feedModel);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return feedModels.size();
    }

    public void setItemCLickListener(ItemCLickListener itemCLickListener) {
        this.itemCLickListener = itemCLickListener;
    }

    private class FeedViewHolder extends RecyclerView.ViewHolder {

        DeployImageView userImage;
        TextView userName;
        RelativeLayout containerLayout;
        public FeedViewHolder(View itemView) {
            super(itemView);
            this.containerLayout=itemView.findViewById(R.id.continer_layout);
            userImage = (DeployImageView) itemView.findViewById(R.id.userImage);
            userName = (TextView) itemView.findViewById(R.id.userName);
        }
    }
    public interface ItemCLickListener{
        void onItemCLickListener(int pos,FeedModel feedModel);
    }
}
