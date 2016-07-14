package com.appcloud.frankiappforyoigo.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.appcloud.frankiappforyoigo.Activities.DetalleTerminalActivity;
import com.appcloud.frankiappforyoigo.Activities.MainActivity;
import com.appcloud.frankiappforyoigo.Configuracion;
import com.appcloud.frankiappforyoigo.POJO.OfertaTactica;
import com.appcloud.frankiappforyoigo.R;
import com.appcloud.frankiappforyoigo.Utils.FirebaseSingleton;
import com.appcloud.frankiappforyoigo.ViewHolders.TerminalesViewHolder;
import com.appcloud.frankiappforyoigo.ViewPagerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private final static int VIEWPAGER_TIME = 5000;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter adapterMasVendidos;
    private FirebaseRecyclerAdapter adapterLoquesea;
    private RecyclerView rvMasVendidos;
    private RecyclerView rvLoquesea;
    private ViewPager viewPager;
    private ViewPagerAdapter  viewPagerAdapter;
    private RadioButton rb1, rb2, rb3, rb4, rb5;
    private Handler handler;
    private Runnable runnable;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View parentView = inflater.inflate(R.layout.fragment_home, container, false);
        mDatabase = FirebaseSingleton.getDatabase().getReference();
        rvMasVendidos = (RecyclerView)parentView.findViewById(R.id.list_mas_vendidos);
        rvLoquesea = (RecyclerView)parentView.findViewById(R.id.list);
        rvMasVendidos.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rvLoquesea.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference imagesRef = storage.getReferenceFromUrl("gs://frankiapp-for-yoigo.appspot.com").child("images");

        final ArrayList<Uri>images = new ArrayList<>();
        imagesRef.child("oferta1.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.i("uri",uri.toString());
                        images.add(uri);
                        images.add(uri);
                        images.add(uri);
                        images.add(uri);
                        images.add(uri);
                        prepareViewPager(parentView, images);
                    }
                });

        return parentView;
    }

    private void prepareViewPager(View parentView, ArrayList<Uri> images) {
        // Locate the ViewPager in viewpager_main.xml
        viewPager = (ViewPager) parentView.findViewById(R.id.pager);
        rb1 = (RadioButton)parentView.findViewById(R.id.rb1);
        rb2 = (RadioButton)parentView.findViewById(R.id.rb2);
        rb3 = (RadioButton)parentView.findViewById(R.id.rb3);
        rb4 = (RadioButton)parentView.findViewById(R.id.rb4);
        rb5 = (RadioButton)parentView.findViewById(R.id.rb5);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetHandler();
               switch (position){
                    case 0: rb1.setChecked(true);
                        break;
                    case 1: rb2.setChecked(true);
                        break;
                    case 2: rb3.setChecked(true);
                        break;
                    case 3: rb4.setChecked(true);
                        break;
                    case 4: rb5.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // Pass results to ViewPagerAdapter Class
        viewPagerAdapter = new ViewPagerAdapter(getActivity(), images);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(viewPagerAdapter);



        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    viewPager.setCurrentItem(0,true);
                    resetHandler();
                }
            }
        });
        rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    viewPager.setCurrentItem(1,true);
                    resetHandler();
                }
            }
        });
        rb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    viewPager.setCurrentItem(2,true);
                    resetHandler();
                }
            }
        });
        rb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    viewPager.setCurrentItem(3,true);
                    resetHandler();
                }
            }
        });
        rb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    viewPager.setCurrentItem(4,true);
                    resetHandler();
                }
            }
        });
    }

    private void resetHandler(){
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable,VIEWPAGER_TIME);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Query query = mDatabase.child("ofertas_tacticas").orderByChild("foto").limitToFirst(5);
        adapterMasVendidos = new FirebaseRecyclerAdapter<OfertaTactica,TerminalesViewHolder>(OfertaTactica.class,R.layout.cardview_terminales_home,TerminalesViewHolder.class,query) {

            @Override
            protected void populateViewHolder(TerminalesViewHolder viewHolder, OfertaTactica terminal, int position) {
                final DatabaseReference terminalRef = getRef(position);
                final String keyTerminal = terminalRef.getKey();
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), DetalleTerminalActivity.class);
                        intent.putExtra(Configuracion.KEY_TERMINAL, keyTerminal);
                        ImageView ivFoto = (ImageView) v.findViewById(R.id.iv_terminal_foto);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ivFoto.setTransitionName("foto_terminal");
                        }
                        Pair<View, String> p1 = Pair.create((View) ivFoto, "foto_terminal");
                        //Pair<View, String> p2 = Pair.create((View) tvNombre, "nombre_terminal");
                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation(getActivity(), p1);
                        startActivity(intent, options.toBundle());
                    }
                };
                viewHolder.bindToOferta(getActivity().getApplicationContext(),terminal,onClickListener);
            }
        };
        rvMasVendidos.setAdapter(adapterMasVendidos);
        Query query2 = mDatabase.child("ofertas_tacticas").limitToFirst(5);
        adapterLoquesea = new FirebaseRecyclerAdapter<OfertaTactica,TerminalesViewHolder>(OfertaTactica.class,R.layout.cardview_terminales_home,TerminalesViewHolder.class,query2) {

            @Override
            protected void populateViewHolder(TerminalesViewHolder viewHolder, OfertaTactica terminal, int position) {
                final DatabaseReference terminalRef = getRef(position);
                final String keyTerminal = terminalRef.getKey();
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), DetalleTerminalActivity.class);
                        intent.putExtra(Configuracion.KEY_TERMINAL, keyTerminal);
                        ImageView ivFoto = (ImageView) v.findViewById(R.id.iv_terminal_foto);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ivFoto.setTransitionName("foto_terminal");
                        }
                        Pair<View, String> p1 = Pair.create((View) ivFoto, "foto_terminal");
                        //Pair<View, String> p2 = Pair.create((View) tvNombre, "nombre_terminal");
                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation(getActivity(), p1);
                        startActivity(intent, options.toBundle());
                    }
                };
                viewHolder.bindToOferta(getActivity().getApplicationContext(),terminal,onClickListener);
            }
        };
        rvLoquesea.setAdapter(adapterLoquesea);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if(viewPagerAdapter!=null && viewPagerAdapter.getCount()>0){
                        if(viewPager.getCurrentItem()+1==viewPagerAdapter.getCount()){
                            viewPager.setCurrentItem(0);
                        }else{
                            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                        }
                    }
                } finally {
                    handler.postDelayed(runnable, VIEWPAGER_TIME);
                }
            }
        };
        handler.postDelayed(runnable, VIEWPAGER_TIME);

    }

}
