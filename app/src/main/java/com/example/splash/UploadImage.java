package com.example.splash;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadImage extends AppCompatActivity {
   Button browse,upload;
   ImageView imageView;
   EditText text;
    TextView type;
    Uri uri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        imageView=findViewById(R.id.imageView7);
        browse=findViewById(R.id.browse);
        text=findViewById(R.id.text);
        upload=findViewById(R.id.upload);
        type=findViewById(R.id.type);
        currentUser= FirebaseAuth.getInstance().getCurrentUser();
        storageReference= FirebaseStorage.getInstance().getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference("PatientDetail").child(currentUser.getUid());


        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(UploadImage.this)
                        .galleryOnly()
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                       .compress(1024)			//Final image size will be less than 1 MB(Optional)
                       .maxResultSize(200, 200)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { super.onActivityResult(requestCode, resultCode, data);

        uri=data.getData();
        imageView.setBackground(null);
        imageView.setImageURI(uri);
        text.setVisibility(View.VISIBLE);
        upload.setEnabled(true);
    }

    public String getExtension(){
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(getContentResolver().getType(uri));
    }
    public void upload(){
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("uploding profile.....");
        pd.show();
        final StorageReference uploader=storageReference.child("myImage/"+System.currentTimeMillis()+"."+getExtension());

        uploader.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                FileModel obj=new FileModel(text.getText().toString(),uri.toString(),currentUser.getUid().toString());
                                databaseReference.setValue(obj)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                pd.dismiss();
                                                Toast.makeText(UploadImage.this, "uploaded successfully", Toast.LENGTH_SHORT).show();
                                           startActivity(new Intent(UploadImage.this,PatientDashboard.class));
                                            }
                                        }).addOnCanceledListener(new OnCanceledListener() {
                                    @Override
                                    public void onCanceled() {
                                        pd.dismiss();
                                        Toast.makeText(UploadImage.this, "uploaded failed", Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }
                        });
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {Toast.makeText(UploadImage.this, "Uploading , please wait..", Toast.LENGTH_SHORT).show();
                float per=(100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                pd.setMessage("uploaded:"+(int)per+"%");

            }
        });


    }
}