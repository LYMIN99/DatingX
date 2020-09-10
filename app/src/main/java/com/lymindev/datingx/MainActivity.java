package com.lymindev.datingx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lymindev.datingx.databinding.ActivityMainBinding;
import com.lymindev.datingx.fragments.AccountFragment;
import com.lymindev.datingx.fragments.ChatsFragment;
import com.lymindev.datingx.fragments.ExploreFragment;

import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private com.lymindev.datingx.databinding.ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.navigation.setSelectedItemId(R.id.bot_explore);
        setFragment(new ExploreFragment());
        binding.navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bot_message : setFragment(new ChatsFragment()); break;
                    case R.id.bot_explore : setFragment(new ExploreFragment()); break;
                    case R.id.bot_settings : setFragment(new AccountFragment()); break;
                }
                return true;
            }
        });
    }

    protected void setFragment(Fragment fragment) {
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.layout_frame, fragment);
        t.commit();
    }
}