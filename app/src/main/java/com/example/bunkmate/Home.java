package com.example.bunkmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        drawerLayout = findViewById(R.id.main);
        drawerToggle=findViewById(R.id.imageButton);
        navigationView = findViewById(R.id.nav_view);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,new HomePage());
        fragmentTransaction.commit();
        drawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case (R.id.home): {
                        FragmentManager fragmentManager=getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayout,new HomePage());
                        fragmentTransaction.commit();
                        drawerLayout.close();
                        break;
                    }
                    case (R.id.attendance): {
                        FragmentManager fragmentManager=getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayout,new Attendance());
                        fragmentTransaction.commit();
                        drawerLayout.close();
                        break;
                    }
                    case (R.id.gpa): {
                        FragmentManager fragmentManager=getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayout,new Target());
                        fragmentTransaction.commit();
                        drawerLayout.close();
                        break;
                    }
                    case (R.id.about):{
                        Toast.makeText(Home.this, "about", Toast.LENGTH_SHORT).show();
                        drawerLayout.close();
                        break;
                    }
                    case (R.id.course):{
                        FragmentManager fragmentManager=getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayout,new Course());
                        fragmentTransaction.commit();
                        drawerLayout.close();
                        break;
                    }
                    case (R.id.login):{
                        Toast.makeText(Home.this, "login/sign-up", Toast.LENGTH_SHORT).show();
                        drawerLayout.close();
                        break;
                    }
                }
                return false;
            }
        });
    }
    public void openNavigationDrawer(){
        if(drawerLayout!=null){
            drawerLayout.open();
        }
    }
}