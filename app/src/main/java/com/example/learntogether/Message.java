package com.example.learntogether;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Message extends AppCompatActivity {
TextView textView;
 Bitmap btmap;

    Blob b ;
    int blobLength ;
    byte[] blobAsBytes ;
    Bitmap btm;

    private ArrayList<ClassListItems> itemArrayList;  //List items Array
    private MyAppAdapter myAppAdapter; //Array Adapter
    private RecyclerView recyclerView; //RecyclerView
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean success = false; // boolean
    private Dbclass connectionClass; //Connection Class Variable
public static final String From_CHANNEL_ID="FROM_CHANNEL_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView); //Recylcerview Declaration
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        connectionClass = new Dbclass(); // Connection Class Initialization
        itemArrayList = new ArrayList<ClassListItems>(); // Arraylist Initialization
        // Calling Async Task
        SyncData orderData = new SyncData();
        orderData.execute("");
    }

    // Async Task has three overrided methods,
    private class SyncData extends AsyncTask<String, String, String>
    {
        String msg = "Internet/DB_Credentials/Windows_FireWall_TurnOn Error, See Android Monitor in the bottom For details!";
        ProgressDialog progress;
        @Override
        protected void onPreExecute() //Starts the progress dailog
        {
            progress = ProgressDialog.show(Message.this, "Loading!",
                    " Please Wait...", true);
        }

        @Override
        protected String doInBackground(String... strings)  // Connect to the database, write query and add items to array list
        {
            try
            {
                Connection conn = connectionClass.ConnectToDB(); //Connection Object
                if (conn == null)
                {
                    success = false;
                }
                else {
                    // Change below query according to your own database.
                    String query = "select * from teacher ";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    if (rs != null) // if resultset not null, I add items to itemArraylist using class created
                    {
                        while (rs.next())
                        {
                            try {
                                itemArrayList.add(new ClassListItems(rs.getString("tname"),rs.getString("img"),rs.getString("tnationality")));
                                //Blob blob =rs.getBlob("Img");
                                 //Blob b =rs.getBlob("img");
                                Log.d("Sehere", "doInBackground: \n Name :"+rs.getString("tname")+"\n: Imgaes :"+rs.getString("img")+"\n: Nationality :"+rs.getString("tnationality"));
                             /*   Log.d("INLOGDMETHOD", "doInBackground: ");
                                Log.d("II", "doInBackground: ");
                                blobLength = (int) b.length();
                                 blobAsBytes = b.getBytes(2,blobLength);
                                 btm = BitmapFactory.decodeByteArray(blobAsBytes,0,blobAsBytes.length);
                                Log.d("bloblength", "doInBackground: "+blobLength);*/




                                /*ByteArrayOutputStream boas = new ByteArrayOutputStream();
                                btmap.compress(Bitmap.CompressFormat.JPEG, 100, boas ); //bm is the bitmap object
                                byte[] byteArrayImage = boas .toByteArray();
                                String encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);

                                Log.d("SeeBlobher", "doInBackground: "+encodedImage);
                                Log.d("Sehere", "doInBackground: \n Name :"+rs.getString("tname")+"\n: Imgaes :"+rs.getBlob("Img")+"\n: Nationality :"+rs.getString("tnationality"));
                                Log.d("while", "In While Lppp: ");
*/
                            } catch (Exception ex) {

                                ex.printStackTrace();
                            }
                        }

                        msg = "Found";

                        success = true;
                    } else {

                        msg = "No Data found!";

                        success = false;
                    }
                }
            } catch (Exception e)
            {
                e.printStackTrace();

                Writer writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                msg = writer.toString();
                success = false;
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) // disimissing progress dialoge, showing error and setting up my listview
        {
            progress.dismiss();
            Toast.makeText(Message.this, msg + "", Toast.LENGTH_LONG).show();
            if (success == false)
            {
            }
            else {
                try
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            myAppAdapter = new MyAppAdapter(itemArrayList , Message.this);
                            Log.d("adapter", "onPostExecute: "+myAppAdapter);
                            recyclerView.setAdapter(myAppAdapter);
                        }
                    });
                    if(myAppAdapter==null){
                        Log.d("See99", "Error Adapter Null: ");
                    }

                } catch (Exception ex)
                {


                }

            }
        }
    }

    public class MyAppAdapter extends RecyclerView.Adapter<MyAppAdapter.ViewHolder> {
        private List<ClassListItems> values;
        public Context context;
        public class ViewHolder extends RecyclerView.ViewHolder
        {
            // public image title and image url
            public TextView textName,textnation;
            public ImageView imageView;
            public View layout;
            public ViewHolder(View v)
            {
                super(v);
                layout = v;
                textName = (TextView) v.findViewById(R.id.textName);
                imageView = (ImageView) v.findViewById(R.id.imageView);
                textnation=(TextView)v.findViewById(R.id.Txtnation);
            }
        }
        // Constructor
        public MyAppAdapter(List<ClassListItems> myDataset, Context context)
        {
            values = myDataset;
            this.context = context;
        }

        // Create new views (invoked by the layout manager) and inflates
        @Override
        public MyAppAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            // create a new view
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.list_content_for_tutors, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Binding items to the view

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {


            final ClassListItems classListItems = values.get(position);
            holder.textName.setText(classListItems.getName());
//            Log.d("SEENAME", "onBindViewHolder: "+classListItems.getSkypeid());
            holder.textnation.setText(classListItems.getSkypeid());
  //          Log.d("SeeSkype", "onBindViewHolder: "+classListItems.getSkypeid());
           holder.imageView.setImageBitmap(classListItems.getImg());
    //        Log.d("Picdture", "onBindViewHolder: "+btm);
            Toast.makeText(context, "Postion is : "+position, Toast.LENGTH_SHORT).show();
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int s=position+1;
                    Toast.makeText(context, ""+s, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Message.this,chats.class);
                   String Nmae=classListItems.getName();
                   String Nationality=classListItems.getSkypeid();
                    intent.putExtra("NNAME",classListItems.getName());
                    intent.putExtra("NN",classListItems.getSkypeid());
                    Log.d("pp", "onClick: "+classListItems.getName());
                    Log.d("Nation", "onClick: "+classListItems.getSkypeid());
                   startActivity(intent);
                }
            });
            // Picasso.get().load("http://192.168.10.6/phpmyadmin/sql.php?server=1&db=centerofexecellence&table=teacher&pos=0"+classListItems.getImg()).into(holder.imageView);


            //String url="https://debrecentoday.com/wp-content/uploads/2019/11/picasso-painting-exhibiton-Oradea-Romania.jpg";
            //Picasso.get().load(url).into(holder.imageView);
            //Picasso.get().load("https://"+classListItems.getImg()).into(holder.imageView);
            //Picasso.get().load("http://"+(classListItems.getImg())).into(holder.imageView);
           // Picasso.with(context).load(Uri.fromFile(new File(classListItems.getImg()))).into(holder.imageView);
           // Picasso.with(context).load("http://"+classListItems.getImg()).into(holder.imageView);

        }

        // get item count returns the list item count
        @Override
        public int getItemCount() {
            return values.size();
        }

    }
}
       // textView=(TextView)findViewById(R.id.showtoken);

