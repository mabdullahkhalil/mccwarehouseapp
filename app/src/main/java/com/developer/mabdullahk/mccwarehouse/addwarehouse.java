package com.developer.mabdullahk.mccwarehouse;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addwarehouse
  extends AppCompatActivity
{
  private Button addwarehouse1;
  private Button back;
  private DatabaseReference databasewarehouse;
  private FirebaseAuth firebaseAuth;
  private Button logged;
  private EditText txtContact;
  private EditText txtNamee;
  private EditText txtPhone;
  private EditText txtfacesize;
  private EditText txtlocation;
  private EditText txtvalidity;

  public void btnRegister_click1()
  {
    String name = txtNamee.getText().toString();
    String phone = txtPhone.getText().toString();
    String location = txtlocation.getText().toString();
    String contact = txtContact.getText().toString();
    String validity = txtvalidity.getText().toString();
    String facesize = txtfacesize.getText().toString();
    if ((!TextUtils.isEmpty(name)) && (!TextUtils.isEmpty(phone)) && (!TextUtils.isEmpty(location)) && (!TextUtils.isEmpty(contact)) && (!TextUtils.isEmpty(validity)) && (!TextUtils.isEmpty(facesize)))
    {
      ProgressDialog localProgressDialog = ProgressDialog.show(this, "Please wait...", "Processing...", true);
      String id = databasewarehouse.push().getKey();
      warehouseAdd warehouse = new warehouseAdd(name, location, contact, phone, id, validity, facesize);
      databasewarehouse.child(id).setValue(warehouse);
      localProgressDialog.dismiss();
      Toast.makeText(this, "Warehouse added...", Toast.LENGTH_SHORT).show();
    }
    txtlocation.setText("");
    txtNamee.setText("");
    txtPhone.setText("");
    txtContact.setText("");
    txtvalidity.setText("");
    txtfacesize.setText("");
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_addwarehouse);


    txtlocation = ((EditText)findViewById(R.id.txtlocation));
    txtNamee = ((EditText)findViewById(R.id.txtwarehouseName));
    txtPhone = ((EditText)findViewById(R.id.txtcontactperson));
    txtContact = ((EditText)findViewById(R.id.txtcontactPhone));
    txtvalidity = ((EditText)findViewById(R.id.txtvalidity));
    txtfacesize = ((EditText)findViewById(R.id.txtface));
    addwarehouse1 = ((Button)findViewById(R.id.btnaddingwarehouse));
    logged = ((Button)findViewById(R.id.outlog));
    back = ((Button)findViewById(R.id.btnback));
    firebaseAuth = FirebaseAuth.getInstance();
    databasewarehouse = FirebaseDatabase.getInstance().getReference("warehouses");
    logged.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(addwarehouse.this, login.class));
      }
    });
    back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        startActivity(new Intent(addwarehouse.this, adminpage.class));
      }
    });
    addwarehouse1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        btnRegister_click1();
      }
    });
  }
}



/* Location:           Z:\Users\mabdullahk\Downloads\dex2jar-2.0\classes-dex2jar.jar

 * Qualified Name:     com.developer.mabdullahk.mccwarehouse.addwarehouse

 * JD-Core Version:    0.7.0.1

 */