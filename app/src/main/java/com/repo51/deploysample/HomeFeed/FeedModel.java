package com.repo51.deploysample.HomeFeed;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by ahmedmahmoud on 11/4/17.
 */

public class FeedModel implements Parcelable {
    private String id;
    private int width;
    private int height;
    private String color;
    private String textColor;
    private int likes;
    private boolean liked_by_user = false;
    private UserModel userModel;

    public FeedModel() {
    }


    protected FeedModel(Parcel in) {
        id = in.readString();
        width = in.readInt();
        height = in.readInt();
        color = in.readString();
        textColor = in.readString();
        likes = in.readInt();
        liked_by_user = in.readByte() != 0;
        userModel = in.readParcelable(UserModel.class.getClassLoader());
    }

    public static final Creator<FeedModel> CREATOR = new Creator<FeedModel>() {
        @Override
        public FeedModel createFromParcel(Parcel in) {
            return new FeedModel(in);
        }

        @Override
        public FeedModel[] newArray(int size) {
            return new FeedModel[size];
        }
    };

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLiked_by_user() {
        return liked_by_user;
    }

    public void setLiked_by_user(boolean liked_by_user) {
        this.liked_by_user = liked_by_user;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeInt(width);
        parcel.writeInt(height);
        parcel.writeString(color);
        parcel.writeString(textColor);
        parcel.writeInt(likes);
        parcel.writeByte((byte) (liked_by_user ? 1 : 0));
        parcel.writeParcelable(userModel, i);
    }
}
