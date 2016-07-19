package com.appcloud.frankiappforyoigo.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appcloud.frankiappforyoigo.Activities.DetalleTerminalActivity;
import com.appcloud.frankiappforyoigo.Configuracion;
import com.appcloud.frankiappforyoigo.POJO.OfertaTactica;
import com.appcloud.frankiappforyoigo.POJO.Reserva;
import com.appcloud.frankiappforyoigo.R;
import com.appcloud.frankiappforyoigo.Utils.FirebaseSingleton;
import com.appcloud.frankiappforyoigo.ViewHolders.ReservasViewHolder;
import com.appcloud.frankiappforyoigo.ViewHolders.TerminalesViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;


public class Reservas extends Fragment {

    DatabaseReference mDatabase;
    FirebaseRecyclerAdapter adapter;
    RecyclerView recyclerView;

    public Reservas() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_reservas, container, false);
        recyclerView = (RecyclerView)parentView.findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        mDatabase = FirebaseSingleton.getDatabase().getReference();
        return parentView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Query query = mDatabase.child("reservas").orderByChild("fechaReserva");
        adapter = new FirebaseRecyclerAdapter<Reserva,ReservasViewHolder>(Reserva.class,R.layout.cardview_reservas,ReservasViewHolder.class,query) {

            @Override
            protected void populateViewHolder(ReservasViewHolder viewHolder, Reserva model, int position) {
                viewHolder.populate(getContext(),viewHolder, model);
            }


        };
        recyclerView.setAdapter(adapter);
    }

}
