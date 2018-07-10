package com.developer.mabdullahk.mccwarehouse;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask.TaskSnapshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class takepicture
  extends AppCompatActivity {
    private static final int REQUEST_PICTURE_CAPTURE = 11;
    private static final String TAG = "1";
    private DatabaseReference attendancedata;
    private ImageView imageView;
    String latlng;
    private DatabaseReference latlngmark;
    String locationmarked;
    private Button logged;
    String mCurrentPhotoPath;
    private Button mSelectImage;
    private StorageReference mStorage;
    private Button mSubmitBtn;
    private Uri photoURI;
    private ProgressDialog progressDialog;
    private FirebaseUser user;
    List<String> latlnglist;

    @Override
    protected void onCreate(Bundle paramBundle) {

        super.onCreate(paramBundle);
        setContentView(R.layout.activity_takepicture);
        user = FirebaseAuth.getInstance().getCurrentUser();
        mStorage = FirebaseStorage.getInstance().getReference();
        attendancedata = FirebaseDatabase.getInstance().getReference("inspectors");
        latlngmark = FirebaseDatabase.getInstance().getReference("warehouses");
        mSelectImage = ((Button) findViewById(R.id.takepic));
        logged = ((Button) findViewById(R.id.btnlogout));
//    imageView = ((ImageView)findViewById(R.id.btnback));
        progressDialog = new ProgressDialog(this);
        paramBundle = getIntent().getExtras();
        if (paramBundle != null) {
            String[] name = ((String) paramBundle.get("location")).split(",");
            locationmarked = name[0];
            latlng = name[1];
        }
        logged.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(takepicture.this, login.class));
            }
        });
        mSelectImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                dispatchTakePictureIntent(locationmarked);
            }
        });
    }

//    private void addthings(Map<String, Object> paramMap, String paramString1, String paramString2)
//  {
//    paramMap = paramMap.entrySet().iterator();
//    while (paramMap.hasNext())
//    {
//      Map localMap = (Map)((Map.Entry)paramMap.next()).getValue();
//      FirebaseAuth.getInstance().getCurrentUser().getUid();
//      if (((String)localMap.get("name")).equals(paramString1)) {
//        FirebaseDatabase.getInstance().getReference("warehouses").child((String)localMap.get("id")).child("latlng").push().setValue(paramString2);
//      }
//    }
//  }

//  private void collectnames(Map<String, Object> paramMap, String paramString1, String paramString2)
//  {
//    new ArrayList();
//    System.out.println(paramString1);
//    paramMap = paramMap.entrySet().iterator();
//    while (paramMap.hasNext())
//    {
//      Object localObject1 = (Map)((Map.Entry)paramMap.next()).getValue();
//      Object localObject2 = FirebaseAuth.getInstance().getCurrentUser().getUid();
//      String str = (String)((Map)localObject1).get("email");
//      System.out.println(localObject1);
//      System.out.println(((Map)localObject1).get("warehouse"));
//      System.out.println((String)localObject2);
//      if (str.equals(paramString1))
//      {
//        localObject2 = FirebaseDatabase.getInstance().getReference("inspectors");
//        localObject1 = (String)((Map)localObject1).get("id");
//        str = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        ((DatabaseReference)localObject2).child((String)localObject1).child("attendance").push().setValue(paramString2 + "__" + str);
//      }
//    }
//  }

    private File createImageFile(String paramString)
            throws IOException {
        String str1 = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String str2 = user.getEmail();
        File image = File.createTempFile("JPEG_" + str1 + "_" + str2 + "_" + paramString + "_", ".jpg", getExternalFilesDir(Environment.DIRECTORY_PICTURES));
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent(String paramString) {

//        String str1 = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String str2 = user.getEmail();
//        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
//        File output = new File(dir, "JPEG_" + str1 + "_" + str2 + "_" + paramString + "_" + ".jpg");
//
//        Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
//        cameraIntent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true);
//        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));
//        startActivityForResult(cameraIntent, REQUEST_PICTURE_CAPTURE);

      Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      if (cameraIntent.resolveActivity(getPackageManager()) != null) {
          // Create the File where the photo should go
          File photoFile = null;
          try {
              photoFile = createImageFile(paramString);
          } catch (IOException ex) {
              // Error occurred while creating the File
              Log.i(TAG, "IOException");
          }
          // Continue only if the File was successfully created
          if (photoFile != null) {
              cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, "com.developer.android.fileprovider", photoFile));
              startActivityForResult(cameraIntent, REQUEST_PICTURE_CAPTURE);
          }
      }

