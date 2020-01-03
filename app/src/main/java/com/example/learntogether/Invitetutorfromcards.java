package com.example.learntogether;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Invitetutorfromcards {

    public Bitmap b;

    public String img; //Image URL
    public String name; //Name
    public String Skypeid;//Skype_id For Tutor
    public String Gender;//Skype_id For Tutor
    public String Languages;//Skype_id For Tutor
    public String Teaches;//Skype_id For Tutor
    public String Rateperhour;//Skype_id For Tutor

    public Invitetutorfromcards() {
    }

    public Invitetutorfromcards(String img, String name, String skypeid, String gender, String languages, String teaches, String rateperhour) {
        this.img = img;
        this.name = name;
        Skypeid = skypeid;
        Gender = gender;
        Languages = languages;
        Teaches = teaches;
        Rateperhour = rateperhour;
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

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getLanguages() {
        return Languages;
    }

    public void setLanguages(String languages) {
        Languages = languages;
    }

    public String getTeaches() {
        return Teaches;
    }

    public void setTeaches(String teaches) {
        Teaches = teaches;
    }

    public String getRateperhour() {
        return Rateperhour;
    }

    public void setRateperhour(String rateperhour) {
        Rateperhour = rateperhour;
    }
}
