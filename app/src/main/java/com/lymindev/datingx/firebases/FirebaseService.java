package com.lymindev.datingx.firebases;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.lymindev.datingx.model.user.UsersModel;
import com.lymindev.datingx.model.user.UsersRealm;

import static android.content.ContentValues.TAG;

public class FirebaseService {

    public static void addNewUser(UsersModel user){
        // Create a new user with a first and last name

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Users").document(user.getId()).set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: ");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public static void uploadImageToFireBaseStorage(Context context,Uri uri, final OnCallBack onCallBack){
        StorageReference riversRef = FirebaseStorage.getInstance().getReference().child("Images/Users" + System.currentTimeMillis()+"."+getFileExtention(context,uri));
        riversRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!urlTask.isSuccessful());
                Uri downloadUrl = urlTask.getResult();

                final String sdownload_url = String.valueOf(downloadUrl);

                onCallBack.onUploadSuccess(sdownload_url);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                onCallBack.onUploadFailed(e);
            }
        });
    }

    private static String getFileExtention(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public static UsersModel getUser(String id){
        final UsersModel[] users = {new UsersModel()};
        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("Users").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//            }
//        });

        DocumentReference docRef = db.collection("Users").document(id);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d(TAG, "onSuccess: sss "+documentSnapshot.getData().toString());
                UsersModel city = documentSnapshot.toObject(UsersModel.class);
                Log.d(TAG, "onSuccess: sss x "+city.getProfile());
                users[0] = city;
            }
        });



        return users[0];
    }
    public interface OnCallBack{
        void onUploadSuccess(String imageUrl);
        void onUploadFailed(Exception e);
    }
}
