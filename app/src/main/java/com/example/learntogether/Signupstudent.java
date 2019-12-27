package com.example.learntogether;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Signupstudent extends AppCompatActivity {
    int type;
    EditText txtLanguage,txtskypeid,txtemail,txtnumber,txtNamee;
    //Dbclass connect;
    ProgressDialog progressDialog;
    Intent mIntent = getIntent();
    Button Signupstudents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupstudent);
        txtLanguage=(EditText)findViewById(R.id.Language);
        txtskypeid=(EditText)findViewById(R.id.skypeid);
        txtemail=(EditText)findViewById(R.id.Perhourrate);
        txtnumber=(EditText)findViewById(R.id.Location);
        txtNamee=(EditText)findViewById(R.id.Namee);
        Signupstudents=(Button)findViewById(R.id.signupstudnts);
        Signupstudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddStudents();
            }
        });
//        type= mIntent.getIntExtra("type",0);


    }

    public void Gobacktomainactivity(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void RegistetStudentinDb(View view) {
        Toast.makeText(this, "Student will be Register soon...", Toast.LENGTH_SHORT).show();
    }

    public void AddStudents() {
        Toast.makeText(this, "InAddmethod...", Toast.LENGTH_SHORT).show();
      //  Toast.makeText(connect, "Button Clickess....", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,Loginuser.class);
       /* Login login=new Login();
        login.execute("");*/
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
                          Toast.makeText(Signupstudent.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                      }
                  });

          }
          else
              {

                  query = "INSERT INTO students (name,email,Skypeid,number,languages) VALUES(?,?,?,?,?)";
                  PreparedStatement preparedStatement = null;
                  try{
                      preparedStatement = con.prepareStatement(query);
                      preparedStatement.setString(1,txtNamee.getText().toString());
                      preparedStatement.setString(2,txtemail.getText().toString());
                      preparedStatement.setString(3, txtskypeid.getText().toString());
                      preparedStatement.setString(4, txtnumber.getText().toString());
                      preparedStatement.setString(5, txtLanguage.getText().toString());
                      preparedStatement.execute();

                  }
                  catch (final SQLException e){
                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             Toast.makeText(Signupstudent.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                         }
                     });
                  }

              }
          }
      }).start();

        startActivity(intent);


    }
/*
     private class Login extends AsyncTask<String,String,String>
    {
        boolean isSuccess=false;
        String putLanguage,putskypeid,putPerhourrate,putLocation,putNamee;
        int type;
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String query;


            //Connection con = connect.ConnectToDB();
            Connection con ;
            try {
                 con = connect.ConnectToDB();
                if (con == null)
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Signupstudent.this, "check your internet", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    query = "INSERT INTO students (name,email,Skypeid,number,languages) VALUES(?,?,?,?,?)";
                    PreparedStatement preparedStatement = null;
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setString(1,txtNamee.getText().toString());
                    preparedStatement.setString(2,txtemail.getText().toString());
                    preparedStatement.setString(3, txtskypeid.getText().toString());
                    preparedStatement.setString(4, txtnumber.getText().toString());
                    preparedStatement.execute();
                }
            }
            catch (Exception ex)
            {
                isSuccess = false;
            }
            return null;        }

        @Override
        protected void onPostExecute(String s) {

        }
    }*/


}
