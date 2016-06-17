package com.appcloud.frankiappforyoigo.Activities;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.appcloud.frankiappforyoigo.POJO.OfertaTactica;
import com.appcloud.frankiappforyoigo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetalleTerminalActivity extends AppCompatActivity {

    Context context = this;
    ImageView fotoTerminal;
    CollapsingToolbarLayout collapsingToolbarLayout;
    OfertaTactica ofertaTactica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_terminal);

        String keyTerminal = getIntent().getStringExtra("keyTerminal");

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        fotoTerminal = (ImageView)findViewById(R.id.iv_terminal_foto);

        if(keyTerminal!=null)
        {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("ofertas_tacticas").child(keyTerminal).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ofertaTactica = dataSnapshot.getValue(OfertaTactica.class);
                    collapsingToolbarLayout.setTitle(ofertaTactica.getTerminal());
                    Picasso.with(context).load(ofertaTactica.getFoto()).into(fotoTerminal);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }
}
