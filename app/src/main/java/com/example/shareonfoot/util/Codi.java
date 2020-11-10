package com.example.shareonfoot.util;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;


import java.util.Objects;

public class Codi implements Comparable<Codi>, Parcelable {


    ColorArrange colorArrange;
    double temperature=-1;


    protected Codi(Parcel in) {

        colorArrange = in.readParcelable(ColorArrange.class.getClassLoader());
        temperature = in.readDouble();
    }

    public Codi(){

        colorArrange = new ColorArrange();
        temperature = 10000;
    }

    public Codi(ColorArrange colorArrange){

        this.colorArrange = colorArrange;
        temperature = 10000;
    }




    public static final Creator<Codi> CREATOR = new Creator<Codi>() {
        @Override
        public Codi createFromParcel(Parcel in) {
            return new Codi(in);
        }

        @Override
        public Codi[] newArray(int size) {
            return new Codi[size];
        }
    };





    public ColorArrange getColorArrange() {
        return colorArrange;
    }

    public void setColorArrange(ColorArrange colorArrange) {
        this.colorArrange = colorArrange;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeParcelable(colorArrange, flags);
        dest.writeDouble(temperature);
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int compareTo(Codi codi) {
        if (this.colorArrange.total_score < codi.getColorArrange().total_score) {
            return 1;
        } else if (this.colorArrange.total_score > codi.getColorArrange().total_score) {
            return -1;
        }
        /*
        //같은 점수일 경우 옷 번호 순서대로 나열
        else if(this.colorArrange.total_score == codi.getColorArrange().total_score){
            ClothesVO thisTop = top;
            ClothesVO thisSuit = suit;
            ClothesVO codiTop = codi.getTop();
            ClothesVO codiSuit = codi.getSuit();
            if(thisTop.getCategory()!=null && codiTop.getCategory()!=null){
                return Integer.compare(thisTop.getCloNo(),codiTop.getCloNo());
            }else if(thisTop.getCategory()!=null && codiSuit.getCategory()!=null){
                return Integer.compare(thisTop.getCloNo(),codiSuit.getCloNo());
            }else if(thisSuit.getCategory()!=null && codiTop.getCategory()!=null){
                return Integer.compare(thisSuit.getCloNo(),codiTop.getCloNo());
            }else if(thisSuit.getCategory()!=null && codiSuit.getCategory()!=null){
                return Integer.compare(thisSuit.getCloNo(),codiSuit.getCloNo());
            }
        }*/
        return 0;
    }
}
