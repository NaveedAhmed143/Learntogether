package com.example.learntogether;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    int usertypee;
    //Dbclass connect;
    Button login,signup;
    EditText txtemail,txtnumber;
    String Email,Number,usertype;
    Dbclass getConnect;
    private Spinner spinget;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog=new ProgressDialog(this);
        spinget=(Spinner)findViewById(R.id.spintype);
        login=(Button)findViewById(R.id.login);
        signup=(Button)findViewById(R.id.signup);
        txtemail=(EditText)findViewById(R.id.email);
        txtnumber=(EditText)findViewById(R.id.pass);
        spinget=(Spinner) findViewById(R.id.spintype);
        spinget.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Type=spinget.getSelectedItem().toString();
                if(Type.equalsIgnoreCase("Tutor")){
                    usertypee=1;

                    Toast.makeText(MainActivity.this, "Teacher", Toast.LENGTH_SHORT).show();
                }
                else {
                    usertypee=2;
                    Toast.makeText(MainActivity.this, "Student...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getConnect=new Dbclass();
    }

    public void LoginStudent(View view) {
        Email= txtemail.getText().toString();
        Number= txtnumber.getText().toString();

        Log.d("NUM", "Number is: "+Number+" Email is "+Email);
        //Log.d("Email", "Email is: "+Number);

      /*  Email=txtemail.getText().toString();
        Password=txtpass.getText().toString();
        usertype=spinget.getSelectedItem().toString();

            Login login=new Login();
            login.execute("");*/
        Intent intent = new Intent(this,Loginuser.class);
        if(usertypee==2){

            Login login=new Login();
            login.execute("");
        }
        else{
            if(usertypee==1){
                Login login=new Login();
                login.execute("");
            }

        }

    }
    private class Login extends AsyncTask<String,String,String>
    {
        String query;
        boolean isSuccess=false;
        String phn,email;
        int type;
        @Override
        protected void onPreExecute()
        {

            progressDialog.dismiss();
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String ahah="AFSD";

            Log.d("Check", "doInBackground: "+ahah);
            try {
                Connection con = getConnect.ConnectToDB();
                if (con == null)
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("CON", "run: In A Conn Null Methd");
                            Toast.makeText(MainActivity.this, "check your internet", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else if(usertypee==2)
                {
                    query=" select * from students";
                    Statement stmt = con.createStatement();
                    ResultSet rs=stmt.executeQuery(query);
                    while (rs.next())
                    {
                        if (rs.getString("number").equals(Number))
                        {

                            phn=rs.getString("number");
                            email=rs.getString("email");
                            Log.d("inALoop : ", "NUMBER.... "+phn+" Email: "+email);
                            if(phn.equals(Number)&&email.equals(Email.toString()))
                            {
                                isSuccess=true;
                            }
                            else
                            {
                                isSuccess=false;
                            }
                        }
                    }
                }
                else{

                    query=" select * from teacher";
                    Statement stmt = con.createStatement();
                    ResultSet rs=stmt.executeQuery(query);
                    while (rs.next())
                    {
                        if (rs.getString("tnumber").equals(Number))
                        {

                            phn=rs.getString("tnumber");
                            email=rs.getString("tEmail");
                            Log.d("inALoop : ", "NUMBER.... "+phn+" Email: "+email);
                            if(phn.equals(Number)&&email.equals(Email.toString()))
                            {
                                isSuccess=true;
                            }
                            else
                            {
                                isSuccess=false;
                            }
                        }
                    }



                }
            }
            catch (Exception ex)
            {
                isSuccess = false;
              //  Toast.makeText(MainActivity.this, ""+ex.toString(), Toast.LENGTH_SHORT).show();
            }
            return null;        }

        @Override
        protected void onPostExecute(String s) {
            if(isSuccess && usertypee==2) {
                Intent intent=new Intent(MainActivity.this,Loginuser.class);
                startActivity(intent);
            }
            else if(isSuccess==true && usertypee==1)
            {
                Intent Teacherintent = new Intent(MainActivity.this,LoginTeacher.class);
                startActivity(Teacherintent);
            }
            else {
                Toast.makeText(MainActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();


            }
            progressDialog.hide();
            // progressDialog.dismiss();
        }
    }



    public void RegisterStudent(View view) {

        Intent intent = new Intent(this,Signupteacher.class);
        if(usertypee==1){
            startActivity(intent);
        }
        else{
            Intent myintent= new Intent(this,Signupstudent.class);
            startActivity(myintent);
        }

    }
}


