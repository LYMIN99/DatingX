package com.lymindev.datingx.view.activities.signup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lymindev.datingx.R;
import com.lymindev.datingx.firebases.FirebaseService;
import com.lymindev.datingx.managers.UsersManager;
import com.lymindev.datingx.view.activities.BaseActivity;

import java.util.Random;

public class SignUpStepFourProfileActivity extends BaseActivity {

    public static void launch(Context context, String id) {
        context.startActivity(new Intent(context,SignUpStepFourProfileActivity.class).putExtra("id",id));

    }
    private ImageView imageProfile;
    private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step_four_profile);
        String id = getIntent().getStringExtra("id");
        imageProfile = findViewById(R.id.image_profile);
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (uri!=null){
                    FirebaseService.uploadImageToFireBaseStorage(SignUpStepFourProfileActivity.this, uri, new FirebaseService.OnCallBack() {
                        @Override
                        public void onUploadSuccess(String imageUrl) {
                            new UsersManager().setImageProfile(imageUrl,id);
                            SignUpStepFiveLocationActivity.launch(SignUpStepFourProfileActivity.this,id);
                        }

                        @Override
                        public void onUploadFailed(Exception e) {
                            Toast.makeText(getApplicationContext(),"Error upload",Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(),"please pick a profile",Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissionOpenCamera();
            }
        });
    }

    private void checkPermissionOpenCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    new Random().nextInt(999));
        } else {
            openGallery();
        }
    }

    private void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select image"),  22);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 22
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null){

            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageProfile.setImageBitmap(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}