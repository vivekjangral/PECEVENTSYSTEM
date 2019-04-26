package com.example.peceventsystem;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class eventinfo extends AppCompatActivity {

    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReferenceevent;
    static final String TAG = "eventinfo";
    TextView et_date;
    EditText et_name;
    EditText et_or1_name;
    EditText et_or2_name;
    EditText et_or1_contact;
    EditText et_or2_contact;
    EditText et_venue;
    Spinner et_club_society;
    Button et_submit;



    DatePickerDialog.OnDateSetListener listenerevent_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventinfo);

        et_date=findViewById(R.id.event_date);
        et_name=findViewById(R.id.event_name);
        et_or1_name=findViewById(R.id.event_or1);
        et_or2_name=findViewById(R.id.event_or2);
        et_or1_contact=findViewById(R.id.event_or1_contact);
        et_or2_contact=findViewById(R.id.event_or2_contact);
        et_venue=findViewById(R.id.event_venue);
        et_club_society=findViewById(R.id.event_club_society);
        et_submit=findViewById(R.id.event_submit);




        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(eventinfo.this, listenerevent_date,year,month,day);
                dialog.getWindow();
                dialog.show();


            }
        });

        listenerevent_date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                Log.d(TAG,"onDateset: mm/dd/yy" + month + "/" + dayOfMonth +"/" +year);
                String date = month +"/"+dayOfMonth+"/"+year;


                et_date.setText(date);
            }
        };


        et_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth=FirebaseAuth.getInstance();
                firebaseUser=firebaseAuth.getCurrentUser();

                String name = et_name.getText().toString().trim();
                String Club_society = et_club_society.getSelectedItem().toString();
                String venue = et_venue.getText().toString().trim();
                String or1name = et_or1_name.getText().toString().trim();
                String or1contact = et_or1_contact.getText().toString().trim();
                String or2name = et_or2_name.getText().toString().trim();
                String or2contact = et_or2_contact.getText().toString().trim();
                String etdate = et_date.getText().toString().trim();


                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(venue) && !TextUtils.isEmpty(etdate)
                            && !TextUtils.isEmpty(or1name) && !TextUtils.isEmpty(or1contact))
                {
                    if(Club_society.contentEquals("choose"))
                    {
                       Toast.makeText(getApplicationContext(),"Please select Club/Society ",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        databaseReferenceevent = FirebaseDatabase.getInstance().getReference("Event/" + Club_society);
                        String key = databaseReferenceevent.push().getKey();
                        eventinfoclass eventinfoclass = new eventinfoclass(key, name, Club_society, etdate, venue, or1name, or1contact, or2name, or2contact);
                        databaseReferenceevent.child(key).setValue(eventinfoclass);
                        et_name.setText("");
                        et_venue.setText("");
                        et_or1_name.setText("");
                        et_or2_name.setText("");
                        et_or1_contact.setText("");
                        et_or2_contact.setText("");
                        et_date.setText("Click here to enter date");
                        Toast.makeText(getApplicationContext(), "Event details Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                        Toast.makeText(getApplicationContext(), "Event fill all the details details ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
