package com.oron.restaurantrating.Activities;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.oron.restaurantrating.R;

import static android.os.Environment.DIRECTORY_DOWNLOADS;


public class UtilitiesActivity extends AppCompatActivity {

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private StorageReference ref;
    private Button down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilities);
        down = findViewById(R.id.selfInsPDF);

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download();
            }


        });
    }

    private void download() {
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        ref = storageReference.child("Util.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFile(UtilitiesActivity.this, "Util", ".pdf", DIRECTORY_DOWNLOADS, url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UtilitiesActivity.this, "Error Download File",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {
        Toast.makeText(UtilitiesActivity.this, "Download File",Toast.LENGTH_SHORT).show();
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadManager.enqueue(request);
    }
}
