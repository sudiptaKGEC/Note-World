package com.sudiptahaldarkgec.noteworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class WriteNote extends AppCompatActivity {
    EditText titleEditTxt, contentEditTxt;
    AppCompatButton saveNoteButton;

    String title, content;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);

        titleEditTxt = findViewById(R.id.noteTitleEditTxtWriteNoteId);
        contentEditTxt = findViewById(R.id.noteContentEditTxtWriteNoteId);



        saveNoteButton = findViewById(R.id.saveNoteButtonWriteNoteId);

        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // running the save note function
                saveNotesClass();

                // below commented code for save note .because save note er void function made below so this is code is not needed

//                MySQLDataBaseHelper mySQLDataBaseHelper = new MySQLDataBaseHelper(WriteNote.this);
//                title = titleEditTxt.getText().toString().trim();
//                content = contentEditTxt.getText().toString().trim();
//
//                if(title.equals("")){
//                    Toast.makeText(WriteNote.this, "title can not be empty", Toast.LENGTH_SHORT).show();
//                }
//                else{
//
//                    mySQLDataBaseHelper.saveNote(title, content);
//
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }




            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // on backPress notes will save
        title = titleEditTxt.getText().toString().trim();
        content = contentEditTxt.getText().toString().trim();

        if(title.equals("") && content.equals("")){
            goBackToMainActivity();
        }
        else if(title.equals("")){

                Toast.makeText(WriteNote.this, "title can not be empty", Toast.LENGTH_SHORT).show();
            
        }
        else {
            saveNotesClass();
            goBackToMainActivity();
        }



    }

    void saveNotesClass(){
        MySQLDataBaseHelper mySQLDataBaseHelper = new MySQLDataBaseHelper(WriteNote.this);
        title = titleEditTxt.getText().toString().trim();
        content = contentEditTxt.getText().toString().trim();

        if(title.equals("")){
            Toast.makeText(WriteNote.this, "title can not be empty", Toast.LENGTH_SHORT).show();
        }
        else{

            mySQLDataBaseHelper.saveNote(title, content);

            goBackToMainActivity();
        }
    }

    void goBackToMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }


}