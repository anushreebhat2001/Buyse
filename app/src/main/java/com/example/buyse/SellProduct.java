package com.example.buyse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class SellProduct extends AppCompatActivity {
    EditText ed_itemname,ed_price,ed_desc,ed_meetplace,ed_phoneno;
    Button importimage,submit;

    public ImageView ProfilePic;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    public String randomKey;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_product);

        ed_itemname = (EditText) findViewById(R.id.editTextItemName);
        ed_price = (EditText) findViewById(R.id.editTextPrice);
        ed_desc = (EditText) findViewById(R.id.editTextDescription);
        ed_meetplace = (EditText) findViewById(R.id.editTextMeetPlace);
        ed_phoneno = (EditText) findViewById(R.id.editTextPhone);
        importimage = (Button) findViewById(R.id.ImportButton);
        submit = (Button) findViewById(R.id.SubmitButton);

        importimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance("https://house-rentals-app-default-rtdb.firebaseio.com/");
                reference = rootNode.getReference("Products");


                //Get all the values
                String itemname = ed_itemname.getText().toString();
                String price = ed_price.getText().toString();
                String description = ed_desc.getText().toString();
                String meetplace = ed_meetplace.getText().toString();
                String phoneno = ed_phoneno.getText().toString();


                ProductInfo helperClass = new ProductInfo(itemname,price,description,meetplace,phoneno,randomKey);
                reference.child(itemname).setValue(helperClass);
                Toast.makeText(SellProduct.this, "Item Posted", Toast.LENGTH_SHORT).show();
                Intent calIntent = new Intent(SellProduct.this, BuyseHome.class);
                startActivity(calIntent);
            }
        });

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        ProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });

    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            ProfilePic.setImageURI(imageUri);
            uploadPicture();
        }

    }

    public void uploadPicture() {
        // Create a reference to "mountains.jpg"

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();
        randomKey = UUID.randomUUID().toString();
        StorageReference mountainsRef = storageReference.child("images/" + randomKey);
        mountainsRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pd.dismiss();
                Snackbar.make(findViewById(android.R.id.content),"Image Uploaded.", Snackbar.LENGTH_LONG).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),"Failed to Upload", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progressPercent = (100.00 * snapshot.getBytesTransferred()/ snapshot.getTotalByteCount());
                        pd.setMessage("Uploading " + (int)progressPercent + "%");
                    }
                });

    }
}