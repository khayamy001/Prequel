package com.torchbyte.prql.pager.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
    public String title;
    public String body;
    public int headerIconUrl;
    public int thumbnailIconUrl;
    public int[] slideShowItems;

    public Data(String title, String body, int headerIconUrl, int thumbnailIconUrl, int[] slideShowItems) {
        this.title = title;
        this.body = body;
        this.headerIconUrl = headerIconUrl;
        this.thumbnailIconUrl = thumbnailIconUrl;
        this.slideShowItems = slideShowItems;
    }

    public Data(Parcel in) {
        this.title = in.readString();
        this.body = in.readString();
        this.headerIconUrl = in.readInt();
        this.thumbnailIconUrl = in.readInt();
        this.slideShowItems = in.createIntArray();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(body);
        parcel.writeInt(headerIconUrl);
        parcel.writeInt(thumbnailIconUrl);
        parcel.writeIntArray(slideShowItems);
    }

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>()
    {
        public Data createFromParcel(Parcel in)
        {
            return new Data(in);
        }

        public Data[] newArray(int size)
        {
            return new Data[size];
        }
    };
}