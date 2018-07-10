package com.developer.mabdullahk.mccwarehouse;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class updateInspector
  extends AppCompatActivity
{
  private static final int REQUEST_CODE = 111;
  private static final String TAG = "msg";
  private Button adduser;
  ArrayList<String> attendance;
  private Button back;
  boolean check;
  private DatabaseReference databaseInspectors;
  private Button delete;
  private FirebaseAuth firebaseAuth;
  Button getwarehouse;
  private Button logged;
  private EditText txtCnic;
  private EditText txtEmail;
  private EditText txtNamee;
  private EditText txtPhone;
  ArrayList<String> warehouse;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_update_inspector);
    txtEmail = ((EditText)findViewById(R.id.txtEmailSignup));
    txtNamee = ((EditText)findViewById(R.id.txtName));
    txtPhone = ((EditText)findViewById(R.id.txtPhone));
    txtCnic = ((EditText)findViewById(R.id.txtCnic));
    adduser = ((Button)findViewById(R.id.btnEmailSignup));
    back = ((Button)findViewById(R.id.btnback));
    delete = ((Button)findViewById(R.id.btndelete));
    firebaseAuth = FirebaseAuth.getInstance();
    databaseInspectors = FirebaseDatabase.getInstance().getReference("inspectors");
    logged = ((Button)findViewById(R.id.outlog));
    getwarehouse = ((Button)findViewById(R.id.warehousebtn));
    paramBundle = getIntent().getExtras();
    if (paramBundle != null) {
      warehouse = ((ArrayList)paramBundle.get("warehouses"));
    }
    getwarehouse.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        startActivityForResult(new Intent(updateInspector.this, warehouseListtwo.class), 111);
      }
    });
    logged.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(updateInspector.this, login.class));
      }
    });
    back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        startActivity(new Intent(updateInspector.this, adminpage.class));
      }
    });
    adduser.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        btnRegister_click1();
      }
    });
    delete.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        btndelete_click();
      }
    });
  }

  private void collectnames(DataSnapshot paramMap, String name, String phone, String cnic, String email, ArrayList<String> warehouse)
  {
    for (DataSnapshot noteDataSnapshot : paramMap.getChildren()) {
      System.out.println(noteDataSnapshot);
      inspector Inspector = noteDataSnapshot.getValue(inspector.class);

      System.out.println(email);
      System.out.println(Inspector.getEmail());
      if (Inspector.getEmail().equals(email)) {

//        if (email == Inspector.getEmail()) {
          if (!name.equals(Inspector.getName()) && !name.isEmpty() && !name.equals("")) {
            databaseInspectors.child(Inspector.getId()).child("name").setValue(name);
          }
          if (!phone.equals( Inspector.getPhone()) && !phone.isEmpty() && !phone.equals("")) {
            databaseInspectors.child(Inspector.getId()).child("phone").setValue(name);
          }
          if (!cnic.equals( Inspector.getCnic()) && !cnic.isEmpty() && !cnic.equals("")) {
            databaseInspectors.child(Inspector.getId()).child("cnic").setValue(name);
          }
          System.out.println(warehouse);
        if (!warehouse.isEmpty()) {
          databaseInspectors.child(Inspector.getId()).child("warehouse").setValue(warehouse);
        }
        Toast.makeText(getApplicationContext(), "Inspector Updated.", Toast.LENGTH_SHORT);
      }

    }


  }

  private void deletenames(DataSnapshot paramMap, String name, String phone, String cnic, String email)
  {
    for (DataSnapshot noteDataSnapshot : paramMap.getChildren()) {
      System.out.println(noteDataSnapshot);
      inspector Inspector = noteDataSnapshot.getValue(inspector.class);

      if (Inspector.getEmail().equals(email)) {
        databaseInspectors.child(Inspector.getId()).removeValue();
        sendSMS("+923424554488" , email);
      }

    }
  }

  public void btnRegister_click1()
  {
    final String name = txtNamee.getText().toString();
    final String phone = txtPhone.getText().toString();
    final String cnic = txtCnic.getText().toString();
    final String email = txtEmail.getText().toString();
      databaseInspectors.addListenerForSingleValueEvent(new ValueEventListener()
      {
        public void onCancelled(DatabaseError paramAnonymousDatabaseError) {}

        public void onDataChange(DataSnapshot paramAnonymousDataSnapshot)
        {
          collectnames(paramAnonymousDataSnapshot, name, phone, cnic, email, warehouse);
        }
      });
    txtNamee.setText("");
    txtPhone.setText("");
    txtCnic.setText("");
    txtEmail.setText("");
  }

  public void btndelete_click() {
      {
          final String name = txtNamee.getText().toString();
          final String phone = txtPhone.getText().toString();
          final String cnic = txtCnic.getText().toString();
          final String email = txtEmail.getText().toString();

          databaseInspectors.addListenerForSingleValueEvent(new ValueEventListener() {
              public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
              }

              public void onDataChange(DataSnapshot paramAnonymousDataSnapshot) {
                  deletenames(paramAnonymousDataSnapshot, name, phone, cnic, email);
              }
          });
          txtNamee.setText("");
          txtPhone.setText("");
          txtCnic.setText("");
          txtEmail.setText("");
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

 * Qualified Name:     com.developer.mabdullahk.mccwarehouse.updateInspector

 * JD-Core Version:    0.7.0.1

 */