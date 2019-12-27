package com.example.learntogether;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import androidx.appcompat.app.AppCompatActivity;

public class Updateprofile extends AppCompatActivity {
    EditText MSkype_ID ,Mnationality ,MEmail ,MUpName;
    ProgressDialog progressDialog;
    Dbclass connectionClass;
    String Mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);
        MSkype_ID=(EditText)findViewById(R.id.Skype_ID);
        Mnationality=(EditText)findViewById(R.id.nationality);
        MEmail=(EditText)findViewById(R.id.Email);
        MUpName=(EditText)findViewById(R.id.UpName);
        connectionClass = new Dbclass();
        progressDialog=new ProgressDialog(this);

        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.FILE_NAME,MODE_PRIVATE);
        Mobile = sharedPreferences.getString("PHONE","");

        GettingData gd=new GettingData();
        gd.execute("");
    }

    public void UpdateuserProfile(View view) {
        Toast.makeText(this, "Udpated here Soon....", Toast.LENGTH_SHORT).show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String query;
                Dbclass connectionClass = new Dbclass();
                Connection con = connectionClass.ConnectToDB();
                if (con == null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Updateprofile.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    query = "UPDATE `students` SET `name`='"+MUpName.getText().toString()+"',`email`='"+MEmail.getText().toString()+"',`Skypeid`='"+MSkype_ID.getText().toString()+"' WHERE number='"+Mobile+"';";
                    try {
                        PreparedStatement stmt = con.prepareStatement(query);
                        stmt.execute();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        });
                    } catch (final SQLException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Updateprofile.this, "excaption is "+e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        e.printStackTrace();
                    }


                    /////////////////////
                    /////////////////////
                    /////////////////////

                }
            }
        }).start();




    }


    private class GettingData extends AsyncTask<String,String,String>
    {
        boolean isSuccess=false;
        String phn,na,Skypeid,em;
        @Override
        protected void onPreExecute()
        {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection con = connectionClass.ConnectToDB();
                if (con == null)
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Updateprofile.this, "check your internet", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    String query=" select * from students";
                    Statement stmt = con.createStatement();
                    ResultSet rs=stmt.executeQuery(query);
                    while (rs.next())
                    {
                        if (rs.getString("number").equals(Mobile))
                        {
                            phn=rs.getString("number");
                            na=rs.getString("name");
                            Skypeid=rs.getString("Skypeid");
                            em=rs.getString("email");

                        }
                    }
                }
            }
            catch (Exception ex)
            {
                isSuccess = false;
            }
            return null;        }

        @Override
        protected void onPostExecute(String s) {

            MUpName.setText(na);
            MEmail.setText(em);
            MSkype_ID.setText(Skypeid);
            progressDialog.hide();
            progressDialog.dismiss();
        }
    }
}
