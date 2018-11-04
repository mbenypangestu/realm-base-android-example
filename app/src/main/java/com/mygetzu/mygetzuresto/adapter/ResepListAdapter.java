package com.mygetzu.mygetzuresto.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mygetzu.mygetzuresto.AddResepActivity;
import com.mygetzu.mygetzuresto.MainActivity;
import com.mygetzu.mygetzuresto.R;
import com.mygetzu.mygetzuresto.helper.RealmHelper;
import com.mygetzu.mygetzuresto.model.ResepModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ResepListAdapter extends RecyclerView.Adapter<ResepListAdapter.Holder> {
    List<ResepModel> resepModelList;
    Context context;
    Realm realm;
    RealmHelper realmHelper;
    ResepModel resepModel;

    public ResepListAdapter(Context context, List<ResepModel> resepModelList) {
        this.resepModelList = resepModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_resep,
                parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        holder.tv_nama.setText(resepModelList.get(position).getNama_resep());
        holder.tv_deskripsi.setText(resepModelList.get(position).getDeskripsi());
        holder.tv_id.setText(resepModelList.get(position).getId().toString());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm.init(context);
                RealmConfiguration configuration = new RealmConfiguration.Builder().build();
                realm = Realm.getInstance(configuration);

                realmHelper     = new RealmHelper(realm);
                resepModelList  = new ArrayList<>();

                realmHelper.delete(resepModelList.get(position).getId());

                context.getApplicationContext().startActivity(
                        new Intent(context.getApplicationContext(), AddResepActivity.class)
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return resepModelList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_nama, tv_deskripsi, tv_id;
        Button btnDelete;

        public Holder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            tv_id       = itemView.findViewById(R.id.tv_id);
            tv_nama     = itemView.findViewById(R.id.tv_nama);
            tv_deskripsi= itemView.findViewById(R.id.tv_deskripsi);
            btnDelete   = itemView.findViewById(R.id.btn_detail);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        }
    }
}
