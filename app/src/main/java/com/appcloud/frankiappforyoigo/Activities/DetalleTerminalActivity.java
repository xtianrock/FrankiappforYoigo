package com.appcloud.frankiappforyoigo.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.appcloud.frankiappforyoigo.Configuracion;
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
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private OfertaTactica ofertaTactica;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_terminal);
        keyTerminal = getIntent().getStringExtra(Configuracion.KEY_TERMINAL);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ivFotoTerminal = (ImageView) findViewById(R.id.iv_terminal_foto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (keyTerminal != null) {
            DatabaseReference databaseReference = FirebaseSingleton.getDatabase().getReference();
            databaseReference.child("ofertas_tacticas").child(keyTerminal).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ofertaTactica = dataSnapshot.getValue(OfertaTactica.class);
                    Picasso.with(context).load(ofertaTactica.getFoto()).into(ivFotoTerminal);
                    getSupportActionBar().setTitle(ofertaTactica.getTerminal());

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
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
