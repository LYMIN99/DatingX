package com.lymindev.datingx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserManager;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lymindev.datingx.firebases.FirebaseService;
import com.lymindev.datingx.managers.UsersManager;
import com.lymindev.datingx.model.user.UsersModel;
import com.lymindev.datingx.model.user.UsersRealm;
import com.lymindev.datingx.view.activities.BaseActivity;
import com.lymindev.datingx.view.activities.StepperWizardColor;
import com.lymindev.datingx.view.activities.login.LoginActivity;

import io.realm.Realm;

public class SplashScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Realm.init(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (firebaseUser != null) {
                    UsersRealm usersRealm = new UsersManager().getUserInfo(firebaseUser.getUid());
                    if (usersRealm !=null) {
                        if (usersRealm.getName().equals("")) {
                            startActivity(new Intent(SplashScreenActivity.this, StepperWizardColor.class));
                        } else {
                            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                        }
                }

                }  else {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                }


                finish();
            }
        },3000);
    }
}