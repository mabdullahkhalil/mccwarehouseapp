package com.developer.mabdullahk.mccwarehouse;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class displayinfowarehouse
  extends AppCompatActivity
{
  Button back;
  TextView contactperson;
  private DatabaseReference databasewarehouse;
  TextView dataname;
  TextView facesize;
  List<String> items = new ArrayList();
  TextView location;
  Button logged;
  String name;
  TextView phonenumber;
  ArrayList<String> selectedItems;
  String nameofinspector;
  String returnName;
  TextView validity;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_displayinfowarehouse);
    selectedItems = new ArrayList();
    databasewarehouse = FirebaseDatabase.getInstance().getReference("warehouses");
    dataname = ((TextView)findViewById(R.id.name));
    location = ((TextView)findViewById(R.id.locat));
    contactperson = ((TextView)findViewById(R.id.contacperson));
    phonenumber = ((TextView)findViewById(R.id.phone));
    validity = ((TextView)findViewById(R.id.validity));
    facesize = ((TextView)findViewById(R.id.facesize));
    logged = ((Button)findViewById(R.id.outlog));
    back = ((Button)findViewById(R.id.btnback));
    paramBundle = getIntent().getExtras();
    if (paramBundle != null)
    {
      Object things = paramBundle.get("inspectors");
      System.out.println(things);
      List<String> all = Arrays.asList(((String) things).split("/"));
      if (all.size() >2){
          nameofinspector = all.get(2);
      }

      name = all.get(1);
      returnName= all.get(0);
      dataname.setText(name.toUpperCase().toString());
    }
    logged.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(displayinfowarehouse.this,login.class));

      }
    });
    back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {

        try {

            if (returnName.equals("displayinfo")){
                Intent intent = new Intent(displayinfowarehouse.this, Class.forName(getPackageName()+"."+returnName));
                intent.putExtra("inspectors", nameofinspector);
                startActivity(intent);

            }else {


                startActivity(new Intent(displayinfowarehouse.this, Class.forName(getPackageName() + "." + returnName)));
            }

        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }

      }
    });
  }

  public void onStart()
  {
    super.onStart();
    databasewarehouse.addListenerForSingleValueEvent(new ValueEventListener()
    {
      public void onCancelled(DatabaseError paramAnonymousDatabaseError) {}

      public void onDataChange(DataSnapshot paramAnonymousDataSnapshot)
      {

          for (DataSnapshot noteDataSnapshot : paramAnonymousDataSnapshot.getChildren()) {
              warehouseAdd warehouse_add = noteDataSnapshot.getValue(warehouseAdd.class);
            System.out.println(warehouse_add.getName());
            System.out.println(name);

              if (name.equals(warehouse_add.getName())) {

                  location.setText((String) warehouse_add.getLocation());
                  contactperson.setText(warehouse_add.getContactperson());
                  phonenumber.setText(warehouse_add.getPhone());
                  validity.setText(warehouse_add.getValidity());
                  facesize.setText(warehouse_add.getFacesize());
              }
          }

      }
    });
  }
}



/* Location:           Z:\Users\mabdullahk\Downloads\dex2jar-2.0\classes-dex2jar.jar

 * Qualified Name:     com.developer.mabdullahk.mccwarehouse.displayinfowarehouse

 * JD-Core Version:    0.7.0.1

 */