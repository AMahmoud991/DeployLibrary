package com.repo51.deploysample.HomeFeed;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ahmedmahmoud on 11/4/17.
 */
public class UserModel implements Parcelable {
    private String id;
    private String userName;
    private String profileIamge;

    public UserModel() {
    }

    protected UserModel(Parcel in) {
        id = in.readString();
        userName = in.readString();
        profileIamge = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileIamge() {
        return profileIamge;
    }

    public void setProfileIamge(String profileIamge) {
        this.profileIamge = profileIamge;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(userName);
        parcel.writeString(profileIamge);
    }
}
