package com.example.learntogether;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ClassListItems {
    public String img; //Image URL
    public String name; //Name
    public String Skypeid;//Skype_id For Tutor
    public Bitmap b;

    public ClassListItems(String name, String img, String Skypeid)
    {

        this.name = name;
        this.img = img;
        this.Skypeid = Skypeid;
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

    public String getName() {
        return name;
    }

    public String getSkypeid() {
        return Skypeid;
    }

}
