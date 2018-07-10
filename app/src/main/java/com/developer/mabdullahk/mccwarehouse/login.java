package com.developer.mabdullahk.mccwarehouse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class login extends AppCompatActivity
{
    private static final int CAMERA_REQUEST_CODE = 111;
    private static final int SMS_REQUEST_CODE = 11;

    private FirebaseAuth firebaseAuth;
    private TextView forgot;
    ArrayList<String> items = new ArrayList<>();
    private Button loginbtn;
    private DatabaseReference mailchecker;
    private EditText passwordd;
    private EditText username;
    protected void onCreate(Bundle paramBundle) {
        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.CAMERA"}, CAMERA_REQUEST_CODE);
        }


        super.onCreate(paramBundle);
        setContentView(R.layout.activity_login);


        username = (EditText) findViewById(R.id.loginUsername);
        passwordd = (EditText) findViewById(R.id.loginPassword);
        loginbtn = (Button) findViewById(R.id.loginBtn);
        forgot = (TextView) findViewById(R.id.txtforgot);
        firebaseAuth = FirebaseAuth.getInstance();
        mailchecker = FirebaseDatabase.getInstance().getReference("adminmail");
        loginbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                btnRegister_click1();
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                startActivity(new Intent(login.this, forgotpassword.class));
            }
        });

    }

    public void btnRegister_click1()
    {
        mailchecker.addListenerForSingleValueEvent(new ValueEventListener()
        {

            public void onDataChange(DataSnapshot paramAnonymousDataSnapshot)
            {

                for (DataSnapshot noteDataSnapshot : paramAnonymousDataSnapshot.getChildren()) {
                    System.out.println(noteDataSnapshot);
                    items.add(noteDataSnapshot.getValue(String.class));
                }


                System.out.println(items);
                if ((!username.getText().toString().isEmpty()) && (!passwordd.getText().toString().isEmpty()))
                {
                    final ProgressDialog progressDialog = ProgressDialog.show(login.this, "Please wait...", "Processing...", true);




                    firebaseAuth.signInWithEmailAndPassword(username.getText().toString().trim(), passwordd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if ((task.isSuccessful()) && (!items.isEmpty()))
                            {                                    progressDialog.dismiss();

                                firebaseAuth.getCurrentUser();
                                if (items.contains(username.getText().toString().trim()))
                                {
                                    Toast.makeText(login.this, "User logged in...", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(login.this, adminpage.class));
                                    return;
                                }
                                Toast.makeText(login.this, "User logged in...", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(login.this, attendance.class));
                                return;
                            } else {
                                progressDialog.dismiss();

                                Log.e("error", task.getException().toString());
                            Toast.makeText(login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            username.setText("");
                            passwordd.setText("");
                            }

                        }
                    });


                    return;
                }
                Toast.makeText(login.this, "Complete the Empty Fields", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    }



