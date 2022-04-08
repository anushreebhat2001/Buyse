package com.example.buyse;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
   Button b1;
   EditText ed1,ed2;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      ed1 = (EditText)findViewById(R.id.editTextTextEmailAddress);
      ed2 = (EditText)findViewById(R.id.editTextTextPassword);

      b1 = (Button)findViewById(R.id.button2);
       
      b1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if(ed1.getText().toString().equals("admin") &&
               ed2.getText().toString().equals("thisisarandompassword")) {
                  Toast.makeText(getApplicationContext(),
                     "Login Successful",Toast.LENGTH_SHORT).show();
//                 Changes to the Homepage after successful login
//                 Need to create a BUYSE HOME class
               Intent intent = new Intent(MainActivity.this, BUYSE_Home.class);
               startActivity(intent);
               }
             else{
                  Toast.makeText(getApplicationContext(), "Wrong 
                     Credentials",Toast.LENGTH_SHORT).show();
               }
         }
      });

   }
}
