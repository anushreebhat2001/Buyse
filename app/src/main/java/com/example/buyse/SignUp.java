package com.example.buyse;

public class SignUp {
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
            if(ed1.getText().toString()!="" &&
               ed2.getText().toString()!="") {
                  Toast.makeText(getApplicationContext(),
                     "Registration Successful",Toast.LENGTH_SHORT).show();
//               Need to send the registered data into a db
              
//               This changes back to login page
               Intent intent = new Intent(SignUp.this, MainActivity.class);
               startActivity(intent);
            }
         }
      });

   }
}
