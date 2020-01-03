package com.example.learntogether;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learntogether.Model.MessageAdapter;
import com.example.learntogether.Model.Messagefromfirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class chats extends AppCompatActivity {

    EditText ed;
    TextView show;
    String mybal, gettingvaluefromchats;
    String Message;
    private DatabaseReference myRef;
    private RecyclerView MrecyclerView;
    private MessageAdapter messageAdapter;
    private List<Messagefromfirebase> mDatalist;
    String Tutor, Nation, Mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        initialview();
        ed = (EditText) findViewById(R.id.entervalues);
        gettingvaluefromchats = null;
        // show=(TextView)findViewById(R.id.shownmae);
        mDatalist = new ArrayList<>();

        MrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        messageAdapter = new MessageAdapter(this, mDatalist);
        MrecyclerView.setAdapter(messageAdapter);

        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.FILE_NAME, MODE_PRIVATE);

        Tutor = getIntent().getStringExtra("NNAME");
        gettingvaluefromchats = getIntent().getStringExtra("tutorId");
        Log.d("SEEINVITEFROM", "onCreate: here is Invite msg " + gettingvaluefromchats);
        Nation = getIntent().getStringExtra("NN");
        // Mobile=getIntent().getStringExtra("PHONE");
        Mobile = sharedPreferences.getString("PHONE", "");


        Toast.makeText(this, "Mobiles" + Mobile, Toast.LENGTH_SHORT).show();
        Log.d("NaveedNumber", "onCreate: " + Mobile);
        //  show.setText(Mobile);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("UsersChat");
    }

    public void sendchatsTofirebase(View view) {

        if (gettingvaluefromchats == null) {
            Message = ed.getText().toString();
            String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            String DatandTime = currentTime + ":" + currentDateTimeString;
            Log.d("TIME", "READDATAFROMDATABASE: " + DatandTime);
            Log.d("SEEMANE", "HERE IS TIME : " + currentDateTimeString + "\n" + currentTime);
            String mykey = myRef.push().getKey();
            Map<String, Object> insertvalues = new HashMap<>();
            insertvalues.put("Tutor", Tutor);
            insertvalues.put("Time", DatandTime);
            insertvalues.put("Message", Message);
            myRef.child(mykey).setValue(insertvalues);
            Toast.makeText(this, "Message Sent...", Toast.LENGTH_SHORT).show();
      /*  myRef.child(mykey).child("Tutor").setValue(Name);
        myRef.child(mykey).child("Time").setValue(DatandTime);
        myRef.child(mykey).child("Message").setValue(Chtmesg);
        */
            ed.getText().clear();
            ReadFormDAtaBase();
        } else {
            SendmsgtoinviteTutor();
        }

    }

    public void SendmsgtoinviteTutor() {
        Message = ed.getText().toString();
        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        String DatandTime = currentTime + ":" + currentDateTimeString;
        Log.d("TIME", "READDATAFROMDATABASE: " + DatandTime);
        Log.d("SEEMANE", "HERE IS TIME : " + currentDateTimeString + "\n" + currentTime);
        String mykey = myRef.push().getKey();
        Map<String, Object> insertvalues = new HashMap<>();
        insertvalues.put("Tutor", gettingvaluefromchats);
        insertvalues.put("Time", DatandTime);
        insertvalues.put("Message", Message);
        myRef.child(mykey).setValue(insertvalues);
        ed.getText().clear();
        ReadFormDAtaBase();
    }

    public void ReadFormDAtaBase() {
        Messagefromfirebase messagesfromfire = new Messagefromfirebase(Message, Tutor);
        


       /* myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot napshot1 : dataSnapshot.getChildren())
                {
                    Map<String,Object> data = (Map<String, Object>) napshot1.getValue();
                    Log.d("Tutor", "\nTutor Nmae : "+data.get("Tutor")+"        Message : "+data.get("Message")+"\n Key is : "+napshot1.getKey());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

    }

    public void initialview() {
        MrecyclerView = findViewById(R.id.messages_view);
    }
}
