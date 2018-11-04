package com.mygetzu.mygetzuresto.helper;

import android.util.Log;

import com.mygetzu.mygetzuresto.model.ResepModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void save(final ResepModel resepModel) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Log.d("Created : ", "Database was created");

                    Number currentIdNum = realm.where(ResepModel.class).max("id");
                    int nextId;

                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }

                    resepModel.setId(nextId);
                    ResepModel model = realm.copyToRealm(resepModel);
                } else {
                    Log.e("Error : ", "Database not exist");
                }
            }
        });
    }

    public List<ResepModel> getAllResep() {
        RealmResults<ResepModel> results = realm.where(ResepModel.class).findAll();
        return results;
    }

    public void delete(Integer id) {
        final RealmResults<ResepModel> model = realm.where(ResepModel.class)
                .equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

}
