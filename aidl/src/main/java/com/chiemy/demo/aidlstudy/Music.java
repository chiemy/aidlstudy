package com.chiemy.demo.aidlstudy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chiemy on 16/4/18.
 */
public class Music implements Parcelable{
    private String name;
    private String url;

    public Music(){
    }

    protected Music(Parcel in) {
        // 注意：按write的顺序读出
        name = in.readString();
        url = in.readString();
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
    }

    public void readFromParcel(Parcel in){
        name = in.readString();
        url = in.readString();
    }

}
