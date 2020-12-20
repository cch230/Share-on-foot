package com.example.shareonfoot.VO;

import android.os.Parcel;
import android.os.Parcelable;

public class ClothesVO implements Parcelable {
    private String idx;
    private String name; // PRIMARY KEY. AUTO INCREMENT
    private String category;
    private String review;
    private String star; // not null
    private String adress; // not null
    private int image;
    //임시
    private int pageStart =-1;
    private int pageSize =-1;




    protected ClothesVO(Parcel in) {
        idx=in.readString();
        name = in.readString();
        category = in.readString();
        review = in.readString();
        star = in.readString();
        adress = in.readString();
        image= in.readInt();
    }

    public static final Creator<ClothesVO> CREATOR = new Creator<ClothesVO>() {
        @Override
        public ClothesVO createFromParcel(Parcel in) {
            return new ClothesVO(in);
        }

        @Override
        public ClothesVO[] newArray(int size) {
            return new ClothesVO[size];
        }
    };

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }




    //생성자
    public ClothesVO() {
        System.out.println("ClothesVO 생성자 호출");
    }


    public ClothesVO(String idx, String name, String category, String review, String star,
                     String adress, int image){
        this.idx = idx;
        this.name = name;
        this.category =category;
        this.review =review;
        this.star= star;
        this.adress=adress;
        //this.dst=dst;
        this.image=image;
    }

    public String getidx() {
        return idx;
    }
    public void setidx(String idx){this.idx=idx;}

    public String getname() {
        return name;
    }
    public void setname(String name){this.name=name;}

    public String getcategory() {
        return category;
    }
    public void setcategory(String category) {
        this.category = category;
    }

    public String getreview(){return review;}
    public void setreview(String review){this.review=review;}

    public String getstar(){return star;}
    public void setstar(String star){this.star=star;}

    public String getadress(){return adress;}
    public void setadress(String adress){this.adress=adress;}

    /*public String getdst(){return  dst;}
    public void setdst(String dst){this.dst=dst;}*/

    public int getimage(){return image;}
    public void setimage(int image){this.image=image;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idx);
        dest.writeString(name);
        dest.writeString(category);
        dest.writeString(review);
        dest.writeString(star);
        dest.writeString(adress);
        //dest.writeString(dst);
        dest.writeInt(image);
    }
}
