package com.example.jina.imagedb;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListAdapter;

public class MainActivity extends ListActivity {
    private Cursor images;
    private MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new MyDatabase(this);
        images = db.getImages(); // you would not typically call this on the main thread

        ListAdapter adapter = new ImageCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                images,
                new String[] {"src", "key"},
                new int[] {R.id.pic, R.id.label});

        getListView().setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        images.close();
        db.close();
    }
}
