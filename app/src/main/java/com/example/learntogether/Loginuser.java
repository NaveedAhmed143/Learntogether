package com.example.learntogether;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Loginuser extends AppCompatActivity {

    ImageView imgsetting,imgmessage,imgmytutor,imgfindtutor,
            imgrecommend,imgclassroom,imginvite,imgshare,imgupdate,imglocation,imgplanandpricing;
   private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginuser);
                
        imgsetting=(ImageView)findViewById(R.id.setting);
        imginvite=(ImageView)findViewById(R.id.invitetutor);
        imgclassroom=(ImageView)findViewById(R.id.classroom);
        imgfindtutor=(ImageView)findViewById(R.id.findtutor);
        imgmessage=(ImageView)findViewById(R.id.messagee);
        imgmytutor=(ImageView)findViewById(R.id.mytutor);
        imgrecommend=(ImageView)findViewById(R.id.recommended);


        imgshare=(ImageView)findViewById(R.id.share);
        imgupdate=(ImageView)findViewById(R.id.update);
        imglocation=(ImageView)findViewById(R.id.Location);
        imgplanandpricing=(ImageView)findViewById(R.id.planandpricing);
        
        imgplanandpricing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetPlanandPrice();
            }
        });
        
        imglocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoaction();      
            }
        });
        
        imgupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();
            }
        });
        
        imgshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Share();
            }
        });
        

        imginvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InviteTutor();
            }
        });
        imgsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsShowSideBar();
            }
        });
        imgrecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recommend();
            }
        });
        imgmytutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mytutor();
            }
        });
        imgmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage();
            }
        });
        imgfindtutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findmytutor();
            }
        });
        imgclassroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClassroom();
            }
        });
    }

    public void mytutor(){
        Toast.makeText(this, "MyTutor", Toast.LENGTH_SHORT).show();
        intent = new Intent(this,Mytutor.class);
        startActivity(intent);
    }
    public void showMessage(){
        Toast.makeText(this, "ShowMessages", Toast.LENGTH_SHORT).show();
        intent = new Intent(this,Message.class);
        startActivity(intent);
    }
    public void findmytutor(){
        Toast.makeText(this, "FindMytutor", Toast.LENGTH_SHORT).show();
        intent = new Intent(this,Search.class);
        startActivity(intent);
    }
    public void Recommend(){
        Toast.makeText(this, "Recommended..", Toast.LENGTH_SHORT).show();
        intent = new Intent(this,Recommendation.class);
        startActivity(intent);
    }

    public void settingsShowSideBar(){
        Toast.makeText(this, "You Cliked Setting,,,", Toast.LENGTH_SHORT).show();
    }
    public void showClassroom(){

        Toast.makeText(this, "ShowClassRoom", Toast.LENGTH_SHORT).show();
        intent = new Intent(this,Classroom.class);
        startActivity(intent);
    }
    public void InviteTutor(){

        Toast.makeText(this, "Invite Tutor", Toast.LENGTH_SHORT).show();
        intent = new Intent(this,Invitetutor.class);
        startActivity(intent);
    }
        
            
    public void GetPlanandPrice()
    {
        Toast.makeText(this, "Plan and price Will apear", Toast.LENGTH_SHORT).show();
        intent = new Intent(this,Planandpricing.class);
        startActivity(intent);
    }
    public void getLoaction()
    {
        Toast.makeText(this, "User Location", Toast.LENGTH_SHORT).show();
        intent = new Intent(this,Locatoin.class);
        startActivity(intent);
        
    }
    public void Update()
    {
        Toast.makeText(this, "USer Can Update Profile", Toast.LENGTH_SHORT).show();
        intent = new Intent(this,Updateprofile.class);
        startActivity(intent);
    }
    public void Share()
    {
        Toast.makeText(this, "Can Share On Social Media....", Toast.LENGTH_SHORT).show();
        intent = new Intent(this,Share.class);
        startActivity(intent);
    }
}