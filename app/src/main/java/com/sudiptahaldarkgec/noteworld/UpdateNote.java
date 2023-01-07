package com.sudiptahaldarkgec.noteworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class UpdateNote extends AppCompatActivity {
    EditText noteTitleTxt, noteContentTxt;
    AppCompatButton updateNoteButton, deleteButton;

    String id, title, content;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        noteTitleTxt = findViewById(R.id.noteTitleEditTxtUpdateNoteId);
        noteContentTxt = findViewById(R.id.noteContentEditTxtUpdateNoteId);
        updateNoteButton = findViewById(R.id.saveNoteButtonUpdateNoteId);
        deleteButton = findViewById(R.id.deleteNoteButtonUpdateNoteId);
        toolbar = findViewById(R.id.toolbarUpdateId);

        getAndSetIntentData();

        updateNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MySQLDataBaseHelper myDb= new MySQLDataBaseHelper(UpdateNote.this);
//                myDb.updateData(id, noteTitleTxt.getText().toString(), noteContentTxt.getText().toString());
//                Toast.makeText(UpdateNote.this, "updated successfully", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//                finish();
                updateNote();
                Toast.makeText(UpdateNote.this, "updated successfully", Toast.LENGTH_SHORT).show();
            }
        });


            toolbar.setTitle(title);


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });


    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title")
                && getIntent().hasExtra("content")){

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            content = getIntent().getStringExtra("content");


            noteTitleTxt.setText(title);
            noteContentTxt.setText(content);



        }
        else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + title + " ?");
        builder.setMessage("are ouy sure ??");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MySQLDataBaseHelper mySQLHelper = new MySQLDataBaseHelper(UpdateNote.this);
                mySQLHelper.deleteData(id);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updateNote();
    }
    void updateNote(){
        MySQLDataBaseHelper myDb= new MySQLDataBaseHelper(UpdateNote.this);
        myDb.updateData(id, noteTitleTxt.getText().toString(), noteContentTxt.getText().toString());

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}