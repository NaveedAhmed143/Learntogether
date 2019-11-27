package com.example.learntogether;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Signupteacher extends AppCompatActivity {

    AutoCompleteTextView multiAutoCompleteTextView;
    EditText txtLanguage, txtskypeid ,txtPerhourrate ,txtnationality ,txtLocation ,txtAge ,txtNamee,txtemAil;
    Button signupforteachers;
   int Gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupteacher);

        txtLanguage=(EditText)findViewById(R.id.Language);
        txtskypeid=(EditText)findViewById(R.id.Language);
        txtLanguage=(EditText)findViewById(R.id.skypeid);
        txtPerhourrate=(EditText)findViewById(R.id.Perhourrate);
        txtnationality=(EditText)findViewById(R.id.nationality);
        txtLocation=(EditText)findViewById(R.id.Location);
        txtemAil=(EditText)findViewById(R.id.txtemail);
        txtAge=(EditText)findViewById(R.id.Age);
        txtNamee=(EditText)findViewById(R.id.Namee);
        signupforteachers=(Button)findViewById(R.id.SignupTeachers);
       // RadioGroup rg = (RadioGroup) findViewById(R.id.radioSex);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioSex);



        /*rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioMale:
                        Toast.makeText(Signupteacher.this, ""+group.toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(Signupteacher.this, "", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioFemale:
                        // do operations specific to this selection
                        Toast.makeText(Signupteacher.this, ""+group.toString(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });*/
        signupforteachers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gender= rg.getCheckedRadioButtonId();
                if(Gender!=-1){
                    RadioButton selectedRadioButton = (RadioButton) findViewById(Gender);
                    String selectedRadioButtonText = selectedRadioButton.getText().toString();
                    Toast.makeText(Signupteacher.this, "Are You: "+selectedRadioButtonText, Toast.LENGTH_SHORT).show();
                    Addteachers();
                }

            }
        });
//        multiAutoCompleteTextView=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
       /* String[] languages={"Male ","Female"};
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,languages);
        multiAutoCompleteTextView.setAdapter(arrayAdapter);*/
       // String[]Languages=getResources().getStringArray(R.array.languages);
        //multiAutoCompleteTextView=(MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);
        /*ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Languages);
       multiAutoCompleteTextView.setAdapter(adapter);
        multiAutoCompleteTextView.setThreshold(1);
        multiAutoCompleteTextView.setAdapter(adapter);
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());*/
       /* ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_dropdown_item_1line, languages);
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.RED);*/
    }

    public void Addteachers(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                String query;
                String Sex =String.valueOf(Gender);
                Dbclass connectionClass = new Dbclass();
                Connection con = connectionClass.ConnectToDB();

                if (con == null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Signupteacher.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else
                {

                    query = "INSERT INTO teacher (tname,tage,tnationality,tperhourrate,tskypeid,languages,gender,tnumber,tEmail) VALUES(?,?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = null;
                    try{
                        preparedStatement = con.prepareStatement(query);
                        preparedStatement.setString(1,txtNamee.getText().toString());
                        preparedStatement.setString(2,txtAge.getText().toString());
                        preparedStatement.setString(3, txtnationality.getText().toString());
                        preparedStatement.setString(4, txtPerhourrate.getText().toString());
                        preparedStatement.setString(5, txtskypeid.getText().toString());
                        preparedStatement.setString(6, txtLanguage.getText().toString());
                        preparedStatement.setString(7, Sex);
                        preparedStatement.setString(8, txtLocation.getText().toString());//Location mean Number.
                        preparedStatement.setString(9, txtemAil.getText().toString());//Location mean Number.

                        preparedStatement.execute();

                    }
                    catch (final SQLException e){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Signupteacher.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
            }
        }).start();



    }

    public void GotoLoginTeacher(View view) {

        Intent intent = new Intent(this,LoginTeacher.class);
        startActivity(intent);
    }
}
