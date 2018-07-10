package com.developer.mabdullahk.mccwarehouse;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;

public class adminpage
  extends AppCompatActivity
{
  Button addInspector;
  Button addwarehouse;
  Button back;
  private FirebaseAuth firebaseAuth;
  Button logged;
  Button updateInspector;
  Button updatewarehouse;
  Button viewInspector;
  Button viewWarehouse;


  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_adminpage);
    this.viewInspector = ((Button)findViewById(R.id.btnviewinspec));
    this.viewWarehouse = ((Button)findViewById(R.id.btnviewwarehouse));
    this.addInspector = ((Button)findViewById(R.id.btnaddinspec));
    this.addwarehouse = ((Button)findViewById(R.id.btnwarehosue));
    this.updatewarehouse = ((Button)findViewById(R.id.btnupdatewarehouse));
    this.updateInspector = ((Button)findViewById(R.id.btnupdateinspec));
    this.logged = ((Button)findViewById(R.id.btnlogout));
    this.back = ((Button)findViewById(R.id.btnback));

    this.firebaseAuth = FirebaseAuth.getInstance();
    this.addInspector.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        adminpage.this.startActivity(new Intent(adminpage.this, signUp.class));
      }
    });
    this.viewInspector.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        adminpage.this.startActivity(new Intent(adminpage.this, chooseInspec.class));
      }
    });
    this.viewWarehouse.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        adminpage.this.startActivity(new Intent(adminpage.this, chooseWarehouse.class));
      }
    });
    this.logged.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {

            FirebaseAuth.getInstance().signOut();
            adminpage.this.startActivity(new Intent(adminpage.this, login.class));
            adminpage.this.finish();

      }
    });
    this.back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {

            FirebaseAuth.getInstance().signOut();
            adminpage.this.startActivity(new Intent(adminpage.this, login.class));
            adminpage.this.finish();

      }
    });
    this.addwarehouse.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        adminpage.this.startActivity(new Intent(adminpage.this, addwarehouse.class));
      }
    });
    this.updateInspector.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        adminpage.this.startActivity(new Intent(adminpage.this, updateInspector.class));
      }
    });
    this.updatewarehouse.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        adminpage.this.startActivity(new Intent(adminpage.this, updateWarehouse.class));
      }
    });
  }
}



/* Location:           Z:\Users\mabdullahk\Downloads\dex2jar-2.0\classes-dex2jar.jar

 * Qualified Name:     com.developer.mabdullahk.mccwarehouse.adminpage

 * JD-Core Version:    0.7.0.1

 */