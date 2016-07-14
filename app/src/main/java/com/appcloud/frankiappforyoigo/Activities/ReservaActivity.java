package com.appcloud.frankiappforyoigo.Activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appcloud.frankiappforyoigo.Configuracion;
import com.appcloud.frankiappforyoigo.POJO.Tarifa;
import com.appcloud.frankiappforyoigo.R;
import com.appcloud.frankiappforyoigo.Utils.Commons;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReservaActivity extends AppCompatActivity {

    private int codTarifa;
    private LinearLayout lnDetalleTarifa;
    private TextView tvTitle;
    private Tarifa tarifa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        codTarifa = getIntent().getIntExtra(Configuracion.TARIFA,-1);
        if(codTarifa!=-1)
            setTheme(Commons.getTema(codTarifa));
        setContentView(R.layout.activity_reserva);

        lnDetalleTarifa = (LinearLayout)findViewById(R.id.ln_detalle_tarifa);
        tvTitle = (TextView) findViewById(R.id.tv_title);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("tarifas");
        switch (codTarifa){
            case Configuracion.TARIFA_CERO:
                ref.child("cero").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tarifa = dataSnapshot.getValue(Tarifa.class);
                        setDetalleTarifa();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                tvTitle.setText(getString(R.string.tarifa_cero));
                break;
            case Configuracion.TARIFA_CINCO:
                ref.child("cinco").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tarifa = dataSnapshot.getValue(Tarifa.class);
                        setDetalleTarifa();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                tvTitle.setText(getString(R.string.tarifa_cinco));
                break;
            case Configuracion.TARIFA_SINFIN:
                ref.child("sinfin").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tarifa = dataSnapshot.getValue(Tarifa.class);
                        setDetalleTarifa();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                tvTitle.setText(getString(R.string.tarifa_sinfin));
                break;
            case Configuracion.TARIFA_UNO:
                ref.child("uno").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tarifa = dataSnapshot.getValue(Tarifa.class);
                        setDetalleTarifa();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                tvTitle.setText(getString(R.string.tarifa_uno));
                break;
            case Configuracion.TARIFA_650:
                ref.child("650").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tarifa = dataSnapshot.getValue(Tarifa.class);
                        setDetalleTarifa();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                tvTitle.setText(getString(R.string.tarifa_650));
                break;
        }


    }

    private void   setDetalleTarifa(){
        LinearLayout lnCosteMinuto = (LinearLayout) findViewById(R.id.ln_coste_minuto);
        RelativeLayout rlIlimitadas = (RelativeLayout) findViewById(R.id.ln_ilimitadas);
        LinearLayout lnMinutos = (LinearLayout)findViewById(R.id.ln_minutos);
        TextView tvLlamadas = (TextView) findViewById(R.id.tv_llamadas);
        TextView tvMinnutos = (TextView) findViewById(R.id.tv_min_gratis);
        TextView tvDatos = (TextView) findViewById(R.id.tv_datos);
        TextView tvUnidadDatos = (TextView) findViewById(R.id.tv_unidad_datos);
        TextView tvCuota = (TextView) findViewById(R.id.tv_cuota);
        if(tarifa.isIlimitadas()){
            lnCosteMinuto.setVisibility(View.GONE);
        }else{
            rlIlimitadas.setVisibility(View.GONE);
            tvLlamadas.setText(tarifa.getPrecio_minuto());
            if(tarifa.getMinutos_gratis()!=null && !tarifa.getMinutos_gratis().equals(""))
            {
                tvMinnutos.setText(tarifa.getMinutos_gratis());
            }else{
                lnMinutos.setVisibility(View.INVISIBLE);
            }
        }
        if(tarifa.getUnidad_datos().equals("MB")){
            tvLlamadas.setTextSize(TypedValue.COMPLEX_UNIT_SP,35);
        }
        tvDatos.setText(tarifa.getDatos());
        tvUnidadDatos.setText(tarifa.getUnidad_datos());
        tvCuota.setText(tarifa.getCuota());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        lnDetalleTarifa.setVisibility(View.GONE);
    }
}
