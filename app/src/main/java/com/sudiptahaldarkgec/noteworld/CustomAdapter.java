package com.sudiptahaldarkgec.noteworld;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList book_id, book_title, note_content;
    Activity activity;

    CustomAdapter(Activity activity, Context context, ArrayList book_id, ArrayList book_title, ArrayList note_content){
        this.activity = activity;
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.note_content = note_content;

    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.note_view,parent, false);

        return new CustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        int pos = position;
        holder.noteId.setText(String.valueOf(book_id.get(position)));
        holder.noteTitle.setText(String.valueOf(book_title.get(position)));
        holder.noteContent.setText(String.valueOf(note_content.get(position)));


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateNote.class);
                intent.putExtra("id", String.valueOf(book_id.get(pos)));
                intent.putExtra("title", String.valueOf(book_title.get(pos)));
                intent.putExtra("content", String.valueOf(note_content.get(pos)));

//                context.startActivity(intent);

                activity.startActivityForResult(intent, 1);
                activity.finish();

            }
        });

    }

    @Override
    public int getItemCount() {
        return book_title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView noteId, noteTitle, noteContent;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            noteId = itemView.findViewById(R.id.serialNoteViewId);
            noteTitle = itemView.findViewById(R.id.titleNoteViewId);
            linearLayout = itemView.findViewById(R.id.linearViewId);
            noteContent = itemView.findViewById(R.id.contentViewId);
        }
    }
}
