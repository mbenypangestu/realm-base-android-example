package com.mygetzu.mygetzuresto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mygetzu.mygetzuresto.helper.RealmHelper;
import com.mygetzu.mygetzuresto.model.ResepModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AddResepActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btnSimpan) Button btnSimpan;
    @BindView(R.id.et_nama) EditText et_nama;
    @BindView(R.id.et_deskripsi) EditText et_deskripsi;
    @BindView(R.id.et_resep) EditText et_resep;

    Realm realm;
    RealmHelper realmHelper;
    ResepModel resepModel;

    String nama, deskripsi, resep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resep);

        ButterKnife.bind(this);

        Realm.init(AddResepActivity.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        btnSimpan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSimpan) {
            nama        = et_nama.getText().toString();
            deskripsi   = et_deskripsi.getText().toString();
            resep       = et_resep.getText().toString();

            if (!nama.equals("") && !deskripsi.equals("") && !resep.equals("")) {
                resepModel = new ResepModel();
                resepModel.setNama_resep(nama);
                resepModel.setDeskripsi(deskripsi);
                resepModel.setResep(resep);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(resepModel);

                startActivity(new Intent(AddResepActivity.this, MainActivity.class));

                Toast.makeText(getApplicationContext(), "Data Berhasil di simpan !", Toast.LENGTH_LONG);
            } else {
                Toast.makeText(getApplicationContext(), "Isian tidak boleh kosong", Toast.LENGTH_LONG);
            }
        }
    }
}
