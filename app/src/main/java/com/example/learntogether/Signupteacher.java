package com.example.learntogether;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import androidx.appcompat.app.AppCompatActivity;

public class Signupteacher extends AppCompatActivity {
    private static final int IMG_REQUEST = 1;
    AutoCompleteTextView multiAutoCompleteTextView;
    EditText txtLanguage, txtskypeid ,txtPerhourrate ,txtnationality ,txtLocation ,txtAge ,txtNamee,txtemAil;
    Button signupforteachers;
    ImageView showpic;
    Bitmap bitmap;
    ProgressDialog progressDialog;
    String selectedRadioButtonText;
   int Gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupteacher);

        progressDialog=new ProgressDialog(this);
        showpic=(ImageView)findViewById(R.id.showprofilepic);
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

                     selectedRadioButtonText = selectedRadioButton.getText().toString();
                    Log.d("SeeGenderhere", "onClick: here is Gender  "+selectedRadioButtonText);
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
                Log.d("Sex", "run: here is sex"+Sex);
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
                   // Toast.makeText(Signupteacher.this, "Please wait...", Toast.LENGTH_SHORT).show();

                   bitmap = ((BitmapDrawable) showpic.getDrawable()).getBitmap();
                    query = "INSERT INTO teacher (tname,tage,tnationality,tperhourrate,tskypeid,languages,gender,tnumber,tEmail,img) VALUES(?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = null;
                    try{
                        preparedStatement = con.prepareStatement(query);
                        preparedStatement.setString(1,txtNamee.getText().toString());
                        preparedStatement.setString(2,txtAge.getText().toString());
                        preparedStatement.setString(3, txtnationality.getText().toString());
                        preparedStatement.setString(4, txtPerhourrate.getText().toString());
                        preparedStatement.setString(5, txtskypeid.getText().toString());
                        preparedStatement.setString(6, txtLanguage.getText().toString());
                        preparedStatement.setString(7, selectedRadioButtonText);
                        preparedStatement.setString(8, txtLocation.getText().toString());//Location mean Number.
                        preparedStatement.setString(9, txtemAil.getText().toString());//Location mean Number.
                        preparedStatement.setString(10, imgetostring(bitmap));//profilepic
                        preparedStatement.execute();
                        Intent intent = new Intent(Signupteacher.this,MainActivity.class);
                        startActivity(intent);
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
    private String imgetostring(Bitmap bitmap) {

        Log.d("c", "imgetostring: ");
        //  Toast.makeText(this, "In Image to string....", Toast.LENGTH_SHORT).show();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        //String ss=  Base64.encodeToString(imgBytes, Base64.DEFAULT);
        return   Base64.encodeToString(imgBytes, Base64.DEFAULT);
        //Log.d("SS", "imgetostring: "+ss);
    }

    public void getgallerypic(View view) {
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), path);
                showpic.setImageBitmap(bitmap);
                Log.d("img", "onActivityResult: "+bitmap);
                showpic.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
