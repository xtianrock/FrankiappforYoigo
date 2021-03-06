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
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.appcloud.frankiappforyoigo.Activities.DetalleTerminalActivity;
import com.appcloud.frankiappforyoigo.Config;
import com.appcloud.frankiappforyoigo.POJO.OfertaTactica;
import com.appcloud.frankiappforyoigo.R;
import com.appcloud.frankiappforyoigo.Utils.FirebaseSingleton;
import com.appcloud.frankiappforyoigo.ViewHolders.TerminalesViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
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
        mDatabase = FirebaseSingleton.getDatabase().getReference();
        return parentView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Query query = mDatabase.child(Config.OFERTAS_TACTICAS).orderByChild("activo").equalTo(true);
        adapter = new FirebaseRecyclerAdapter<OfertaTactica,TerminalesViewHolder>(OfertaTactica.class,R.layout.cardview_terminales,TerminalesViewHolder.class,query) {

            @Override
            protected void populateViewHolder(TerminalesViewHolder viewHolder, OfertaTactica terminal, int position) {
                final DatabaseReference terminalRef = getRef(position);
                final String keyTerminal = terminalRef.getKey();
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), DetalleTerminalActivity.class);
                        intent.putExtra(Config.KEY_TERMINAL, keyTerminal);
                        ImageView ivFoto = (ImageView) v.findViewById(R.id.iv_terminal_foto);
                        //TextView tvNombre = (TextView) v.findViewById(R.id.tv_terminal_nombre);
                        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ivFoto.setTransitionName("foto_terminal");
                            Pair<View, String> p1 = Pair.create((View) ivFoto, "foto_terminal");
                            //Pair<View, String> p2 = Pair.create((View) tvNombre, "nombre_terminal");
                            ActivityOptionsCompat options = ActivityOptionsCompat.
                                    makeSceneTransitionAnimation(getActivity(), p1);
                            startActivity(intent, options.toBundle());
                        }
                        else
                        {
                            startActivity(intent);
                        }*/
                        startActivity(intent);

                    }
                };
                viewHolder.bindToOferta(getActivity().getApplicationContext(),terminal, keyTerminal,onClickListener);
            }
        };
        recyclerView.setAdapter(adapter);
    }

}
