package com.developer.mabdullahk.mccwarehouse;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword
  extends AppCompatActivity
{
  private EditText email;
  private FirebaseAuth mauth;
  private Button reset;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_forgotpassword);
    email = ((EditText)findViewById(R.id.forgotemail));
    reset = ((Button)findViewById(R.id.forgotbtn));
    mauth = FirebaseAuth.getInstance();
    reset.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        String emailforgot = email.getText().toString();
        if (emailforgot.equals(""))
        {
          Toast.makeText(forgotpassword.this, "Enter your registered email id", Toast.LENGTH_LONG).show();
          return;
        }


        mauth.sendPasswordResetEmail(emailforgot).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(forgotpassword.this, "Email sent", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(forgotpassword.this, login.class));
                    return;
                }
                Toast.makeText(forgotpassword.this, "wrong email", 1).show();
            }
        });


      }
    });
  }
}



/* Location:           Z:\Users\mabdullahk\Downloads\dex2jar-2.0\classes-dex2jar.jar

 * Qualified Name:     com.developer.mabdullahk.mccwarehouse.forgotpassword

 * JD-Core Version:    0.7.0.1

 */