//
//      if (cameraIntent.resolveActivity(getPackageManager()) != null) {
//
//          File pictureFile = null;
//          try {
//              pictureFile = createImageFile(paramString);
//          } catch (IOException ex) {
//              Toast.makeText(this,
//                      "Photo file can't be created, please try again",
//                      Toast.LENGTH_SHORT).show();
//              return;
//          }
//          if (pictureFile != null) {
//              photoURI = FileProvider.getUriForFile(this,
//                      "com.developer.android.fileprovider",
//                      pictureFile);
//              startActivityForResult(cameraIntent, REQUEST_PICTURE_CAPTURE);
//          }
//    }

}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PICTURE_CAPTURE && resultCode == RESULT_OK) {
            File f = new File(mCurrentPhotoPath);
            photoURI = Uri.fromFile(f);
                Toast.makeText(this, "picture captured", Toast.LENGTH_SHORT);
                progressDialog.setMessage("Uploading...");
                progressDialog.show();
                mStorage.child(user.getEmail()).child(photoURI.getLastPathSegment()).putFile(photoURI).addOnSuccessListener(new OnSuccessListener<TaskSnapshot>() {
                    @Override
                    public void onSuccess(TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Toast.makeText(takepicture.this, "done uploading", Toast.LENGTH_LONG).show();

                        attendancedata.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                               for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                                System.out.println(noteDataSnapshot);
                                inspector Inspector = noteDataSnapshot.getValue(inspector.class);

                                if (Inspector.getEmail().equals(user.getEmail())) {
                                    String str1 = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

                                    if (Inspector.getAttendance() == null){
                                        attendancedata.child(Inspector.getId()).child("attendance").child("0").setValue(locationmarked+"_"+str1);

                                    } else {
                                        int size = Inspector.getAttendance().size();

                                        attendancedata.child(Inspector.getId()).child("attendance").child(Integer.toString(size)).setValue(locationmarked + "_" + str1);
                                    }
                                }
                            }
//
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


//






                    }
                }).addOnFailureListener(new OnFailureListener() {
        public void onFailure(@NonNull Exception paramAnonymousException)
        {
          Toast.makeText(takepicture.this, "Upload Failed!", Toast.LENGTH_LONG).show();
        }
      });


//                image.setImageURI(Uri.fromFile(imgFile));

        }
    }

//  protected void onActivityResult(int paramInt1, int paramInt2, final Intent paramIntent)
//  {
//    super.onActivityResult(paramInt1, paramInt2, paramIntent);
//    if ((paramInt1 == 111) && (paramInt2 == -1))
//    {
//      progressDialog.setMessage("Uploading...");
//      progressDialog.show();
//      paramIntent = user.getEmail();
//      mStorage.child(paramIntent).child(photoURI.getLastPathSegment()).putFile(photoURI).addOnSuccessListener(new OnSuccessListener()
//      {
//        public void onSuccess(UploadTask.TaskSnapshot paramAnonymousTaskSnapshot)
//        {
//          progressDialog.dismiss();
//          attendancedata.addListenerForSingleValueEvent(new ValueEventListener()
//          {
//            public void onCancelled(DatabaseError paramAnonymous2DatabaseError) {}
//
//            public void onDataChange(DataSnapshot paramAnonymous2DataSnapshot)
//            {
//              collectnames((Map)paramAnonymous2DataSnapshot.getValue(), takepicture.4.val$au, locationmarked);
//            }
//          });
//          latlngmark.addListenerForSingleValueEvent(new ValueEventListener()
//          {
//            public void onCancelled(DatabaseError paramAnonymous2DatabaseError) {}
//
//            public void onDataChange(DataSnapshot paramAnonymous2DataSnapshot)
//            {
//              addthings((Map)paramAnonymous2DataSnapshot.getValue(), locationmarked, latlng);
//            }
//          });
//          Toast.makeText(takepicture.this, "Upload Successful!", 0).show();
//        }
//      }).addOnFailureListener(new OnFailureListener()
//      {
//        public void onFailure(@NonNull Exception paramAnonymousException)
//        {
//          Toast.makeText(takepicture.this, "Upload Failed!", 0).show();
//        }
//      });
//    }
//  }



}



/* Location:           Z:\Users\mabdullahk\Downloads\dex2jar-2.0\classes-dex2jar.jar

 * Qualified Name:     com.developer.mabdullahk.mccwarehouse.takepicture

 * JD-Core Version:    0.7.0.1

 */