package com.redtxai.shoppinglist;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.redtxai.shoppinglist.DAO.ItemListManager;
import com.redtxai.shoppinglist.model.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_ITEM = 0;
    private LinearLayout mainContainer;
    private List<Item> itemList;
    private ItemListManager itemListManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            this.itemListManager = new ItemListManager(this);
            this.itemList = this.itemListManager.getItemList();
        } catch (IOException e) {}


        this.mainContainer = (LinearLayout) findViewById(R.id.main_container);

        if (this.itemList.isEmpty()) {
            this.mainContainer.addView(this.getTextView("It's empty."));
        } else {
            for (int i = 0 ; i <= (this.itemList.size()-1) ; i++) {
                this.mainContainer.addView(this.getTextView(this.itemList.get(i).toString()));
            }
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this.getBtnAddClickEvent());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        try {
            this.itemListManager.saveItemList(this.itemList);
        } catch (IOException e) {}

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private View.OnClickListener getBtnAddClickEvent() {
        return new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent it = new Intent(getApplicationContext(), ItemActivity.class);
                startActivityForResult(it, REQUEST_ITEM);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ITEM && resultCode == Activity.RESULT_OK) {
            Item item = new Item(data.getStringExtra("name")
                                ,data.getStringExtra("brand")
                                ,Integer.parseInt(data.getStringExtra("amount")));
            item.setId(this.itemListManager.getIdControl());
            this.itemList.add(item);
            this.mainContainer.addView(this.getTextView(item.toString()));
        }
    }

    private TextView getTextView(String text) {
        final TextView rowTextView = new TextView(this);
        rowTextView.setText(text);
        return rowTextView;
    }
}
