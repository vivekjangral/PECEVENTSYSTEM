package com.example.peceventsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class userinfo extends AppCompatActivity {

FirebaseUser firebaseUser;
FirebaseAuth firebaseAuth;
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
EditText st_name;
EditText st_sid;
EditText st_contact;
Spinner st_branch;
Spinner st_year;
Button st_data_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        st_name=findViewById(R.id.stu_name);
        st_sid=findViewById(R.id.stu_sid);
        st_contact=findViewById(R.id.stu_phonenumber);
        st_year=(Spinner)findViewById(R.id.stu_year);
        st_branch=(Spinner)findViewById(R.id.stu_branch);
        st_data_add=findViewById(R.id.stu_submit);


        st_data_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                firebaseAuth=FirebaseAuth.getInstance();
                firebaseUser=firebaseAuth.getCurrentUser();
                //String email=firebaseUser.getEmail();
                String name = st_name.getText().toString().trim();
                String sid = st_sid.getText().toString().trim();
                String contact = st_contact.getText().toString().trim();
                String year = st_year.getSelectedItem().toString();
                String branch = st_branch.getSelectedItem().toString();

                if(Integer.parseInt(year)==1 || Integer.parseInt(year)==2) {
                        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(sid) && !TextUtils.isEmpty(contact)){
                            databaseReference = FirebaseDatabase.getInstance().getReference("Userdata/Participant");
                        String key = databaseReference.push().getKey();
                        userinfoclass userinfoclass = new userinfoclass(key, name, "email", sid, branch, year, contact);
                        databaseReference.child(key).setValue(userinfoclass);
                        st_name.setText("");
                        st_sid.setText("");
                        st_contact.setText("");
                        Toast.makeText(getApplicationContext(),"Participant Details Added Successfully",Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Participant Details Not Added Successfully \n Please fill all Details",Toast.LENGTH_SHORT).show();
                        }

                }

                if(Integer.parseInt(year)==3 || Integer.parseInt(year)==4) {
                    if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(sid) && !TextUtils.isEmpty(contact)){
                    databaseReference=FirebaseDatabase.getInstance().getReference("Userdata/Organiser");
                    String key = databaseReference.push().getKey();
                    userinfoclass userinfoclass = new userinfoclass(key, name, "email", sid, branch, year, contact);
                    databaseReference.child(key).setValue(userinfoclass);
                    st_name.setText("");
                    st_sid.setText("");
                    st_contact.setText("");
                        Toast.makeText(getApplicationContext(),"Organiser Details Added Successfully",Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Organiser Details NOt Added Successfully \n Please fill all Details",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }



}
