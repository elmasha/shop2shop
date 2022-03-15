package com.shop.shop2shop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.shop.shop2shop.Fragments.DashFragment;
import com.shop.shop2shop.Fragments.NotificationFragment;
import com.shop.shop2shop.Fragments.OrdersFragment;
import com.shop.shop2shop.Fragments.ProfileFragment;
import com.shop.shop2shop.Fragments.SettingsFragment;
import com.shop.shop2shop.R;

public class DashboardActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private TextView chooseCat,chooseCounty,SearchCategory,OpenDrawer,notification;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment SelectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_dashboard:

                    SelectedFragment = new DashFragment();

                    break;
                case R.id.navigation_orders:

                    SelectedFragment = new OrdersFragment();

                    break;
                case R.id.navigation_profile:

                    SelectedFragment = new ProfileFragment();

                    break;
                case R.id.navigation_settings:

                    SelectedFragment = new SettingsFragment();

                    break;

            }

            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_provider,

                    SelectedFragment).commit();

            return true;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_provider,new
                DashFragment()).commit();

        dl = (DrawerLayout) findViewById(R.id.drawer);
        OpenDrawer = findViewById(R.id.drawerOpen2);
        notification = findViewById(R.id.notification);



        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_provider,new
                        NotificationFragment()).commit();
            }
        });


        OpenDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!dl.isDrawerVisible(GravityCompat.START)){
                    dl.openDrawer(GravityCompat.START);
                }else if (dl.isDrawerVisible(GravityCompat.START)){
                    dl.closeDrawer(GravityCompat.START);
                }
            }
        });
    }




    private Toast backToast;
    private void ToastBack(String message){


        backToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        backToast.show();
    }

    private long backPressedTime;
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2500 > System.currentTimeMillis()) {
            backToast.cancel();
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
            super.onBackPressed();
            finish();
            return;
        } else {

            ToastBack("Double tap to exit");
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_provider,new
                    DashFragment()).commit();
        }
        backPressedTime = System.currentTimeMillis();
    }
}