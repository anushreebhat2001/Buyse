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

public class MainActivity extends AppCompatActivity {
   Button b1;
   EditText ed1,ed2;
   private FirebaseAuth mAuth;
   TextView signup_redirect;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      mAuth = FirebaseAuth.getInstance();
      ed1 = (EditText)findViewById(R.id.editTextTextEmailAddress);
      ed2 = (EditText)findViewById(R.id.editTextTextPassword);
      signup_redirect = (TextView)findViewById(R.id.SignupRedirect);
      b1 = (Button)findViewById(R.id.button2);

//      Switching between intents does not work but login and signup functionality works

      signup_redirect.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Intent intent2 = new Intent (MainActivity.this, SignUp.class);
            startActivity(intent2);
         }
      });

      b1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            loginUserAccount();
         }
      });
   }


   private void loginUserAccount()
   {


      // Take the value of two edit texts in Strings
      String email, password;
      email =ed1.getText().toString();
      password = ed2.getText().toString();

      // validations for input email and password
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

      mAuth.signInWithEmailAndPassword(email, password)
              .addOnCompleteListener(
                      new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(
                                 @NonNull Task<AuthResult> task)
                         {
                            if (task.isSuccessful()) {
                               Toast.makeText(getApplicationContext(),
                                       "Login successful!!",
                                       Toast.LENGTH_LONG)
                                       .show();


                               // if sign-in is successful
                               // intent to home activity
                               Intent intent
                                       = new Intent(MainActivity.this,
                                       BuyseHome.class);
                               startActivity(intent);
                            }

                            else {

                               // sign-in failed
                               Toast.makeText(getApplicationContext(),
                                       "Login failed!!",
                                       Toast.LENGTH_LONG)
                                       .show();
                            }
                         }
                      });
   }
}
