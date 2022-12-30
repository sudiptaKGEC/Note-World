package com.sudiptahaldarkgec.noteworld;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton newNoteButton;

    MySQLDataBaseHelper mySQLDataBaseHelper;
    ArrayList<String>note_id, note_title, note_content;
    CustomAdapter customAdapter;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    private long pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleViewMainId);
        newNoteButton = findViewById(R.id.fabMainId);

        drawerLayout = findViewById(R.id.drawerId);
        navigationView = findViewById(R.id.drawerNavigationId);
        toolbar = findViewById(R.id.home_toolbar_id);

        toolbar.setTitle("NOTE WORlD");
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.firstId) {
                    Toast.makeText(MainActivity.this, "touch on first", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.shareAppId2) {
                    Toast.makeText(MainActivity.this, "touch on share", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "touched", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        newNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WriteNote.class);
                startActivity(intent);
                finish();

            }
        });


        mySQLDataBaseHelper = new MySQLDataBaseHelper(MainActivity.this);
        note_id = new ArrayList<>();
        note_title = new ArrayList<>();
        note_content = new ArrayList<>();


        storeDataInArray();

        customAdapter = new CustomAdapter(MainActivity.this, this,  note_id, note_title, note_content);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArray(){
        Cursor cursor= mySQLDataBaseHelper.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else {
            while(cursor.moveToNext()){
                note_id.add(cursor.getString(0));
                note_title.add(cursor.getString(1));
                note_content.add(cursor.getString(2));

            }
        }

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            if (pressedTime + 1500 > System.currentTimeMillis()) {
                super.onBackPressed();
                finish();
            } else {
                Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
            }
            pressedTime = System.currentTimeMillis();

        }

    }
}