/*
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel fromchannel = new NotificationChannel(
                   From_CHANNEL_ID,"FCM_CHANNEL",NotificationManager.IMPORTANCE_HIGH
            );
            NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(fromchannel);
        }
*/

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1);
       /* NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("EDU","MyNotiy",NotificationManager.IMPORTANCE_DEFAULT);
                //NotificationManager manager = getSystemServiceName(NotificationManager.class);
               // Notification manager = getSystemService(Notification.class);
                //manager.notify();
                manager.createNotificationChannel(channel);

            }
        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>(){

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg= "Successfull";
                        if(!task.isSuccessful()){
                            msg="Failed";
                        }
                        Toast.makeText(Message.this, ""+msg, Toast.LENGTH_SHORT).show();
                    }
                });
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Message.this, "Token : "+task.toString(), Toast.LENGTH_SHORT).show();
                            Log.d("TOKEN", "TOKE IS HERH>>>>: "+task);
                            textView.setText(task.toString());
                        }
                        else{
                            Toast.makeText(Message.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            Log.d("EXE", "Exception is her >>>>: "+task.getException());
                        }

                    }
                });*/

            /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel=
                        new NotificationChannel("MyNotification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);

        NotificationManager manager = new (NotificationManager)getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);*/

         /*  Notification notification = builer.getNotification();//将Builder对象转变成普通的notification
        startForeground(1, notification);*/




