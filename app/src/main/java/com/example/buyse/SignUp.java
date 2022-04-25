package com.example.buyse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import android.os.Bundle;
import android.content.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SignUp extends AppCompatActivity {
   Button b1;
   EditText ed1,ed2;
   FirebaseAuth mAuth;
   TextView LoginRedirect;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.signup);
      mAuth = FirebaseAuth.getInstance();
      ed1 = (EditText)findViewById(R.id.editTextTextEmailAddress);
      ed2 = (EditText)findViewById(R.id.editTextTextPassword);
      LoginRedirect = (TextView)findViewById(R.id.LoginRedirect);
      b1 = (Button)findViewById(R.id.button2);

      LoginRedirect.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Intent intent = new Intent(SignUp.this, MainActivity.class);
            startActivity(intent);
         }
      });

      b1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            registerNewUser();
         }
      });

   }
   private void registerNewUser()
   {

      // show the visibility of progress bar to show loading

      // Take the value of two edit texts in Strings
      String email, password;
      email = ed1.getText().toString();
      password = ed2.getText().toString();

      // Validations for input email and password
      if (TextUtils.isEmpty(email)) {
         Toast.makeText(getApplicationContext(),
                 "Please enter email!!",
                 Toast.LENGTH_LONG)
                 .show();
         return;
      }
      if (TextUtils.isEmpty(password)) {
         Toast.makeText(getApplicationContext(),
                 "Please enter password!!",
                 Toast.LENGTH_LONG)
                 .show();
         return;
      }

      // create new user or register new user
      mAuth
              .createUserWithEmailAndPassword(email, password)
              .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task)
                 {
                    if (task.isSuccessful()) {
                       Toast.makeText(getApplicationContext(),
                               "Registration successful!",
                               Toast.LENGTH_LONG)
                               .show();


                       // if the user created intent to login activity
                       Intent intent
                               = new Intent(SignUp.this,
                               MainActivity.class);
                       startActivity(intent);
                    }
                    else {

                       // Registration failed
                       Toast.makeText(
                               getApplicationContext(),
                               "Registration failed!!"
                                       + " Please try again later",
                               Toast.LENGTH_LONG)
                               .show();

                    }
                 }
              });
   }

}



