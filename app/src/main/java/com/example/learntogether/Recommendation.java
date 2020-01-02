package com.example.learntogether;

import android.app.ProgressDialog;
import android.content.Context;
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

public class Recommendation extends AppCompatActivity {

    TextView textView;
    Bitmap btmap;

    Blob b ;
    int blobLength ;
    byte[] blobAsBytes ;
    Bitmap btm;

    private ArrayList<RecommendTutorsfromCards> itemArrayList;  //List items Array
    private MyAppAdapter myAppAdapter; //Array Adapter
    private RecyclerView recyclerView; //RecyclerView
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean success = false; // boolean
    private Dbclass connectionClass; //Connection Class Variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewfortutorRecommended); //Recylcerview Declaration
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        connectionClass = new Dbclass(); // Connection Class Initialization
        itemArrayList = new ArrayList<RecommendTutorsfromCards>(); // Arraylist Initialization
        // Calling Async Task
        SyncData orderData = new SyncData();
        orderData.execute("");

    }



    private class SyncData extends AsyncTask<String, String, String>
    {
        String msg = "Internet/DB_Credentials/Windows_FireWall_TurnOn Error, See Android Monitor in the bottom For details!";
        ProgressDialog progress;
        @Override
        protected void onPreExecute() //Starts the progress dailog
        {
            progress = ProgressDialog.show(Recommendation.this, "Loading!",
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
                                itemArrayList.add(new RecommendTutorsfromCards(rs.getString("img"),rs.getString("tname"),rs.getString("tnationality"),
                                        rs.getString("gender"),rs.getString("languages"),rs.getString("Id"),rs.getString("tperhourrate")));
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
            Toast.makeText(Recommendation.this, msg + "", Toast.LENGTH_LONG).show();
            if (success == false)
            {
            }
            else {
                try
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            //myAppAdapter = new Message.MyAppAdapter(itemArrayList , Message.this);
                           myAppAdapter = new Recommendation.MyAppAdapter(itemArrayList,Recommendation.this);
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
        private List<RecommendTutorsfromCards> values;
        public Context context;
        public class ViewHolder extends RecyclerView.ViewHolder
        {
            // public image title and image url
            public TextView textName,textnation,Gender,Languages,Teaches,Rateperhour;
            public ImageView imageView;
            public View layout;
            public ViewHolder(View v)
            {
                super(v);
                layout = v;


                textName = (TextView) v.findViewById(R.id.textName);
                imageView = (ImageView) v.findViewById(R.id.imageView);
                textnation=(TextView)v.findViewById(R.id.Txtnation);

                Gender =(TextView)v.findViewById(R.id.Genderforrecomendedtutor);
                Languages=(TextView)v.findViewById(R.id.Languagesforrecomendedtutor);
                Teaches=(TextView)v.findViewById(R.id.Teachesforrecomendedtutor);
                Rateperhour=(TextView)v.findViewById(R.id.Perhourateforrecomendedtutor);
            }
        }
        // Constructor
        public MyAppAdapter(List<RecommendTutorsfromCards> myDataset, Context context)
        {
            values = myDataset;
            this.context = context;
        }

        // Create new views (invoked by the layout manager) and inflates
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            // create a new view
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.mylayout, parent, false);
            Recommendation.MyAppAdapter.ViewHolder vh = new Recommendation.MyAppAdapter.ViewHolder(v);
            return vh;
        }


        // Binding items to the view

        @Override
        public void onBindViewHolder(final Recommendation.MyAppAdapter.ViewHolder holder, final int position) {


            final RecommendTutorsfromCards recommendTutorsfromCards = values.get(position);
            holder.textName.setText(recommendTutorsfromCards.getName());
            holder.textnation.setText(recommendTutorsfromCards.getSkypeid());
            holder.imageView.setImageBitmap(recommendTutorsfromCards.getImg());
            //Setting Profile here
            holder.Gender.setText(recommendTutorsfromCards.getGender());
            holder.Rateperhour.setText(recommendTutorsfromCards.getRateperhour());
            holder.Languages.setText(recommendTutorsfromCards.getLanguages());
            holder.Teaches.setText(recommendTutorsfromCards.getTeaches());


            Toast.makeText(context, "Postion is : "+position, Toast.LENGTH_SHORT).show();
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int s=position+1;
                    Toast.makeText(context, ""+s, Toast.LENGTH_SHORT).show();
                    String Nmae=recommendTutorsfromCards.getName();
                    String Nationality=recommendTutorsfromCards.getSkypeid();
                    Log.d("pp", "onClick: "+recommendTutorsfromCards.getName());
                    Log.d("Nation", "onClick: "+recommendTutorsfromCards.getSkypeid());
                   // startActivity(intent);
                }
            });
        }

        // get item count returns the list item count
        @Override
        public int getItemCount() {
            return values.size();
        }

    }
}
