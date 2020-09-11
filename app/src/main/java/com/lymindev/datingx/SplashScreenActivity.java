package com.lymindev.datingx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lymindev.datingx.firebases.FirebaseService;
import com.lymindev.datingx.model.user.UsersModel;
import com.lymindev.datingx.model.user.UsersRealm;
import com.lymindev.datingx.view.activities.StepperWizardColor;

public class SplashScreenActivity extends AppCompatActivity {

    private UsersModel usersRealm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            usersRealm = FirebaseService.getUser(firebaseUser.getUid());
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (usersRealm !=null) {
                    Log.d("TAG", "run: ss "+usersRealm.toString());
                    if (usersRealm.getName()==null) {
                        startActivity(new Intent(SplashScreenActivity.this, StepperWizardColor.class));
                    } else {
                        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    }
                }  else {
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                }


                finish();
            }
        },3000);
    }
}