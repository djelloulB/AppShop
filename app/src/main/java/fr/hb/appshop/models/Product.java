package fr.hb.appshop.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public abstract class Product implements Parcelable {
    @SerializedName("_id")
    private String _id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("price")
    private float price;
    @SerializedName("imageUrl")
    private String imageUrl;

    //constructeur
    public Product() {
    }

    public Product(String _id, String name, String description, float price, String imageUrl) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }
//guetters and setters
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeFloat(this.price);
        dest.writeString(this.imageUrl);
    }

    public void readFromParcel(Parcel source) {
        this._id = source.readString();
        this.name = source.readString();
        this.description = source.readString();
        this.price = source.readFloat();
        this.imageUrl = source.readString();
    }

    protected Product(Parcel in) {
        this._id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.price = in.readFloat();
        this.imageUrl = in.readString();
    }


}

