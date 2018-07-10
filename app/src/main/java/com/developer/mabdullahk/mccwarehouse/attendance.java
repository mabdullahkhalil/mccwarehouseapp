//package com.developer.mabdullahk.mccwarehouse;
//import android.content.Intent;
//import android.location.Location;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.telephony.SmsManager;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Spinner;
//import android.widget.Toast;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//public class attendance
//  extends AppCompatActivity
//  implements OnMapReadyCallback, AdapterView.OnItemSelectedListener
//{
//  @Override
//  public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//  }
//
//  @Override
//  public void onNothingSelected(AdapterView<?> adapterView) {
//
//  }
//
//  @Override
//  public void onMapReady(GoogleMap googleMap) {
//
//  }
//  private static final String COURSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";
//  private static final float DEFAULT_ZOOM = 15.0F;
//  private static final String FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
//  private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
//  private static final int SMS_REQUEST_CODE = 1234;
//  private static final String TAG = "MapActivity";
//  private DatabaseReference databasewarehouse;
//  private FirebaseUser firebaseAuth;
//  List<String> items = new ArrayList();
//  private FusedLocationProviderClient mFusedLocationProviderClient;
//  private ImageView mGps;
//  private Boolean mLocationPermissionsGranted = Boolean.valueOf(false);
//  private GoogleMap mMap;
//  private EditText mSearchText;
//  public Button marking;
//  String msearchstring;
//  ArrayList<String> selectedItems;
//  Spinner spinner;
//  private DatabaseReference warehouselatlng;
//
//  private Map<String, Object> collectlatlng(Map<String, Object> paramMap, String paramString)
//  {
//    System.out.println(paramString);
//    Object localObject = "";
//    Iterator localIterator = paramMap.entrySet().iterator();
//    paramMap = (Map<String, Object>)localObject;
//    while (localIterator.hasNext())
//    {
//      localObject = (Map)((Map.Entry)localIterator.next()).getValue();
//      String str = (String)((Map)localObject).get("name");
//      System.out.println(str);
//      System.out.println(((Map)localObject).get("warehouse"));
//      if (str.equals(paramString))
//      {
//        localObject = (Map)((Map)localObject).get("latlng");
//        if (localObject == null) {
//          Toast.makeText(this, "The location marked is not in database, contact admin.", 1).show();
//        } else {
//          paramMap = (Map<String, Object>) ((Map)localObject).get("main");
//        }
//      }
//    }
//    return paramMap;
//  }
//
//  private void collectnames(Map<String, Object> paramMap, String paramString)
//  {
//    System.out.println(paramString);
//    paramMap = (Map<String, Object>) paramMap.entrySet().iterator();
//    while (paramMap.hasNext())
//    {
//      Map localMap = (Map)((Map.Entry)paramMap.next()).getValue();
//      String str = (String)localMap.get("email");
//      System.out.println(str);
//      System.out.println(localMap.get("warehouse"));
//      if (str.equals(paramString))
//      {
//        items = ((List)localMap.get("warehouse"));
//        System.out.println("checking the warehouses");
//        System.out.println(str);
//      }
//    }
//  }
//
//  private void geoLocate()
//  {
//    Log.d("MapActivity", "geoLocate: geolocating");
//    final String str = msearchstring;
//    warehouselatlng.addListenerForSingleValueEvent(new ValueEventListener()
//    {
//      public void onCancelled(DatabaseError paramAnonymousDatabaseError) {}
//
//      public void onDataChange(DataSnapshot paramAnonymousDataSnapshot)
//      {
//        paramAnonymousDataSnapshot = collectlatlng((Map)paramAnonymousDataSnapshot.getValue(), str);
//        Log.d("MapActivity", paramAnonymousDataSnapshot);
//        if ((str.equals("")) || (paramAnonymousDataSnapshot.equals(""))) {
//          if ((ActivityCompat.checkSelfPermission(attendance.this, "android.permission.ACCESS_FINE_LOCATION") == 0) || (ActivityCompat.checkSelfPermission(attendance.this, "android.permission.ACCESS_COARSE_LOCATION") == 0)) {}
//        }
//        final double d1;
//        double d2;
//        do
//        {
//          return;
//          mFusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener()
//          {
//            public void onComplete(@NonNull Task paramAnonymous2Task)
//            {
//              if (paramAnonymous2Task.isSuccessful())
//              {
//                paramAnonymous2Task = (Location)paramAnonymous2Task.getResult();
//                double d1 = paramAnonymous2Task.getLatitude();
//                double d2 = paramAnonymous2Task.getLongitude();
//                paramAnonymous2Task = attendance.4.val$searchString + "," + d1 + "," + d2;
//                sendSMS("+923424554488", paramAnonymous2Task);
//                Toast.makeText(attendance.this, "Error in Location Marking.", 1).show();
//              }
//            }
//          });
//          return;
//          paramAnonymousDataSnapshot = paramAnonymousDataSnapshot.split(",");
//          d1 = Double.parseDouble(paramAnonymousDataSnapshot[0]);
//          d2 = Double.parseDouble(paramAnonymousDataSnapshot[1]);
//        } while ((ActivityCompat.checkSelfPermission(attendance.this, "android.permission.ACCESS_FINE_LOCATION") != 0) && (ActivityCompat.checkSelfPermission(attendance.this, "android.permission.ACCESS_COARSE_LOCATION") != 0));
//        mFusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener()
//        {
//          public void onComplete(@NonNull Task paramAnonymous2Task)
//          {
//            if (paramAnonymous2Task.isSuccessful())
//            {
//              Log.d("MapActivity", "onComplete: found location!");
//              paramAnonymous2Task = (Location)paramAnonymous2Task.getResult();
//              double d1 = paramAnonymous2Task.getLatitude();
//              double d2 = paramAnonymous2Task.getLongitude();
//              paramAnonymous2Task = d1 + "," + d2;
//              Object localObject = new float[1];
//              Location.distanceBetween(d1, val$finalChecklong, d1, d2, (float[])localObject);
//              if (localObject[0] < 600.0F)
//              {
//                new StringBuilder().append("you were at ").append(attendance.4.val$searchString).toString();
//                localObject = new Intent(attendance.this, takepicture.class);
//                ((Intent)localObject).putExtra("location", attendance.4.val$searchString + "_" + paramAnonymous2Task);
//                startActivity((Intent)localObject);
//                return;
//              }
//              Toast.makeText(attendance.this, "you were not in the location mentioned", 0).show();
//              return;
//            }
//            Log.d("MapActivity", "onComplete: current location is null");
//            Toast.makeText(attendance.this, "unable to get current location", 0).show();
//          }
//        });
//      }
//    });
//  }
//
//  private void getDeviceLocation()
//  {
//    Log.d("MapActivity", "getDeviceLocation: getting the devices current location");
//    mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//    try
//    {
//      if (mLocationPermissionsGranted.booleanValue()) {
//        mFusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener()
//        {
//          public void onComplete(@NonNull Task paramAnonymousTask)
//          {
//            if (paramAnonymousTask.isSuccessful())
//            {
//              Log.d("MapActivity", "onComplete: found location!");
//              paramAnonymousTask = (Location)paramAnonymousTask.getResult();
//              moveCamera(new LatLng(paramAnonymousTask.getLatitude(), paramAnonymousTask.getLongitude()), 15.0F, "My Location");
//              return;
//            }
//            Log.d("MapActivity", "onComplete: current location is null");
//            Toast.makeText(attendance.this, "unable to get current location", 0).show();
//          }
//        });
//      }
//      return;
//    }
//    catch (SecurityException localSecurityException)
//    {
//      Log.e("MapActivity", "getDeviceLocation: SecurityException: " + localSecurityException.getMessage());
//    }
//  }
//
//  private void getLocationPermission()
//  {
//    Log.d("MapActivity", "getLocationPermission: getting location permissions");
//    String[] arrayOfString = new String[2];
//    arrayOfString[0] = "android.permission.ACCESS_FINE_LOCATION";
//    arrayOfString[1] = "android.permission.ACCESS_COARSE_LOCATION";
//    if (ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0)
//    {
//      if (ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0)
//      {
//        mLocationPermissionsGranted = Boolean.valueOf(true);
//        initMap();
//        return;
//      }
//      ActivityCompat.requestPermissions(this, arrayOfString, 1234);
//      return;
//    }
//    ActivityCompat.requestPermissions(this, arrayOfString, 1234);
//  }
//
//  private void hideSoftKeyboard()
//  {
//    getWindow().setSoftInputMode(3);
//  }
//
//  private void init()
//  {
//    Log.d("MapActivity", "init: initializing");
//    mGps.setOnClickListener(new View.OnClickListener()
//    {
//      public void onClick(View paramAnonymousView)
//      {
//        Log.d("MapActivity", "onClick: clicked gps icon");
//        getDeviceLocation();
//      }
//    });
//    hideSoftKeyboard();
//  }
//
//  private void initMap()
//  {
//    Log.d("MapActivity", "initMap: initializing map");
//    ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
//  }
//
//  private void moveCamera(LatLng paramLatLng, float paramFloat, String paramString)
//  {
//    Log.d("MapActivity", "moveCamera: moving the camera to: lat: " + paramLatLng.latitude + ", lng: " + paramLatLng.longitude);
//    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paramLatLng, paramFloat));
//    if (!paramString.equals("My Location"))
//    {
//      paramLatLng = new MarkerOptions().position(paramLatLng).title(paramString);
//      mMap.addMarker(paramLatLng);
//    }
//    hideSoftKeyboard();
//  }
//
//  protected void onCreate(@Nullable final Bundle paramBundle)
//  {
//    if (ContextCompat.checkSelfPermission(this, "android.permission.SEND_SMS") == -1) {
//      ActivityCompat.requestPermissions(this, new String[] { "android.permission.SEND_SMS" }, 1234);
//    }
//    super.onCreate(paramBundle);
//    setContentView(2131361821);
//    selectedItems = new ArrayList();
//    spinner = ((Spinner)findViewById(2131230896));
//    mGps = ((ImageView)findViewById(2131230817));
//    marking = ((Button)findViewById(2131230840));
//    getLocationPermission();
//    databasewarehouse = FirebaseDatabase.getInstance().getReference("inspectors");
//    warehouselatlng = FirebaseDatabase.getInstance().getReference("warehouses");
//    firebaseAuth = FirebaseAuth.getInstance().getCurrentUser();
//    spinner.setOnItemSelectedListener(this);
//    paramBundle = firebaseAuth.getEmail().toString().trim();
//    databasewarehouse.addListenerForSingleValueEvent(new ValueEventListener()
//    {
//      public void onCancelled(DatabaseError paramAnonymousDatabaseError) {}
//
//      public void onDataChange(DataSnapshot paramAnonymousDataSnapshot)
//      {
//        collectnames((Map)paramAnonymousDataSnapshot.getValue(), paramBundle);
//        System.out.println(items);
//        paramAnonymousDataSnapshot = new ArrayAdapter(attendance.this, 17367048, items);
//        paramAnonymousDataSnapshot.setDropDownViewResource(17367049);
//        spinner.setAdapter(paramAnonymousDataSnapshot);
//      }
//    });
//    marking.setOnClickListener(new View.OnClickListener()
//    {
//      public void onClick(View paramAnonymousView)
//      {
//        geoLocate();
//      }
//    });
//  }
//
//  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
//  {
//    msearchstring = paramAdapterView.getItemAtPosition(paramInt).toString();
//  }
//
//  public void onMapReady(GoogleMap paramGoogleMap)
//  {
//    Toast.makeText(this, "Map is Ready", 0).show();
//    Log.d("MapActivity", "onMapReady: map is ready");
//    mMap = paramGoogleMap;
//    if (mLocationPermissionsGranted.booleanValue())
//    {
//      getDeviceLocation();
//      if ((ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) || (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0)) {}
//    }
//    else
//    {
//      return;
//    }
//    mMap.setMyLocationEnabled(true);
//    mMap.getUiSettings().setMyLocationButtonEnabled(false);
//    init();
//  }
//
//  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
//
//  public void onRequestPermissionsResult(int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfInt)
//  {
//    Log.d("MapActivity", "onRequestPermissionsResult: called.");
//    mLocationPermissionsGranted = Boolean.valueOf(false);
//    switch (paramInt)
//    {
//    }
//    do
//    {
//      return;
//    } while (paramArrayOfInt.length <= 0);
//    paramInt = 0;
//    while (paramInt < paramArrayOfInt.length)
//    {
//      if (paramArrayOfInt[paramInt] != 0)
//      {
//        mLocationPermissionsGranted = Boolean.valueOf(false);
//        Log.d("MapActivity", "onRequestPermissionsResult: permission failed");
//        return;
//      }
//      paramInt += 1;
//    }
//    Log.d("MapActivity", "onRequestPermissionsResult: permission granted");
//    mLocationPermissionsGranted = Boolean.valueOf(true);
//    initMap();
//  }
//
//  public void sendSMS(String paramString1, String paramString2)
//  {
//    try
//    {
//      SmsManager.getDefault().sendTextMessage(paramString1, null, paramString2, null, null);
//      Toast.makeText(getApplicationContext(), "Message Sent", 1).show();
//      return;
//    }
//    catch (Exception exp)
//    {
//      Toast.makeText(getApplicationContext(), exp.getMessage().toString(), Toast.LENGTH_SHORT).show();
//      exp.printStackTrace();
//    }
//  }
//}
//
//
//
///* Location:           Z:\Users\mabdullahk\Downloads\dex2jar-2.0\classes-dex2jar.jar
//
// * Qualified Name:     com.developer.mabdullahk.mccwarehouse.attendance
//
// * JD-Core Version:    0.7.0.1
//
// */

