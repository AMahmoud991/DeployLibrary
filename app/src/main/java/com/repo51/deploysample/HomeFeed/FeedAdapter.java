package com.repo51.deploysample.HomeFeed;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.repo51.deploy.deploy.Deploy;
import com.repo51.deploy.widgets.DeployImageView;
import com.repo51.deploysample.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmedmahmoud on 11/4/17.
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<FeedModel> feedModels;
private Activity activity;
    public FeedAdapter(Context context, List<FeedModel> feedModels) {
        this.context = context;
        this.feedModels = feedModels;
        this.activity= (Activity) context;
    }

    @Override
    public FeedAdapter.FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_feed_item, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
FeedModel feedModel=feedModels.get(position);
        ((FeedViewHolder)holder).userImage.loadImage(feedModel.getUserModel().getProfileIamge(), activity);
    }


    @Override
    public int getItemCount() {
        return feedModels.size();
    }

    private class FeedViewHolder extends RecyclerView.ViewHolder{

        DeployImageView userImage;
        TextView userName;
        public FeedViewHolder(View itemView) {
            super(itemView);
userImage=(DeployImageView)itemView.findViewById(R.id.userImage);
        }
    }
}
