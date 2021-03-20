package com.example.expandablefilter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private RecyclerView recyclerView;
    private ParentAdapter parentAdapter;
    private ArrayList<ParentItem> parentItems;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.parentRecyclerView);


        parentItems = prepareData();

        parentAdapter = new ParentAdapter(parentItems, MainActivity.this);
        LinearLayoutManager manager  = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(parentAdapter);

        editText = (EditText)findViewById(R.id.editText);
        editText.addTextChangedListener(this);
    }

    private ArrayList<ParentItem> prepareData() {
        ArrayList<ParentItem> parentItems = new ArrayList<ParentItem>();

        ParentItem parentItem1 = new ParentItem();
        parentItem1.parentName = "그룹 1";
        parentItem1.childItems = new ArrayList<ChildItem>();
        ChildItem childItem1 = new ChildItem();
        childItem1.childName = "이준호";
        ChildItem childItem2 = new ChildItem();
        childItem2.childName = "이지혜";
        parentItem1.childItems.add(childItem1);
        parentItem1.childItems.add(childItem2);
        parentItems.add(parentItem1);

        ParentItem parentItem2 = new ParentItem();
        parentItem2.parentName = "그룹 2";
        parentItem2.childItems = new ArrayList<ChildItem>();
        ChildItem childItem3 = new ChildItem();
        childItem3.childName = "이지은";
        parentItem2.childItems.add(childItem3);
        parentItems.add(parentItem2);

        ParentItem parentItem3 = new ParentItem();
        parentItem3.parentName = "그룹 3";
        parentItem3.childItems = new ArrayList<ChildItem>();
        ChildItem childItem4 = new ChildItem();
        childItem4.childName = "박서준";
        ChildItem childItem5 = new ChildItem();
        childItem5.childName = "이영지";
        ChildItem childItem6 = new ChildItem();
        childItem6.childName = "박지민";
        ChildItem childItem7 = new ChildItem();
        childItem7.childName = "박나래";
        parentItem3.childItems.add(childItem4);
        parentItem3.childItems.add(childItem5);
        parentItem3.childItems.add(childItem6);
        parentItem3.childItems.add(childItem7);
        parentItems.add(parentItem3);

        return parentItems;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        parentAdapter.getFilter().filter(charSequence);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


}