package com.example.jina.imagedb;


import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageCursorAdapter extends SimpleCursorAdapter {
    private Cursor c;
    private Context context;

    public ImageCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
        this.c = c;
        this.context = context;
    }

    public View getView(int pos, View inView, ViewGroup parent) {
        View v = inView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.row_layout, null);
        }
        this.c.moveToPosition(pos);

        String key = this.c.getString(this.c.getColumnIndex("key"));
        byte[] image = this.c.getBlob(this.c.getColumnIndex("src"));

        ImageView iv = v.findViewById(R.id.pic);
        if (image != null && image.length > 3) {
            // If there is no image in the database "NA" is stored instead of a blob
            // test if there more than 3 chars "NA" + a terminating char if more than
            // there is an image otherwise load the default
            iv.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
        } else {
            iv.setImageResource(R.drawable.icon);
        }

        TextView fname = v.findViewById(R.id.label);
        fname.setText(key);

        return(v);
    }
}
