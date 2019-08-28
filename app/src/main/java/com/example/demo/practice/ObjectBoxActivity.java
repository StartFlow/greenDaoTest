package com.example.demo.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ListView;
import android.widget.TextView;

import com.example.demo.practice.adapter.NoteAdapter;
import com.example.demo.practice.entity.Note;
import com.example.demo.practice.objectbox.ObjectBox;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import io.objectbox.Box;

public class ObjectBoxActivity extends AppCompatActivity {

    @BindView(R.id.note_input_edt)
    AppCompatEditText inputEdt;

    @BindView(R.id.note_list)
    ListView noteList;

    @BindView(R.id.add_bt)
    AppCompatButton addBt;

    private Box<Note> noteBox;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectbox_layout);
        ButterKnife.bind(this);
        noteBox = ObjectBox.getBoxStore().boxFor(Note.class);

        setUpViews();
        updateData();
    }

    private void setUpViews() {
        noteAdapter = new NoteAdapter();
        ListView listView = findViewById(R.id.note_list);
        listView.setAdapter(noteAdapter);
        addBt.setEnabled(false);
        inputEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addNote();
                    return true;
                }
                return false;
            }
        });
        inputEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    addBt.setEnabled(s.length()!=0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.add_bt)
    public void onAddNote(){
            addNote();
            inputEdt.setText("");
    }

    private void addNote(){
      Note note = new Note();
      note.setContent(inputEdt.getText().toString());
      DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
      String commit = "添加于 "+df.format(new Date());
      note.setCommitTime(commit);
      noteBox.put(note);
      updateData();
    }

    private void updateData(){
        List<Note> list =  noteBox.query().build().find();
        noteAdapter.setDataList(list);
    }

    @OnItemClick(R.id.note_list)
    public void onItemClick(int position){
        Note note =(Note) noteAdapter.getItem(position);
        noteBox.remove(note);
        updateData();
    }
}
