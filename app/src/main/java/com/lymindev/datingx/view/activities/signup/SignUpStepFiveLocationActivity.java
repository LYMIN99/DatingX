package com.lymindev.datingx.view.activities.signup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.lymindev.datingx.MainActivity;
import com.lymindev.datingx.R;
import com.lymindev.datingx.firebases.FirebaseService;
import com.lymindev.datingx.managers.UsersManager;
import com.lymindev.datingx.model.user.UsersModel;
import com.lymindev.datingx.model.user.UsersRealm;
import com.lymindev.datingx.utils.LocationX;
import com.lymindev.datingx.view.activities.BaseActivity;

import io.realm.Realm;

public class SignUpStepFiveLocationActivity extends BaseActivity {

    public static void launch(Context context, String id) {
        context.startActivity(new Intent(context,SignUpStepFiveLocationActivity.class).putExtra("id",id));
    }
    private  String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        setContentView(R.layout.activity_sign_up_step_five_location);
        id = getIntent().getStringExtra("id");
        findViewById(R.id.btn_allow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(SignUpStepFiveLocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SignUpStepFiveLocationActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            55);
                } else {
                    getLocation();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 55 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLocation();

        }
    }
    private void getLocation(){

        ProgressDialog progressDialog = new ProgressDialog(SignUpStepFiveLocationActivity.this);
        progressDialog.setMessage("Please wait..");
        progressDialog.show();

        new LocationX(SignUpStepFiveLocationActivity.this).getCurrentLocation(new LocationX.OnGetLatLngCallBack() {
            @Override
            public void onSuccess(String address, LatLng latLng) {
                new UsersManager().setLocation(address,latLng.latitude,latLng.longitude,id);
                UsersRealm usersR = new UsersManager().getUserInfo(id);
                UsersModel usersM = new UsersModel(
                        usersR.getId(),  usersR.getName(), usersR.getProfile(), usersR.getAddress(), usersR.getGander(), usersR.getDateOfBirth(), usersR.getAbout(), usersR.getEmail(),usersR.getAge(),usersR.getLatitude(), usersR.getLongitude());

                FirebaseService.addNewUser(usersM);
                startActivity(new Intent(SignUpStepFiveLocationActivity.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                progressDialog.dismiss();
            }

            @Override
            public void onPermissionDenied() {

            }
        });
    }
}