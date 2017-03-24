package com.appcloud.frankiappforyoigo.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.appcloud.frankiappforyoigo.AutoResizeTextView;
import com.appcloud.frankiappforyoigo.Config;
import com.appcloud.frankiappforyoigo.POJO.OfertaTactica;
import com.appcloud.frankiappforyoigo.R;
import com.appcloud.frankiappforyoigo.Utils.FirebaseSingleton;
import com.appcloud.frankiappforyoigo.ViewPagerTarifasAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetalleTerminalActivity extends AppCompatActivity {

    private String keyTerminal;
    private Context context = this;
    private ImageView ivFotoTerminal;
    private AutoResizeTextView tvPantalla, tvProcesador, tvRam, tvRom, tvTrasera, tvDelantera,tvBateria;
    private OfertaTactica ofertaTactica;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_terminal);
        DatabaseReference databaseReference = FirebaseSingleton.getDatabase().getReference();
        keyTerminal = getIntent().getStringExtra(Config.KEY_TERMINAL);
        ivFotoTerminal = (ImageView) findViewById(R.id.iv_terminal_foto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvPantalla = (AutoResizeTextView)findViewById(R.id.tv_espec_pantalla);
        tvProcesador = (AutoResizeTextView)findViewById(R.id.tv_espec_procesador);
        tvRam = (AutoResizeTextView)findViewById(R.id.tv_espec_ram);
        tvRom = (AutoResizeTextView)findViewById(R.id.tv_spec_rom);
        tvTrasera = (AutoResizeTextView)findViewById(R.id.tv_spec_trasera);
        tvDelantera = (AutoResizeTextView)findViewById(R.id.tv_spec_delantera);
        tvBateria = (AutoResizeTextView)findViewById(R.id.tv_espec_bateria);
        setSupportActionBar(toolbar);

        if (keyTerminal != null) {
            databaseReference.child("ofertas_tacticas").child(keyTerminal).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ofertaTactica = dataSnapshot.getValue(OfertaTactica.class);
                    Picasso.with(context)
                            .load(ofertaTactica.getFotoUrl())
                            .placeholder(R.drawable.phone_mockup)
                            .error(R.drawable.phone_mockup).
                            into(ivFotoTerminal);
                    getSupportActionBar().setTitle(keyTerminal);
                    tvPantalla.setText(ofertaTactica.getPantalla()+"''");
                    tvProcesador.setText(ofertaTactica.getProcesador());
                    tvRom.setText(ofertaTactica.getRom());
                    tvRam.setText(ofertaTactica.getRam());
                    tvTrasera.setText(ofertaTactica.getCamaraTrasera());
                    tvDelantera.setText(ofertaTactica.getCamaraDelantera());
                    tvBateria.setText(ofertaTactica.getBateria());

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tarifas móvil"));
        tabLayout.addTab(tabLayout.newTab().setText("Tarifas combinadas"));
        ViewPagerTarifasAdapter pagerAdapter = new ViewPagerTarifasAdapter(getSupportFragmentManager(),keyTerminal);
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //change ViewPager page when tab selected
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }





}
