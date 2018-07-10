package com.developer.mabdullahk.mccwarehouse;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class chooseInspec
  extends Activity
{
  private DatabaseReference databasewarehouse;
  List<String> items = new ArrayList();
  public String selectedItem;
  ArrayList<String> selectedItems;
  Button back;


  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_choose_inspec);
    selectedItems = new ArrayList();
    databasewarehouse = FirebaseDatabase.getInstance().getReference("inspectors");
      back = (Button) findViewById(R.id.btnback);
      back.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              items.clear();
              startActivity(new Intent(chooseInspec.this, adminpage.class));
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
          for(DataSnapshot childSnapShot : paramAnonymousDataSnapshot.getChildren()) {
              String name = ((Map<String, Object>) childSnapShot.getValue()).get("name").toString();
              System.out.println(name);
              items.add(name);
          }

          ListView list = (ListView)findViewById(R.id.checkable_list);
          list.setChoiceMode(1);

          System.out.println(items.toString());

          list.setAdapter(new ArrayAdapter(chooseInspec.this, android.R.layout.simple_list_item_checked, items));

          list.setOnItemClickListener(new OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                  String text = ((TextView) view).getText().toString();
                  if (selectedItems.contains(text)){
                      selectedItems.remove(text);
                      return;
                  }

                  selectedItems.add(text);

              }
          });
      }
    });
  }

  public void showSelectedItems(View view)
  {
      items.clear();
      Intent paramView = new Intent(this, displayinfo.class);
      System.out.println(selectedItem);
      paramView.putExtra("inspectors", selectedItems.get(0));
      startActivity(paramView);
  }
}
