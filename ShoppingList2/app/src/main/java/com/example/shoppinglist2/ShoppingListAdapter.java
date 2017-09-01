package com.example.shoppinglist2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jcocklin on 6/26/2017.
 */

public class ShoppingListAdapter extends ArrayAdapter<ShoppingItems> {

    private static final String TAG = "ShoppingListAdapter";
    private Context context;
    private int resource;

    public ShoppingListAdapter( Context context, int resource, ArrayList<ShoppingItems> objects) {
        super(context, resource, objects);
        this.context= context;
        this.resource= resource;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String name= getItem(position).getName();
        double price = getItem(position).getPrice();
        int quantity= getItem(position).getQuantity();


        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView= inflater.inflate(this.resource, parent, false);

        TextView txtItem = (TextView) convertView.findViewById(R.id.txtItem);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.txtPrice);
        TextView txtQuantity= (TextView) convertView.findViewById(R.id.txtQuantity);



        txtItem.setText(name);

        String formatedPrice= String.format( "%1.2f",price);
        txtPrice.setText("$"+formatedPrice);

        txtQuantity.setText("Qty:"+quantity);
        

        return convertView;
    }
}
