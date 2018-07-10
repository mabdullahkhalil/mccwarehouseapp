package com.developer.mabdullahk.mccwarehouse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class signUp extends AppCompatActivity
{
  private static final int REQUEST_CODE = 111;
  private static final int SMS_REQUEST_CODE = 1234;
  private static final String TAG = "mymsg";
  private Button adduser;
  ArrayList<String> attendance;
  private Button back;
  boolean check;
  private DatabaseReference databaseInspectors;
  private FirebaseAuth firebaseAuth;
  Button getwarehouse;
  private Button logged;
  private EditText txtCnic;
  private EditText txtEmail;
  private EditText txtNamee;
  private EditText txtPassword;
  private EditText txtPhone;
  ArrayList<String> warehouse;


    protected void onCreate(Bundle paramBundle)
    {
        if (ContextCompat.checkSelfPermission(this, "android.permission.SEND_SMS") != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { "android.permission.SEND_SMS" }, SMS_REQUEST_CODE);
        }
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_sign_up);
        txtEmail = ((EditText)findViewById(R.id.txtEmailSignup));
        txtPassword = ((EditText)findViewById(R.id.txtPasswordSignup));
        txtNamee = ((EditText)findViewById(R.id.txtName));
        txtPhone = ((EditText)findViewById(R.id.txtPhone));
        txtCnic = ((EditText)findViewById(R.id.txtCnic));
        adduser = ((Button)findViewById(R.id.btnEmailSignup));
        back = ((Button) findViewById(R.id.btnback));
        firebaseAuth = FirebaseAuth.getInstance();
        databaseInspectors = FirebaseDatabase.getInstance().getReference("inspectors");
        logged = ((Button)findViewById(R.id.outlog));
        getwarehouse = ((Button)findViewById(R.id.warehousebtn));
        paramBundle = getIntent().getExtras();
        if (paramBundle != null) {
            warehouse = ((ArrayList)paramBundle.get("warehouses"));
        }
        getwarehouse.setOnClickListener(new OnClickListener()
        {
            public void onClick(View view)
            {
                startActivityForResult(new Intent(signUp.this, warehouseListtwo.class), REQUEST_CODE);
            }
        });
        logged.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
//                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(signUp.this, login.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                startActivity(new Intent(signUp.this, adminpage.class));
            }
        });

        adduser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegister_click1();

            }
        });

    }


    public void btnRegister_click1()
  {
    final String name = txtNamee.getText().toString();
    final String phone = txtPhone.getText().toString();
    String cnic = txtCnic.getText().toString();
    final String email = txtEmail.getText().toString();
    final String password = txtPassword.getText().toString();



    if ((!TextUtils.isEmpty(name)) && (!TextUtils.isEmpty(phone)) && (!TextUtils.isEmpty(cnic)) && (!TextUtils.isEmpty(email)) && (!TextUtils.isEmpty(password)))
    {
      final ProgressDialog localProgressDialog = ProgressDialog.show(this, "Please wait...", "Processing...", true);

        (firebaseAuth.fetchProvidersForEmail(txtEmail.getText().toString())).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                check = !task.getResult().getProviders().isEmpty();

            }
        });




        final String id = databaseInspectors.push().getKey();

       final inspector Inspector = new inspector(name,phone,cnic,email,warehouse,attendance,id);



        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                localProgressDialog.dismiss();
                if (warehouse == null) {
                    Toast.makeText(signUp.this, "Add warehouses to continue !", Toast.LENGTH_SHORT).show();
                    return;

                } else if ((task.isSuccessful()) && (!check) && (warehouse != null)) {
                    databaseInspectors.child(id).setValue(Inspector);
                    Toast.makeText(signUp.this, "User added...", Toast.LENGTH_SHORT).show();
                    sendSMS("+92" + phone.substring(1), email + "\n Password: " + password);
                    txtNamee.setText("");
                    txtPhone.setText("");
                   txtCnic.setText("");
                    txtEmail.setText("");
                    txtPassword.setText("");
                    warehouse = null;
                    return;
                }else {
                    Log.e("error", task.getException().toString());
                    Toast.makeText(signUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }




            }
        });


    }

  }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("heleoweorjwejijerer");
        System.out.println(requestCode);
        System.out.println(resultCode);
//        System.out.println(REQUEST_OK);


        if(resultCode == RESULT_OK){
            warehouse = data.getStringArrayListExtra("warehouses");
            System.out.println(warehouse);
            System.out.println("hellowowowowowowowowowowowowow");
        }

    }


  public void sendSMS(String paramString1, String paramString2)
  {
      SmsManager.getDefault().sendTextMessage(paramString1, null, paramString2, null, null);
      Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_SHORT).show();
      return;

  }
}



/* Location:           Z:\Users\mabdullahk\Downloads\dex2jar-2.0\classes-dex2jar.jar

 * Qualified Name:     com.developer.mabdullahk.mccwarehouse.signUp

 * JD-Core Version:    0.7.0.1

 */