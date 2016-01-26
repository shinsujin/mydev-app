package com.example.ktds.myapplication.store;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ktds.myapplication.R;

import java.util.List;

/**
 * Created by KTDS on 2016-01-26.
 */
public class StoreListAdapter extends ArrayAdapter{

    private int resource;
    private LayoutInflater inflater;
    private Context context;

    public StoreListAdapter(Context ctx, int resourceId,  List listStore) {
        super( ctx, resourceId, listStore );
        resource = resourceId;
        inflater = LayoutInflater.from( ctx );
        context = ctx;
    }

    @Override
    public View getView ( int position, View convertView, ViewGroup parent ) {

        convertView = (RelativeLayout) inflater.inflate( resource, null );

        StoreInfo storeInfo = (StoreInfo) getItem(position);

        TextView txtName = (TextView) convertView.findViewById(R.id.storeName);
        txtName.setText(storeInfo.getName());

        TextView txtGrade = (TextView) convertView.findViewById(R.id.storeGrade);
        txtGrade.setText(storeInfo.getGrade());

        TextView txtNumber = (TextView) convertView.findViewById(R.id.storeNumber);
        txtNumber.setText(storeInfo.getNumber());

        ImageView imageStore = (ImageView) convertView.findViewById(R.id.store_image);
        String uri = "mipmap/" + storeInfo.getImage();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable image = context.getResources().getDrawable(imageResource);
        imageStore.setImageDrawable(image);
        return convertView;
    }
}
