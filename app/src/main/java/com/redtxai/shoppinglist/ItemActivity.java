package com.redtxai.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ItemActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtBrand;
    private EditText txtAmount;
    private FloatingActionButton btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);

        this.txtName = (EditText) findViewById(R.id.txtName);
        this.txtBrand = (EditText) findViewById(R.id.txtBrand);
        this.txtAmount = (EditText) findViewById(R.id.txtAmount);

        this.btnAddItem = (FloatingActionButton) findViewById(R.id.confirm_item);
        this.btnAddItem.setOnClickListener(this.getBtnAddClickEvent());
    }

    private View.OnClickListener getBtnAddClickEvent() {
        return new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent previousScreen = new Intent(ItemActivity.this.getApplicationContext(), MainActivity.class);
                previousScreen.putExtra("name", ItemActivity.this.txtName.getText().toString());
                previousScreen.putExtra("brand", ItemActivity.this.txtBrand.getText().toString());
                previousScreen.putExtra("amount", ItemActivity.this.txtAmount.getText().toString());
                ItemActivity.this.setResult(Activity.RESULT_OK, previousScreen);
                ItemActivity.this.finish();
            }
        };
    }
}