package com.developer.mabdullahk.mccwarehouse;

//import android.*;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.location.LocationServices;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class attendance extends AppCompatActivity implements OnMapReadyCallback {

  @Override
  public void onMapReady(GoogleMap googleMap) {
    Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
    Log.d(TAG, "onMapReady: map is ready");
    mMap = googleMap;


    if (mLocationPermissionsGranted) {
      getDeviceLocation();

      if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
              != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
              Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        return;
      }
      mMap.setMyLocationEnabled(true);
      mMap.getUiSettings().setMyLocationButtonEnabled(false);

      init();
    }
  }

  private static final String TAG = "MapActivity";

  private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
  private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
  private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
  private static final float DEFAULT_ZOOM = 15f;

  //widgets
  private EditText mSearchText;
  Spinner spinner;
  private ImageView mGps;
  DatabaseReference databasewarehouse;
  DatabaseReference databasewarehouse1;
  FirebaseUser firebaseAuth;
  public Button marking;
  String email;
  String latlng;
  ArrayList<String> warehouses= new ArrayList<String>();

  //vars
  private Boolean mLocationPermissionsGranted = false;
  private GoogleMap mMap;
  private FusedLocationProviderClient mFusedLocationProviderClient;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_attendance_map);
    databasewarehouse = FirebaseDatabase.getInstance().getReference("inspectors");
    databasewarehouse1 = FirebaseDatabase.getInstance().getReference("warehouses");
    firebaseAuth = FirebaseAuth.getInstance().getCurrentUser();
    email= firebaseAuth.getEmail().toString().trim();
    spinner = (Spinner) findViewById(R.id.spinner);
    mGps = (ImageView) findViewById(R.id.ic_gps);
    marking = (Button) findViewById(R.id.marked);
    getLocationPermission();

    marking.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        geoLocate();
      }
    });


  }

  private void init() {
    Log.d(TAG, "init: initializing");


    databasewarehouse.addListenerForSingleValueEvent(new ValueEventListener()
    {
      public void onCancelled(DatabaseError paramAnonymousDatabaseError) {}

      public void onDataChange(DataSnapshot paramAnonymousDataSnapshot)
      {

        for (DataSnapshot noteDataSnapshot : paramAnonymousDataSnapshot.getChildren()) {
          System.out.println(noteDataSnapshot);
          inspector Inspector = noteDataSnapshot.getValue(inspector.class);


          if (email.equals(Inspector.getEmail())) {

            warehouses = Inspector.getWarehouse();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_spinner_item, warehouses);
              adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
              spinner.setAdapter(adapter);
              System.out.println(warehouses);

          }
        }

      }
    });



    mGps.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Log.d(TAG, "onClick: clicked gps icon");
        getDeviceLocation();
      }
    });

    hideSoftKeyboard();
  }

  private void geoLocate() {
    Log.d(TAG, "geoLocate: geolocating");





//                String searchString = mSearchText.getText().toString();

    final String searchString = "hello";


    if (searchString.equals("")) {
      Toast.makeText(attendance.this, "enter the location first", Toast.LENGTH_LONG).show();
    } else {

        databasewarehouse1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    System.out.println(noteDataSnapshot);
                    final warehouseAdd Warehouseadd= noteDataSnapshot.getValue(warehouseAdd.class);


                    if (spinner.getSelectedItem().toString().equals(Warehouseadd.getName())) {

                        if (Warehouseadd.getLatlng() != null){


                        double checklat = 0;
                        double checklong = 0;

                        latlng = Warehouseadd.getLatlng();
                        List<String> latlnglist = Arrays.asList(latlng.split(","));
                        checklat = Double.parseDouble(latlnglist.get(0));
                        checklong = Double.parseDouble(latlnglist.get(1));


                        if (ActivityCompat.checkSelfPermission(attendance.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(attendance.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }


                        final Task location = mFusedLocationProviderClient.getLastLocation();
                        final double finalChecklat = checklat;
                        final double finalChecklong = checklong;
                        location.addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "onComplete: found location!");
                                    Location currentLocation = (Location) task.getResult();
                                    double currlat = currentLocation.getLatitude();
                                    double currlong = currentLocation.getLongitude();
                                    float[] results = new float[1];
                                    System.out.println(finalChecklat);
                                    System.out.println(finalChecklong);
                                    System.out.println(currlong);
                                    System.out.println(currlat);
                                    Location.distanceBetween(finalChecklat, finalChecklong, currlat, currlong, results);
                                    float distanceInMeters = results[0];
                                    if (distanceInMeters < 100) {
                                        String text = "you were at " + Warehouseadd.getName();
                                        Toast.makeText(attendance.this, text, Toast.LENGTH_SHORT).show();
//              startActivity(new Intent(attendance.this,takepicture.class));
                                        System.out.println(spinner.getSelectedItem().toString());
                                        Intent paramView = new Intent(attendance.this, takepicture.class);
                                        System.out.println(Warehouseadd.getName()+","+Double.toString(currlat)+","+Double.toString(currlat));
                                        paramView.putExtra("location", Warehouseadd.getName()+","+Double.toString(currlat)+"_"+Double.toString(currlong));
                                        startActivity(paramView);


                                    } else {
                                        System.out.println(spinner.getSelectedItem().toString());
                                        Toast.makeText(attendance.this, "you were not in the location mentioned", Toast.LENGTH_SHORT).show();

                                    }


                                } else {
                                    Log.d(TAG, "onComplete: current location is null");
                                    Toast.makeText(attendance.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                            Toast.makeText(attendance.this, "No location found, you would be contacted shortly.", Toast.LENGTH_SHORT).show();

                            final Task location = mFusedLocationProviderClient.getLastLocation();
                            location.addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    Location currentLocation = (Location) task.getResult();
                                    double currlat = currentLocation.getLatitude();
                                    double currlong = currentLocation.getLongitude();

                                    sendSMS("+923424554488",Warehouseadd.getName()+","+Double.toString(currlat)+","+Double.toString(currlong));
                                }
                            });

                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }



  }

  private void getDeviceLocation(){
    Log.d(TAG, "getDeviceLocation: getting the devices current location");

    mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

    try{
      if(mLocationPermissionsGranted){

        final Task location = mFusedLocationProviderClient.getLastLocation();
        location.addOnCompleteListener(new OnCompleteListener() {
          @Override
          public void onComplete(@NonNull Task task) {
            if(task.isSuccessful()){
              Log.d(TAG, "onComplete: found location!");
              Location currentLocation = (Location) task.getResult();

              moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                      DEFAULT_ZOOM,
                      "My Location");

            }else{
              Log.d(TAG, "onComplete: current location is null");
              Toast.makeText(attendance.this, "unable to get current location", Toast.LENGTH_SHORT).show();
            }
          }
        });
      }
    }catch (SecurityException e){
      Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
    }
  }

  private void moveCamera(LatLng latLng, float zoom, String title){
    Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

    if(!title.equals("My Location")){
      MarkerOptions options = new MarkerOptions()
              .position(latLng)
              .title(title);
      mMap.addMarker(options);
    }

    hideSoftKeyboard();
  }

  private void initMap(){
    Log.d(TAG, "initMap: initializing map");
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

    mapFragment.getMapAsync(attendance.this);
  }

  private void getLocationPermission(){
    Log.d(TAG, "getLocationPermission: getting location permissions");
    String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
            FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
      if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
              COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
        mLocationPermissionsGranted = true;
        initMap();
      }else{
        ActivityCompat.requestPermissions(this,
                permissions,
                LOCATION_PERMISSION_REQUEST_CODE);
      }
    }else{
      ActivityCompat.requestPermissions(this,
              permissions,
              LOCATION_PERMISSION_REQUEST_CODE);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    Log.d(TAG, "onRequestPermissionsResult: called.");
    mLocationPermissionsGranted = false;

    switch(requestCode){
      case LOCATION_PERMISSION_REQUEST_CODE:{
        if(grantResults.length > 0){
          for(int i = 0; i < grantResults.length; i++){
            if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
              mLocationPermissionsGranted = false;
              Log.d(TAG, "onRequestPermissionsResult: permission failed");
              return;
            }
          }
          Log.d(TAG, "onRequestPermissionsResult: permission granted");
          mLocationPermissionsGranted = true;
          //initialize our map
          initMap();
        }
      }
    }
  }

  private void hideSoftKeyboard(){
    this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
  }

    public void sendSMS(String paramString1, String paramString2)
    {
        SmsManager.getDefault().sendTextMessage(paramString1, null, paramString2, null, null);
        Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_SHORT).show();
        return;

    }
}


