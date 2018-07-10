package com.developer.mabdullahk.mccwarehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class updateWarehouse
  extends AppCompatActivity
{
  private Button addwarehouse1;
  private Button back;
  private DatabaseReference databasewarehouse;
  private Button delete;
  private FirebaseAuth firebaseAuth;
  private Button logged;
  private EditText txtContact;
  private EditText txtNamee;
  private EditText txtPhone;
  private EditText txtfacesize;
  private EditText txtlocation;
  private EditText txtvalidity;

  private void collectnames(DataSnapshot paramMap, String name,String phone,String location,String contact,String validity,String face)
  {
      for (DataSnapshot noteDataSnapshot : paramMap.getChildren()) {
          System.out.println(noteDataSnapshot);
          warehouseAdd warehouse_add = noteDataSnapshot.getValue(warehouseAdd.class);
          System.out.println(warehouse_add.getName());

          if (name.equals(warehouse_add.getName())) {

              System.out.println(name);
//              warehouseAdd nwarehouse = new warehouseAdd()
              if (!phone.equals(warehouse_add.getPhone()) && !phone.isEmpty() && !phone.equals("")) {
                  databasewarehouse.child(warehouse_add.getId()).child("phone").setValue(phone);
              }
              if (!face.equals(warehouse_add.getFacesize()) && !face.isEmpty() && !face.equals("")) {
                  databasewarehouse.child(warehouse_add.getId()).child("facesize").setValue(face);
              }
              if (!validity.equals(warehouse_add.getValidity()) && !validity.isEmpty() && !validity.equals("")) {
                  databasewarehouse.child(warehouse_add.getId()).child("validity").setValue(validity);
              }
              if (!location.equals(warehouse_add.getLocation()) && !location.isEmpty() && !location.equals("")) {
                  databasewarehouse.child(warehouse_add.getId()).child("location").setValue(location);
              }
              if (!contact.equals(warehouse_add.getContactperson()) && !contact.isEmpty() && !contact.equals("")) {
                  databasewarehouse.child(warehouse_add.getId()).child("contactperson").setValue(contact);
              }

              Toast.makeText(this, "Warehouse updated",Toast.LENGTH_LONG).show();


          }
      }
  }

  private void deletenames(DataSnapshot paramMap, String paramString1, String paramString2, String paramString3, String paramString4)
  {
      for (DataSnapshot noteDataSnapshot : paramMap.getChildren()) {
          System.out.println(noteDataSnapshot);
          warehouseAdd warehouse_add = noteDataSnapshot.getValue(warehouseAdd.class);

          if (paramString1.equals(warehouse_add.getName())) {
              databasewarehouse.child(warehouse_add.getId()).removeValue();
              Toast.makeText(this, "warehouse deleted",Toast.LENGTH_LONG).show();

          }

      }
  }

  public void btnRegister_click1()
  {
    final String name = txtNamee.getText().toString();
    final String phone = txtPhone.getText().toString();
    final String location = txtlocation.getText().toString();
    final String contact = txtContact.getText().toString();
    final String validity = txtvalidity.getText().toString();
    final String face = txtfacesize.getText().toString();
    databasewarehouse.addListenerForSingleValueEvent(new ValueEventListener()
    {
      public void onCancelled(DatabaseError paramAnonymousDatabaseError) {}

      public void onDataChange(DataSnapshot paramAnonymousDataSnapshot)
      {
        collectnames(paramAnonymousDataSnapshot, name, phone, location, contact, validity, face);
      }
    });
    txtNamee.setText(null);
    txtPhone.setText(null);
    txtlocation.setText(null);
    txtContact.setText(null);
    txtvalidity.setText(null);
    txtfacesize.setText(null);
  }

  public void btndelete_click()
  {
      final String name = txtNamee.getText().toString();
      final String phone = txtPhone.getText().toString();
      final String location = txtlocation.getText().toString();
      final String contact = txtContact.getText().toString();
      final String validity = txtvalidity.getText().toString();
      final String face = txtfacesize.getText().toString();
      
    databasewarehouse.addListenerForSingleValueEvent(new ValueEventListener()
    {
      public void onCancelled(DatabaseError paramAnonymousDatabaseError) {}

      public void onDataChange(DataSnapshot paramAnonymousDataSnapshot)
      {
        deletenames(paramAnonymousDataSnapshot, name, phone, location, contact);
      }
    });
    txtNamee.setText(null);
    txtPhone.setText(null);
    txtlocation.setText(null);
    txtContact.setText(null);
    txtvalidity.setText(null);
    txtfacesize.setText(null);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_update_warehouse);

    txtlocation = ((EditText)findViewById(R.id.txtlocation));
    txtNamee = ((EditText)findViewById(R.id.txtwarehouseName));
    txtPhone = ((EditText)findViewById(R.id.txtcontactPhone));
    txtContact = ((EditText)findViewById(R.id.txtcontactperson));
    txtvalidity = ((EditText)findViewById(R.id.txtvalid));
    txtfacesize = ((EditText)findViewById(R.id.txtfacesize));
    addwarehouse1 = ((Button)findViewById(R.id.btnwarehouseUpdate));
    logged = ((Button)findViewById(R.id.outlog));
    back = ((Button)findViewById(R.id.btnback));
    delete = ((Button)findViewById(R.id.btndelete));
    firebaseAuth = FirebaseAuth.getInstance();
    databasewarehouse = FirebaseDatabase.getInstance().getReference("warehouses");
    logged.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(updateWarehouse.this, login.class));
      }
    });
    back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        startActivity(new Intent(updateWarehouse.this, adminpage.class));
      }
    });
    addwarehouse1.setOnClickListener(new View.OnClickListener()
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
}



/* Location:           Z:\Users\mabdullahk\Downloads\dex2jar-2.0\classes-dex2jar.jar

 * Qualified Name:     com.developer.mabdullahk.mccwarehouse.updateWarehouse

 * JD-Core Version:    0.7.0.1

 */