package com.developer.mabdullahk.mccwarehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class displayinfo
  extends AppCompatActivity
{
  Button back;
  private DatabaseReference databasewarehouse;
  TextView dataname;
  List<String> items = new ArrayList();
  Button logged;
  Button attendancebtn;
  Button warehousebtn;
  String name;
  ArrayList<String> selectedItems;
  ArrayList<String> warehouses= new ArrayList<String>();
  Spinner spinner;




    private void collectnames(DataSnapshot paramMap, String name) throws ParseException {

      for (DataSnapshot noteDataSnapshot : paramMap.getChildren()) {
          System.out.println(noteDataSnapshot);
          inspector Inspector = noteDataSnapshot.getValue(inspector.class);

          if (Inspector.getName().equals(name)) {


                if (Inspector.getAttendance() != null) {

                    ArrayList<String> attendance = Inspector.getAttendance();

                    for (String att : attendance) {
                        System.out.println(att);
                        List<String> all = Arrays.asList(att.split("_"));
                        String time = all.get(1);

                        String locate = all.get(0);
                        Date timee = new SimpleDateFormat("yyyyMMdd_HHmmss").parse(time + "_" + all.get(2));
                        items.add(locate + " on " + timee);
                    }

                } else {
                    items.add("Nothing to Display Yet!");

                }
              return;
          }
      }
  }


  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_displayinfo);
    selectedItems = new ArrayList();
    databasewarehouse = FirebaseDatabase.getInstance().getReference("inspectors");
    dataname = ((TextView)findViewById(R.id.name));
    logged = ((Button)findViewById(R.id.outlog));
    back = ((Button)findViewById(R.id.btnback));
    attendancebtn = ((Button)findViewById(R.id.attendancebtn));
    warehousebtn = ((Button)findViewById(R.id.warehousebtn));
    spinner= (Spinner) findViewById(R.id.spinner);
    paramBundle = getIntent().getExtras();

    if (paramBundle != null)
    {
        Object things = paramBundle.get("inspectors");
        System.out.println(things);
        name = ((String) things);
        dataname.setText(name.toUpperCase().toString());
    }
    logged.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(displayinfo.this, login.class));

      }
    });
    back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(displayinfo.this, chooseInspec.class));

      }
    });

    attendancebtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name= spinner.getSelectedItem().toString();


            if (items.get(0).equals("Nothing to Display Yet!")){
                ListView listView = (ListView) findViewById(R.id.checkable_list);
                listView.setChoiceMode(1);
                System.out.println(items.toString());
                listView.setAdapter(new ArrayAdapter(displayinfo.this, R.layout.simple_list_item, items));

            } else{
                List<String> allitems= new ArrayList<>();
                for (String item: items){
                    List<String> all = Arrays.asList(item.split(" on "));
                    if (name.equals(all.get(0))){
                        System.out.println(item);
                        allitems.add(item);
                    }
                }

                ListView listView = (ListView) findViewById(R.id.checkable_list);
              listView.setChoiceMode(1);
              System.out.println(items.toString());
                List<String> itemm = new ArrayList<>() ;

                if (allitems == null || allitems.size() == 0){
                  itemm.add("Nothing to display yet");
              listView.setAdapter(new ArrayAdapter(displayinfo.this, R.layout.simple_list_item, itemm));
              } else {
                  listView.setAdapter(new ArrayAdapter(displayinfo.this, R.layout.simple_list_item, allitems));
              }

            }

        }
    });

    warehousebtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            items.clear();
            Intent paramView = new Intent(displayinfo.this, displayinfowarehouse.class);
//            System.out.println(selectedItem);
            paramView.putExtra("inspectors", "displayinfo/"+spinner.getSelectedItem().toString()+"/"+name);
            startActivity(paramView);

        }
    });
  }

  public void onStart()
  {
    super.onStart();


      databasewarehouse.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              try {
                  collectnames(dataSnapshot, name);
              } catch (ParseException e) {
                  e.printStackTrace();
              }

              for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                  System.out.println(noteDataSnapshot);
                  inspector Inspector = noteDataSnapshot.getValue(inspector.class);

                  if (Inspector.getName().equals(name)) {
                      warehouses = Inspector.getWarehouse();
                      ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                              android.R.layout.simple_spinner_item, warehouses);
                      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                      spinner.setAdapter(adapter);
                      System.out.println(warehouses);

                  }
              }

          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });

//    databasewarehouse.addListenerForSingleValueEvent(new ValueEventListener()
//    {
//      public void onCancelled(DatabaseError paramAnonymousDatabaseError) {}
//
//      public void onDataChange(DataSnapshot paramAnonymousDataSnapshot)
//      {
//
//

//
//      }
//    });
  }
}



/* Location:           Z:\Users\mabdullahk\Downloads\dex2jar-2.0\classes-dex2jar.jar

 * Qualified Name:     com.developer.mabdullahk.mccwarehouse.displayinfo

 * JD-Core Version:    0.7.0.1

 */