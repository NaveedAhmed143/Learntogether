package com.example.learntogether;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class RecommendTutorsfromCards {

    public String img; //Image URL
    public String name; //Name
    public String Skypeid;//Skype_id For Tutor
    public Bitmap b;

    public RecommendTutorsfromCards(String name, String img, String Skypeid ) {
        this.img = img;
        this.name = name;
        this.Skypeid= Skypeid;
    }
    public RecommendTutorsfromCards() {
    }

    public Bitmap getImg() {
        String Pics=img;
        Log.d("Checkimg", "getImg: "+img);
        b=StringToBitMap(Pics);
        return b;

    }
    public Bitmap StringToBitMap(String image){
        try{
            byte [] encodeByte= Base64.decode(image,Base64.DEFAULT);

            InputStream inputStream  = new ByteArrayInputStream(encodeByte);
            Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }
    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkypeid() {
        return Skypeid;
    }

    public void setSkypeid(String skypeid) {
        Skypeid = skypeid;
    }
}
