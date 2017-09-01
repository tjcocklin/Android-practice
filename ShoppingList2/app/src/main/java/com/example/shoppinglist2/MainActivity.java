package com.example.shoppinglist2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


import static android.widget.AdapterView.*;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    EditText itemInput;
    EditText priceInput;
    EditText quantityInput;

    TextView txtTotal;
    String formatedTotal;
    double numTotal=0;
    ArrayList<ShoppingItems> theList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Started");

        ListView listView= (ListView) findViewById(R.id.list);


        final ShoppingListAdapter adapter = new ShoppingListAdapter(MainActivity.this, R.layout.adapter_view_layout,theList);
        listView.setAdapter(adapter);

        Button addButton= (Button) findViewById(R.id.addBtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TextView
                 txtTotal= (TextView) findViewById(R.id.total);


                 //get the EditText views by their ids
                EditText itemInput = (EditText) findViewById(R.id.addItem);
                EditText priceInput = (EditText) findViewById(R.id.addPrice);
                EditText quantityInput = (EditText) findViewById(R.id.addQuantity);

                //Store the strings for the EditText views
                String iName = itemInput.getText().toString();
                String iPrice = priceInput.getText().toString();
                String iQuantity = quantityInput.getText().toString();

                //Reset the EditText views to blank.
                itemInput.setText("");
                priceInput.setText("");
                quantityInput.setText("");


                //try catch.
            try{
                if(iName.isEmpty()||iPrice.isEmpty()||iQuantity.isEmpty())
                    throw new Exception("you forgot an input!");


                double checkPrice= -1;
                int checkQuantity=-1;

                try {
                    checkPrice = Double.parseDouble(iPrice);
                     checkQuantity = Integer.parseInt(iQuantity);
                }

                catch(NumberFormatException e){
                    throw new Exception("Both price and quantity must be numbers");
                }

                if (checkPrice < 0 || checkQuantity < 0) {

                    throw new Exception("both Item and quantity must be >= 0");
                    //throw new excpetion and dsiplay as toast
                }
                adapter.add(new ShoppingItems(iName, checkPrice, checkQuantity));
                numTotal+= checkPrice * checkQuantity;
                formatedTotal= String.format("%1.2f",numTotal);
                txtTotal.setText("Total: $"+formatedTotal);
                //theList.add(new ShoppingItems(iName,checkPrice,checkQuantity));
                adapter.notifyDataSetChanged();

            }catch (Exception e){
                    raiseToast(e);
                }
            }
        });
        Button clearButton=(Button) findViewById(R.id.clrBtn);
        clearButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!theList.isEmpty()){
                    adapter.clear();
                    numTotal=0;

                    formatedTotal= String.format("%1.2f",numTotal);
                    txtTotal.setText("Total: $"+formatedTotal);
                }
            }
        });

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,int position, long id) {

               // Log.d(TAG, "onItemClick: Clicked on ListViewItem at: "+position);
                ShoppingItems item = (ShoppingItems) parent.getItemAtPosition(position);

                numTotal-= item.getPrice()*item.getQuantity();
                //Log.d(TAG, "onItemClick: item price: "+ item.getPrice());
                //Log.d(TAG, "onItemClick: numTotal: "+numTotal);
                formatedTotal= String.format("%1.2f",numTotal);
                //Log.d(TAG, "onItemClick: formatedTotal: "+formatedTotal);
                txtTotal.setText("Total: $"+formatedTotal);
               // Log.d(TAG, "Removing ListItem name: "+item.getName());
                theList.remove(item);
                adapter.notifyDataSetChanged();

            }

        });


    }
    private void raiseToast(Exception e){

        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

    }

}
