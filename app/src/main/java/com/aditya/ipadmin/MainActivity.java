package com.aditya.ipadmin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseRecyclerAdapter<Data,PostviewHolder> firebaseRecyclerAdapter;
    private Query userfEvents;
    private DatabaseReference mDatabase;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        FirebaseApp.initializeApp(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        userfEvents = mDatabase.child("Events");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_main);
        startFirebaseRecycler();
    }

    @Override
    protected void onStart() {
        super.onStart();
        userfEvents = mDatabase.child("Events");
        startFirebaseRecycler();
    }

    public void startFirebaseRecycler() {

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Data, PostviewHolder>(
                Data.class,
                R.layout.all_events_cards,
                PostviewHolder.class,
                userfEvents

        ) {
            @Override
            protected void populateViewHolder(final PostviewHolder viewHolder, Data model, final int position) {
                Log.d("Model", firebaseRecyclerAdapter.getRef(position).getKey());

                viewHolder.setTitle(model.getTitle());
                viewHolder.setdesc(model.getDescription());
                viewHolder.setelig(model.getEligibility());
                viewHolder.setdate(model.getDate() + " to " + model.getEndDate());
                viewHolder.setcity(model.getArea()+ " , " + model.getCity());
                viewHolder.setcontact(model.getContact());
                viewHolder.setType(model.getType());
                viewHolder.setPhone(model.getPhone());
                viewHolder.setPaymentMode(model.getPaymentMode());
                viewHolder.setPrice(model.price);
                final String eventKey = firebaseRecyclerAdapter.getRef(position).getKey();


                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                    }
                });

            }

            @Override
            public void onBindViewHolder(final PostviewHolder viewHolder, final int position) {
                super.onBindViewHolder(viewHolder, position);
                final Data obj = new Data();
                final String eventKey = firebaseRecyclerAdapter.getRef(position).getKey();
                DatabaseReference event = mDatabase.child("Events").child(eventKey);

                event.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Data value = dataSnapshot.getValue(Data.class);
                        if(value != null){
                            obj.date = value.date;
                            obj.title = value.title;
                            obj.description = value.description;
                            obj.city = value.city;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.Delete.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        /// button click event
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                        builder.setTitle("Confirm");
                        builder.setMessage("Are you sure?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing but close the dialog
                                firebaseRecyclerAdapter.getRef(position).removeValue();
                                dialog.dismiss();
                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // Do nothing
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public static class PostviewHolder extends RecyclerView.ViewHolder {
        View mview;
        ImageButton Delete;

        public PostviewHolder(View itemView) {
            super(itemView);
            mview = itemView;
            Delete = (ImageButton) itemView.findViewById(R.id.delete);

        }

        public void setTitle(String title) {
            TextView posttitle = (TextView) mview.findViewById(R.id.tit_rec);
            posttitle.setText(title);
        }

        public void setdesc(String desc) {
            TextView posttitle = (TextView) mview.findViewById(R.id.desc_rec);
            posttitle.setText(desc);
        }

        public void setelig(String elig) {
            TextView posttitle = (TextView) mview.findViewById(R.id.eligibility_rec);
            posttitle.setText("Work: " + elig);
        }

        public void setdate(String date) {
            TextView posttitle = (TextView) mview.findViewById(R.id.date_rec);
            posttitle.setText("Date: " + date);
        }

        public void setcontact(String contact) {
            TextView posttitle = (TextView) mview.findViewById(R.id.contact_rec);
            posttitle.setText("Email: " + contact);
        }

        public void setcity(String city) {
            TextView posttitle = (TextView) mview.findViewById(R.id.city);
            posttitle.setText("Location: " + city);
        }

        public void setType(String type) {
            TextView posttitle = (TextView) mview.findViewById(R.id.type);
            posttitle.setText("Category: " + type);
        }

        public void setPhone(String phone)
        {
            TextView phoneText = (TextView) mview.findViewById(R.id.phone_rec);
            phoneText.setText("Phone: "+phone);
        }

        public void setPaymentMode (String mode)
        {
            TextView modeText = (TextView) mview.findViewById(R.id.paymentMode_rec);
            modeText.setText("Payment: "+ mode);
        }
        public void setPrice(String price){
            TextView prce = (TextView) mview.findViewById(R.id.price);
            prce.setText("Price: "+price);
        }

    }
}
