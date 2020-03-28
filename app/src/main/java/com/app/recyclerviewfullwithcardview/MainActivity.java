package com.app.recyclerviewfullwithcardview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExampleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<ExampleItem> exampleItems;

    private Button buttonInsert;
    private Button buttonRemove;
    private EditText editTextInsert;
    private EditText editTextRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        buttonInsert = findViewById(R.id.button_insert);
        buttonRemove = findViewById(R.id.button_remove);
        editTextInsert = findViewById(R.id.edittext_insert);
        editTextRemove = findViewById(R.id.edittext_remove);


        createExampleList();

        /*
        Setting RecyclerView
         */
        buildRecyclerView();

        /* Set Listeners */
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insert at Given Position
                int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Remove at Given Position
                int position = Integer.parseInt(editTextRemove.getText().toString());
                removeItem(position);
            }
        });

    }

    public void createExampleList() {
        exampleItems = new ArrayList<>();
        exampleItems.add(new ExampleItem(R.drawable.ic_android, "Line 1", "Line 2"));
        exampleItems.add(new ExampleItem(R.drawable.ic_audio, "Line 3", "Line 4"));
        exampleItems.add(new ExampleItem(R.drawable.ic_sun, "Line 5", "Line 6"));
        exampleItems.add(new ExampleItem(R.drawable.ic_android, "Line 1", "Line 2"));
        exampleItems.add(new ExampleItem(R.drawable.ic_audio, "Line 3", "Line 4"));
        exampleItems.add(new ExampleItem(R.drawable.ic_sun, "Line 5", "Line 6"));
    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(exampleItems);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        /* Set Custom Listeners on Adapter by Interface*/
        adapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position, "Clicked");
            }

            @Override
            public void onDeleteClick(int position) {
                    removeItem(position);
            }
        });
    }

    /*
    Add Item into RecyclerView
     */
    public void insertItem(int position) {
        exampleItems.add(position, new ExampleItem(R.drawable.ic_android, "New Item At Position" + position, "This is Line 2"));
        adapter.notifyItemInserted(position);
        //adapter.notifyDataSetChanged();
    }

    /*
    Remove Item From RecyclerView
     */
    public void removeItem(int position) {
        exampleItems.remove(position);
        adapter.notifyItemRemoved(position);
        //adapter.notifyDataSetChanged();
    }

    public void changeItem(int position, String text) {
        exampleItems.get(position).changeText(text);
        adapter.notifyItemChanged(position);
    }

}
