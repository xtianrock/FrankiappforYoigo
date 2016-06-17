package com.appcloud.frankiappforyoigo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.appcloud.frankiappforyoigo.POJO.OfertaTactica;
import com.appcloud.frankiappforyoigo.R;
import com.appcloud.frankiappforyoigo.ViewHolders.TerminalesViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class Terminales extends Fragment {

    DatabaseReference mDatabase;
    FirebaseRecyclerAdapter adapter;
    RecyclerView recyclerView;

    public Terminales() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_terminales, container, false);
        recyclerView = (RecyclerView)parentView.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return parentView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Query query = mDatabase.child("ofertas_tacticas");

        adapter = new FirebaseRecyclerAdapter<OfertaTactica,TerminalesViewHolder>(OfertaTactica.class,R.layout.cardview_terminales,TerminalesViewHolder.class,query) {

            @Override
            protected void populateViewHolder(TerminalesViewHolder viewHolder, OfertaTactica terminal, int position) {
                final DatabaseReference terminalRef = getRef(position);
                final String keyTerminal = terminalRef.getKey();
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), DetalleTerminalActivity.class);
                        intent.putExtra("keyTerminal", keyTerminal);
                        ImageView ivFoto = (ImageView) v.findViewById(R.id.iv_terminal_foto);
                        TextView tvNombre = (TextView) v.findViewById(R.id.tv_terminal_nombre);
                        Pair<View, String> p1 = Pair.create((View) ivFoto, "foto_terminal");
                       // Pair<View, String> p2 = Pair.create((View) tvNombre, "nombre_terminal");
                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation(getActivity(), p1);
                        startActivity(intent, options.toBundle());
                    }
                };
                viewHolder.bindToOferta(getActivity().getApplicationContext(),terminal,onClickListener);
            }
        };
        recyclerView.setAdapter(adapter);
    }

}
