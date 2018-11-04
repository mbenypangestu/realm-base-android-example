package com.mygetzu.mygetzuresto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.mygetzu.mygetzuresto.adapter.ResepListAdapter;
import com.mygetzu.mygetzuresto.helper.RealmHelper;
import com.mygetzu.mygetzuresto.model.ResepModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_add) FloatingActionButton btn_add;
    @BindView(R.id.rv_list_resep) RecyclerView rv_list_resep;

    Realm realm;
    RealmHelper realmHelper;
    RecyclerView rvResep;
    ResepListAdapter resepListAdapter;
    List<ResepModel> resepModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        rv_list_resep.setLayoutManager(new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv_list_resep.setHasFixedSize(true);


        Realm.init(MainActivity.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        realmHelper     = new RealmHelper(realm);
        resepModelList  = new ArrayList<>();

        resepModelList = realmHelper.getAllResep();
        
        show();

        btn_add.setOnClickListener(this);
    }

    private void show() {
        resepListAdapter = new ResepListAdapter(this, resepModelList);
        rv_list_resep.setAdapter(resepListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_add) {
            startActivity(new Intent(MainActivity.this, AddResepActivity.class));
        }
    }
}
